package org.example.kafka.controller;

import com.alibaba.fastjson.JSON;
import org.example.kafka.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/hello")
    public String hello(){
        User user = new User();
        user.setUsername("小明");
        user.setAge(10);
        String message = JSON.toJSONString(user);
        kafkaTemplate.send("topic-user",message);
        return "ok";
    }
}
