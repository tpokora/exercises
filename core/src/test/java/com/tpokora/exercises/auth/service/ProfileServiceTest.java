package com.tpokora.exercises.auth.service;

import com.tpokora.exercises.auth.model.Profile;
import com.tpokora.exercises.auth.service.ProfileService;
import com.tpokora.exercises.common.service.BaseServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class ProfileServiceTest extends BaseServiceTest {

    @Autowired
    private ProfileService profileService;

    private Profile profile;

    @Before
    public void setup() {
        profile = new Profile("testProfile", "testToken".getBytes(), "test@email.com");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getProfileById_success() {
        Profile testProfile = profileService.createOrUpdate(this.profile);
        Assert.assertTrue(profileService.getById(testProfile.getId()) != null);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_getAllProfiles_success() {
        Profile testProfile1 = profileService.createOrUpdate(this.profile);
        Profile testProfile2 = profileService.createOrUpdate(this.profile);
        // check same email
        Assert.assertTrue(profileService.getAll().size() == 1);

        Profile testProfil3 = profileService.createOrUpdate(new Profile("testProfile", "testToken".getBytes(), "diffEmail"));

        Assert.assertTrue(profileService.getAll().size() == 2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_deleteProfileById_success() {
        Profile testProfile1 = profileService.createOrUpdate(this.profile);

        Assert.assertTrue(profileService.getById(testProfile1.getId()) != null);

        profileService.delete(testProfile1.getId());
        Assert.assertTrue(profileService.getAll().size() == 0);
    }
}
