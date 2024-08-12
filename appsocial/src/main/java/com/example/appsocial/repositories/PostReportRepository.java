package com.example.appsocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appsocial.models.Post;
import com.example.appsocial.models.PostReport;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {
	List<PostReport> findByPost(Post post);
}
