package com.qbk.maptest;

/**
 * HashMap
 *  TODO
 */
public class HashMapTest {

    public static void main(String[] args) {
        int h;
        int key = 24663434;
        h = (h = Integer.hashCode(key)) ^ (h >>> 16);
        int n = (32-1)  & h;
        System.out.println(n);
    }

}
