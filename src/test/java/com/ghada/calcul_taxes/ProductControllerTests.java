package com.ghada.calcul_taxes;


import com.ghada.calcul_taxes.controller.ProductController;
import com.ghada.calcul_taxes.model.Country;
import com.ghada.calcul_taxes.model.Product;
import com.ghada.calcul_taxes.repository.ProductRepository;
import com.ghada.calcul_taxes.service.TaxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private TaxService taxService;

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product(1L, "Product 1", new BigDecimal("100.00"), Country.FRANCE);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":100.00,\"country\":\"FRANCE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.price").value(100.00))
                .andExpect(jsonPath("$.country").value("FRANCE"));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", new BigDecimal("100.00"), Country.FRANCE);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.price").value(100.00))
                .andExpect(jsonPath("$.country").value("FRANCE"));
    }

    @Test
    public void testGetFinalPrice() throws Exception {
        Product product1 = new Product(1L, "Product 1", new BigDecimal("100.00"), Country.FRANCE);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(taxService.getStrategy(Country.FRANCE)).thenReturn(product -> new BigDecimal("10.00"));

        mockMvc.perform(get("/products/1/price"))
                .andExpect(status().isOk())
                .andExpect(content().string("110.00"));
    }
}
