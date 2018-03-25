package com.springwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.springwebapp.component.Story;

@Configuration
public class StoriesConfig {
	
	@Bean
	public Story stories()
	{
		return Story.getStory();
	}

}
