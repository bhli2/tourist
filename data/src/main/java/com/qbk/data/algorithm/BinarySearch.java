package com.qbk.data.algorithm;

import java.util.Arrays;

/**
 * 二分查找法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {677,265,222,25,111,8,13,33,0,87,25,67,55};
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));

        int i = find(arr , 677);
        System.out.println(i);

    }

    /**
     * 排序
     */
    private static int[] sort(int[] arr){
        for (int i = 0; i < arr.length -1 ; i++) {
            for (int j = i + 1 ; j < arr.length; j++) {
                int a = arr[i];
                int b = arr[j];
                if(a > b){
                    a = a + b ;
                    b = a - b;
                    a = a - b;

                    arr[i] = a;
                    arr[j] = b;
                }
            }
        }
        return arr;
    }

    /**
     * 查找 位置
     */
    private static int find(int[] arr ,int n){
        int start =  0 ;
        int end =  arr.length - 1;

        int middle =  (start + end) / 2 ;

        while ( end >= start  ){
            System.out.println( start  + ":" + end);
              if( n == arr[middle]){
                  return middle;
              }else if(n > arr[middle]){
                  start = middle + 1;
              }else if(n < arr[middle]){
                  end = middle -1 ;
              }

            middle = (start + end) / 2 ;
        }
        return -1;
    }
}
