package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseServiceTest extends BaseServiceTest {

    private Generator<Exercise> generator;

    @Autowired
    private GenericService<Exercise> exerciseService;

    private Exercise exercise;
    private List<Exercise> exerciseList;

    @Before
    public void setup() {
        generator = new ExerciseGenerator();
        exercise = generator.generate();
        exerciseList = generator.generateList(3);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getExerciseById() {
        exercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue("Exercise id should equal: " + exercise.getId(), exerciseService.getById(exercise.getId()).getId().equals(exercise.getId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getExercises_isEmpty_false() {
        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseList.set(i, exerciseService.createOrUpdate(exerciseList.get(i)));
        }
        Assert.assertTrue("Should not be empty", !exerciseService.getAll().isEmpty());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_createOrUpdateExercise_newExercise_success() {
        exercise = new Exercise("TestNewExerciseCreate", "TestNewExerciseDesc");
        Exercise newExercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue(newExercise.getName().equals(exercise.getName()));
        Assert.assertTrue(newExercise.getDescription().equals(exercise.getDescription()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_createOrUpdateExerciseWithID_newExercise_success() {
        Exercise exercise = new Exercise("TestNewExerciseCreateWithID", "TestNewExerciseDesc");
        exercise = exerciseService.createOrUpdate(exercise);
        Exercise exerciseWithID = new Exercise(exercise.getId(), "TestExercise", "TestDescription");
        Exercise updatedExercise = exerciseService.createOrUpdate(exerciseWithID);

        Assert.assertTrue(updatedExercise.getId() == exerciseWithID.getId());
        Assert.assertTrue(updatedExercise.getName().equals(exerciseWithID.getName()));
        Assert.assertTrue(updatedExercise.getDescription().equals(exerciseWithID.getDescription()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_deleteExerciseById_success() {
        exercise = new Exercise("TextExerciseToDelete", "TextExerciseToDeleteDesc");
        exercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue(exerciseService.getById(exercise.getId()).getName().equals(exercise.getName()));

        exerciseService.delete(exercise.getId());
        Assert.assertTrue(exerciseService.getById(exercise.getId()) == null);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getExercisesByName_success() {
        String keyword = "test";

        Exercise exerciseWithKeyWord = new Exercise("Exercise" + keyword, "TestNewExerciseDesc");
        exerciseWithKeyWord = exerciseService.createOrUpdate(exerciseWithKeyWord);

        Exercise exerciseWithoutKeyWord = new Exercise("Exercise", "TestNewExerciseDesc");
        exerciseWithoutKeyWord = exerciseService.createOrUpdate(exerciseWithoutKeyWord);

        Assert.assertTrue(((ExerciseServiceImpl) exerciseService).getByName(keyword).size() == 1);
    }


}
