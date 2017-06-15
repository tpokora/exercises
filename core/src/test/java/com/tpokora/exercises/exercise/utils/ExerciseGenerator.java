package com.tpokora.exercises.exercise.utils;

import com.tpokora.exercises.common.Generator;
import com.tpokora.exercises.exercise.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseGenerator implements Generator {

    @Override
    public Object generate(int id) {
        return new Exercise(id, "Exercise" + id, "Exercise" + id + " description");
    }

    public List<Exercise> generateExerciseList(int size) {
        List<Exercise> exerciseList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            exerciseList.add((Exercise) generate(i));
        }

        return exerciseList;

    }

}
