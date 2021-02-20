package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("blog")
public class PostController {
    private final PostDAO postDAO;

    @Autowired
    public PostController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    //==========================================
    //============= CREATING POST ==============
    //==========================================

    @GetMapping("post_create")
    public String getCreatingPage() {
        return "create_post";
    }

    @PostMapping("post_create")
    public String addPost(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String text
    ) {
        postDAO.save(new Post(title, text, user));
        return "redirect:/blog";
    }

    //==========================================
    //============= UPDATING POST ==============
    //==========================================

    @GetMapping("post_update/{id}")
    public String getUpdatingPage(@PathVariable Long id, Map<String, Object> model) {
        Post postFromDb = postDAO.findPostById(id).get(0);
        model.put("id", id);
        model.put("title", postFromDb.getTitle());
        model.put("text", postFromDb.getText());
        return "update_post";
    }

    @PostMapping("post_update/{id}")
    public String updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String text
    ) {
        Post postFromDb = postDAO.findPostById(id).get(0);
        postFromDb.setTitle(title);
        postFromDb.setText(text);
        postDAO.save(postFromDb);
        return "redirect:/blog";
    }

    //==========================================
    //============= DELETING POST ==============
    //==========================================

    @PostMapping("post_delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postDAO.deleteAll(postDAO.findPostById(id));
        return "redirect:/blog";
    }

    @PostMapping("delete_all")
    public String deletePosts(@AuthenticationPrincipal User user) {
        postDAO.deleteAll(postDAO.findPostByAuthor(user));
        return "redirect:/blog";
    }

}
