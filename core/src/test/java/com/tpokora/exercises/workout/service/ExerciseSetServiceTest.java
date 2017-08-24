package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.utils.DayGenerator;
import com.tpokora.exercises.workout.utils.ExerciseSetGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ExerciseSetServiceTest extends BaseServiceTest {

    private Generator<ExerciseSet> exerciseSetGenerator;
    private Generator<Exercise> exerciseGenerator;
    private Generator<Day> dayGenerator;

    @Autowired
    private GenericService<ExerciseSet> exerciseSetGenericService;

    @Autowired
    private GenericService<Exercise> exerciseGenericService;

    private Exercise exercise;
    private ExerciseSet exerciseSet;

    @Before
    public void setup() {
        exerciseSetGenerator = new ExerciseSetGenerator();
        exerciseGenerator = new ExerciseGenerator();
        dayGenerator = new DayGenerator();

    }

    private void createExercise() {
        this.exercise = exerciseGenerator.generate();
        this.exercise = exerciseGenericService.createOrUpdate(this.exercise);
    }

    private void createExerciseSet() {
        this.exerciseSet = ((ExerciseSetGenerator)exerciseSetGenerator).generate(this.exercise, 4, 10, null);
        this.exerciseSet = exerciseSetGenericService.createOrUpdate(this.exerciseSet);
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
        List<ExerciseSet> exerciseSetList = exerciseSetGenerator.generateList(2);
        for (int i = 0; i < exerciseSetList.size(); i++) {
            Exercise exercise = exerciseGenericService.createOrUpdate(exerciseSetList.get(i).getExercise());
            exerciseSetList.get(i).setExercise(exercise);
            exerciseSetList.set(i, exerciseSetGenericService.createOrUpdate(exerciseSetList.get(i)));
        }
        Assert.assertTrue(exerciseSetGenericService.getAll().size() == exerciseSetList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void test_removeExerciseById_success() {
        createExercise();
        createExerciseSet();
        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) != null);

        exerciseSetGenericService.delete(this.exerciseSet.getId());

        Assert.assertTrue(exerciseSetGenericService.getById(this.exerciseSet.getId()) == null);
    }

    @Test
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
