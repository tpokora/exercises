package com.tpokora.exercises.workout.service;

import com.tpokora.exercises.common.service.BaseServiceTest;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.common.utils.Generator;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.model.ExerciseSet;
import com.tpokora.exercises.workout.model.Workout;
import com.tpokora.exercises.workout.utils.DayGenerator;
import com.tpokora.exercises.workout.utils.ExerciseSetGenerator;
import com.tpokora.exercises.workout.utils.WorkoutGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkoutServiceTest extends BaseServiceTest {

    private Generator<Workout> workoutGenerator;
    private Generator<Day> dayGenerator;
    private Generator<ExerciseSet> exerciseSetGenerator;

    @Autowired
    private GenericService<Workout> workoutGenericService;

    @Autowired
    private GenericService<Exercise> exerciseGenericService;

    private Workout workout;
    private List<Workout> workoutList;

    @Before
    public void setup() {
        workoutGenerator = new WorkoutGenerator();
        dayGenerator = new DayGenerator();
        exerciseSetGenerator = new ExerciseSetGenerator();
        workout = workoutGenerator.generate(1);
        workoutList = workoutGenerator.generateList(3);
    }

    private void createWorkout(boolean day, boolean exerciseSets) {
        if (day) {
            List<Day> dayList = new ArrayList<>();
            Day newDay = dayGenerator.generate();

            if (exerciseSets) {
                List<ExerciseSet> exerciseSetList = exerciseSetGenerator.generateList(2);
                for (int i = 0; i <exerciseSetList.size(); i++) {
                    Exercise exercise = exerciseGenericService.createOrUpdate(exerciseSetList.get(i).getExercise());
                    exerciseSetList.get(i).setExercise(exercise);

                }
                newDay.setExerciseSets(exerciseSetList);
            }
            dayList.add(newDay);
            workout.setDays(dayList);
        }
        workout = workoutGenericService.createOrUpdate(workout);
    }

    @Test
    @Transactional
    @Rollback
    public void test_createWorkout_success() {
        createWorkout(false, false);
        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void test_getWorkoutById_success() {
        createWorkout(false, false);
        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);
        Assert.assertTrue(workoutGenericService.getById(2) == null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getAllWorkouts_success() {
        for (int i = 0; i < workoutList.size(); i++) {
            workoutList.set(i, workoutGenericService.createOrUpdate(workoutList.get(i)));
        }

        Assert.assertEquals(workoutGenericService.getAll().size(), workoutList.size());
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void test_deleteWorkout_success() {
        createWorkout(false, false);

        Assert.assertTrue(workoutGenericService.getById(workout.getId()) != null);

        workoutGenericService.delete(workout.getId());

        Assert.assertTrue(workoutGenericService.getById(workout.getId()) == null);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutByByWithDay_success() {
        createWorkout(true, false);

        Workout workoutWithDay = workoutGenericService.getById(workout.getId());
        Assert.assertTrue(workoutWithDay != null);
        Assert.assertTrue(workoutWithDay.getDays().size() == 1);
    }

    @Test
    @Transactional
    @Rollback
    public void test_getWorkoutByByWithDayAndExerciseSet_success() {
        createWorkout(true, true);

        Workout workoutWithDay = workoutGenericService.getById(workout.getId());
        Assert.assertTrue(workoutWithDay != null);
        Assert.assertTrue(workoutWithDay.getDays().size() == 1);
        Assert.assertTrue(workoutWithDay.getDays().get(0).getExerciseSets().size() == workout.getDays().get(0).getExerciseSets().size());
    }
}
