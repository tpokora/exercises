package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.ExerciseSetRepository;
import com.tpokora.exercises.workout.model.ExerciseSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("exerciseSetService")
public class ExerciseSetService implements GenericService<ExerciseSet> {

    @Resource
    private ExerciseSetRepository exerciseSetRepository;

    @Override
    public List<ExerciseSet> getAll() {
        return exerciseSetRepository.findAll();
    }

    @Override
    public ExerciseSet getById(Integer id) {
        return exerciseSetRepository.findOne(id);
    }

    @Override
    public ExerciseSet createOrUpdate(ExerciseSet exerciseSet) {
        return exerciseSetRepository.saveAndFlush(exerciseSet);
    }

    @Override
    public void delete(Integer id) {
        exerciseSetRepository.delete(id);
    }
}
