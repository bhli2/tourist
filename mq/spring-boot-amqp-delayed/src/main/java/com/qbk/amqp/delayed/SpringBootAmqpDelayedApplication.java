package com.qbk.amqp.delayed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 延时队列
 */
@SpringBootApplication
public class SpringBootAmqpDelayedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqpDelayedApplication.class,args);
    }
}
