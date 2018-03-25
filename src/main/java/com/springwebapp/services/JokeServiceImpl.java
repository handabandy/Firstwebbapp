package com.springwebapp.services;

import org.springframework.stereotype.Service;

import com.springwebapp.component.ChuckNorrisQuotes;


@Service
public class JokeServiceImpl implements JokeService{

	private final ChuckNorrisQuotes chuckNorrisQuotes;
	
	
	public JokeServiceImpl(ChuckNorrisQuotes chuckNorrisQuotes) {
		
		this.chuckNorrisQuotes = chuckNorrisQuotes;
	}

	@Override
	public String getJoke() {
		
		return chuckNorrisQuotes.getRandomQuote();
	}
	

	
}
