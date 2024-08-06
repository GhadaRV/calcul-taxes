package com.ghada.calcul_taxes.controller;

import com.ghada.calcul_taxes.model.Product;
import com.ghada.calcul_taxes.repository.ProductRepository;
import com.ghada.calcul_taxes.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxService taxService;

    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must not be empty");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be greater than zero");
        }

        if (product.getCountry() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country must be specified");
        }

        try {
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully with ID: " + savedProduct.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while saving the product", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getFinalPrice(@PathVariable Long id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Product product = productOptional.get();
            BigDecimal tax = taxService.getStrategy(product.getCountry()).calculateTax(product);
            BigDecimal finalPrice = product.getPrice().add(tax);
            return ResponseEntity.ok(finalPrice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while calculating the final price", e);
        }
    }
}