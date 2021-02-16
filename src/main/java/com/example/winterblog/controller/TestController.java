package com.example.winterblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String getPage(@RequestParam(name="msg", required = false, defaultValue = "def") String msg, Map<String, Object> model){
        model.put("msg", msg);
        return "dumb";
    }
}
