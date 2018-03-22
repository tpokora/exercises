package com.tpokora.exercises.profile.web;

import com.tpokora.exercises.profile.model.Profile;
import com.tpokora.exercises.profile.service.ProfileService;
import com.tpokora.exercises.common.ConfigsString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConfigsString.PROFILE_API_URL)
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") Integer id) {
        Profile profile = profileService.getById(id);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
