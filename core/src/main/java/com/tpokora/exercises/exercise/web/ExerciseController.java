package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pokor on 08.06.2017.
 */
@RestController
@RequestMapping("/exercise")
@Api(value = "exercise", description = "Exercise API")
public class ExerciseController {

    private final static Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ApiOperation(value = "Get exercise", notes = "Return exercise by ID")
    public ResponseEntity<Exercise> getExercise(@PathVariable("id") int id) {
        Exercise exercise = exerciseService.getExercise(id);

        if (exercise == null) {
            return new ResponseEntity<Exercise>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ApiOperation(value = "Get exercises list", notes = "Get exercises list")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getExercises();

        if (exercises == null || exercises.isEmpty()) {
            return new ResponseEntity<List<Exercise>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Exercise>>(exercises, HttpStatus.OK);
    }

}
