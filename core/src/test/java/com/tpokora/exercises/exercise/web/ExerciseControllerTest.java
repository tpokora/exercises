package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.utils.TestUtils;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.service.ExerciseServiceImpl;
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

import javax.validation.ConstraintViolationException;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by pokor on 15.06.2017.
 */
public class ExerciseControllerTest extends BaseControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ExerciseControllerTest.class);

    @Mock
    private ExerciseServiceImpl exerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    private Generator<Exercise> exerciseGenerator;

    private ArrayList<Exercise> emptyExerciseList;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(exerciseController).build();

        exerciseGenerator = new ExerciseGenerator();
        emptyExerciseList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test_getExerciseById_1_success() throws Exception {
        int id = 1;
        Exercise exercise = exerciseGenerator.generate(id);
        exercise.setId(id);
        when(exerciseService.getById(exercise.getId())).thenReturn(exercise);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL + "/" +exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(exercise.getId())))
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }

    @Test
    public void test_getExercise_success() throws Exception {
        List<Exercise> exerciseList = exerciseGenerator.generateList(3);
        when(exerciseService.getAll()).thenReturn(exerciseList);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createNewExercise_success() throws Exception {
        Exercise exercise = new Exercise( "ExerciseControllerTest", "ExerciseControllerTestDescription");

        when(exerciseService.createOrUpdate(any(Exercise.class))).thenReturn(exercise);

        mockMvc.perform(post(ConfigsString.EXERCISES_API_URL)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtils.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }


    @Test
    @Transactional
    @Rollback
    public void createNewExercise_unprocessable_entity() throws Exception {
        Exercise exercise = new Exercise("Exercise", "Description");
        when(exerciseService.createOrUpdate(any(Exercise.class))).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(post(ConfigsString.EXERCISES_API_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeExercise_success() throws Exception {
        Exercise exercise = new Exercise("ExerciseControllerDeleteTest", "ExerciseControllerDeleteTestDescription");
        exercise.setId(1);
        when(exerciseService.getById(exercise.getId())).thenReturn(exercise);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL + "/" + exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(exercise.getId())))
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));

        mockMvc.perform(delete(ConfigsString.EXERCISES_API_URL + "/" + exercise.getId()))
                .andExpect(status().isNoContent());

        when(exerciseService.getById(exercise.getId())).thenReturn(null);
        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL + "/" + exercise.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Rollback
    public void getExercisesByName_success() throws Exception {
        String name = "Exercise";
        ArrayList exerciseList = (ArrayList) exerciseGenerator.generateList(3);
        when(exerciseService.getByName(name)).thenReturn(exerciseList);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL + "/find-by-name?name=" + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    @Transactional
    @Rollback
    public void getExercisesByName_empty() throws Exception {
        String name = "Exercise";
        when(exerciseService.getByName(name)).thenReturn(emptyExerciseList);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL + "/find-by-name?name=" + name))
                .andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    @Rollback
    public void getAllExercises_empty() throws Exception {
        when(exerciseService.getAll()).thenReturn(emptyExerciseList);

        mockMvc.perform(get(ConfigsString.EXERCISES_API_URL))
                .andExpect(status().isNotFound());
    }


}
