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

@Controller
@RequestMapping("blog")
public class BlogController {
    private final PostDAO postDAO;

    @Autowired
    public BlogController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @GetMapping
    public String getPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<Post> posts = postDAO.findPostByAuthor(user);
        Collections.reverse(posts);
        model.put("posts", posts);
        return "blog";
    }

    @PostMapping("filter")
    public String filterPosts(@AuthenticationPrincipal User user, @RequestParam String filter, Map<String, Object> model) {
        List<Post> posts;
        if (filter.equals("") || filter.isEmpty()) {
            posts = postDAO.findPostByAuthor(user);
            Collections.reverse(posts);
            model.put("posts", posts);
            return "redirect:/blog";
        } else {
            posts = postDAO.findPostByTagIsStartingWithAndAuthor(filter, user);
            Collections.reverse(posts);
            model.put("posts", posts);
            return "blog";
        }
    }
}
