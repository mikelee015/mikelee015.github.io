/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.StateTax;
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
public class StateTaxDaoFileImplTest {

    public StateTaxDaoFileImplTest() {
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
     * Test of getTax method, of class StateTaxDaoFileImpl.
     */
    @Test
    public void testGetTax() throws Exception {
        StateTaxDaoFileImpl toTest = new StateTaxDaoFileImpl("StateTaxes.txt");
        List<StateTax> allTaxes = toTest.getTax();

        assertEquals(4, allTaxes.size());

        StateTax t1 = allTaxes.get(0);
        assertEquals("OH", t1.getState());
        assertEquals(new BigDecimal("6.25"), t1.getTaxRate());

        StateTax t4 = allTaxes.get(3);
        assertEquals("IN", t4.getState());
        assertEquals(new BigDecimal("6.00"), t4.getTaxRate());
    }

    /**
     * Test of getTaxByState method, of class StateTaxDaoFileImpl.
     */
//    @Test
//    public void testGetTaxByState() {
//        StateTaxDaoFileImpl toTest = new StateTaxDaoFileImpl("StateTaxes.txt");
//        String state = "OH";
//        StateTax testTax1 = toTest.getTaxByState(state);
//        assertEquals(new BigDecimal("6.25"), testTax1.getTaxRate());
//
//        state = "IN";
//        StateTax testTax2 = toTest.getTaxByState(state);
//        assertEquals(new BigDecimal("6.00"), testTax2.getTaxRate());
//
//    }

}
