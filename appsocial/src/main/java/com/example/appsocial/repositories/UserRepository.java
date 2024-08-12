package com.example.appsocial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appsocial.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
