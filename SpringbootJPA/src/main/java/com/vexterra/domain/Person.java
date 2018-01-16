package com.vexterra.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name = "PERSON_ID")    
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String name;
	private int age;

    protected Person() {
    	// do nothing
    }

    public Person(String name, int age) {
    	this.name = name;
    	this.age = age;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + "," + this.getAge());
		return sb.toString();
	}
}
