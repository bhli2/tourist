package com.qbk.test4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/get")
    public List<String> get(int id , String name){
        System.out.println("------------------"+id);
        System.out.println("------------------"+name);
        return new ArrayList<>(Arrays.asList("A","B","C"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
