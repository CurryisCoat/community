package com.example.demo.service;


import com.example.demo.dao.DisallowMapper;
import com.example.demo.entity.DisallowWord;
import com.example.demo.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisallowService {

    @Autowired
    private DisallowMapper disallowMapper;

    public List<DisallowWord> wordList(){
        return disallowMapper.selectAll();
    }

}
