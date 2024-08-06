package com.ghada.calcul_taxes.service;


import com.ghada.calcul_taxes.model.Product;

import java.math.BigDecimal;

public interface TaxStrategy {
    BigDecimal calculateTax(Product product);
}
