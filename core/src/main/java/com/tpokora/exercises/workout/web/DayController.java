package com.tpokora.exercises.workout.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.service.GenericService;
import com.tpokora.exercises.workout.model.Day;
import com.tpokora.exercises.workout.service.DayService;
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


    @ApiOperation(value = "Get list of days by workout ID")
    @CrossOrigin
    @RequestMapping(value = "/workout/{workoutId}", method = RequestMethod.GET, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<List<Day>> getDayByWorkoutId(@PathVariable("workoutId") Integer workoutId) {
        List<Day> dayList = ((DayService) dayGenericService).getDaysByWorkoutId(workoutId);

        if (dayList == null || dayList.isEmpty()) {
            return new ResponseEntity<List<Day>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Day>>(dayList, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove Day by ID")
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = ConfigsString.HEADERS_APPLICATION_JSON)
    public ResponseEntity<Day> removeDayById(@PathVariable("id") Integer id) {
        dayGenericService.delete(id);
        return new ResponseEntity<Day>(HttpStatus.NO_CONTENT);
    }
}
