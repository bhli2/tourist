package com.qbk.collection;

import java.util.Arrays;

/**
 * 数组复制
 */
public class ArrayCopy {

    public static void main(String[] args) {
        /**
         *
         * * @param      src      源数组
         * * @param      srcPos   源数组要复制的起始位置
         * * @param      dest     目标数组
         * * @param      destPos  目标数组复制的起始位置
         * * @param      length   复制的长度
         *  public static native void arraycopy(Object src,  int  srcPos,
         *                                         Object dest, int destPos,
         *                                         int length);
         */
        //第一个源数组
       char[] src1 = {'a','b','c'};
        //第二个源数组
       char[] src2 = {'d','e'};
       //首先把第一个数组复制进目标数组 且数组长度为两个数组长度之和
       char[] dest = Arrays.copyOf(src1, src1.length + src2.length);
       System.out.println(dest);
       System.out.println(dest.length);
       //然后把第二个数组复制进目标数组，目标数组复制起始位置为第一个数组长度，复制长度为第二个数组长度
       System.arraycopy(src2,0,dest,src1.length,src2.length);
       System.out.println(dest);
    }
}
