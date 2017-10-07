package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.utils.TestUtils;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.service.DayService;
import com.tpokora.exercises.workout.utils.DayGenerator;
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

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class DayControllerTest extends BaseControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(DayControllerTest.class);

    @Mock
    private DayService dayService;

    @InjectMocks
    private DayController dayController;

    private Generator<Day> dayGenerator;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dayController).build();

        dayGenerator = new DayGenerator();

    }

    @Test
    @Transactional
    @Rollback
    public void test_createDay_success() throws Exception {
        Day day = dayGenerator.generate();
        when(dayService.createOrUpdate(any(Day.class))).thenReturn(day);

        mockMvc.perform(post(ConfigsString.DAY_API_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(day)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(day.getName())));
    }

    @Test
    @Transactional
    @Rollback
    public void test_createDay_unprocessable_entity() throws Exception {
        Day day = dayGenerator.generate();
        when(dayService.createOrUpdate(any(Day.class))).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(post(ConfigsString.DAY_API_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(day)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    @Rollback
    public void test_getDaysByWorkoutId_success() throws Exception {
        Workout workout = new Workout();
        workout.setId(1);

        ArrayList<Day> dayArrayList = (ArrayList<Day>) dayGenerator.generateList(2);
        for(int i = 0; i < dayArrayList.size(); i++) {
            Day day = dayArrayList.get(i);
            day.setWorkout(workout);
            dayArrayList.set(i, day);
        }

        when(dayService.getDaysByWorkoutId(workout.getId())).thenReturn(dayArrayList);

        mockMvc.perform(get(ConfigsString.DAY_API_URL + "/workout/" + workout.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    @Transactional
    @Rollback
    public void test_getDaysByWorkoutId_empty() throws Exception {
        int id = 1;
        ArrayList<Day> dayArrayList = new ArrayList<>();
        when(dayService.getDaysByWorkoutId(id)).thenReturn(dayArrayList);

        mockMvc.perform(get(ConfigsString.DAY_API_URL + "/workout/" + id))
                .andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    @Rollback
    public void test_deleteDayById_success() throws Exception {
        int id = 1;

        mockMvc.perform(delete(ConfigsString.DAY_API_URL + "/" + id))
                .andExpect(status().isNoContent());
    }
}
