package com.example.springCloudTest.Services;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String firstTest(){
        return "first test";
    }

    public void secondTest(){
        System.out.println("second test.");
    }

    public void thirdTest(){
        System.out.println("third test.");
    }

    public void exceptionTest(){
        String testStr = null;
        System.out.println(testStr.length());
    }

    public void timeoutTest() throws InterruptedException{
        Thread.sleep(1000);
    }

    public String callOtherMethod(){
        return "call other method";
    }


}
