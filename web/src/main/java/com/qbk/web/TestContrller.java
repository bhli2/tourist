package com.qbk.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 */
@ControllerAdvice
@RestController
public class TestContrller {

    @GetMapping("/get")
    public String get(){
        return "get";
    }
}
