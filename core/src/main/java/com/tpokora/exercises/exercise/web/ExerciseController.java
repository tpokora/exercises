package com.tpokora.exercises.exercise.web;

import com.tpokora.exercises.exercise.model.Exercise;
import com.tpokora.exercises.exercise.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pokor on 08.06.2017.
 */
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final static Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Exercise> getExercise(@PathVariable("id") int id) {
        Exercise exercise = exerciseService.getExercise(id);

        if (exercise == null) {
            return new ResponseEntity<Exercise>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
    }

}
