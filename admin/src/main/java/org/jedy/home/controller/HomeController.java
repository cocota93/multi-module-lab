package org.jedy.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "home";
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/corsTest")
    public String corsTest(){
        return "corsTest";
    }

    @GetMapping("/ajaxTest")
    @ResponseBody
    public String ajaxTest(){
        return "ajaxTestSuccess";
    }
}
