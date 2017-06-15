package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.common.Generator;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.service.ExerciseService;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by pokor on 15.06.2017.
 */
public class ExerciseControllerTest extends BaseControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseControllerTest.class);

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    private Generator exerciseGenerator;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(exerciseController).build();

        exerciseGenerator = new ExerciseGenerator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test_getExerciseById_1_success() throws Exception {
        Exercise exercise = (Exercise) exerciseGenerator.generate(1);
        when(exerciseService.getExercise(exercise.getId())).thenReturn(exercise);
        mockMvc.perform(get("/exercise/" + exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(exercise.getId())))
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }

    @Test
    public void test_getExercise_success() throws Exception {
        List<Exercise> exerciseList = ((ExerciseGenerator) exerciseGenerator).generateExerciseList(3);
        when(exerciseService.getExercises()).thenReturn(exerciseList);
        mockMvc.perform(get("/exercise/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
