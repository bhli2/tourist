package com.qbk.dockerplugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：quboka
 * @description：
 * @date ：2020/1/14 13:30
 */
@RestController
@SpringBootApplication
public class DockerPluginApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerPluginApplication.class,args);
    }

    @GetMapping("/")
    public String get(){
        return "hello world" ;
    }
}
