package com.qbk.source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.qbk.source.mapper")
public class SpringRoutingDataSource {
    public static void main(String[] args) {
        SpringApplication.run(SpringRoutingDataSource.class,args);
    }
}
