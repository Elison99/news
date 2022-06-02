package org.example.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Version: V1.0
 */
@SpringBootApplication
@MapperScan("org.example.es.mapper")
public class EsInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsInitApplication.class, args);
    }

}
