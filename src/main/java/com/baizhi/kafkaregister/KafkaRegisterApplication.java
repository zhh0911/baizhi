package com.baizhi.kafkaregister;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.kafkaregister.dao")
public class KafkaRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaRegisterApplication.class, args);
    }

}
