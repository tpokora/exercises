package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.utils.DayGenerator;
import com.tpokora.exercises.workout.utils.ExerciseSetGenerator;
import com.tpokora.exercises.workout.utils.WorkoutGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


public class DayServiceTest extends BaseServiceTest {

    private Generator<Workout> workoutGenerator;
    private Generator<Day> dayGenerator;
    private Generator<ExerciseSet> exerciseSetGenerator;

    @Autowired
    private GenericService<Workout> workoutGenericService;

    @Autowired
    private GenericService<Day> dayGenericService;

    @Autowired
    private GenericService<Exercise> exerciseGenericService;

    @Before
    public  void setup() {
        workoutGenerator = new WorkoutGenerator();
        dayGenerator = new DayGenerator();
        exerciseSetGenerator = new ExerciseSetGenerator();
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

    @Test
    @Transactional
    @Rollback
    public void test_getDaysByWorkoutIdWithExerciseSets_success() {
        Workout workout1 = createWorkout(0);

        List<Day> firstDayList = ((DayGenerator)dayGenerator).generateList(2, workout1);

        List<List<ExerciseSet>> list = new ArrayList<>();

        list.add(exerciseSetGenerator.generateList(2));
        list.add(exerciseSetGenerator.generateList(3));
        for (int i = 0; i < list.size(); i++) {
            List<ExerciseSet> exerciseSetList = list.get(i);
            for (int j = 0; j < exerciseSetList.size(); j++) {
                Exercise exercise = exerciseSetList.get(j).getExercise();
                exercise = exerciseGenericService.createOrUpdate(exercise);
                exerciseSetList.get(j).setExercise(exercise);
                list.set(i, exerciseSetList);
            }
        }

        for (int i = 0; i < firstDayList.size(); i++) {
            Day currentDay = firstDayList.get(i);
            currentDay.setExerciseSets(list.get(i));
            firstDayList.set(i, dayGenericService.createOrUpdate(currentDay));
        }

        List<Day> dayList = ((DayService)dayGenericService).getDaysByWorkoutId(workout1.getId());
        Assert.assertTrue(dayList.size() == firstDayList.size());
        for (int i = 0; i < dayList.size(); i++) {
            Assert.assertTrue(dayList.get(i).getExerciseSets().size() == firstDayList.get(i).getExerciseSets().size());
        }
    }

    @Test
    @Transactional
    @Rollback
    public void test_removeDaywithoutExercises_success() {
        Workout workout = createWorkout(0);
        List<Day> firstDayList = ((DayGenerator)dayGenerator).generateList(1, workout);
        workout.setDays(firstDayList);

        List<ExerciseSet> exerciseSetList = new ArrayList<>();

        Exercise exercise = new Exercise("ABC", "DEF");
        exercise = exerciseGenericService.createOrUpdate(exercise);

        Assert.assertTrue(exerciseGenericService.getById(exercise.getId()) != null);

        ExerciseSet exerciseSet = new ExerciseSet(exercise, 4, 10, firstDayList.get(0));
        exerciseSetList.add(exerciseSet);

        Day day = firstDayList.get(0);
        day.setExerciseSets(exerciseSetList);

        day = dayGenericService.createOrUpdate(day);

        Assert.assertTrue(dayGenericService.getById(day.getId()) != null);

        dayGenericService.delete(day.getId());

        Assert.assertTrue(dayGenericService.getById(day.getId()) == null);
        Assert.assertTrue(exerciseGenericService.getById(exercise.getId()) != null);
    }
}
