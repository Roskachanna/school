package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.SchoolApplication;
import ru.hogwarts.school.exception.StudentIsNotFound;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final StudentRepository studentRepository;

    private final AvatarRepository avatarRepository;
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);
    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("uploadAvatar method was invoked");
        Student student = studentRepository
                .findById(studentId).orElseThrow(StudentIsNotFound::new );

        Path path = uploadToDisk(student, avatarFile);
        uploadToDataBase(path, student, avatarFile);

    }

    @Override
    public Avatar findById(Long studentId) {
        logger.info("findById method was invoked");
        Avatar avatar = avatarRepository.findByStudent_Id(studentId);
        return avatar;
    }

    @Override
    public Collection<Avatar> getAvatars(int pageNumber, int pageSize) {
        logger.info("getAvatars method was invoked");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

    private Path uploadToDisk (Student student, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(
                avatarsDir, student.getName() + "StudentAvatar" + student.getId() + "." + getExtensions(avatarFile.getOriginalFilename())
        );
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }
    private void uploadToDataBase(Path path, Student student, MultipartFile avatarFile) throws IOException {
        Avatar avatar = findAvatar(student.getId());
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    private Avatar findAvatar(Long studentId) {
        Avatar avatar = avatarRepository.findByStudent_Id(studentId);
        return avatar == null ? new Avatar() : avatar;
    }
}


