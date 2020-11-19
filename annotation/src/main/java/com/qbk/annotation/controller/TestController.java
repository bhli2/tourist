package com.qbk.annotation.controller;

import com.qbk.annotation.anno.QbkAnno;
import com.qbk.annotation.service.TestService;
import com.qbk.annotation.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String get(){
        testService.fun();
        for (Class<?> i : TestService.class.getInterfaces()) {
            QbkAnno annotation = i.getAnnotation(QbkAnno.class);
            return annotation.value();
        }
        return "sss";
    }
}
