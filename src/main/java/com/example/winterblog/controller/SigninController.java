package com.example.winterblog.controller;

import com.example.winterblog.domain.User;
import com.example.winterblog.domain.UserRole;
import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("signin")
public class SigninController {
    private final UserDAO userDAO;

    @Autowired
    public SigninController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String getPage() {
        return "registration";
    }

    @PostMapping
    public String registration(User user, Map<String, Object> model) {
        User userFromDB = userDAO.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            model.put("warning", "A user with this name is already registered");
            return getPage();
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(UserRole.USER));
            userDAO.save(user);
            return "redirect:/login";
        }
    }
}
