package com.example.demo.service.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.entity.Product;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final PersonRepository personRepository;

  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  @Transactional
  public List<Product> getProducts() {
    List<Product> products = productRepository.getProducts();
    return products;
  }

  @Override
  public void removeProduct(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public Product findById(Long id) {
    System.out.println(id);
    return productRepository.getProductById(id);
  }

  @Override
  public void updateProduct2(Long id, Product newProduct) {
    Product product = productRepository.getProductById(id);
    if (newProduct.getName() != null) {
      product.setName(newProduct.getName());
    }
    if (newProduct.getPrice() != 0) {
      product.setPrice(newProduct.getPrice());
    }
    if (newProduct.getPerson() != null && newProduct.getPerson().getId() != 0) {
      Person person = personRepository.findPersonByPersonIdAndIsDelete(newProduct.getPerson().getId());
      product.setPerson(person);
    }
    productRepository.save(product);
  }

}
