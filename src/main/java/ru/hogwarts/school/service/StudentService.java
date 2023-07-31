package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;


public interface StudentService {
    Student add(Student student);
    Student getById(Long id);
    Collection<Student> getAll();
    void delete(Long id);
    Student update(Student student);

    Collection<Student> getAllById(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    int getCountOfStudents();

    int getAverageAge();

    List<Student> getFiveStudentsOrderedById();

}
