package com.tpokora.exercises.common.web;

import com.tpokora.exercises.ExercisesApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by pokor on 15.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExercisesApplication.class)
@WebAppConfiguration
public abstract class BaseControllerTest {

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON_UTF8.getType(),
            MediaType.APPLICATION_JSON_UTF8.getSubtype());

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;
}
