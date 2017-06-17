package com.tpokora.exercises.home.web;

import com.tpokora.exercises.home.domain.Home;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pokor on 21.05.2017.
 */
@RestController("/rest/")
public class HomeController {

    @CrossOrigin
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<Home> getHome() {
        return new ResponseEntity<Home>(new Home(), HttpStatus.OK);
    }
}
