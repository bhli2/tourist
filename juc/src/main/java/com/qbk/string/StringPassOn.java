package com.qbk.string;

import java.util.Arrays;

/**
 * string传递
 */
public class StringPassOn {
    private String str = new String("abc");
    private String[] arr = {"a","b","c"};
    private Integer i = new Integer(666);
    public static void main(String[] args) {
        StringPassOn spo = new StringPassOn();
        spo.fun(spo.str,spo.arr,spo.i);
        System.out.println(spo.str);
        System.out.println(Arrays.toString(spo.arr));
        System.out.println(spo.i);
    }
    private void fun(String str, String[] arr,Integer i){
        str = "qbk";
        arr[1] = "y";
        i = 2333;

    }
}
