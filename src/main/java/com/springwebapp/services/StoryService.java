package com.springwebapp.services;

import java.util.List;

import com.springwebapp.component.Story;

public interface StoryService {

	List<Story> getStories();
	Story getStory();
	Story getSpecifiedStory(String title);
	Story getSpecialStory();
}
