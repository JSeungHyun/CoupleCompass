package com.CoupleCompass;

import org.sitemesh.webapp.SiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoupleCompassApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoupleCompassApplication.class, args);
	}
}
