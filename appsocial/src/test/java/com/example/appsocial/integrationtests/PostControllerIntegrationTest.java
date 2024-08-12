package com.example.appsocial.integrationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.appsocial.models.User;
import com.example.appsocial.services.UserService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostControllerIntegrationTest {
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Test
    void testGetFeed() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userService.registerUser(user);
        mockMvc.perform(get("/feed")
                .with(user(user.getUsername())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("posts"));
    }
}
