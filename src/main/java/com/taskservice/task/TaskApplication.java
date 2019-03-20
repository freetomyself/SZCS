package com.taskservice.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@MapperScan("com.taskservice.task.mapper")
@EnableScheduling
//扫描，用于过滤器
@ServletComponentScan
public class TaskApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }


}
