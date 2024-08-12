package com.example.appsocial.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appsocial.models.Follow;
import com.example.appsocial.models.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	List<Follow> findByFollower(User follower);
    List<Follow> findByFollowed(User followed);
    Optional<Follow> findByFollowerAndFollowed(User follower, User followed);

}
