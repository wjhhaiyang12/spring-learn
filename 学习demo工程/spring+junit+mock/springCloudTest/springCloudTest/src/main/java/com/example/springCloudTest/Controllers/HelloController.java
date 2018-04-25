package com.example.springCloudTest.Controllers;

import com.example.springCloudTest.Services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    public HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        helloService.hello();
        return "666";
    }

}
