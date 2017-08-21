package com.tpokora.exercises.exercise.utils;

import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseGenerator implements Generator<Exercise> {

    @Override
    public Exercise generate() {
        return new Exercise("ExerciseName", "ExerciseDescription");
    }

    @Override
    public Exercise generate(int id) {
        return new Exercise("Exercise" + id, "Exercise" + id + " description");
    }

    public List<Exercise> generateList(int size) {
        List<Exercise> exerciseList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            exerciseList.add(generate(i));
        }

        return exerciseList;

    }

}
