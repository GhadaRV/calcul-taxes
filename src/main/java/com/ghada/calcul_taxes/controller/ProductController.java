package com.ghada.calcul_taxes.controller;

import com.ghada.calcul_taxes.model.Product;
import com.ghada.calcul_taxes.repository.ProductRepository;
import com.ghada.calcul_taxes.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxService taxService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<BigDecimal> getFinalPrice(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            BigDecimal tax = taxService.getStrategy(product.getCountry()).calculateTax(product);
            BigDecimal finalPrice = product.getPrice().add(tax);
            return ResponseEntity.ok(finalPrice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

