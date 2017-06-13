package com.tpokora.exercises.exercise.utils;

import com.tpokora.exercises.common.Generator;
import com.tpokora.exercises.exercise.model.Exercise;

/**
 * Created by pokor on 13.06.2017.
 */
public class ExerciseGenerator implements Generator {

    @Override
    public Object generate(int id) {
        return new Exercise(id, "Exercise" + id, "Exercise" + id + " description");
    }
}
