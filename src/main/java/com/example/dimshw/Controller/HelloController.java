package com.example.dimshw.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("")
    public String showHomePage()
    {
        return "index";
    }

}