package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;


public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty getById(Long id);
    Collection<Faculty> getAll();
    void remove(Long id);
    Faculty update (Faculty faculty);


    Collection<Faculty> getAllByColor(String Color);

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color,String name);
}
