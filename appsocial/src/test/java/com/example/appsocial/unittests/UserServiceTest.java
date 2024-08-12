package com.example.appsocial.unittests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.appsocial.models.User;
import com.example.appsocial.services.UserService;

@SpringBootTest
public class UserServiceTest {
	@Autowired
    private UserService userService;

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userService.registerUser(user);
        assertNotNull(user.getId());
    }
}
