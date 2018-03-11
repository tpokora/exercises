package com.tpokora.exercises.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tpokora.exercises.auth.domain.ProfileRepository;
import com.tpokora.exercises.auth.model.Profile;
import com.tpokora.exercises.common.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class ProfileServiceImpl implements GenericService<Profile> {

    public static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private static final String CLIENT_ID = "356969504268-1q2tfe73dm2nvb3toqaq1p7okcbog586.apps.googleusercontent.com";

    @Autowired
    private HttpTransport httpTransport;

    @Autowired
    private ProfileRepository profileRepository;

    public Profile authenticate(String tokenString) {
        Profile profile = new Profile();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JacksonFactory.getDefaultInstance()).setAudience(Collections.singleton(CLIENT_ID)).build();
        GoogleIdToken googleIdToken = null;

        try {
            googleIdToken = verifier.verify(tokenString);

            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            profile.setName((String) payload.get("name"));
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

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getById(Integer id) {
        return profileRepository.getOne(id);
    }

    @Override
    public Profile createOrUpdate(Profile profile) {
        return profileRepository.saveAndFlush(profile);
    }

    @Override
    public void delete(Integer id) {
        profileRepository.delete(id);
    }
}
