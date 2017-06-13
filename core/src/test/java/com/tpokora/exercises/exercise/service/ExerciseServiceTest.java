package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.common.Generator;
import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseServiceTest extends BaseServiceTest {

    private Generator generator;

    @Autowired
    private ExerciseService exerciseService;

    @Before
    public void setup() {
        generator = new ExerciseGenerator();
    }


    @Test
    public void test_getExerciseById() {
        Exercise exercise = exerciseService.getExercise(1);
        Assert.assertTrue("Exercise id should equal 1", exercise.getId().equals(1));
    }

    @Test
    public void test_getExercises_isEmpty_false() {
        List<Exercise> exercisesList = exerciseService.getExercises();
        Assert.assertTrue("Should not be empty", !exercisesList.isEmpty());
    }

}
