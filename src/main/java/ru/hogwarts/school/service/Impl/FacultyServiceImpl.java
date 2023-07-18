package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {
    private Long counter = 0L;
    private final Map<Long, Faculty> faculties = new HashMap<>();
    @Override
    public Faculty add(Faculty faculty) {
        faculty.setId(++counter);
        faculties.put(faculty.getId(), faculty);

        return faculties.get(faculty.getId());
    }

    @Override
    public Faculty getById(Long id) {
        return faculties.get(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        return faculties.values();
    }

    @Override
    public void remove(Long id) {
        faculties.remove(id);
    }

    @Override
    public Faculty update(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculties.get(faculty.getId());
    }
}
