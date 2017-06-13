package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.exercise.model.Exercise;

import java.util.List;

/**
 * Created by pokor on 08.06.2017.
 */
public interface ExerciseService {

    public List<Exercise> getExercises();
    public Exercise getExercise(Integer id);
}
