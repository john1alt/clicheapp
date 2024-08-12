//package com.example.appsocial.services;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.appsocial.models.User;
//import com.example.appsocial.repositories.UserRepository;
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//    private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	    Optional<User> user = userRepository.findByUsername(username);
//	    return user.map(u -> User.withUsername(u.getUsername())
//	        .password(u.getPassword())
//	        .authorities(u.getRoles().stream()
//	            .map(Role::getName)
//	            .map(SimpleGrantedAuthority::new)
//	            .toList())
//	        .build())
//	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	}
//	
//	/*
//	 * public UserDetails loadUserByUsername(String username) throws
//	 * UsernameNotFoundException { User user =
//	 * userRepository.findByUsername(username) .orElseThrow(() -> new
//	 * UsernameNotFoundException("User not found")); return new
//	 * org.springframework.security.core.userdetails.User( user.getUsername(),
//	 * user.getPassword(), user.getAuthorities()); }
//	 */
//	public static UserDetails withUsername(String username) {
//	    return new org.springframework.security.core.userdetails.User(
//	        username, "", Collections.emptyList());
//	}
//}
