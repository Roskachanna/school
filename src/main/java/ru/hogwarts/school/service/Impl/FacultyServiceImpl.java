package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("add method was invoked");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getById(Long id) {
        logger.info("getById method was invoked");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("getAll method was invoked");
        return facultyRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        logger.info("remove method was invoked");
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty update(Faculty faculty) {
        logger.info("update method was invoked");
        return facultyRepository.save(faculty);
    }
    @Override
    public Collection<Faculty> getAllByColor(String color) {
        logger.info("getAllByColor method was invoked");
        return getAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());

    }
    @Override
    public Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        logger.info("findColorIgnoreCaseOrNameIgnoreCase method was invoked");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
    @Override
    public Collection<Student> getStudents(Long id) {
        logger.info("getStudents method was invoked");
        return studentRepository.findAllByFaculty_Id(id);
    }

    @Override
    public String getLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }
}
