package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;



@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;
    private FacultyController facutltyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;

    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return facultyService.getAll();
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable Long id) {
        return facultyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        facultyService.remove(id);
    }

    @GetMapping("/faculties")
    public Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(@RequestParam String color,String name) {
        return facultyService.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
    @GetMapping("/{id}/students")
    public Collection<Student> getStudents(@PathVariable Long id){
        return facutltyService.getStudents(id);
    }

}
