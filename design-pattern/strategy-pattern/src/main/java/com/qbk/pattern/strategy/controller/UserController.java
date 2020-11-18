package com.qbk.pattern.strategy.controller;

import com.qbk.pattern.strategy.service.UserService;
import com.qbk.pattern.strategy.serviceimpl.UserQQServiceImpl;
import com.qbk.pattern.strategy.serviceimpl.UserWechatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;;
import java.util.Map;

@RestController
public class UserController {

    /**
     * Map<String, T> map ,即: 键必须是String类型, 值可以是任意类型
     * 这样的话,这个map就会将context中所有类型为T的bean都注入到这个map中了.
     */
    @Autowired
    private Map<String, UserService> userServices;

    @Bean
    public ApplicationRunner runner(){
        return args -> {
            System.out.println(userServices);
        };
    }

    @GetMapping("/")
    public String login(int key){
        String keyStr = captureName(UserQQServiceImpl.class.getSimpleName());
        if(key == 1){
            keyStr = captureName(UserWechatServiceImpl.class.getSimpleName());
        }
        return userServices.get(keyStr).login();
    }

    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]+=32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        String name = UserQQServiceImpl.class.getName();
        String simpleName = UserQQServiceImpl.class.getSimpleName();
        String canonicalName = UserQQServiceImpl.class.getCanonicalName();
        String typeName = UserQQServiceImpl.class.getTypeName();
        System.out.println(name);
        System.out.println(simpleName);
        System.out.println(canonicalName);
        System.out.println(typeName);
    }
}
