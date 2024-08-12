package com.example.appsocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appsocial.models.Post;
import com.example.appsocial.models.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);
}
