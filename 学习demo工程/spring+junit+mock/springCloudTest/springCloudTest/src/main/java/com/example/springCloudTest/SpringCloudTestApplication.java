package com.example.springCloudTest;

import com.example.springCloudTest.Listeners.MyApplicationEnvironmentPreparedEventListener;
import com.example.springCloudTest.Listeners.MyApplicationFailedEventListener;
import com.example.springCloudTest.Listeners.MyApplicationPreparedEventListener;
import com.example.springCloudTest.Listeners.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudTestApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringCloudTestApplication.class);
		app.addListeners(new MyApplicationStartedEventListener());
		app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
		app.addListeners(new MyApplicationPreparedEventListener());
		app.addListeners(new MyApplicationFailedEventListener());
		app.run(args);
	}
}
