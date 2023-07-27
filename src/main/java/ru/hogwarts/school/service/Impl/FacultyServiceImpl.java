package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getById(Long id) {

        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    @Override
    public Collection<Faculty> getAllByColor(String color) {
        return facultyRepository.findAll();

    }
    @Override
    public Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
    @Override
    public Collection<Student> getStudents(Long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }


}
