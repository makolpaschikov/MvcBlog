package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {
    private final PostRepo postRepo;

    @Autowired
    public TestController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public String getPage(Map<String, Object> model){
        model.put("posts", postRepo.findAll());
        return "dumb";
    }

    @PostMapping
    public String addPost(@RequestParam String tag, @RequestParam String text, Map<String, Object> model) {
        Post post = new Post(tag, text);
   //     postRepo.save(post);
   //     model.put("posts", postRepo.findAll());
        return "dumb";
    }

    @PostMapping("filter")
    public String filterPosts(@RequestParam String tag, Map<String, Object> model) {
   //     model.put("posts", postRepo.findByTag(tag));
        return "dumb";
    }
}
