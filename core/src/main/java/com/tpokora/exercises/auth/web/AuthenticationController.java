package com.tpokora.exercises.auth.web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tpokora.exercises.auth.domain.Profile;
import com.tpokora.exercises.auth.service.ProfileService;
import com.tpokora.exercises.common.ConfigsString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConfigsString.AUTH_API_URL)
public class AuthenticationController {

    public static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/authtoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Profile> authenticateUser(@RequestParam("token") String tokenString) {
        Profile profile = profileService.authenticate(tokenString);

        if (profile == null) {
            return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

}
