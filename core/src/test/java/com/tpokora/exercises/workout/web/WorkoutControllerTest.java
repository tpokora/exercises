package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.utils.TestUtils;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.utils.WorkoutGenerator;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class WorkoutControllerTest extends BaseControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(WorkoutControllerTest.class);

    @Mock
    private GenericService<Workout> workoutGenericService;

    @InjectMocks
    private WorkoutController workoutController;

    private Generator<Workout> workoutGenerator;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(workoutController).build();

        workoutGenerator = new WorkoutGenerator();
    }

    @Test
    @Transactional
    @Rollback
    public void test_createWorkout_success() throws Exception {
        Workout workout = workoutGenerator.generate();

        mockMvc.perform(post(ConfigsString.WORKOUT_API_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(workout)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutById_success() throws Exception {
        int id = 1;
        Workout workout = workoutGenerator.generate();
        workout.setId(id);

        when(workoutGenericService.getById(id)).thenReturn(workout);

        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(workout.getId())))
                .andExpect(jsonPath("$.name", is(workout.getName())))
                .andExpect(jsonPath("$.description", is(workout.getDescription())));
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkouts_success() throws Exception {
        List<Workout> workoutList = workoutGenerator.generateList(3);

        when(workoutGenericService.getAll()).thenReturn(workoutList);

        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Transactional
    @Rollback
    public void test_removeWorkout_success() throws Exception {
        int id = 1;
        Workout workout = workoutGenerator.generate();
        workout.setId(id);

        when(workoutGenericService.getById(id)).thenReturn(workout);

        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(workout.getId())))
                .andExpect(jsonPath("$.name", is(workout.getName())))
                .andExpect(jsonPath("$.description", is(workout.getDescription())));

        mockMvc.perform(delete(ConfigsString.WORKOUT_API_URL + "/" + workout.getId()))
                .andExpect(status().isNoContent());

        when(workoutGenericService.getById(workout.getId())).thenReturn(null);
        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL + "/" + workout.getId()))
                .andExpect(status().isNotFound());
    }
}
