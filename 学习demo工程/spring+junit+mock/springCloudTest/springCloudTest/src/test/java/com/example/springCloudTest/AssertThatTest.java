package com.example.springCloudTest;

import com.example.springCloudTest.Services.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssertThatTest {

    @Autowired
    public TestService testService;

    @Test
    public void assertThatTest(){
        Assert.assertThat("123", is("123"));
    }

}
