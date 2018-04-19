package com.example.springboot.hr.demo.misc;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Value("${app.version}")
	private String version;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm:ss a");

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDateTime() {
		return LocalDateTime.now().format(formatter);
	}

	
	public static void main(String[] args) {
		VersionInfo info = new VersionInfo();
		System.out.println(info.getDateTime());
	}
	
}
