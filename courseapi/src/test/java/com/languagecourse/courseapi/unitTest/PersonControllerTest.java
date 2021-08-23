package com.languagecourse.courseapi.unitTest;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.languagecourse.courseapi.controller.PersonController;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({PersonController.class})
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private static final Person TEST_PERSON1 = new Person(null, "Jenő", 35, "jeno@dd.hu", null);
    private static final Person TEST_PERSON2 = new Person(null, "Ede", 30, "ede@dd.hu", null);
    private static final Person TEST_PERSON3 = new Person(null, "Tódor", 31, "todor@dd.hu", null);

    public static String asJsonString (final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll_returnAllPersons () throws Exception {
        TEST_PERSON1.setId(1L);
        TEST_PERSON2.setId(2L);

        List<Person> personList = List.of(
                TEST_PERSON1,
                TEST_PERSON2,
                TEST_PERSON3);

        when(personService.getAll()).thenReturn(personList);
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(TEST_PERSON1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(TEST_PERSON1.getName())))
                .andExpect(jsonPath("$[1].id", is(TEST_PERSON2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(TEST_PERSON2.getName())));

        verify(personService, times(1)).getAll();

        TEST_PERSON1.setId(null);
        TEST_PERSON2.setId(null);

    }




}
