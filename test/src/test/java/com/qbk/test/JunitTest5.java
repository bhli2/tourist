package com.qbk.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * springboot2.2.0 集成的 junit5
 */
@SpringBootTest
class JunitTest5 {

    @BeforeAll
    static void initStatic(){
        System.out.println("测试前");
    }
    @AfterAll
    static void destoryStatic(){
        System.out.println("测试后");
    }

    /**
     * junit5
     */
    @Test
    @DisplayName("junit5测试用例")
    void junit5(){
        int statusCode = 200;
        String name = "kk";
        Integer age = 29;
        Assertions.assertAll("报错提醒",
                ()-> Assertions.assertEquals(200, statusCode),
                ()-> Assertions.assertEquals("kk", name),
                ()-> Assertions.assertNotNull(age)
        );
        System.out.println("测试成功");
    }

    /**
     * Spring Assert
     */
    @Test
    @DisplayName("SpringAssert测试用例")
    void springAssert(){
        int statusCode = 200;
        String name = "kk";
        Integer age = 29;
        Map sources = new HashMap(){{put("k","v");}};

        //org.springframework.util.Assert;
        Assert.notNull(age, "age must not be null");
        Assert.notEmpty(sources, "Sources must not be empty");
        Assert.isTrue(200 == statusCode,"请求错误" );
        Assert.isTrue("kk".equals(name),"名称错误" );
        System.out.println("测试成功");
    }

}
