package com.tpokora.exercises.exercise.domain;

import com.tpokora.exercises.exercise.model.Exercise;
import org.junit.Assert;
import org.junit.Test;

public class ExerciseModelTest {

    private static final Integer EXERCISE_ID = 1;
    private static final String EXERCISE_NAME = "EXERCISE_NAME";
    private static final String EXERCISE_DESCRIPTION = "EXERCISE_DESCRIPTION";

    @Test
    public void test_createExerciseWithId_success() {
        Exercise exercise = new Exercise(EXERCISE_ID);
        Assert.assertTrue(exercise.getId() == EXERCISE_ID);
    }

    @Test
    public void test_createExerciseWithIdNameDescription_success() {
        Exercise exercise = new Exercise(EXERCISE_ID, EXERCISE_NAME, EXERCISE_DESCRIPTION);
        Assert.assertTrue(exercise.getId() == EXERCISE_ID);
        Assert.assertTrue(exercise.getName().equals(EXERCISE_NAME));
        Assert.assertTrue(exercise.getDescription().equals(EXERCISE_DESCRIPTION));
    }
}
