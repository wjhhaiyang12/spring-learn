package com.example.springCloudTest;

import com.example.springCloudTest.Services.TestService;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public TestService testService;

    @Test(expected = NullPointerException.class)
    @Ignore
    public void exceptionTest(){
        testService.exceptionTest();
    }

    @Test
    public void thrownTest(){
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("123");
        testService.exceptionTest();
    }

}
