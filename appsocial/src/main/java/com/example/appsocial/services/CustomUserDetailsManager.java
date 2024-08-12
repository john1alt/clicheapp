package com.example.appsocial.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.Role;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.UserRepository;
@Service
public class CustomUserDetailsManager implements UserDetailsManager {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                    .map(Role::getName)
                    .map(SimpleGrantedAuthority::new)
                    .toList()
            );
		/*
		 * return User.withUsername(user.getUsername()) //withUsername?
		 * .password(user.getPassword()) .authorities(user.getRoles().stream()
		 * .map(Role::getName) .map(SimpleGrantedAuthority::new) .toList()) .build();
		 */
    }

    @Override
    public void createUser(UserDetails user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(roleName -> {
                Role role = new Role();
                role.setName(roleName);
                return role;
            })
            .collect(Collectors.toSet()));
        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setRoles(user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(roleName -> {
                Role role = new Role();
                role.setName(roleName);
                return role;
            })
            .collect(Collectors.toSet()));
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // Implement password change logic here
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
