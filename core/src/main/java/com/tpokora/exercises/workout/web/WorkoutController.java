package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.model.Workout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Api(value = "workouts", description = "Workout API")
@RestController
@RequestMapping(ConfigsString.WORKOUT_API_URL)
public class WorkoutController {

    public final static Logger logger = LoggerFactory.getLogger(WorkoutController.class);

    @Autowired
    private GenericService<Workout> workoutService;

    @ApiOperation(value = "Get workouts list", notes = "Get workouts list")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<List<Workout>> getWorkouts() {
        List<Workout> workoutList = workoutService.getAll();

        if (workoutList == null || workoutList.isEmpty()) {
            return new ResponseEntity<List<Workout>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Workout>>(workoutList, HttpStatus.OK);
    }

    @ApiOperation(value = "Create workout", notes = "Create workout")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) throws Exception {
        Workout newWorkout;

        try {
            newWorkout = workoutService.createOrUpdate(workout);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<Workout>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Workout>(newWorkout, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get workout by ID", notes = "Get workout by ID")
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Workout> getWorkoutById(@PathVariable("id") Integer id) {
        Workout workout = workoutService.getById(id);

        if (workout == null) {
            return new ResponseEntity<Workout>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Workout>(workout, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Workout> deleteWorkout(@PathVariable("id") Integer id) throws Exception {
        workoutService.delete(id);
        return new ResponseEntity<Workout>(HttpStatus.NO_CONTENT);
    }
}
