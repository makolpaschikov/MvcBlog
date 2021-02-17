package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {
    private final PostDAO postDAO;

    @Autowired
    public TestController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @GetMapping
    public String getPage(Map<String, Object> model){
        model.put("posts", postDAO.findAll());
        return "dumb";
    }

    @PostMapping
    public String addPost(@RequestParam String tag, @RequestParam String text, Map<String, Object> model) {
        postDAO.save(new Post(tag, text));
        return getPage(model);
    }

    @PostMapping("filter")
    public String filterPosts(@RequestParam String filter, Map<String, Object> model) {
        model.put("posts", postDAO.findPostByTagStartingWith(filter));
        return "dumb";
    }

    @PostMapping("clear")
    public String filterPosts(Map<String, Object> model) {
        postDAO.deleteAll();
        return getPage(model);
    }
}
