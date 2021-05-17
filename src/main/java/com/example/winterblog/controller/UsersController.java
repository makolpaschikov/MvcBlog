package com.example.winterblog.controller;

import com.example.winterblog.repository.UserDAO;
import com.example.winterblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * The controller displaying all users for the user
 * @author makolpaschikov
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    /**
     * Users repository
     * @see UserDAO
     */
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a page listing all users on it
     */
    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("users", userService.getAll());
        return "users";
    }

    /**
     * Finds users in the list starting with the specified username
     * @param username - filter for searching users by username
     */
    @PostMapping("user")
    public String getUser(@RequestParam String username, Map<String, Object> model) {
        model.put("users", userService.getByNameStartWith(username));
        return "users";
    }
}
