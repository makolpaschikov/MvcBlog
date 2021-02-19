package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getPage(Map<String, Object> model) {
        List<Post> posts = (List<Post>) postDAO.findAll();
        Collections.reverse(posts);
        model.put("posts", posts);
        return "blog";
    }

    @PostMapping
    public String addPost(@RequestParam String tag, @RequestParam String text, Map<String, Object> model) {
        postDAO.save(new Post(tag, text));
        return getPage(model);
    }

    @PostMapping("filter")
    public String filterPosts(@RequestParam String filter, Map<String, Object> model) {
        List<Post> posts = postDAO.findPostByTagIsStartingWith(filter);
        Collections.reverse(posts);
        model.put("posts", posts);
        return "blog";
    }

    @PostMapping("clear")
    public String filterPosts(Map<String, Object> model) {
        postDAO.deleteAll();
        return getPage(model);
    }
}
