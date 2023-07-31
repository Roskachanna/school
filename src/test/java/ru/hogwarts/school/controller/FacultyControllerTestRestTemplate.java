package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplate {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @AfterEach
    public void resetDb() { facultyRepository.deleteAll();}

    @Test
    void shouldCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Color");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculties", faculty, Faculty.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());
    }
    @Test
    void shouldGetFaculty() {
        Long facultyId = persistTestFaculty("Gryffindor", "Color").getId();

        ResponseEntity<Faculty> responseEntity = restTemplate.getForEntity("/faculties/{id}", Faculty.class, facultyId);

        Faculty faculty = responseEntity.getBody();
        Assertions.assertThat(faculty).isNotNull();
        Assertions.assertThat(faculty.getId()).isEqualTo(facultyId);
    }

    private Faculty persistTestFaculty(String name, String color) {
        Faculty faculty = new Faculty();
        return facultyRepository.save(faculty);
    }

}
