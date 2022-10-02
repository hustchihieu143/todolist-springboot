package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/")
  public List<Product> getProducts() {
    return productService.getProducts();
  }

  @PostMapping("/create")
  public BaseResponse<Product> createProduct(@RequestBody Product product) {
    Product newProduct = productService.createProduct(product);
    return BaseResponse.ofSuccess(newProduct);
  }

  @GetMapping("/{id}")
  public BaseResponse<Product> getProductById(@PathVariable("id") Long id) {
    Product product = productService.findById(id);
    return BaseResponse.ofSuccess(product);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<String> deleteProductById(@PathVariable("id") Long id) {
    productService.removeProduct(id);
    return BaseResponse.ofSuccess("Deleted");
  }

  @PutMapping("/{id}")
  public BaseResponse<String> updateProductById(@PathVariable("id") Long id, @RequestBody Product product) {
    productService.updateProduct2(id, product);
    return BaseResponse.ofSuccess("Updated");
  }

  @GetMapping("/find-by-person_id/{id}")
  public BaseResponse<Product> findByPersonId(@PathVariable("id") Long id) {
    Product product = productService.findByPerson_Id(id);
    return BaseResponse.ofSuccess(product);
  }
}
