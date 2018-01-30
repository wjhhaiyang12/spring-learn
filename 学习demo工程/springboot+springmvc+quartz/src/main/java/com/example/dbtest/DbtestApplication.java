package com.example.dbtest;

import com.example.dbtest.model.AutowiredTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:myConfigration.xml"})
public class DbtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbtestApplication.class, args);
	}
}
