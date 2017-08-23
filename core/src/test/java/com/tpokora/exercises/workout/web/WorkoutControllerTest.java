package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.utils.TestUtils;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.utils.DayGenerator;
import com.tpokora.exercises.workout.utils.ExerciseSetGenerator;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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
    private Generator<Day> dayGenerator;
    private Generator<ExerciseSet> exerciseSetGenerator;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(workoutController).build();

        workoutGenerator = new WorkoutGenerator();
        dayGenerator = new DayGenerator();
        exerciseSetGenerator = new ExerciseSetGenerator();
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

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutByIdWithDays_success() throws Exception {
        int id = 1;
        Workout workout = workoutGenerator.generate();
        workout.setId(id);

        List<Day> dayList = new ArrayList<>();
        dayList.add(dayGenerator.generate(0));
        dayList.add(dayGenerator.generate(1));
        workout.setDays(dayList);

        when(workoutGenericService.getById(id)).thenReturn(workout);

        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(workout.getId())))
                .andExpect(jsonPath("$.name", is(workout.getName())))
                .andExpect(jsonPath("$.description", is(workout.getDescription())))
                .andExpect(jsonPath("$.days").isArray())
                .andExpect(jsonPath("$.days").isNotEmpty())
                .andExpect(jsonPath("$.days", hasSize(dayList.size())))
                .andExpect(jsonPath("$.days[0].id", is(dayList.get(0).getId())))
                .andExpect(jsonPath("$.days[0].index", is(dayList.get(0).getIndex())))
                .andExpect(jsonPath("$.days[0].name", is(dayList.get(0).getName())))
                .andExpect(jsonPath("$.days[1].id", is(dayList.get(1).getId())))
                .andExpect(jsonPath("$.days[1].index", is(dayList.get(1).getIndex())))
                .andExpect(jsonPath("$.days[1].name", is(dayList.get(1).getName())));
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutByIdWithDaysAndExerciseSets_success() throws Exception {
        int id = 1;
        Workout workout = workoutGenerator.generate();
        workout.setId(id);

        List<Day> dayList = new ArrayList<>();
        Day day = dayGenerator.generate(0);
        day.setId(id);

        List<ExerciseSet> exerciseSetList = exerciseSetGenerator.generateList(1);
        exerciseSetList.get(0).setId(id);
        exerciseSetList.get(0).getExercise().setId(id);

        day.setExerciseSets(exerciseSetList);
        dayList.add(day);
        workout.setDays(dayList);

        when(workoutGenericService.getById(id)).thenReturn(workout);

        mockMvc.perform(get(ConfigsString.WORKOUT_API_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(workout.getId())))
                .andExpect(jsonPath("$.name", is(workout.getName())))
                .andExpect(jsonPath("$.description", is(workout.getDescription())))
                .andExpect(jsonPath("$.days").isArray())
                .andExpect(jsonPath("$.days").isNotEmpty())
                .andExpect(jsonPath("$.days", hasSize(dayList.size())))
                .andExpect(jsonPath("$.days[0].id", is(dayList.get(0).getId())))
                .andExpect(jsonPath("$.days[0].index", is(dayList.get(0).getIndex())))
                .andExpect(jsonPath("$.days[0].name", is(dayList.get(0).getName())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].id", is(day.getExerciseSets().get(0).getId())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].sets", is(day.getExerciseSets().get(0).getSets())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].reps", is(day.getExerciseSets().get(0).getReps())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].exercise.id", is(day.getExerciseSets().get(0).getExercise().getId())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].exercise.name", is(day.getExerciseSets().get(0).getExercise().getName())))
                .andExpect(jsonPath("$.days[0].exerciseSets[0].exercise.description", is(day.getExerciseSets().get(0).getExercise().getDescription())));

    }
}
