package com.languagecourse.courseapi.integrationTestsController;

import com.languagecourse.courseapi.entity.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")

public class GroupIT {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/group";
    }

    @Test
    public void addNewGroup_emptyDb_shouldReturnSameGroupId() {
        Group tesGroup1 = new Group(1L, null, null);


        Group result = testRestTemplate.postForObject(baseUrl, tesGroup1, Group.class);
        assertEquals(tesGroup1.getId(), result.getId());
    }

    @Test
    public void addTwoGroups_emptyDb_shouldReturnListWithTwoGroups() {
        Group testGroup1 = new Group(null, null, null);
        Group testGroup2 = new Group(null, null, null);

        testRestTemplate.postForObject(baseUrl, testGroup1, Group.class);
        testRestTemplate.postForObject(baseUrl, testGroup2, Group.class);

        List<Group> groups = List.of(testRestTemplate
                .getForObject(baseUrl, Group[].class));
        assertEquals(2, groups.size());

    }

    @Test
    public void getGroups_emptyDb_returnsEmptyList() {
        List<Group> groups = List.of(testRestTemplate.getForObject(baseUrl, Group[].class));
        assertEquals(0, groups.size());
    }

    @Test
    public void getGroupById_onePostedGroup_returnsSameId() {
        Group testGroup1 = new Group(null, null, null);
        testGroup1.setId(testRestTemplate.postForObject(baseUrl, testGroup1, Group.class).getId());
        Group resultGroup = testRestTemplate.getForObject(baseUrl + "/" + testGroup1.getId(), Group.class);
        assertEquals(testGroup1.getId(), resultGroup.getId());
    }

    @Test
    public void updateGroup_withoutGroup_returnsBadRequest() {
        Group testGroup1 = new Group(null, null, null);
        HttpEntity<Group> httpEntity = createHttpEntityWithMediaTypeJson(testGroup1);

        ResponseEntity<Object> putResponse = testRestTemplate
                .exchange(baseUrl + "/500", HttpMethod.PUT, httpEntity, Object.class);

        assertEquals(HttpStatus.NOT_FOUND, putResponse.getStatusCode());
    }

    private HttpEntity<Group> createHttpEntityWithMediaTypeJson(Group testGroup) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(testGroup, headers);
    }



}
