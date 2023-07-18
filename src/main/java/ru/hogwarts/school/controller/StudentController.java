package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping()
    public Collection<Student> getAll() {
        return studentService. getAll();
    }
    @PostMapping
    public Student add(@RequestBody Student student) {

        return studentService.add(student);
    }
    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.delete(id);
    }
    @GetMapping("/find")
    public Collection<Student> getAllByAge(@RequestParam int age){
        return studentService.getAllById(age);
    }
}
