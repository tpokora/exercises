package com.tpokora.exercises.workout.utils;

import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.utils.ExerciseGenerator;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSetGenerator implements Generator<ExerciseSet> {

    private static ExerciseGenerator exerciseGenerator = new ExerciseGenerator();

    @Override
    public ExerciseSet generate() {
        return new ExerciseSet();
    }

    public ExerciseSet generate(Exercise exercise, Integer sets, Integer reps, Day day, Workout workout){
        return new ExerciseSet(exercise, sets, reps, day, workout);
    }

    @Override
    public ExerciseSet generate(int id) {
        return null;
    }

    @Override
    public List<ExerciseSet> generateList(int size) {
        List<ExerciseSet> exerciseSetList = new ArrayList<>();

        List<Exercise> exerciseList = exerciseGenerator.generateList(size);

        for(int i = 0; i < size; i++) {
            ExerciseSet exerciseSet = new ExerciseSet(exerciseList.get(i), 4, 10, null, null);
            exerciseSetList.add(exerciseSet);
        }

        return exerciseSetList;
    }

    public List<ExerciseSet> generateList(int size, Day day, Workout workout) {
        List<ExerciseSet> exerciseSetList = new ArrayList<>();

        for (ExerciseSet exerciseSet : exerciseSetList) {
            exerciseSet.setDay(day);
            exerciseSet.setWorkout(workout);
        }

        return exerciseSetList;
    }
}
