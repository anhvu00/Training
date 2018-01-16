package com.vexterra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vexterra.domain.Person;
import com.vexterra.service.PersonService;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(MainApp.class);

	@Autowired
	PersonService personService;

	public void run(String... args) {
		Person adam = new Person("Adam", 2000);
		personService.addPerson(adam);
		
		Person eve = new Person("Eve", 1999);
		personService.addPerson(eve);

		for (Person p : personService.getAllPerson()) {
			logger.info(p.toString());
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
