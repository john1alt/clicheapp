package com.example.appsocial.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.appsocial.models.Post;
import com.example.appsocial.models.User;
import com.example.appsocial.services.PostService;
import com.example.appsocial.services.UserService;

@SpringBootTest
public class PostServiceTest {
	@Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Test
    void testCreatePost() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userService.registerUser(user);
        postService.createPost(user, "Test Post", "This is a test post.");
        List<Post> posts = postService.getPostsByUser(user);
        assertEquals(1, posts.size());
    }
}
