package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RestController
@RequestMapping("/students")
public class  StudentController {

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
    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/count")
    public int getCountOfStudents() {
        return studentService.getCountOfStudents();
    }
    @GetMapping("/average-age")
    public int getAverageAge() {
        return studentService.getAverageAge();
    }
    @GetMapping("/five-ordered-by-id")
    public Collection<Student> getFiveStudentsOrderedById() {

        return studentService.getFiveStudentsOrderedById();
    }

    @GetMapping("/starts-with-a")
    public Collection<String> getStudentsStartsWithA() {
        return studentService.getStudentsStartsWithA();
    }

    @GetMapping("/average-age-via-streams")
    public double getAverageAgeViaStreams() {
        return studentService.getAverageAgeViaStreams();
    }
}
