package com.testdocker.dockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.testdocker.dockerdemo")
public class DockerDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

}
