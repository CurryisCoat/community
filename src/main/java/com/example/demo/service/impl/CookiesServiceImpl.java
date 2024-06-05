package com.example.demo.service.impl;

import com.example.demo.service.cookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Service
public class CookiesServiceImpl implements cookieService {

    @Override
    public String setCookies(HttpServletResponse response) throws UnsupportedEncodingException {
        String value = "嗨，欢迎您再次来到from zero to expert.";
        Cookie cookie = new Cookie("first",value);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        String cookieValue = cookie.getValue();
        return cookieValue;
    }

    @Override
    public String getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("first".equals(cookie.getName())){
                    String value = null;

                    try {
                        value = URLDecoder.decode(cookie.getValue(),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return value;
                }
            }
        }
        return "没有找到这个cookies";
    }
}
