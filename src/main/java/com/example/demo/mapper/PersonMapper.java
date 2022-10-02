package com.example.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Person;
import com.example.demo.dto.request.PersonRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PersonMapper implements Mapper<Person> {
  public Person to(PersonRequest request) {
    Person person = new Person();
    BeanUtils.copyProperties(request, person);
    return person;
  }
}
