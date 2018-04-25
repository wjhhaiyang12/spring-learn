package com.example.springCloudTest;

import com.example.springCloudTest.Services.HelloService;
import com.example.springCloudTest.Services.TestService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockTest {

    @Autowired
    @InjectMocks
    @Spy
    private HelloService helloService;

    @Mock
    private TestService testService;

    @Test
    public void mockTest(){
        when(testService.firstTest()).thenReturn("it's a mock instance");
        doReturn(" still mock").when(helloService).callOtherMethod();
        System.out.println(helloService.callTest());
    }

    @Test
    @Ignore
    public void easyMockTest(){
        when(testService.firstTest()).thenReturn("it's a mock instance");
        String mockResult = testService.firstTest();
        System.out.println(mockResult);
    }


}
