package com.languagecourse.courseapi.unitTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.languagecourse.courseapi.controller.GroupController;
import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.GroupService;
import com.languagecourse.courseapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.GroupSequence;
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


@WebMvcTest
public class GroupControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    private static final Group TEST_GROUP1 = new Group (null, null, null);
    private static final Group TEST_GROUP2 = new Group (null, null, null);
    private static final Group TEST_GROUP3 = new Group (null, null, null);

    public static String asJsonString (final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
