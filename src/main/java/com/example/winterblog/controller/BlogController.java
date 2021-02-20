package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The controller that manages the user page
 * @author makolpaschikov
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    /**
     * User posts repository
     * @see PostDAO
     */
    private final PostDAO postDAO;

    @Autowired
    public BlogController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    /**
     * Returns the user's page, adding all of his posts to it
     * @param user - authorized user
     */
    @GetMapping
    public String getPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<Post> posts = postDAO.findPostByAuthor(user);
        Collections.reverse(posts); // Crutch, allowing you to return first critical posts
        model.put("posts", posts);
        return "blog";
    }

    /**
     * Filters user's posts by post title
     * @param user - authorized user
     * @param filter - filter for post title
     */
    @PostMapping("filter")
    public String filterPosts(@AuthenticationPrincipal User user, @RequestParam String filter, Map<String, Object> model) {
        List<Post> posts;
        if (filter.equals("") || filter.isEmpty()) {
            posts = postDAO.findPostByAuthor(user);
            Collections.reverse(posts); // Crutch, allowing you to return first critical posts
            model.put("posts", posts);
            return "redirect:/blog";
        } else {
            posts = postDAO.findPostByTitleIsStartingWithAndAuthor(filter, user);
            Collections.reverse(posts); // Crutch, allowing you to return first critical posts
            model.put("posts", posts);
            return "blog";
        }
    }
}
