package com.example.springCloudTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    public TestService testService;

    public void hello(){
        System.out.println("hello");
    }

    public String callTest(){
        return testService.firstTest() + callOtherMethod();
    }

    public String callOtherMethod(){
        return "call other method";
    }


}
