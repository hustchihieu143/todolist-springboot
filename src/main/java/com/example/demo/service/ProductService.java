package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {
  public Product createProduct(Product product);

  public List<Product> getProducts();

  public void removeProduct(Long id);

  public Product findById(Long id);

  public void updateProduct2(Long id, Product product);
}
