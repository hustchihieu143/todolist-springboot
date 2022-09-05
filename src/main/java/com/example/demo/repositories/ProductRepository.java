package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT p.* FROM products p", nativeQuery = true)
  List<Product> getProducts();

  @Query(value = "SELECT p.* from products p where p.id = :id", nativeQuery = true)
  Product getProductById(Long id);

  void deleteById(Long id);

  Product findByPerson_Id(Long id);
}
