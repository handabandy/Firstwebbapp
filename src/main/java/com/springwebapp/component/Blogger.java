package com.springwebapp.component;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Blogger {
	
	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private String username;
	@OneToMany(mappedBy = "blogger")
	private List<Story> stories=new ArrayList<>();
	
	private Blogger(){
		
	}
	
	public Blogger getBlogger()
	{
		return new Blogger();
	}
	
	public Blogger(String name, String username){
		this.name = name;
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	

}