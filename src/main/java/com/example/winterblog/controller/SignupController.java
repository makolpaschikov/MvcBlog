package com.example.winterblog.controller;

import com.example.winterblog.domain.User;
import com.example.winterblog.domain.UserRole;
import com.example.winterblog.repository.PostDAO;
import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

/**
 * Controller responsible for registering new users
 * @author makolpaschikov
 */
@Controller
@RequestMapping("signup")
public class SignupController {
    /**
     * Users repository
     * @see UserDAO
     */
    private final UserDAO userDAO;

    @Autowired
    public SignupController(UserDAO userDAO) { this.userDAO = userDAO; }

    /**
     * Returns the registration page
     */
    @GetMapping
    public String getPage() {
        return "registration";
    }

    /**
     * Registers a new user in the database and redirects him to the login page
     * @param user - new user
     */
    @PostMapping
    public String registration(User user, Map<String, Object> model) {
        User userFromDB = userDAO.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            // The database cannot have users with the same username
            model.put("warning", "A user with this username is already registered");
            return getPage();
        } else {
            // The profile is recognized as active, assigned role USER
            user.setActive(true);
            user.setRoles(Collections.singleton(UserRole.USER));
            userDAO.save(user);
            return "redirect:/login";
        }
    }
}
