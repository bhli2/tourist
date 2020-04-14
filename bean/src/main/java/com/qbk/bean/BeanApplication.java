package com.qbk.bean;

import com.qbk.bean.aware.MyApplicationContextAware;
import 循环依赖.三个循环依赖.A1;
import 循环依赖.三个循环依赖.B1;
import 循环依赖.三个循环依赖.C1;
import 循环依赖.属性循环依赖.A;
import 循环依赖.属性循环依赖.B;
import 循环依赖.构造方法属性循环依赖.A2;
import 循环依赖.构造方法属性循环依赖.B2;
import 循环依赖.构造方法循环依赖.A3;
import 循环依赖.构造方法循环依赖.B3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试3种情况的springbean 循环依赖
 */
@SpringBootApplication(
//        scanBasePackageClasses = {A.class,B.class,A2.class ,B2.class,MyApplicationContextAware.class}
//        scanBasePackageClasses = {A3.class ,B3.class}
        scanBasePackageClasses = {A1.class ,B1.class,C1.class,MyApplicationContextAware.class}
)
public class BeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanApplication.class, args);
    }

}
