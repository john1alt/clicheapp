package com.example.appsocial.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.appsocial.models.Post;
import com.example.appsocial.models.User;
import com.example.appsocial.services.FollowService;
import com.example.appsocial.services.PostService;
import com.example.appsocial.services.UserService;

//import ch.qos.logback.core.model.Model;

@Controller
public class PostController {
	@Autowired
    private PostService postService;
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    @GetMapping("/feed")
    public String getFeed(Model model, Principal principal) {
        User currentUser = userService.getUserByUsername(principal.getName());
        List<Post> feedPosts = postService.getFeedPosts(currentUser);
        model.addAttribute("posts", feedPosts);
        return "feed";
    }
    
    @PostMapping("/post")
    public String createPost(@ModelAttribute Post post, Principal principal) {
        User author = userService.getUserByUsername(principal.getName());
        postService.createPost(author, post.getTitle(), post.getContent());
        return "redirect:/feed";
    }
}
