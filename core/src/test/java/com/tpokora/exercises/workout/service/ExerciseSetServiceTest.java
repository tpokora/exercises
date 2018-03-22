package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ExerciseSetServiceTest extends BaseServiceTest {

    private Generator<ExerciseSet> exerciseSetGenerator;
    private Generator<Exercise> exerciseGenerator;
    private Generator<Day> dayGenerator;
    private Generator<Workout> workoutGenerator;

    @Autowired
    private GenericService<ExerciseSet> exerciseSetGenericService;

    @Autowired
    private GenericService<Exercise> exerciseGenericService;

    @Autowired
    private GenericService<Workout> workoutGenericService;

    private Exercise exercise;
    private ExerciseSet exerciseSet;
    private Day day;
    private Workout workout;

    @Before
    public void setup() {
        exerciseSetGenerator = new ExerciseSetGenerator();
        exerciseGenerator = new ExerciseGenerator();
        dayGenerator = new DayGenerator();
        workoutGenerator = new WorkoutGenerator();
    }

    private void createExercise() {
        this.exercise = exerciseGenerator.generate();
        this.exercise = exerciseGenericService.createOrUpdate(this.exercise);
    }

    private void createExerciseSet() {
        createWorkoutAndDay();
        this.exerciseSet = ((ExerciseSetGenerator)exerciseSetGenerator).generate(this.exercise, 4, 10, this.day);
        this.exerciseSet = exerciseSetGenericService.createOrUpdate(this.exerciseSet);
    }

    private void createWorkoutAndDay() {
        this.workout = workoutGenerator.generate();
        this.day = dayGenerator.generate();
        this.day.setWorkout(this.workout);
        List<Day> dayList = new ArrayList<>();
        dayList.add(this.day);
        this.workout.setDays(dayList);
        this.workout = workoutGenericService.createOrUpdate(this.workout);
    }

    @Test
    @Transactional
    @Rollback
    public void test_createExerciseSet_success() {
        createExercise();
        createExerciseSet();
        this.exerciseSet = exerciseSetGenericService.createOrUpdate(this.exerciseSet);
        Assert.assertTrue(exerciseSetGenericService.getById(exerciseSet.getId()) != null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getAllExerciseSets_success() {
        createWorkoutAndDay();
        List<ExerciseSet> exerciseSetList = exerciseSetGenerator.generateList(2);
        for (int i = 0; i < exerciseSetList.size(); i++) {
            exerciseSetList.get(i).setDay(this.day);
            Exercise exercise = exerciseGenericService.createOrUpdate(exerciseSetList.get(i).getExercise());
            exerciseSetList.get(i).setExercise(exercise);
            exerciseSetList.set(i, exerciseSetGenericService.createOrUpdate(exerciseSetList.get(i)));
        }
        Assert.assertTrue(exerciseSetGenericService.getAll().size() == exerciseSetList.size());
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void test_removeExerciseById_success() {
        createExercise();
        createExerciseSet();
        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) != null);

        exerciseSetGenericService.delete(this.exerciseSet.getId());

        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) == null);
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void test_exerciseShouldNotBeRemovedWhenExerciseSetIsRemoved_success() {
        createExercise();
        createExerciseSet();

        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) != null);
        Assert.assertTrue(exerciseGenericService.getById(this.exercise.getId()) != null);

        exerciseSetGenericService.delete(this.exerciseSet.getId());

        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) == null);
        Assert.assertTrue(exerciseGenericService.getById(this.exercise.getId()) != null);
    }
}
