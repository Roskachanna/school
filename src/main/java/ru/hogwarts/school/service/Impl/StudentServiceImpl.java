package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {

        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Student> getAll() {

        return studentRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllById(int age) {
        return getAll()
                .stream()
                .filter(it ->it.getAge() == age)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetweenOrderByAge(min, max);
    }

    @Override
    public int getCountOfStudents() {
        return studentRepository.getCountOfStudents();
    }

    @Override
    public int getAverageAge() {
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getFiveStudentsOrderedById() {
        return studentRepository.getFiveStudentsOrderedById();
    }
}
