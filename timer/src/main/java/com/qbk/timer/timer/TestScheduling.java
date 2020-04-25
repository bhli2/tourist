package com.qbk.timer.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TestScheduling {

    @Scheduled(cron = "0/3 * * * * ?")
    public void task() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String curName = Thread.currentThread().getName() ;
        System.out.println("当前时间:"+ LocalDateTime.now()+",task1 任务对应的线程名: "+curName);
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void taskScheduler() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String curName = Thread.currentThread().getName() ;
        System.out.println("当前时间:"+ LocalDateTime.now()+",task2 任务对应的线程名: "+curName);
    }


}