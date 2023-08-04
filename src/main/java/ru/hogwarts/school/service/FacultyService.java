package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;


public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty getById(Long id);
    Collection<Faculty> getAll();
    void remove(Long id);
    Faculty update (Faculty faculty);


    Collection<Faculty> getAllByColor(String Color);

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color,String name);

    Collection<Student> getStudents(Long id);

    String getLongestName();
}
