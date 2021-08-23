package com.languagecourse.courseapi.integrationTestsController;

import com.languagecourse.courseapi.entity.Course;
import com.languagecourse.courseapi.entity.Grade;
import com.languagecourse.courseapi.entity.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

public class CourseIT {

    @LocalServerPort
    private  int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {this.baseUrl = "http://localhost:" + port + "/course";}

    @Test
    public void addNewCourse_emptyDb_returnsSameGrouopId() {
        Course testCourse = new Course(1L, Language.SPANISH, Grade.BEGINNER, null);

        Course result = testRestTemplate.postForObject(baseUrl, testCourse, Course.class);
        assertEquals(testCourse.getId(), result.getId());
    }

    @Test
    public void addCourses_emptyDb_retursListOfCourses() {
        Course testCourse1 = new Course(null, Language.SPANISH, Grade.BEGINNER, null);
        Course testCourse2 = new Course(null, Language.URDU, Grade.ADVANCED, null);
        Course testCourse3 = new Course(null, Language.CHINESE, Grade.BEGINNER, null);

        testRestTemplate.postForObject(baseUrl, testCourse1, Course.class);
        testRestTemplate.postForObject(baseUrl, testCourse2, Course.class);
        testRestTemplate.postForObject(baseUrl, testCourse3, Course.class);

        List<Course> courses = List.of(testRestTemplate.getForObject(baseUrl, Course[].class));
        assertEquals(3, courses.size());
    }

    @Test
    public void getCourses_emptyDb_returnsEmptyList() {
        List<Course> courses = List.of(testRestTemplate.getForObject(baseUrl, Course[].class));
        assertEquals(0, courses.size());

    }



}
