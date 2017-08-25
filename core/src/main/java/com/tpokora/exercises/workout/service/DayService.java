package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.DayRepository;
import com.tpokora.exercises.workout.domain.WorkoutRepository;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "dayService")
public class DayService implements GenericService<Day> {

    @Resource
    private DayRepository dayRepository;

    @Resource
    private WorkoutRepository workoutRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Day> getAll() {
        return dayRepository.findAll();
    }

    @Override
    @Transactional
    public Day getById(Integer id) {
        return dayRepository.findOne(id);
    }

    @Override
    @Transactional
    public Day createOrUpdate(Day day) {
        Day newDay = day;
        if (day.getWorkout() != null) {
            day.setWorkout(workoutRepository.findOne(day.getWorkout().getId()));
        }
        if (day.getExerciseSets() != null) {
            for (ExerciseSet exerciseSet : day.getExerciseSets()) {
                exerciseSet.setDay(day);
            }
        }

        return dayRepository.saveAndFlush(day);
    }

    @Override
    public void delete(Integer id) {
        dayRepository.delete(id);
    }

    public List<Day> getDaysByWorkoutId(Integer id) {
        return dayRepository.findByWorkout_Id(id);
    }
}
