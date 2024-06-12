package com.example.demo.util;


import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    // 根节点
    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init()
    {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-filter.txt");
        BufferedReader read = new BufferedReader(new InputStreamReader(is));

        try {
            String keyWord;
            while((keyWord=read.readLine())!=null)
            {
                this.addKeyword(keyWord);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败: " + e.getMessage());
        }
    }

    //将一个敏感词添加到前缀树中
    private void addKeyword(String keyWord)
    {

        TrieNode tempNode = rootNode;
        for(int i = 0;i < keyWord.length();i ++)
        {
            char c = keyWord.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);

            if(subNode==null){
                subNode = new TrieNode();
                tempNode.addSubNode(c,subNode);
            }

            tempNode=subNode;
            if(i == keyWord.length()-1){
                tempNode.setKeywordEnd(true);
            }
        }
    }
    //过滤文本
    public String filter(String text)
    {
        if(StringUtils.isBlank(text)){
            return null;
        }

        TrieNode p1 = rootNode;
        int p2 = 0;
        int p3 = 0;
        StringBuilder sb = new StringBuilder();
        while(p3 < text.length())
        {
            char c = text.charAt(p3);
            if(isSymbol(c)){

                if(p1 == rootNode){
                    sb.append(c);
                    p2++;
                }
                p3++;
                continue;
            }

            p1 = p1.getSubNode(c);
            if(p1==null){
                sb.append(text.charAt(p2));
                p3 = ++p2;
                p1 = rootNode;
            }else if(p1.isKeywordEnd()==true){
                sb.append(REPLACEMENT);
                p2 = ++p3;
                p1 = rootNode;
            }else{
                p3++;
            }
        }
        sb.append(text.substring(p2));
        return sb.toString();
    }


    // 判断是否为符号
    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    //树结构
    private class TrieNode{

        private boolean iskeywordEnd = false;

        private HashMap<Character,TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return iskeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            iskeywordEnd = keywordEnd;
        }

        public void addSubNode(Character c,TrieNode node){
            subNodes.put(c,node);
        }

        public TrieNode getSubNode(Character c){
            return subNodes.get(c);
        }
    }
}
