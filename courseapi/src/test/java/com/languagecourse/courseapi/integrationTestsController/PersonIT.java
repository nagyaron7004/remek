package com.languagecourse.courseapi.integrationTestsController;


import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

public class PersonIT {

    @LocalServerPort
    private  int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {this.baseUrl = "http://localhost:" + port + "/person";}

    @Test
    public void addNewPerson_emptyDb_shouldReturnSamePerson() {
        Person testPerson = new Person(1L, "Jenő", 35, "jeno@dd.hu", null);
        Person result = testRestTemplate.postForObject(baseUrl, testPerson, Person.class);
        assertEquals(testPerson.getName(), result.getName());
    }

    @Test
    public void getPersons_emptyDb_returnsEmptyList() {
        List<Person> personList = List.of(testRestTemplate
                .getForObject(baseUrl, Person[].class));
        assertEquals(0, personList.size());
    }

    @Test
    public void getPersonById_withOnePostedPerson_returnsPersonWithSameId() {
        Person testPerson = new Person(1L, "Jenő", 35, "jeno@dd.hu", null);
        Person testPersonResult = testRestTemplate.postForObject(baseUrl, testPerson, Person.class);
        Person result = testRestTemplate.getForObject(baseUrl + "/" + testPersonResult.getId(), Person.class);
        assertEquals(testPersonResult.getId(), result.getId());
    }

    @Test
    public void updatePerson_withoutPersonObject_returnsBadRequest() {
        Person testPerson = new Person(null, "Jenő", 35, "jeno@dd.hu", null);

        HttpEntity<Person> httpEntity = createHttpEntityWithMediaTypeJson(testPerson);
        ResponseEntity<Person> putResponse = testRestTemplate
                .exchange(baseUrl + "/aaa", HttpMethod.PUT, httpEntity, Person.class);

        assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());

    }


    private HttpEntity<Person> createHttpEntityWithMediaTypeJson(Person testIngredient) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(testIngredient, headers);
    }

    @Test
    public void updatePerson_withPostedPerson_returnsUpdatedPerson () {
        Person testPerson = new Person(null, "Jenő", 35, "jeno@dd.hu", null);

        Person testPersonResult = testRestTemplate.postForObject(baseUrl, testPerson, Person.class);
        testPerson.setName("Tódor-updated");
        testRestTemplate.put(baseUrl + "/" + testPersonResult.getId(), testPerson);
        Person updatedPerson = testRestTemplate.getForObject(baseUrl + "/" +
                testPersonResult.getId(), Person.class);

        assertEquals("Tódor-updated", updatedPerson.getName());
    }


}
