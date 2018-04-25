package com.example.springCloudTest;

import com.example.springCloudTest.Services.TestService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//define test sequence
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class SequenceTest {

    @Autowired
    public TestService testService;

    @Test
    public void firstTest(){
        testService.firstTest();
    }

    @Test
    public void thirdTest(){
        testService.thirdTest();
    }

    @Test
    public void secondTest(){
        testService.secondTest();
    }



}
