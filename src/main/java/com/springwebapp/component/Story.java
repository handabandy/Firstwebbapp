package com.springwebapp.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="stories")
public class Story {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="cím")
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	@ManyToOne
	private Blogger blogger;
	private Date posted;
	
	
	public Story(String title, String content, Blogger blogger, Date posted) {
		super();
		this.title = title;
		this.content = content;
		this.blogger = blogger;
		this.posted = posted;
	}
	
	private Story()
	{}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public static Story getStory()
	{
		return new Story();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}
	
	public List<Story> getStories()
	{
		Story elso=new Story();
		Story masod=new Story();
		List<Story> stories = new ArrayList<Story>();
		
		elso.setTitle("Elsö Sztorim");
		elso.setBlogger(new Blogger("Budi Rudi","budirudi"));
		elso.setPosted(new Date());
		elso.setContent("Budi Rudi elsö története, melyben nem mesél el semmit.");
		
		masod.setBlogger(new Blogger("Bithünkö Thünkö","bithunkoo"));
		masod.setContent("Thünkö története nem igazán izgalmas.");
		masod.setPosted(new Date());
		masod.setTitle("Thünkö története");
		
		stories.add(elso);
		stories.add(masod);
		
		return stories;
	}
	
	public Story getStory(String title)
	{
		Story elso=new Story();
		
		elso.setTitle("Elsö Sztorim");
		elso.setBlogger(new Blogger("Budi Rudi","budirudi"));
		elso.setPosted(new Date());
		elso.setContent("Budi Rudi elsö története, melyben nem mesél el semmit.");
		
		return elso;
	}
	
	

}
