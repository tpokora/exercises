package com.tpokora.exercises.common.service;

import com.tpokora.exercises.ExercisesApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by pokor on 13.06.2017.
 */
@SpringBootTest(classes = ExercisesApplication.class)
@TestPropertySource(locations = "classpath:properties/test.properties")
@RunWith(SpringRunner.class)
@WebAppConfiguration
public abstract class BaseServiceTest {
}
