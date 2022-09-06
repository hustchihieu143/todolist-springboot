package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Person;
import com.example.demo.request.PersonRequest;

@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

  // public List<Person> getPerson() {
  // Query q = em.createNativeQuery("SELECT * FROM persons");
  // List<Person> persons = q.getResultList();
  // return persons;
  // }
  @Query(value = "SELECT p.* FROM persons p", nativeQuery = true)
  Optional<List<Person>> getListPerson();

  Page<Person> findAll(Pageable pageable);

  @Query(value = "select p.* from persons p where p.id = :person_id", nativeQuery = true)
  Person findPersonByPersonIdAndIsDelete(Long person_id);

  @Transactional
  @Modifying
  @Query(value = "update persons p set p.name = :name, p.age = :age where p.id = :person_id", nativeQuery = true)
  void updatePerson(Long person_id, String name, int age);

  void deleteById(Long id);

  List<Person> findAllByOrderByAgeDesc();

  Person save(PersonRequest newPerson);
}
