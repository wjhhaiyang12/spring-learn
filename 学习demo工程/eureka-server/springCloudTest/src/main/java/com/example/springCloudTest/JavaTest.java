package com.example.springCloudTest;

import org.springframework.util.StopWatch;

public class JavaTest {

    public static Object lock = 0;

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("test");
        stopWatch.start("main");
        int sum = 0;
        synchronized (lock) {
            for (int i = 0; i < 1000000; ++i)
                sum++;
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

}
