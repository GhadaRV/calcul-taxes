package com.ghada.calcul_taxes.service;

import com.ghada.calcul_taxes.model.Product;

import java.math.BigDecimal;


public class USTaxStrategy implements TaxStrategy {

    @Override
    public BigDecimal calculateTax(Product product) {
        return product.getPrice().multiply(new BigDecimal("0.15"));
    }
}
