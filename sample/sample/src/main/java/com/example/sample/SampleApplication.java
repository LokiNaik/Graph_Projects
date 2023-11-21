package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SampleApplication {

	public static void main(String[] args) {
		ApplicationContext con = SpringApplication.run(SampleApplication.class, args);
	
	}

}
