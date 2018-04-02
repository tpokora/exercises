package com.tpokora.exercises.profile.web;

import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.web.BaseControllerTest;
import com.tpokora.exercises.profile.model.Profile;
import com.tpokora.exercises.profile.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfileControllerTest extends BaseControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void test_getProfileById_success() throws Exception {
        Profile profile = new Profile();
        profile.setEmail("test@email.com");
        profile.setName("test");
        profile.setToken("QWEQWEQWE".getBytes());
        profile.setId(1);

        when(profileService.getById(1)).thenReturn(profile);

        mockMvc.perform(get(ConfigsString.PROFILE_API_URL + "/" + profile.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(profile.getId())))
                .andExpect(jsonPath("$.name", is(profile.getName())))
                .andExpect(jsonPath("$.email", is(profile.getEmail())));
    }

    @Test
    public void test_getAllProfiles_success() throws Exception {
        Profile profile1 = new Profile();
        profile1.setEmail("test1@email.com");
        profile1.setName("test1");
        profile1.setToken("testToken1".getBytes());
        profile1.setId(1);

        Profile profile2 = new Profile();
        profile2.setEmail("test2@email.com");
        profile2.setName("test2");
        profile2.setToken("testToken2".getBytes());
        profile2.setId(2);

        List<Profile> profiles = List.of(profile1, profile2);

        when(profileService.getAll()).thenReturn(profiles);

        mockMvc.perform(get(ConfigsString.PROFILE_API_URL + "/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
