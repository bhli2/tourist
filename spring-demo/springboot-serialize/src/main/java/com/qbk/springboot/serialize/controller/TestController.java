package com.qbk.springboot.serialize.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/get")
    public Map<String,Object> get(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }
}
