package com.springwebapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springwebapp.component.Blogger;
import com.springwebapp.repository.BloggerRepository;

@Service
public class BloggerServiceImpl implements BloggerService{

	private BloggerRepository bloggerRepo;
	
	@Autowired
	public void setBloggerRepo(BloggerRepository bloggerRepo) {
		this.bloggerRepo = bloggerRepo;
	}
	
	@Override
	public List<Blogger> getBloggers() {
		
		return bloggerRepo.findAll();
	}

}
