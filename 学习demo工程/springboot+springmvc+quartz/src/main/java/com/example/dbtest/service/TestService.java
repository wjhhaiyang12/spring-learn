package com.example.dbtest.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class TestService implements InitializingBean{

    public String testData = "test";

    @Override
    public void afterPropertiesSet(){
        System.out.println("initalize TestService Bean");
    }

}
