package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
@RequestMapping("/")//this is root redirect into login-page
    public String indexPage(){
        return "login-page";
    }
}
