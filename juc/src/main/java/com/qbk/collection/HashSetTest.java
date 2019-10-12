package com.qbk.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 86186 on 2019/10/9.
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<Object> st = new HashSet<>();
        st.add("1");
        st.add("1");
        st.add(new String("2"));
        st.add(new String("2"));
        //StringBuffer 没有重写 equals 和 hashCode方法
        st.add(new StringBuffer("3"));
        st.add(new StringBuffer("3"));
        //if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
        System.out.println(st);
    }
}
