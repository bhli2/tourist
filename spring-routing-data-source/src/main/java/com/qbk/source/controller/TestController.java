package com.qbk.source.controller;

import com.qbk.source.domain.TabUser;
import com.qbk.source.service.TabUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private TabUserService tabUserService;
    private TestController(TabUserService tabUserService){
        this.tabUserService = tabUserService ;
    }

    @GetMapping("/")
    public List<TabUser> get(){
        return tabUserService.selectList();
    }
}
