package org.example.search.test;


import org.example.search.SearchApplication;
import org.example.search.pojos.ApAssociateWords;
import org.example.search.pojos.ApUserSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class MongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    //保存
    @Test
    public void saveTest(){
        /*for (int i = 0; i < 10; i++) {
            ApAssociateWords apAssociateWords = new ApAssociateWords();
            apAssociateWords.setAssociateWords("黑马头条");
            apAssociateWords.setCreatedTime(new Date());
            mongoTemplate.save(apAssociateWords);
        }*/
        ApUserSearch apAssociateWords = new ApUserSearch();
        apAssociateWords.setKeyword("直播");
        apAssociateWords.setCreatedTime(new Date());
        apAssociateWords.setUserId(16);
        mongoTemplate.save(apAssociateWords);

    }

    @Test
    public void testSave(){
        String[] list = {"java","c++","pycharm","python","pytorch","c#","c","电音","抖音","股票","飞机票","金融"};

        for (String s : list) {
            ApAssociateWords apAssociateWords = new ApAssociateWords();
            apAssociateWords.setAssociateWords(s);
            apAssociateWords.setCreatedTime(new Date());
            mongoTemplate.save(apAssociateWords);
        }
    }
}