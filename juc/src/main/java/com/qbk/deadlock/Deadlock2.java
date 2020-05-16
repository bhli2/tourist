package com.qbk.deadlock;

/**
 * 死锁
 * 通过 Jconsole  观察
 * 通过 Jstack  观察
 */
public class Deadlock2 {
    class A {

    }
    class B {

    }
    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (B.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A.class) {

                    }
                }
            }
        },"deadlock1").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (A.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B.class) {
                    }
                }
            }
        },"deadlock2").start();
    }
}

