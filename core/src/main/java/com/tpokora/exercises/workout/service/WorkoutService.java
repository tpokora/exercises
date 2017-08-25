package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.WorkoutRepository;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
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
        List<Day> dayList = newWorkout.getDays();
        for (int i = 0; i < dayList.size(); i++) {
            Day day = dayList.get(i);
            day.setWorkout(newWorkout);
            List<ExerciseSet> exerciseSetList = day.getExerciseSets();
            for (int j = 0; j < exerciseSetList.size(); j++) {
                ExerciseSet exerciseSet = exerciseSetList.get(j);
                exerciseSet.setDay(day);
                exerciseSetList.set(j, exerciseSet);
            }
            dayList.set(i, day);
        }
        newWorkout = workoutRepository.saveAndFlush(newWorkout);
        return newWorkout;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        workoutRepository.delete(id);
    }
}
