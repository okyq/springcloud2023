package com.yq.suanfa;

import org.junit.Test;

/**
 * @author yuqian
 * @ClassName Interview
 * @description:
 * @date 2023年03月15日
 */
public class Interview {

    @Test
    public void getString(){
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println(a.equals(b));
        System.out.println(a == b);
        System.out.println(a.equals(c));
        System.out.println(a == c);
    }

    @Test
    public void testPlus(){
        short x = 1;
        char a = (char) x;

        char b = 1;
        short y = (short) b;

        int c = 1;
        float d = c;
    }
}
