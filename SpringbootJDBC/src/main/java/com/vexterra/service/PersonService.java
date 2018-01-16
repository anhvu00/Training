package com.vexterra.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vexterra.domain.Person;

@Service
public class PersonService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int addPerson(Person person) {
	    String sql = "INSERT INTO person(name,age) VALUES(?,?)";
	    int retval = jdbcTemplate.update(sql, person.getName(), person.getAge());
	    return retval;
	}
	
	  public List<Person> getAllPerson(){
		    return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>(){

		      public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		        Person p = new Person();
		        p.setAge(rs.getInt("age"));
		        p.setName(rs.getString("name"));
		        return p;
		      }

		    });
		  }	
	
}
