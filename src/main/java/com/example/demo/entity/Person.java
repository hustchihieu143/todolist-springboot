package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "persons")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
// nhom 1 cac thuoc tinh vao trong database, neu khong co thi moi key se la 1
// phan tu
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class Person extends BaseEntity {
  @Column(name = "name")
  public String name;

  @Column(name = "age")
  public int age;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private List<Product> products;

  public Person(@JsonProperty("name") String name, @JsonProperty("age") int age) {
    this.name = name;
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @JsonManagedReference
  public List<Product> getProducts() {
    return products;
  }
}
