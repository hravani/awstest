package com.example.springboot.hr.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class Config {

	@Bean
	public AuditorAwareImpl auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	
}
