package com.tpokora.exercises.auth.web;

import com.tpokora.exercises.auth.model.Profile;
import com.tpokora.exercises.auth.service.ProfileService;
import com.tpokora.exercises.common.ConfigsString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConfigsString.AUTH_API_URL)
public class AuthenticationController {

    public static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ProfileService profileService;

    @CrossOrigin
    @RequestMapping(value = "/authtoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Profile> authenticateUser(@RequestParam("token") String tokenString) {
        Profile profile = profileService.authenticate(tokenString);
        if (profile == null) {
            return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED);
        }
        Profile profileToUpdate = profileService.getByEmail(profile.getEmail());
        if (profileToUpdate != null) {
            profile.setId(profileToUpdate.getId());
        }
        profile = profileService.createOrUpdate(profile);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

}
