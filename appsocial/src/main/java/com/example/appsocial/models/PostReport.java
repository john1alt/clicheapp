package com.example.appsocial.models;

import java.time.LocalDateTime;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PostReport {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne
    private Post post;
    
    @ManyToOne
    private User reporter;
    
    private String reason;
    
    private LocalDateTime reportedAt;
    
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LocalDateTime getReportedAt() {
		return reportedAt;
	}
	public void setReportedAt(LocalDateTime reportedAt) {
		this.reportedAt = reportedAt;
	}
	public Long getId() {
		return id;
	}
	public boolean isReportedByUser(User user) {
        return this.reporter.equals(user);
    }
    
}
