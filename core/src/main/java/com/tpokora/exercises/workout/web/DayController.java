package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.model.Day;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@Api(value = "days", description = "Day API")
@RestController
@RequestMapping(ConfigsString.DAY_API_URL)
public class DayController {

    public final static Logger logger = LoggerFactory.getLogger(DayController.class);

    @Autowired
    private GenericService<Day> dayGenericService;

    @ApiOperation(value = "Create day", notes = "Create day")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Day> createDay(@RequestBody Day day) throws Exception {
        Day newDay;

        try {
            newDay = dayGenericService.createOrUpdate(day);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<Day>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Day>(newDay, HttpStatus.OK);
    }

//    public ResponseEntity<List<Day>> getDayByWorkoutId(@PathVariable("workoutId") Integer workoutId) {
//
//    }
}
