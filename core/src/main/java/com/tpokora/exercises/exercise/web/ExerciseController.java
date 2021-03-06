package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.service.ExerciseServiceImpl;
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

/**
 * Created by pokor on 08.06.2017.
 */
@Api(value = "exercise", description = "Exercise API")
@RestController
@RequestMapping(ConfigsString.EXERCISES_API_URL)
public class ExerciseController {

    private final static Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    private GenericService<Exercise> exerciseService;

    @ApiOperation(value = "Get exercise", notes = "Return exercise by ID")
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Exercise> getExercise(@PathVariable("id") int id) {
        Exercise exercise = exerciseService.getById(id);

        if (exercise == null) {
            return new ResponseEntity<Exercise>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
    }

    @ApiOperation(value = "Get exercises with name like value param", notes = "Return exercises by name")
    @CrossOrigin
    @RequestMapping(value = "/find-by-name", method = RequestMethod.GET)
    public ResponseEntity<List<Exercise>> findByName(@RequestParam String name) {
        List<Exercise> exerciseList = ((ExerciseServiceImpl) exerciseService).getByName(name);

        if (exerciseList == null || exerciseList.isEmpty()) {
            return new ResponseEntity<List<Exercise>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Exercise>>(exerciseList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get exercises list", notes = "Get exercises list")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAll();

        if (exercises == null || exercises.isEmpty()) {
            return new ResponseEntity<List<Exercise>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Exercise>>(exercises, HttpStatus.OK);
    }

    @ApiOperation(value = "Create exercise", notes = "Create exercise")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) throws Exception {
        Exercise newExercise = null;

        try {
            newExercise = exerciseService.createOrUpdate(exercise);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<Exercise>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Exercise>(newExercise, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete exercise", notes = "Delete exercise by ID")
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") int id) throws Exception {
        exerciseService.delete(id);
        return new ResponseEntity<Exercise>(HttpStatus.NO_CONTENT);
    }

}
