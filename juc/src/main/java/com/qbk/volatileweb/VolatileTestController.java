package com.qbk.volatileweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile测试
 *
 *  需求是 当一个用户请求接口时候  其他用户必须等他请求完才能执行逻辑，要不只能立刻返回稍后再试
 */
@RequestMapping("/volatile")
@RestController
public class VolatileTestController {

    private static volatile boolean isTrue = true ;

    /**
     * 高并发下有线程安全问题，因为下面两部操作无法保证原子性
     * 1 if(isTrue){
     * 2    isTrue = false;
     */
    @GetMapping("/get")
    public String get(String name) throws InterruptedException {
        if(isTrue){
            isTrue = false;
            TimeUnit.SECONDS.sleep(5);
            isTrue = true;
        }else {
            return "稍后再试";
        }
        return "成功";
    }

    private static AtomicInteger aint = new AtomicInteger();
    /**
     * 使用cas锁 确保原子性
     */
    @GetMapping("/get2")
    public String get2(String name) throws InterruptedException {
        if(aint.compareAndSet(0,1)){
            TimeUnit.SECONDS.sleep(5);
            aint.getAndDecrement();
        }else {
            return "稍后再试";
        }
        return "成功";
    }

}
