package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import com.example.winterblog.domain.UserRole;
import com.example.winterblog.repository.PostDAO;
import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserDAO userDAO;
    private final PostDAO postDAO;

    @Autowired
    public AdminController(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("users", userDAO.findAllByRoles(UserRole.USER));
        return "admin/users4admin";
    }

    @PostMapping("user_delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userDAO.deleteAll(userDAO.findUserById(id));
        return "redirect:/admin";
    }

    @GetMapping("user_posts/{id}")
    public String userPosts(@PathVariable Long id, Map<String, Object> model) {
        List<Post> posts = postDAO.findPostByAuthor(userDAO.findUserById(id).get(0));
        Collections.reverse(posts); // Crutch, allowing you to return first critical posts
        model.put("posts", posts);
        return "admin/user_posts4admin";
    }

    @PostMapping("post_delete/{id}")
    public String deletePost(@PathVariable Long id) {
        Post postFromDb = postDAO.findPostById(id).get(0);
        Long userID = postFromDb.getAuthor().getId();
        postDAO.deleteAll(Collections.singleton(postFromDb));
        return "redirect:/admin/user_posts/" + userID;
    }
}
