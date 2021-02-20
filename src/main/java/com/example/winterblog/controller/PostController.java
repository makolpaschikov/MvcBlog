package com.example.winterblog.controller;

import com.example.winterblog.domain.Post;
import com.example.winterblog.domain.User;
import com.example.winterblog.repository.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * The controller working with user posts
 * @author makolpaschikov
 */
@Controller
@RequestMapping("blog")
public class PostController {
    /**
     * User posts repository
     * @see PostDAO
     */
    private final PostDAO postDAO;

    @Autowired
    public PostController(PostDAO postDAO) { this.postDAO = postDAO; }

    //==========================================
    //============= CREATING POST ==============
    //==========================================

    /**
     * Returns the page on which the post is created
     */
    @GetMapping("post_create")
    public String getCreatingPage() {
        return "create_post";
    }

    /**
     * Creates a post
     * @param user - authorized user
     * @param title - post title
     * @param text - post text
     */
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

    /**
     * Returns the page on which the post is updated
     * @param id - post id
     */
    @GetMapping("post_update/{id}")
    public String getUpdatingPage(@PathVariable Long id, Map<String, Object> model) {
        Post postFromDb = postDAO.findPostById(id).get(0);
        model.put("id", id);
        model.put("title", postFromDb.getTitle()); // Sets the current post title to the form
        model.put("text", postFromDb.getText()); // Sets the current post text to the form
        return "update_post";
    }

    /**
     * Updates the post
     * @param id - post id
     * @param title - new post title
     * @param text - new post text
     */
    @PostMapping("post_update/{id}")
    public String updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String text
    ) {
        Post postFromDb = postDAO.findPostById(id).get(0); // This is not a crutch, I just write the method in the repository ^^
        postFromDb.setTitle(title);
        postFromDb.setText(text);
        postDAO.save(postFromDb);
        return "redirect:/blog";
    }

    //==========================================
    //============= DELETING POST ==============
    //==========================================

    /**
     * Removes post by id
     * @param id - post id
     */
    @PostMapping("post_delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postDAO.deleteAll(postDAO.findPostById(id));
        return "redirect:/blog";
    }

    /**
     * Removes all posts
     * @param user - authorized user
     */
    @PostMapping("delete_all")
    public String deletePosts(@AuthenticationPrincipal User user) {
        postDAO.deleteAll(postDAO.findPostByAuthor(user));
        return "redirect:/blog";
    }

}
