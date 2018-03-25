package com.springwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springwebapp.component.ChuckNorrisQuotes;

@Configuration
public class ChuckConfig {

	 @Bean
	    public ChuckNorrisQuotes chuckNorrisQuotes(){
	        return new ChuckNorrisQuotes();
	    }
}
