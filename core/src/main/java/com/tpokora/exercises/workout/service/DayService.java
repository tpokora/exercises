package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.domain.DayRepository;
import com.tpokora.exercises.workout.model.Day;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "dayService")
public class DayService implements GenericService<Day> {

    @Resource
    private DayRepository dayRepository;

    @Override
    public List<Day> getAll() {
        return dayRepository.findAll();
    }

    @Override
    public Day getById(Integer id) {
        return dayRepository.findOne(id);
    }

    @Override
    public Day createOrUpdate(Day day) {
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
