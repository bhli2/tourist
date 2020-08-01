package com.qbk.jvmtest.oom;

import java.util.Map;
import java.util.Random;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * gc频繁
 * jvm参数：-Xmx100m -XX:+UseParallelGC
 */
public class GCOverheadLimitExceeded {

    public static void main(String args[]) throws Exception {
        Map<Object,Object> map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }

}
