package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.utils.DayGenerator;
import com.tpokora.exercises.workout.utils.WorkoutGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class WorkoutServiceTest extends BaseServiceTest {

    private Generator<Workout> workoutGenerator;
    private Generator<Day> dayGenerator;

    @Autowired
    private GenericService<Workout> workoutGenericService;

    private Workout workout;
    private List<Workout> workoutList;

    @Before
    public void setup() {
        workoutGenerator = new WorkoutGenerator();
        dayGenerator = new DayGenerator();
        workout = workoutGenerator.generate(1);
        workoutList = workoutGenerator.generateList(3);
    }

    private void createWorkout(boolean day) {
        if (day) {
            List<Day> dayList = new ArrayList<>();
            dayList.add(dayGenerator.generate());
            workout.setDays(dayList);
        }
        workout = workoutGenericService.createOrUpdate(workout);
    }

    @Test
    @Transactional
    @Rollback
    public void test_createWorkout_success() {
        createWorkout(false);
        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutById_success() {
        createWorkout(false);
        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);
        Assert.assertTrue(workoutGenericService.getById(2) == null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getAllWorkouts_success() {
        for (int i = 0; i < workoutList.size(); i++) {
            workoutList.set(i, workoutGenericService.createOrUpdate(workoutList.get(i)));
        }

        Assert.assertEquals(workoutGenericService.getAll().size(), workoutList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void test_deleteWorkout_success() {
        createWorkout(false);

        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);

        workoutGenericService.delete(workout.getId());

        Assert.assertTrue(workoutGenericService.getById(workout.getId()) == null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutByByWithDay_success() {
        createWorkout(true);

        Workout workoutWithDay = workoutGenericService.getById(workout.getId());
        Assert.assertTrue(workoutWithDay != null);
        Assert.assertTrue(workoutWithDay.getDays().size() == 1);
    }
}
