package com.example.appsocial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.appsocial.services.CustomUserDetailsManager;

//
//import java.util.Collections;
////import java.util.Optional;
//import org.apache.el.stream.Optional;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import com.example.appsocial.repositories.UserRepository;
//
@Configuration
public class SecurityConfig {
	//@Autowired
    private final CustomUserDetailsManager customUserDetailsManager;
	
	@Autowired
    public SecurityConfig(CustomUserDetailsManager customUserDetailsManager) {
        this.customUserDetailsManager = customUserDetailsManager;
    }

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/register", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/feed", true)
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/")
            )
            .userDetailsService(customUserDetailsManager);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/", "/register", "/login").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin((form) -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/feed", true)
//            )
//            .logout((logout) -> logout
//                .logoutSuccessUrl("/")
//            );
//
//        return http.build();
//    }
//	
//	@Bean
//	public UserDetailsService userDetailsService(UserRepository userRepository) {
//	    return username -> (userRepository.findByUsername(username)).get(0)
//	        .map(user -> User.withUsername(user.getUsername())
//	            .password(user.getPassword())
//	            .authorities(user.getRoles().stream()
//	                .map(Role::getName)
//	                .map(SimpleGrantedAuthority::new)
//	                .toList())
//	            .build())
//	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	}
//	
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
