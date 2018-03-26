package com.springwebapp.services;

import java.util.Date;
import java.util.List;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springwebapp.component.Blogger;
import com.springwebapp.component.Story;
import com.springwebapp.repository.BloggerRepository;
import com.springwebapp.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService{
	
	private StoryRepository storyRepository;
	private BloggerRepository bloggerReepository;

	@Autowired
	public void setBloggerReepository(BloggerRepository bloggerReepository) {
		this.bloggerReepository = bloggerReepository;
	}

	public StoryServiceImpl(Story story) {
		super();
	}
	
	@Autowired
	public void setStoryRepository(StoryRepository storyRepository) {
		this.storyRepository = storyRepository;
	}

	@Override
	public List<Story> getStories() {
	
		return storyRepository.findAllByOrderById();
	}

	@Override
	public Story getStory() {
		
		return storyRepository.findFirstByOrderByPostedDesc();
	}
	
	@Override
	public Story getSpecifiedStory(String title)
	{
		if (storyRepository.findByTitle(title)!=null) {
			return storyRepository.findByTitle(title);
		}
		else
			return getSpecialStory();
	}
	
	//@PostConstruct
	public void init()
	{
		Blogger blogger=new Blogger("Fikás Frodó", "fikasfrodo");
		bloggerReepository.save(blogger);
		
		Story story=new Story("Frodó sztorija", "Frodó tegnap bebaszott mint az állat!",
				blogger, new Date());
		storyRepository.save(story);
		
		Story teszt=new Story("teszt", "Tegnap nem történt semmi.", blogger, new Date());
		storyRepository.save(teszt);
	}

	@Override
	public Story getSpecialStory() {
		Blogger blogger=new Blogger("", "");
		Story teszt=new Story("HIBA!", "NINCS ILYEN CÍMMEL BLOGBEJEGYZÉS", blogger, new Date());
		return teszt;
	}

}
