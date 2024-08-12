package com.example.appsocial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.Role;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.RoleRepository;
import com.example.appsocial.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	
    public void registerUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }
    
    public void assignRole(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public void removeRole(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.getRoles().remove(role);
        userRepository.save(user);
    }
    
    public User getUserByUsername (String userName){
    	return userRepository.findByUsername(userName).get();
    }
}
