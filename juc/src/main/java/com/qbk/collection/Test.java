package com.qbk.collection;

/**
 * @author ：quboka
 * @description：
 * @date ：2019/10/12 10:03
 */
public class Test {

    public static void main(String[] args) {
        int sum = 0;//总数
        int f = 1;//符号
        String characters = "-123";//转换的值
        char[] chars = characters.toCharArray();//str 变 char
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i] ;//遍历
            if(i == 0 && c == '-'){ //判断第一位是不是负数
                f= -1;  //是负数 转换
                continue; //跳过第一位
            }
            int s = c - '0'; //把char 转换成int
            int x = (int) Math.pow(10,chars.length-i-1); //位数 先遍历的是高位  转换10的次方
            sum += s * x; //位数乘以 数  +等于总数
        }
        System.out.println(sum*f);//最后再把符号位 乘以回去

    }
}
