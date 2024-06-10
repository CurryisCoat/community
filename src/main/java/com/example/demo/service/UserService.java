package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User registers(String username){
        return userMapper.selectByUserName(username);
    }

    public int insert(User user){
        return  userMapper.insertData(user);
    }
}
