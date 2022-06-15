package com.xian.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.quartz.QuartzJobBean;

@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
