package com.springwebapp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements NumberService{

	
	@Override
	public List<List<Integer>> getNumbers1() {
		
		Random rand=new Random();
		List<List<Integer>> numbersList=new LinkedList<List<Integer>>();
		
		Set<Integer> numbers=new HashSet<>();
		while(numbers.size()<50)
			numbers.add(rand.nextInt(50)+1);
		List<Integer> numberses=new ArrayList<>(numbers);
		Collections.shuffle(numberses);
		for(int i=0; i<50; i+=5) {
			List<Integer> numnum=new ArrayList<>(numberses.subList(i, i+5));
			Collections.sort(numnum);
			numnum.addAll(getNumbers2());
		    numbersList.add(numnum);
		}
		return numbersList;
	}

	@Override
	public List<Integer> getNumbers2() {
		
		Random rand=new Random();
		
		Set<Integer> numbers=new HashSet<>();
		while(numbers.size()<2)
			numbers.add(rand.nextInt(10)+1);
		List<Integer> numberses=new ArrayList<>(numbers);
		
		return numberses;
		
	}

	@Override
	public List<List<Integer>> getNumbers3() {
		Random rand=new Random();
		List<List<Integer>> numbersList=new LinkedList<List<Integer>>();
		
		Set<Integer> numbers=new HashSet<>();
		while(numbers.size()<90)
			numbers.add(rand.nextInt(90)+1);
		List<Integer> numberses=new ArrayList<>(numbers);
		Collections.shuffle(numberses);
		for(int i=0; i<90; i+=5) {
			List<Integer> numnum=new ArrayList<>(numberses.subList(i, i+5));
			Collections.sort(numnum);
			
		    numbersList.add(numnum);
		}
		return numbersList;
	}
	
	

}
