package com.ghada.calcul_taxes;

import com.ghada.calcul_taxes.model.Country;
import com.ghada.calcul_taxes.model.Product;
import com.ghada.calcul_taxes.service.CanadaTaxStrategy;
import com.ghada.calcul_taxes.service.FranceTaxStrategy;
import com.ghada.calcul_taxes.service.TaxStrategy;
import com.ghada.calcul_taxes.service.USTaxStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxStrategyTests {

    @Test
    public void testUsTaxStrategy() {
        Product product = new Product(1L, "US Product", new BigDecimal("100.00"), Country.US);
        TaxStrategy strategy = new USTaxStrategy();
        BigDecimal tax = strategy.calculateTax(product);
        assertEquals(new BigDecimal("15.0000"), tax);
    }

    @Test
    public void testCanadaTaxStrategy() {
        Product product = new Product(1L, "Canadian Product", new BigDecimal("100.00"), Country.CANADA);
        TaxStrategy strategy = new CanadaTaxStrategy();
        BigDecimal tax = strategy.calculateTax(product);
        assertEquals(new BigDecimal("8.0000"), tax);
    }

    @Test
    public void testFranceTaxStrategy() {
        Product product = new Product(1L, "French Product", new BigDecimal("100.00"), Country.FRANCE);
        TaxStrategy strategy = new FranceTaxStrategy();
        BigDecimal tax = strategy.calculateTax(product);
        assertEquals(new BigDecimal("10.000"), tax);
    }
}
