package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.PersonService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping("/")
  @ResponseBody
  public List<Person> getPerson() {
    List<Person> persons = this.personService.getAllPerson();
    return persons;
  }

  @PostMapping("/create")
  public BaseResponse<Person> createPerson(@RequestBody Person person) {
    Person newPerson = personService.createPerson(person);
    return BaseResponse.ofSuccess(newPerson);
  }

  @GetMapping("/find/{id}")
  public BaseResponse<Person> getPersonById(@PathVariable("id") Long id) {
    Person person = personService.getPersonById(id);
    return BaseResponse.ofSuccess(person);
  }

  @PutMapping("/update/{id}")
  public BaseResponse<String> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
    personService.updatePerson2(id, person);
    return BaseResponse.ofSuccess("Updated");
  }

  @DeleteMapping("/delete/{id}")
  public BaseResponse<String> deletePerson(@PathVariable("id") Long id) {
    personService.removePerson(id);
    return BaseResponse.ofSuccess("Deleted");
  }

  @GetMapping("/get-all-persons-paginate")
  public Page<Person> getAllPerson(@RequestParam(required = false) int page,
      @RequestParam(required = false) int limit) {
    return personService.getAllPerson2(page, limit);
  }

  @GetMapping("/sort-by-age")
  public List<Person> findAllOrderByAgeAsc() {
    return personService.findAllOrderByAgeAsc();
  }

}
