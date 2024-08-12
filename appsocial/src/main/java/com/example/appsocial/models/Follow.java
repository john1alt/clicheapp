package com.example.appsocial.models;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Follow {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
    
    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followed;
    
	public Long getId() {
		return id;
	}
	/*public void setId(Long id) {
		this.id = id;
	}*/
	public User getFollower() {
		return follower;
	}
	public void setFollower(User follower) {
		this.follower = follower;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}
	public boolean isFollowedByUser(User user) {
        return this.follower.equals(user);
    }

    public boolean isFollowingUser(User user) {
        return this.followed.equals(user);
    }    
}
