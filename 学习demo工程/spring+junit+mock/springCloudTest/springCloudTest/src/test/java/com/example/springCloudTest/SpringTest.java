package com.example.springCloudTest;

import com.example.springCloudTest.Services.HelloService;
import com.example.springCloudTest.Services.TestService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringTest {

    @Autowired
    public HelloService helloService;

    @Autowired
    public TestService testService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Ignore
    public void serviceTest(){
        helloService.hello();
    }

    @Test
    @Ignore
    public void webTest() {
        String response = testRestTemplate.getForObject("/hello", String.class);
        Assert.assertEquals(response, "666");
    }

    @Test(timeout = 500L)
    @Ignore
    public void timeoutTest(){
        try{
            testService.timeoutTest();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
    }

}
