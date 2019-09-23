package com.qbk.juc;

import java.util.concurrent.Semaphore;

/**
 * @author ：quboka
 * @description：信号量
 * @date ：2019/9/23 17:12
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //工人数
        int n = 8;
        //机器数目
        Semaphore semaphore = new Semaphore(5);
        for(int i=0 ;i < n; i++){
            new Worker(i,semaphore).start();
        }
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        Worker(int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("-------------------工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
