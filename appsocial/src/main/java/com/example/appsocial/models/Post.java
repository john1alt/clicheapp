package com.example.appsocial.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String title;
    
    private String content;
    
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostReport> reports = new HashSet<>();
    
	public Long getId() {
		return id;
	}
	/*public void setId(Long id) {
		this.id = id;
	}*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public boolean isReportedByUser(User user) {
        return this.reports.stream()
            .anyMatch(report -> report.getReporter().equals(user));
    }

    public void addReport(PostReport report) {
        this.reports.add(report);
    }    
}
