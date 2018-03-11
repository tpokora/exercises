package com.tpokora.exercises.auth.web;

import com.tpokora.exercises.auth.model.Profile;
import com.tpokora.exercises.auth.service.ProfileService;
import com.tpokora.exercises.common.ConfigsString;
import com.tpokora.exercises.common.utils.TestUtils;
import com.tpokora.exercises.common.web.BaseControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AuthenticationControllerTest extends BaseControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private Profile profile;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        this.profile = new Profile();
        this.profile.setId(1);
        this.profile.setName("testProfile");
        this.profile.setToken("testToken");
        this.profile.setEmail("test@email.com");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_userAuthentication_success() throws Exception {
        when(profileService.authenticate(this.profile.getToken())).thenReturn(this.profile);

        mockMvc.perform(post(ConfigsString.AUTH_API_URL + "/authtoken")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content("token=" + this.profile.getToken()))
                .andExpect(status().isOk());
    }
}
