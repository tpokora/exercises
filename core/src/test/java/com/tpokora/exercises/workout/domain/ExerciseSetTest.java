package com.tpokora.exercises.workout.domain;

import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import org.junit.Assert;
import org.junit.Test;

public class ExerciseSetTest {

    private static final int ID = 1;
    private static final String DAY_NAME = "Day";
    private static final Integer INDEX = 1;

    private static final String EXERCISE_NAME = "Day";

    private static final Integer SETS = 3;
    private static final Integer REPS = 10;

    @Test
    public void test_exerciseSet() {
        Day day = new Day(DAY_NAME, INDEX, null, null);
        day.setId(ID);

        Exercise exercise = new Exercise(EXERCISE_NAME, "");
        exercise.setId(ID);

        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setId(ID);
        exerciseSet.setExercise(exercise);
        exerciseSet.setSets(SETS);
        exerciseSet.setReps(REPS);
        exerciseSet.setDay(day);

        Assert.assertTrue(exerciseSet.getId() == ID);
        Assert.assertTrue(exerciseSet.getSets() == SETS);
        Assert.assertTrue(exerciseSet.getReps() == REPS);
        Assert.assertTrue(exerciseSet.getExercise().getId() == ID);
        Assert.assertTrue(exerciseSet.getDay().getId() == ID);

    }
}
