package com.vexterra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vexterra.domain.Person;
import com.vexterra.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repo;
	
	public Person addPerson(Person person) {
		return repo.save(person);
	}
	
	  public List<Person> getAllPerson(){
		  return repo.findAll();
	  }
	
}
