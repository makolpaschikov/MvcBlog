package com.example.winterblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String getPage(Map<String, Object> model) {
        model.put("name", "lala");

        return "index";
    }
}
