package com.example.winterblog.controller;

import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserDAO userDAO;

    @Autowired
    public AdminController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("users", userDAO.findAll());
        return "admin/users4admin";
    }
}
