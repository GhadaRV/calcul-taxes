package com.ghada.calcul_taxes.repository;

import com.ghada.calcul_taxes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
