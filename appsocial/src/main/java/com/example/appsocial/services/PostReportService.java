package com.example.appsocial.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.Post;
import com.example.appsocial.models.PostReport;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.PostReportRepository;

@Service
public class PostReportService {
	 	@Autowired
	    private PostReportRepository postReportRepository;

	    public void reportPost(Post post, User reporter, String reason) {
	        PostReport report = new PostReport();
	        report.setPost(post);
	        report.setReporter(reporter);
	        report.setReason(reason);
	        report.setReportedAt(LocalDateTime.now());
	        postReportRepository.save(report);
	    }

	    public List<PostReport> getReportsForPost(Post post) {
	        return postReportRepository.findByPost(post);
	    }
}
