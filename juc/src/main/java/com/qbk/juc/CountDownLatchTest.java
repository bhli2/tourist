package com.qbk.juc;

import java.util.concurrent.*;

/**
 * @author ：quboka
 * @description：同步计数器
 * @date ：2019/9/23 17:31
 */
public class CountDownLatchTest {

    public static void main (String[] args) throws ExecutionException, InterruptedException {
        int count = 10 ;
        final ExecutorService executorService = Executors.newCachedThreadPool();
        //同步计数器
        final CountDownLatch countDownLatch = new CountDownLatch(count);

        //执行10个任务线程
        for (int i = 0; i< count ;i++) {
            final Future<String> submit = executorService.submit(() -> {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                //计数减一
                countDownLatch.countDown();
                return "s" ;
            });
        }

        //一个监测线程
        new Thread(() -> {
            try {
                //阻塞等待，计数器为0时才往下执行
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            //关闭线程池
            executorService.shutdown();
        }).start();
    }
}
