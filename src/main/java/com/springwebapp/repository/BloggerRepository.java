package com.springwebapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springwebapp.component.Blogger;

public interface BloggerRepository extends CrudRepository<Blogger, Long> {

	List<Blogger> findAll();
}
