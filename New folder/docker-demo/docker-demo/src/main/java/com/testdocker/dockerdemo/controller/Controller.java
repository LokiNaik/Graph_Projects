package com.testdocker.dockerdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dock")
public class Controller {
	
	private final Logger Logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value = "/getName")
	public String getName() {
		Logger.info("Inside the getName API");
		return "Inside the getName API";
	}

}
