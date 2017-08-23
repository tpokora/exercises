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
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class DayServiceTest extends BaseServiceTest {

    private Generator<Workout> workoutGenerator;
    private Generator<Day> dayGenerator;

    @Autowired
    private GenericService<Workout> workoutGenericService;

    @Autowired
    private GenericService<Day> dayGenericService;

    @Before
    public  void setup() {
        workoutGenerator = new WorkoutGenerator();
        dayGenerator = new DayGenerator();
    }

    private Workout createWorkout(int workoutId) {
        Workout workout = workoutGenerator.generate(workoutId);
        workout = workoutGenericService.createOrUpdate(workout);
        return workout;
    }

    @Test
    @Transactional
    @Rollback
    public void test_createDay_success() {
        Day day = ((DayGenerator)dayGenerator).generate(0, 0, createWorkout(0), null);
        day = dayGenericService.createOrUpdate(day);

        Assert.assertTrue(dayGenericService.getById(day.getId()) != null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getDaysByWorkoutId_success() {
        Workout workout1 = createWorkout(0);
        Workout workout2 = createWorkout(1);

        List<Day> dayList1 = ((DayGenerator)dayGenerator).generateList(2, workout1);
        List<Day> dayList2 = ((DayGenerator)dayGenerator).generateList(3, workout2);

        for (int i = 0; i < dayList1.size(); i++) {
            dayList1.set(i, dayGenericService.createOrUpdate(dayList1.get(i)));
        }

        for (int i = 0; i < dayList2.size(); i++) {
            dayList2.set(i, dayGenericService.createOrUpdate(dayList2.get(i)));
        }

        Assert.assertTrue(((DayService)dayGenericService).getDaysByWorkoutId(workout1.getId()).size() == 2);
        Assert.assertTrue(((DayService)dayGenericService).getDaysByWorkoutId(workout2.getId()).size() == 3);
    }

    @Test
    @Transactional
    @Rollback
    public void test_removeDay_success() {
        Workout workout = createWorkout(0);
        Day day = ((DayGenerator)dayGenerator).generate("Test", 0, workout, null);

        day = dayGenericService.createOrUpdate(day);

        Assert.assertTrue(dayGenericService.getById(day.getId()) != null);

        dayGenericService.delete(day.getId());

        Assert.assertTrue(dayGenericService.getById(day.getId()) == null);
    }

}
