package com.springwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableConfigurationProperties
@PropertySources({
    @PropertySource(value = "file:${app1conf}", ignoreResourceNotFound = true)
})
@SpringBootApplication
public class Spring5webapp2Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Spring5webapp2Application.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		  return application.sources(Spring5webapp2Application.class);
	    }
}
