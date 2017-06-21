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

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseServiceTest extends BaseServiceTest {

    private Generator generator;

    @Autowired
    private GenericService<Exercise> exerciseService;

    private Exercise exercise;
    private List<Exercise> exerciseList;

    @Before
    public void setup() {
        generator = new ExerciseGenerator();
        exercise = (Exercise) generator.generate();
        exerciseList = ((ExerciseGenerator) generator).generateExerciseList(3);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getExerciseById() {
        exercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue("Exercise id should equal: " + exercise.getId(), exerciseService.getById(exercise.getId()).getId() == exercise.getId());
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
    public void createOrUpdateExercise_newExercise_success() {
        exercise = new Exercise("TestNewExerciseCreate", "TestNewExerciseDesc");
        Exercise newExercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue(newExercise.getName().equals(exercise.getName()));
        Assert.assertTrue(newExercise.getDescription().equals(exercise.getDescription()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteExerciseById_success() {
        exercise = new Exercise("TextExerciseToDelete", "TextExerciseToDeleteDesc");
        exercise = exerciseService.createOrUpdate(exercise);

        Assert.assertTrue(exerciseService.getById(exercise.getId()).getName() == exercise.getName());

        exerciseService.delete(exercise.getId());
        Assert.assertTrue(exerciseService.getById(exercise.getId()) == null);
    }


}
