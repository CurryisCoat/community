package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface cookieService {

    String setCookies(HttpServletResponse response) throws UnsupportedEncodingException;

    String getCookies(HttpServletRequest request);
}
