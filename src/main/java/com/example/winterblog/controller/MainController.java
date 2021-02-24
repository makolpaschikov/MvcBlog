package com.example.winterblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller returning home page
 * @author makolpaschikov
 */
@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String getPage() {
        return "index";
    }
}
