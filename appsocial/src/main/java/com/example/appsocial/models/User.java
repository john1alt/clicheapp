package com.example.appsocial.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.springframework.data.annotation.Id;

import com.example.appsocial.repositories.FollowRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import com.example.appsocial.models.Role;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String username;
    
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    
    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followers = new HashSet<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followedUsers = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();
    
	public Long getId() {
		return id;
	}
	/*public void setId(Long id) {
		this.id = id;
	}*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public boolean followsUser(User user) {
        return this.followedUsers.contains(user);
    }

    public void followUser(User user) {
        //this.followedUsers.add(user);
        //user.followers.add(this);
    	Follow follow = new Follow();
        follow.setFollower(this);
        follow.setFollowed(user);
        this.getFollowedUsers().add(follow);
        user.getFollowers().add(follow);
        //followRepository.save(follow);
    }

    public void unfollowUser(User user) {
        this.followedUsers.remove(user);
        user.followers.remove(this);
    }

    public List<Post> getFeed() {
        List<Post> feed = new ArrayList<>(this.posts);
        for (Follow follow : this.getFollowedUsers()) {
            feed.addAll(follow.getFollowed().getPosts());
        }
        feed.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return feed;
//        for (User followedUser : this.followedUsers) {
//            feed.addAll(followedUser.posts);
//        }
//        feed.sort(Comparator.comparing(Post::getCreatedAt).reversed());
//        return feed;
    }
	public Set<Follow> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<Follow> followers) {
		this.followers = followers;
	}
	public Set<Follow> getFollowedUsers() {
		return followedUsers;
	}
	public void setFollowedUsers(Set<Follow> followedUsers) {
		this.followedUsers = followedUsers;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}    
    
}
