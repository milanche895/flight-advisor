package com.test.htec.filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsStopConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
	     registry.addMapping("/**")
         .allowedOrigins("*")
         .allowCredentials(false)
         .maxAge(3600)
         .allowedHeaders("Accept", "Content-Type", "Origin", 
        		 "Authorization", "X-Auth-Token")
         .exposedHeaders("X-Auth-Token", "Authorization")
         .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS", "PATCH");
	}
	
}