package com.qbk.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试Advice
 */
@RestController
public class TestContrller {

    @GetMapping("/get")
    public Map<String,Object> get(String name,Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("name",name);
        resultMap.put("id",id);
        return resultMap;
    }

    @PostMapping("/post")
    public Map<String,Object> post( @RequestBody Map<String,Object> resultMap){
        return resultMap;
    }
}
