package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.UserRole;
import com.example.winterblog.repository.PostDAO;
import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * The controller that implements administrator functions
 * @author makolpaschikov
 */
@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    /**
     * User posts repository
     * @see PostDAO
     */
    private final UserDAO userDAO;

    /**
     * User posts repository
     * @see PostDAO
     */
    private final PostDAO postDAO;

    @Autowired
    public AdminController(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    /**
     * Returns the page with users list
     */
    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("users", userDAO.findAllByRoles(UserRole.USER));
        return "admin/users4admin";
    }

    /**
     * Removes user by id
     * @param id - user id
     */
    @PostMapping("user_delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        postDAO.deleteAll(postDAO.findPostByAuthor(userDAO.findUserById(id).get(0)));
        userDAO.deleteAll(userDAO.findUserById(id));
        return "redirect:/admin";
    }

    /**
     * Returns the page with user posts list
     * @param id - user id
     */
    @GetMapping("user_posts/{id}")
    public String userPosts(@PathVariable Long id, Map<String, Object> model) {
        List<Post> posts = postDAO.findPostByAuthor(userDAO.findUserById(id).get(0));
        Collections.reverse(posts); // Crutch, allowing you to return first critical posts
        model.put("posts", posts);
        return "admin/user_posts4admin";
    }

    /**
     * Removes post by id
     * @param id - post id
     */
    @PostMapping("post_delete/{id}")
    public String deletePost(@PathVariable Long id) {
        Post postFromDb = postDAO.findPostById(id).get(0);
        Long userID = postFromDb.getAuthor().getId();
        postDAO.deleteAll(Collections.singleton(postFromDb));
        return "redirect:/admin/user_posts/" + userID;
    }
}
