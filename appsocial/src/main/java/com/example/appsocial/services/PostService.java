package com.example.appsocial.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.Follow;
import com.example.appsocial.models.Post;
import com.example.appsocial.models.User;
import com.example.appsocial.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
    private PostRepository postRepository;
	
	@Autowired
    private FollowService followService;

    public void createPost(User author, String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(author);
        postRepository.save(post);
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByAuthor(user);
    }
    
    public List<Post> getFeedPosts(User user) {
        List<Post> userPosts = postRepository.findByAuthor(user);
        List<User> followedUsers = followService.getFollowedUsers(user);
        List<Post> feedPosts = new ArrayList<>(userPosts);
        for (User follow : followedUsers) {
            feedPosts.addAll(postRepository.findByAuthor((User) follow.getFollowedUsers()));
        }
        feedPosts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return feedPosts;
    }
}
