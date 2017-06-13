package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.exercise.model.Exercise;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 08.06.2017.
 */
@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {

    public ExerciseServiceImpl() {

    }

    @Override
    public Exercise getExercise(Integer id) {
        // mock exercise for now
        return new Exercise(id, "Exercise name", "Exercise description");
    }

    @Override
    public List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            exercises.add(new Exercise(i, "Exercise name " + i, "Exercise description " + i));
        }
        return exercises;
    }
}
