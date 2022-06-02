package org.example.kafka.listener;

import com.alibaba.fastjson.JSON;
import org.example.kafka.pojo.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HelloListener {

    @KafkaListener(topics = "topic-user")
    public void onMessage(String message){
        if (!StringUtils.isEmpty(message)){
            User user = JSON.parseObject(message, User.class);
            System.out.println(user);
        }
    }
}
