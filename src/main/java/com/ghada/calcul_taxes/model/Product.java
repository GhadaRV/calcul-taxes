package com.ghada.calcul_taxes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name must not be blank")
    private String name;
    @NotNull(message = "Price must not be null")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Price must not be null")
    private Country country;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, Country country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Country getCountry() {
        return country;
    }

}
