package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class   StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        logger.info("add method was invoked");
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        logger.info("getById method was invoked");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("getAll method was invoked");
        return studentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        logger.info("delete method was invoked");
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Student student) {
        logger.info("update method was invoked");
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllById(int age) {
        logger.info("getAllById method was invoked");
        return getAll()
                .stream()
                .filter(it ->it.getAge() == age)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("findByAgeBetween method was invoked");
        return studentRepository.findAllByAgeBetweenOrderByAge(min, max);
    }

    @Override
    public int getCountOfStudents() {
        logger.info("getCountOfStudents method was invoked");
        return studentRepository.getCountOfStudents();
    }

    @Override
    public int getAverageAge() {
        logger.info("getAverageAge method was invoked");
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getFiveStudentsOrderedById() {
        logger.info("getFiveStudentsOrderedById method was invoked");
        return studentRepository.getFiveStudentsOrderedById();
    }

    @Override
    public Collection<String> getStudentsStartsWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(it ->it.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public double getAverageAgeViaStreams() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    @Override
    public void threads() {
        List<Student> students = studentRepository.findAll();
        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }
    private void printStudentName(Student student) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.getName() );
    }

    private synchronized void printStudentNameSynchronized(Student student) {
            System.out.println(student.getName());
    }
    @Override
    public void synchronizerThreads() {
        List<Student> students = studentRepository.findAll();
        printStudentNameSynchronized(students.get(0));
        printStudentNameSynchronized(students.get(1));

        new Thread(() -> {
            printStudentNameSynchronized(students.get(2));
            printStudentNameSynchronized(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentNameSynchronized(students.get(4));
            printStudentNameSynchronized(students.get(5));
        }).start();
    }

}
