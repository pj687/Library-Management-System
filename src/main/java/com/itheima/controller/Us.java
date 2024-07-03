package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Us {
    @RequestMapping("/aaa")
    public String get(){
        return "main";
    }
}
