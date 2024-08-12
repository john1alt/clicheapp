package com.example.appsocial.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.Follow;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.FollowRepository;

@Service
public class FollowService {
	@Autowired
    private FollowRepository followRepository;

    public void follow(User follower, User followed) {
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowed(followed);
        followRepository.save(follow);
    }

    public void unfollow(User follower, User followed) {
        Optional<Follow> followOptional = followRepository.findByFollowerAndFollowed(follower, followed);
        followOptional.ifPresent(followRepository::delete);
    }
    
    public List<User> getFollowersForUser(User user) {
        List<Follow> follows = followRepository.findByFollowed(user);
        return follows.stream()
            .map(Follow::getFollower)
            .collect(Collectors.toList());
    }
    
    public List<User> getFollowedUsers(User user) {
        List<Follow> follows = followRepository.findByFollower(user);
        return follows.stream()
            .map(Follow::getFollowed)
            .collect(Collectors.toList());
    }
}
