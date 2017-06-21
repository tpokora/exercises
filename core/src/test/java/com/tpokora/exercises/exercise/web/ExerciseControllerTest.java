package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.utils.TestUtils;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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
    private GenericService<Exercise> exerciseService;

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
        int id = 1;
        Exercise exercise = (Exercise) exerciseGenerator.generate(id);
        exercise.setId(id);
        when(exerciseService.getById(exercise.getId())).thenReturn(exercise);

        mockMvc.perform(get(TestUtils.restApiLink("exercise") + exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(exercise.getId())))
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }

    @Test
    public void test_getExercise_success() throws Exception {
        List<Exercise> exerciseList = ((ExerciseGenerator) exerciseGenerator).generateExerciseList(3);
        when(exerciseService.getAll()).thenReturn(exerciseList);

        mockMvc.perform(get(TestUtils.restApiLink("exercise")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createNewExercise_success() throws Exception {
        Exercise exercise = new Exercise("ExerciseControllerTest", "ExerciseControllerTestDescription");

        mockMvc.perform(post(TestUtils.restApiLink("exercise"))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtils.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeExercise_success() throws Exception {
        Exercise exercise = new Exercise("ExerciseControllerDeleteTest", "ExerciseControllerDeleteTestDescription");
        exercise.setId(1);
        when(exerciseService.getById(exercise.getId())).thenReturn(exercise);

        mockMvc.perform(get(TestUtils.restApiLink("exercise") + exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(exercise.getId())))
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));

        mockMvc.perform(delete(TestUtils.restApiLink("exercise") + exercise.getId()))
                .andExpect(status().isNoContent());

        when(exerciseService.getById(exercise.getId())).thenReturn(null);
        mockMvc.perform(get(TestUtils.restApiLink("exercise") + exercise.getId()))
                .andExpect(status().isNotFound());
    }

}
