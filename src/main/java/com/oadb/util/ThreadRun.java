package com.oadb.util;

/**
 * Created by beishan on 2017/4/3.
 */
public class ThreadRun {

    public static void test1(){
        test2("test_serial");
    }

    public static void test2(final String str){
        System.out.println("str-1:" + str);
        new Thread(new Runnable() {
            public void run() {
                System.out.println("str-2:" + str);
            }
        }).start();
    }
}
