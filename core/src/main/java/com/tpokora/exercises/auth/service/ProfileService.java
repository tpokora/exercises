package com.tpokora.exercises.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tpokora.exercises.auth.domain.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class ProfileService {

    public static final Logger logger = LoggerFactory.getLogger(ProfileService.class);

    private static final String CLIENT_ID = "356969504268-1q2tfe73dm2nvb3toqaq1p7okcbog586.apps.googleusercontent.com";

    @Autowired
    private HttpTransport httpTransport;

    public Profile authenticate(String tokenString) {
        Profile profile = new Profile();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JacksonFactory.getDefaultInstance()).setAudience(Collections.singleton(CLIENT_ID)).build();
        GoogleIdToken googleIdToken = null;

        try {
            googleIdToken = verifier.verify(tokenString);

            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            profile.setToken(tokenString);
            profile.setEmail(payload.getEmail());

            return profile;

        } catch (GeneralSecurityException gse) {
            logger.error("SecurityException: Invalid ID token");
            return null;
        } catch (Exception e) {
            logger.error("Exception: Invalid ID token");
            return null;
        }
    }
}
