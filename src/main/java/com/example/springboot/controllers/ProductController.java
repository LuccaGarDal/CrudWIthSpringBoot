package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.saveProduct(productRecordDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts () {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct (@PathVariable(value = "id")UUID id) {
        return productService.getOneProduct(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct (@PathVariable (value="id") UUID id,
                                                       @RequestBody ProductRecordDto productRecordDto) {
        return productService.updateProduct(id, productRecordDto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct (@PathVariable (value="id") UUID id) {
        return productService.deleteProduct(id);
    }
}
