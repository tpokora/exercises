package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.WorkoutRepository;
import com.tpokora.exercises.workout.model.Workout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "workoutService")
public class WorkoutService implements GenericService<Workout> {

    @Resource
    private WorkoutRepository workoutRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    @Override
    @Transactional
    public Workout getById(Integer id) {
        return workoutRepository.findOne(id);
    }

    @Override
    @Transactional
    public Workout createOrUpdate(Workout workout) {
        Workout newWorkout = workout;
        newWorkout = workoutRepository.saveAndFlush(newWorkout);
        return newWorkout;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        workoutRepository.delete(id);
    }
}
