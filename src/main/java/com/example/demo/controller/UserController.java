package com.example.demo.controller;


import com.example.demo.service.cookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
public class UserController {

    @Autowired
    private cookieService cookiesService;

    @RequestMapping(path = "/FromZerotoExpert",method = RequestMethod.GET)
    @ResponseBody
    public String visit(HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException {
        Cookie[] cookies =request.getCookies();
        String value = "嗨，欢迎您来到 from zero to expert.";
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("first".equals(cookie.getName())){
                    String originValue = URLDecoder.decode(cookie.getValue(),"UTF-8");
                    value = originValue;
                    break;
                }
            }
        }

        String encodeValue = URLEncoder.encode("嗨，欢迎您再次来到 from zero to expert.", "UTF-8");
        Cookie cookie = new Cookie("first",encodeValue);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        return value;
    }
}
