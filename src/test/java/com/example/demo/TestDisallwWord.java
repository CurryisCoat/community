package com.example.demo;


import com.example.demo.dao.DisallowMapper;
import com.example.demo.entity.DisallowWord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


public class TestDisallwWord extends DemoApplicationTests{

    @Autowired
    private DisallowMapper disallowMapper;

    @Test
    public void test()
    {
        List<DisallowWord> list = disallowMapper.selectAll();
        for(DisallowWord st:list){
            System.out.print(st.getValue()+" ");
        }
    }

}
