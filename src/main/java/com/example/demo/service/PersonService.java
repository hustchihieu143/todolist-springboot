package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entity.Person;
import com.example.demo.model.response.PersonResponse;

public interface PersonService {

  public Person createPerson(Person newPerson);

  public Person getPersonById(Long id);

  public List<Person> getAllPerson();

  public void updatePerson(Long id, Person newPerson);

  public void removePerson(Long id);

  public void updatePerson2(Long id, Person newPerson);

  public Page<Person> getAllPerson2(int page, int limit);

  public List<Person> findAllOrderByAgeAsc();
}
