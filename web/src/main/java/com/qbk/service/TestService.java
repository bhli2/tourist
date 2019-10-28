package com.qbk.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void into() {
        System.out.println("测试自动注入");
    }
}
