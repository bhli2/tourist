package com.qbk.lockweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * trylock 尝试获取锁
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html
 */
@RestController
@RequestMapping("/lock")
public class LockController {

    private static Lock lock = new ReentrantLock();

    @GetMapping("/get")
    public String get(){
        /*
            lock.tryLock()
            仅当锁在调用时处于空闲状态时才获取锁。
            如果锁可用，则获取锁，并立即返回值true。如果锁不可用，则此方法将立即返回值false。
         */
        if (lock.tryLock()) {
            //不要把加锁写在 try里面
            try {
                TimeUnit.SECONDS.sleep(2);
                //int i = 10 / 0;
            }catch (Exception e){
                return "失败";
            }finally {
                //释放锁写在finally里
                lock.unlock();
            }
            return "成功";
        } else {
            return "稍后再试";
        }
    }
}
