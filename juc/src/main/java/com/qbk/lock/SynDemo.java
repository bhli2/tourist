package com.qbk.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 通过打印加锁类来查看对象头
 */
public class SynDemo {
    public static void main(String[] args) throws InterruptedException {
        //默认启动5秒后才开启偏向锁
        //Thread.sleep(5000);
        SynDemo synDemo = new SynDemo();
        synchronized (synDemo) {
            System.out.println("偏向锁立即生效参数：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0");
            //synDemo.hashCode();//计算一次hashcode
            System.out.println(ClassLayout.parseInstance(synDemo).toPrintable());
        }
    }
}
