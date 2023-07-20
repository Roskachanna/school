package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Student;

import java.util.Collection;


public interface StudentService {
    Student add(Student student);
    Student getById(Long id);
    Collection<Student> getAll();
    void delete(Long id);
    Student update(Student student);

    Collection<Student> getAllById(int age);
}
