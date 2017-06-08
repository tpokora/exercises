package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.exercise.model.Exercise;
import org.springframework.stereotype.Service;

/**
 * Created by pokor on 08.06.2017.
 */
@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {

    @Override
    public Exercise getExercise(Integer id) {
        // mock exercise for now
        return new Exercise(id, "Exercise name", "Exercise description");
    }
}
