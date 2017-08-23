package com.tpokora.exercises.workout.utils;

import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class DayGenerator implements Generator<Day> {

    @Override
    public Day generate() {
        return new Day("Day", null, null, null);
    }

    public Day generate(String name, Integer index, Workout workout, List<ExerciseSet> exerciseSets) {
        return new Day(name, index, workout, exerciseSets);
    }

    @Override
    public Day generate(int id) {
        return new Day("Day" + id, null, null, null);
    }

    public Day generate(int id, Integer index, Workout workout, List<ExerciseSet> exerciseSets) {
        return new Day("Day" + id, index, workout, exerciseSets);
    }

    @Override
    public List<Day> generateList(int size) {
        List<Day> dayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            dayList.add(generate(i, i, null, null));
        }

        return dayList;
    }

    public List<Day> generateList(int size, Workout workout) {
        List<Day> dayList = generateList(size);

        for (Day day : dayList) {
            day.setWorkout(workout);
        }

        return dayList;
    }

}
