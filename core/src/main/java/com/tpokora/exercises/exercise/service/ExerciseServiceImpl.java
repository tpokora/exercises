package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.exercise.domain.ExerciseRepository;
import com.tpokora.exercises.exercise.model.Exercise;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 08.06.2017.
 */
@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {

    @Resource
    private ExerciseRepository exerciseRepository;

    @Override
    @Transactional(readOnly = true)
    public Exercise getExercise(Integer id) {
        return exerciseRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    @Transactional
    public Exercise createOrUpdateExercise(Exercise exercise) {
        if (exercise.getId() == null) {
            Integer newExerciseId = exerciseRepository.saveAndFlush(exercise).getId();
            return exerciseRepository.getOne(newExerciseId);
        }

        Exercise updateExercise = exerciseRepository.findOne(exercise.getId());
        updateExercise = exercise;
        updateExercise = exerciseRepository.saveAndFlush(updateExercise);

        return exerciseRepository.findOne(updateExercise.getId());
    }

    @Override
    @Transactional
    public void deleteExercise(Integer id) {
        exerciseRepository.delete(id);
    }
}
