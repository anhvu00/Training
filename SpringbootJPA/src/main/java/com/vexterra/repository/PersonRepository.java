package com.vexterra.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vexterra.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	List<Person> findAll();
}
