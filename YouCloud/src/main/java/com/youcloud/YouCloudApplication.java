package com.youcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync        //开启异步功能的注解
@SpringBootApplication
public class YouCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouCloudApplication.class, args);
    }

}
