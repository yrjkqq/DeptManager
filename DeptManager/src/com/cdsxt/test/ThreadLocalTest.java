package com.cdsxt.test;

import com.cdsxt.util.ThreadLocal;

public class ThreadLocalTest {

    private static ThreadLocal<User> tp = new ThreadLocal<>();

    public static void main(String[] args) {

        User u = new User("lisi", 23);
        System.out.println(u);
        tp.set(u);
        test();

        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(tp.get());
            }
        };
        t.start();


    }

    private static void test() {
        System.out.println(tp.get());
    }
}

