package com.qbk.mq.amqp.demo.web;

import com.qbk.mq.amqp.demo.provider.MyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MyProvider provider;

   @GetMapping("/")
    public String get(){
       provider.send();
       return "s";
   }
}
