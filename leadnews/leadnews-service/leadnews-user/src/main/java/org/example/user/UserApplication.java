package org.example.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //集成祖册中心
@MapperScan("org.example.user.mapper")
@EnableFeignClients(basePackages = "org.example.apis")
public class UserApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class);
    }
}
