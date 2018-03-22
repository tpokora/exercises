package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.ExerciseSetRepository;
import com.tpokora.exercises.workout.model.ExerciseSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("exerciseSetService")
public class ExerciseSetService implements GenericService<ExerciseSet> {

    @Resource
    private ExerciseSetRepository exerciseSetRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseSet> getAll() {
        return exerciseSetRepository.findAll();
    }

    @Override
    @Transactional
    public ExerciseSet getById(Integer id) {
        return exerciseSetRepository.findById(id).get();
    }

    @Override
    @Transactional
    public ExerciseSet createOrUpdate(ExerciseSet exerciseSet) {
        return exerciseSetRepository.saveAndFlush(exerciseSet);
    }

    @Override
    public void delete(Integer id) {
        exerciseSetRepository.deleteById(id);
    }
}
