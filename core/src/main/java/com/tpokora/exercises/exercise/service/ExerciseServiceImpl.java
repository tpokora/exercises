package com.tpokora.exercises.exercise.service;

import com.tpokora.exercises.common.service.GenericService;
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
public class ExerciseServiceImpl implements GenericService<Exercise> {

    @Resource
    private ExerciseRepository exerciseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise getById(Integer id) {
        return exerciseRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Exercise> getByName(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public Exercise createOrUpdate(Exercise exercise) {
        if (exercise.getId() == null) {
            Integer newExerciseId = exerciseRepository.saveAndFlush(exercise).getId();
            return exerciseRepository.getOne(newExerciseId);
        }

        Exercise updateExercise = exerciseRepository.saveAndFlush(exercise);

        return exerciseRepository.findById(updateExercise.getId()).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        exerciseRepository.deleteById(id);
    }
}
