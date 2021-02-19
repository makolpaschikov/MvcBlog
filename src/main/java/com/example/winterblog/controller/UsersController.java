package com.example.winterblog.controller;

import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("users", userDAO.findAll());
        return "users";
    }

    @PostMapping("user")
    public String getUser(@RequestParam String username, Map<String, Object> model) {
        model.put("users", userDAO.findUserByUsernameIsStartingWith(username));
        return "users";
    }
}
