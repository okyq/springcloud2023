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

    @Test
    public void testStr(){
        String x="aa";
        String y="bb";
        String z="aa"+y; //z="aabb"
        String test = "aabb"; //如果把aabb放进常量池，那么z==test
        System.out.println(z==test);

        String str = new String("aaa");
        String xxx = "aaa";
        String yyy = "aaa";
        System.out.println(str == xxx);
        System.out.println(xxx == yyy);
    }

    @Test
    public void aa(){
        byte a = 127;
        byte b = 127;
        byte c = (byte)(a + b);
        System.out.println(c);
        b += a;
        System.out.println(b);
    }
}
