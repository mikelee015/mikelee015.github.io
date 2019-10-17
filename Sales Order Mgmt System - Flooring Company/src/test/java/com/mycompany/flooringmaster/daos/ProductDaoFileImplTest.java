/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mac
 */
public class ProductDaoFileImplTest {

    public ProductDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getCosts method, of class ProductDaoFileImpl.
     */
    @Test
    public void testGetCostsGoldenPath() throws Exception {
        ProductDaoFileImpl toTest = new ProductDaoFileImpl("Products.txt");
        List<Product> allProducts = toTest.getCosts();

        assertEquals(4, allProducts.size());

        Product p1 = allProducts.get(0);
        assertEquals("Carpet", p1.getProductType());
        assertEquals(new BigDecimal("2.25"), p1.getCostPSF());
        assertEquals(new BigDecimal("2.10"), p1.getLaborCostPSF());

        Product p4 = allProducts.get(3);
        assertEquals("Wood", p4.getProductType());
        assertEquals(new BigDecimal("5.15"), p4.getCostPSF());
        assertEquals(new BigDecimal("4.75"), p4.getLaborCostPSF());
    }

}
