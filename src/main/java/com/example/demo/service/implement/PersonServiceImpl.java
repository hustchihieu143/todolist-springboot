package com.example.demo.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.request.PersonRequest;
import com.example.demo.response.PersonResponse;
import com.example.demo.service.PersonService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @Override
  public Person createPerson(PersonRequest newPerson) {
    Person person = personMapper.to(newPerson);
    return personRepository.save(person);

  }

  @Override
  public Person getPersonById(Long id) {
    Person person = personRepository.findPersonByPersonIdAndIsDelete(id);
    return person;
  }

  @Override
  @Transactional
  public List<PersonResponse> getAllPerson() {
    List<Person> list = personRepository.getListPerson()
        .orElseThrow(() -> new IllegalStateException("person not found"));
    return list.stream().map(person -> {
      PersonResponse personResponse = new PersonResponse();
      personResponse.setId(person.getId());
      personResponse.setName(person.getName());
      personResponse.setAge(person.getAge());
      return personResponse;
    }).collect(Collectors.toList());
  }

  @Override
  public void updatePerson(Long id, Person newPerson) {
    System.out.println(newPerson.name);
    System.out.println(id);
    System.out.println(newPerson.age);
    personRepository.updatePerson(id, newPerson.name, newPerson.age);
  }

  @Override
  public void removePerson(Long id) {
    personRepository.deleteById(id);
  }

  @Override
  public void updatePerson2(Long id, Person newPerson) {
    Person person = personRepository.findPersonByPersonIdAndIsDelete(id);
    System.out.println(newPerson.getName());
    if (newPerson.name != null) {
      person.name = newPerson.name;
    }
    if (newPerson.age != 0) {
      person.age = newPerson.age;
    }
    personRepository.save(person);
  }

  @Override
  public Page<Person> getAllPerson2(int page, int limit) {
    Page<Person> persons = personRepository
        .findAll(PageRequest.of(page, limit, Sort.by("age").descending().by("name").ascending()));
    return persons;
  }

  @Override
  public List<Person> findAllOrderByAgeAsc() {
    return personRepository.findAllByOrderByAgeDesc();
  }

}
