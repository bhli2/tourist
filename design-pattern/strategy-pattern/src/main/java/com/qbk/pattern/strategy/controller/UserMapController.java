package com.qbk.pattern.strategy.controller;

import com.qbk.pattern.strategy.service.UserService;
import com.qbk.pattern.strategy.serviceimpl.UserQQServiceImpl;
import com.qbk.pattern.strategy.serviceimpl.UserWechatServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式第三种写法
 */
@RestController
public class UserMapController {

    private final Map<Integer, UserService> userServiceMap;

    private final UserQQServiceImpl userQQService;

    public UserMapController(UserQQServiceImpl userQQService , UserWechatServiceImpl userWechatService) {
        this.userQQService = userQQService;
        userServiceMap = new HashMap<>();
        userServiceMap.put(1,userQQService);
        userServiceMap.put(2,userWechatService);
    }

    @GetMapping("/mapget")
    public String mapget(int key){
        return userServiceMap.getOrDefault(key,userQQService).login();
    }
}
