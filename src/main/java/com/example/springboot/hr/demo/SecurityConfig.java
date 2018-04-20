package com.example.springboot.hr.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

@EnableWebSecurity
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and().csrf()
			.disable()
			.requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
			.authorizeRequests()
			.antMatchers("/api/version").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.authenticationEntryPoint(authEntryPoint);
		
		/*
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/index").permitAll()		
				.antMatchers("/user/**").hasRole("USER")			
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");
		*/	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("hiten").password("{noop}password").roles("USER")
				.and()
				.withUser("tham").password("{noop}password").roles("USER")
				;
	}
}
