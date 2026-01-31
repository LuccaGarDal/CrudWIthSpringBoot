package com.example.springboot.service;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDto dto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(dto, productModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productRepository.save(productModel));
    }

    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> listProducts = productRepository.findAll();

        listProducts.forEach(product ->
                product.add(
                        linkTo(methodOn(ProductController.class)
                                .getOneProduct(product.getIdProduct()))
                                .withSelfRel()
                )
        );

        return ResponseEntity.ok(listProducts);
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        Optional<ProductModel> productModel = productRepository.findById(id);

        if (productModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        productModel.get().add(
                linkTo(methodOn(ProductController.class)
                        .getAllProducts())
                        .withRel("Products List")
        );

        return ResponseEntity.ok(productModel.get());
    }

    public ResponseEntity<Object> updateProduct(UUID id, ProductRecordDto dto) {
        Optional<ProductModel> productModel = productRepository.findById(id);

        if (productModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        BeanUtils.copyProperties(dto, productModel.get());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productRepository.save(productModel.get()));
    }

    public ResponseEntity<Object> deleteProduct(UUID id) {
        Optional<ProductModel> productModel = productRepository.findById(id);

        if (productModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        productRepository.delete(productModel.get());
        return ResponseEntity.ok("Product deleted successfully");
    }
}
