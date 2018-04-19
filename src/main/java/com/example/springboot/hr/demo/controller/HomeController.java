package com.example.springboot.hr.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String index() {
		log.debug("index()");
		return "index.html";
	}
	
}
