package com.example.appsocial.integrationtests;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.appsocial.models.Role;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityConfigIntegrationTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Test
	    void testUnauthorizedAccess() throws Exception {
	        mockMvc.perform(get("/"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrlPattern("/login*"));
	    }

	    @Test
	    void testAuthorizedAccess() throws Exception {
	        
	        User user = new User();
	        user.setUsername("testuser");
	        user.setPassword(passwordEncoder.encode("testpassword"));
	        Set<Role> roles = new HashSet<>();
	        Role role = new Role();
	        role.setName("ROLE_USER");
	        roles.add(role);
	        user.setRoles(roles);
	        userRepository.save(user);

	        
	        mockMvc.perform(post("/login")
	            .param("username", "testuser")
	            .param("password", "testpassword"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/feed"));
	    }

	    @Test
	    void testLogout() throws Exception {
	        // Arrange
	        User user = new User();
	        user.setUsername("testuser");
	        user.setPassword(passwordEncoder.encode("testpassword"));
	        Set<Role> roles = new HashSet<>();
	        Role role = new Role();
	        role.setName("ROLE_USER");
	        roles.add(role);
	        user.setRoles(roles);
	        userRepository.save(user);

	        // Act
	        mockMvc.perform(post("/login")
	            .param("username", "testuser")
	            .param("password", "testpassword"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/feed"));

	        mockMvc.perform(get("/logout"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/"));
	    }
}
