package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(path = "/FromZerotoExpert",method = RequestMethod.GET)
    @ResponseBody
    public String visit(){
        return "嗨，欢迎您来到 from zero to expert。";
    }
}
