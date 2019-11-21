package com.qbk.test4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/get")
    public Map get(int id ,String name){
        System.out.println("------------------"+id);
        System.out.println("------------------"+name);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("code","200");
        return hashMap;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
