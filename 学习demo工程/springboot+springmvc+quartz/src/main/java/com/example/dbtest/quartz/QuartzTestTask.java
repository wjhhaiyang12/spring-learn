package com.example.dbtest.quartz;


import com.example.dbtest.model.AutowiredTest;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class QuartzTestTask{

    @Autowired
    @Qualifier(value="AutowiredTestWutianze")
    private AutowiredTest autowiredTest;

    private static int counter = 0;

    public void execute()  {
        System.out.println(new Date() + " " + autowiredTest.getTestName());
    }

    public AutowiredTest getAutowiredTest() {
        return autowiredTest;
    }

    public void setAutowiredTest(AutowiredTest autowiredTest) {
        this.autowiredTest = autowiredTest;
    }
}
