package com.example.appsocial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appsocial.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName (String name);
}
