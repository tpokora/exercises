package com.tpokora.exercises.workout.utils;

import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.workout.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutGenerator implements Generator<Workout> {

    @Override
    public Workout generate() {
        return new Workout("Workout", "Workout Description");
    }

    @Override
    public Workout generate(int id) {
        return new Workout("Workout " + id, "Workout " + id + "Description ");
    }

    @Override
    public List<Workout> generateList(int size) {
        List<Workout> workoutList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            workoutList.add(generate(i));
        }

        return workoutList;
    }
}
