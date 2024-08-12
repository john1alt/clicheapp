package com.example.appsocial.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.appsocial.models.Role;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.UserRepository;
import com.example.appsocial.services.CustomUserDetailsManager;

@SpringBootTest
public class CustomUserDetailsManagerTest {
	 @Autowired
	    private CustomUserDetailsManager customUserDetailsManager;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Test
	    void testLoadUserByUsername() {
	        
	        User user = new User();
	        user.setUsername("testuser");
	        user.setPassword(passwordEncoder.encode("testpassword"));
	        Set<Role> roles = new HashSet<>();
	        Role role = new Role();
	        role.setName("ROLE_USER");
	        roles.add(role);
	        user.setRoles(roles);
	        userRepository.save(user);

	        
	        UserDetails userDetails = customUserDetailsManager.loadUserByUsername("testuser");

	        
	        assertNotNull(userDetails);
	        assertEquals("testuser", userDetails.getUsername());
	        assertTrue(userDetails.getAuthorities().stream()
	            .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
	    }

	    @Test
	    void testCreateUser() {
	        
	        UserDetails newUser = org.springframework.security.core.userdetails.User.withUsername("newuser")
	            .password("newpassword")
	            .authorities("ROLE_USER")
	            .build();

	        
	        customUserDetailsManager.createUser(newUser);

	        
	        Optional<User> savedUser = userRepository.findByUsername("newuser");
	        assertTrue(savedUser.isPresent());
	        assertTrue(passwordEncoder.matches("newpassword", savedUser.get().getPassword()));
	        assertTrue(savedUser.get().getRoles().stream()
	            .anyMatch(role -> role.getName().equals("ROLE_USER")));
	    }
}
