package com.example.demo.dao;


import com.example.demo.entity.DisallowWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisallowMapper {

    List<DisallowWord> selectAll();
}
