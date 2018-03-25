package com.springwebapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springwebapp.component.Story;

public interface StoryRepository extends CrudRepository<Story, Long> {

	List<Story> findAllByOrderById();
	Story findFirstByOrderByPostedDesc();
	Story findByTitle(String title);
}
