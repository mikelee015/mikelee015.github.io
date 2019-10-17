/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrdersDaoFileImplTest {

    public OrdersDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        //1. delete any files that exist in the test directory
        //2. copy all files from the seed folder to the test folder

        Path testPath = Paths.get("TestOrders");
        Path seedPath = Paths.get("SeedOrders");

        File testFolder = testPath.toFile(); //this method allows to get all files in a folder.
        File seedFolder = seedPath.toFile();

        if (!testFolder.exists()) {
            testFolder.mkdir();
        }

        File[] testFiles = testFolder.listFiles();
        for (File testFile : testFiles) {
            testFile.delete();
        }

        File[] seedFiles = seedFolder.listFiles();
        for (File seedFile : seedFiles) {
            Files.copy(seedFile.toPath(), Paths.get(testPath.toString(), seedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrdersByDate method, of class OrdersDaoFileImpl.
     */
    @Test
    public void testGetOrdersByDateGolenPath() {

        try {
            //1.Arrange
            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            //2.Act
            List<Order> allOrders1 = toTest.getOrdersByDate(LocalDate.of(2000, 01, 15));

            //3. Assert
            assertEquals(3, allOrders1.size());

            Order o1 = allOrders1.get(0);

            assertEquals(0001, o1.getOrderId());
            assertEquals("Wise", o1.getCustName());
            assertEquals("OH", o1.getState());
            assertEquals(new BigDecimal("6.25"), o1.getTaxRate());
            assertEquals("Wood", o1.getProductType());
            assertEquals(new BigDecimal("5.15"), o1.getCostPSF());
            assertEquals(new BigDecimal("4.75"), o1.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o1.getArea());
            assertEquals(new BigDecimal("515.00"), o1.getTotalMatericalCost());
            assertEquals(new BigDecimal("475.00"), o1.getTotalLaborCost());
            assertEquals(new BigDecimal("61.88"), o1.getTotalTax());
            assertEquals(new BigDecimal("1051.88"), o1.getTotalCost());

            Order o2 = allOrders1.get(1);

            assertEquals(0002, o2.getOrderId());
            assertEquals("Mike", o2.getCustName());
            assertEquals("PA", o2.getState());
            assertEquals(new BigDecimal("6.75"), o2.getTaxRate());
            assertEquals("Carpet", o2.getProductType());
            assertEquals(new BigDecimal("2.25"), o2.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o2.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o2.getArea());
            assertEquals(new BigDecimal("225.00"), o2.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o2.getTotalLaborCost());
            assertEquals(new BigDecimal("29.36"), o2.getTotalTax());
            assertEquals(new BigDecimal("464.36"), o2.getTotalCost());

            Order o3 = allOrders1.get(2);

            assertEquals(0003, o3.getOrderId());
            assertEquals("Peggy", o3.getCustName());
            assertEquals("MI", o3.getState());
            assertEquals(new BigDecimal("5.75"), o3.getTaxRate());
            assertEquals("Tile", o3.getProductType());
            assertEquals(new BigDecimal("3.50"), o3.getCostPSF());
            assertEquals(new BigDecimal("4.15"), o3.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o3.getArea());
            assertEquals(new BigDecimal("350.00"), o3.getTotalMatericalCost());
            assertEquals(new BigDecimal("415.00"), o3.getTotalLaborCost());
            assertEquals(new BigDecimal("43.99"), o3.getTotalTax());
            assertEquals(new BigDecimal("808.99"), o3.getTotalCost());

            List<Order> allOrders2 = toTest.getOrdersByDate(LocalDate.of(2001, 02, 02));

            Order o4 = allOrders2.get(0);

            assertEquals(0001, o4.getOrderId());
            assertEquals("Sue", o4.getCustName());
            assertEquals("MI", o4.getState());
            assertEquals(new BigDecimal("5.75"), o4.getTaxRate());
            assertEquals("Tile", o4.getProductType());
            assertEquals(new BigDecimal("3.50"), o4.getCostPSF());
            assertEquals(new BigDecimal("4.15"), o4.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o4.getArea());
            assertEquals(new BigDecimal("350.00"), o4.getTotalMatericalCost());
            assertEquals(new BigDecimal("415.00"), o4.getTotalLaborCost());
            assertEquals(new BigDecimal("43.99"), o4.getTotalTax());
            assertEquals(new BigDecimal("808.99"), o4.getTotalCost());

            Order o5 = allOrders2.get(1);

            assertEquals(0002, o5.getOrderId());
            assertEquals("Heather", o5.getCustName());
            assertEquals("IN", o5.getState());
            assertEquals(new BigDecimal("6.00"), o5.getTaxRate());
            assertEquals("Laminate", o5.getProductType());
            assertEquals(new BigDecimal("1.75"), o5.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o5.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o5.getArea());
            assertEquals(new BigDecimal("175.00"), o5.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o5.getTotalLaborCost());
            assertEquals(new BigDecimal("23.10"), o5.getTotalTax());
            assertEquals(new BigDecimal("408.10"), o5.getTotalCost());

            List<Order> allOrders3 = toTest.getOrdersByDate(LocalDate.of(2018, 03, 03));

            Order o6 = allOrders3.get(0);

            assertEquals(0001, o6.getOrderId());
            assertEquals("PeggySue", o6.getCustName());
            assertEquals("MI", o6.getState());
            assertEquals(new BigDecimal("5.75"), o6.getTaxRate());
            assertEquals("Tile", o6.getProductType());
            assertEquals(new BigDecimal("3.50"), o6.getCostPSF());
            assertEquals(new BigDecimal("4.15"), o6.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o6.getArea());
            assertEquals(new BigDecimal("350.00"), o6.getTotalMatericalCost());
            assertEquals(new BigDecimal("415.00"), o6.getTotalLaborCost());
            assertEquals(new BigDecimal("43.99"), o6.getTotalTax());
            assertEquals(new BigDecimal("808.99"), o6.getTotalCost());

        } catch (OrderDaoException ex) {
            fail();
        }
    }

    @Test
    public void testGetOrdersByDateNoOrders() {
        try {
            //1.Act
            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            //2.Arrange
            List<Order> allOrders1 = toTest.getOrdersByDate(LocalDate.of(2019, 12, 31));

            //3.Assert
            assertEquals(0, allOrders1.size());
        } catch (OrderDaoException ex) {
            //you want this exception to be thrown.
        }
    }

    @Test
    public void testGetOrdersByDateNullDate() {
        try {
            Order toAdd = new Order();
            toAdd.setOrderDate(null);
            toAdd.setCustName("Ray Charles");
            toAdd.setState("IN");
            toAdd.setTaxRate(new BigDecimal("6.00"));
            toAdd.setProductType("Carpet");
            toAdd.setCostPSF(new BigDecimal("2.25"));
            toAdd.setLaborCostPSF(new BigDecimal("2.10"));
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getTotalMatericalCost();
            toAdd.getTotalLaborCost();
            toAdd.getTotalTax();
            toAdd.getTotalCost();

            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            List<Order> allOrders = toTest.getOrdersByDate(null);
            fail();
        } catch (OrderDaoException ex) {
            assertEquals("No orders with null date", ex.getMessage());
        }
    }

    @Test
    public void testAddOrderGoldenPath() {
        try {
            //1.Act
            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2000, 1, 15));
            toAdd.setCustName("Ray Charles");
            toAdd.setState("IN");
            toAdd.setTaxRate(new BigDecimal("6.00"));
            toAdd.setProductType("Carpet");
            toAdd.setCostPSF(new BigDecimal("2.25"));
            toAdd.setLaborCostPSF(new BigDecimal("2.10"));
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getTotalMatericalCost();
            toAdd.getTotalLaborCost();
            toAdd.getTotalTax();
            toAdd.getTotalCost();

            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            Order testOrder = toTest.addOrder(toAdd);

            //2.Arrange
            List<Order> orderstoCheck = toTest.getOrdersByDate(LocalDate.of(2000, 1, 15));

            //3.Assert
            assertEquals(4, orderstoCheck.size());

            Order o1 = orderstoCheck.get(0);
            assertEquals(0001, o1.getOrderId());
            assertEquals("Wise", o1.getCustName());
            assertEquals("OH", o1.getState());
            assertEquals(new BigDecimal("6.25"), o1.getTaxRate());
            assertEquals("Wood", o1.getProductType());
            assertEquals(new BigDecimal("5.15"), o1.getCostPSF());
            assertEquals(new BigDecimal("4.75"), o1.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o1.getArea());
            assertEquals(new BigDecimal("515.00"), o1.getTotalMatericalCost());
            assertEquals(new BigDecimal("475.00"), o1.getTotalLaborCost());
            assertEquals(new BigDecimal("61.88"), o1.getTotalTax());
            assertEquals(new BigDecimal("1051.88"), o1.getTotalCost());

            Order o2 = orderstoCheck.get(3);
            assertEquals(4, o2.getOrderId());
            assertEquals("Ray Charles", o2.getCustName());
            assertEquals("IN", o2.getState());
            assertEquals(new BigDecimal("6.00"), o2.getTaxRate());
            assertEquals("Carpet", o2.getProductType());
            assertEquals(new BigDecimal("2.25"), o2.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o2.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o2.getArea());
            assertEquals(new BigDecimal("225.00"), o2.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o2.getTotalLaborCost());
            assertEquals(new BigDecimal("26.10"), o2.getTotalTax());
            assertEquals(new BigDecimal("461.10"), o2.getTotalCost());

        } catch (OrderDaoException ex) {
            fail();
        }
    }

    @Test
    public void testAddOrderNullInput() {
        try {
            //1.Act

            Order toAdd = null;

            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            Order testOrder = toTest.addOrder(toAdd);
            fail();
        } catch (OrderDaoException ex) {
            assertEquals("Order does not exist", ex.getMessage());
        }

    }

    @Test
    public void testEditOrderGoldenPath() {
        try {
            Order editedOrder = new Order();
            editedOrder.setOrderId(1);
            editedOrder.setOrderDate(LocalDate.of(2000, 1, 15));
            editedOrder.setCustName("Martha");
            editedOrder.setState("IN");
            editedOrder.setTaxRate(new BigDecimal("6.00"));
            editedOrder.setProductType("Carpet");
            editedOrder.setCostPSF(new BigDecimal("2.25"));
            editedOrder.setLaborCostPSF(new BigDecimal("2.10"));
            editedOrder.setArea(new BigDecimal("100.00"));

            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");

            toTest.editOrder(editedOrder);
            List<Order> allOrders = toTest.getOrdersByDate(editedOrder.getOrderDate());

            Order toTestOrder = allOrders.get(2);
            assertEquals(1, toTestOrder.getOrderId());
            assertEquals("Martha", toTestOrder.getCustName());
            assertEquals("IN", toTestOrder.getState());
            assertEquals(new BigDecimal("6.00"), toTestOrder.getTaxRate());
            assertEquals("Carpet", toTestOrder.getProductType());
            assertEquals(new BigDecimal("2.25"), toTestOrder.getCostPSF());
            assertEquals(new BigDecimal("2.10"), toTestOrder.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), toTestOrder.getArea());
            assertEquals(new BigDecimal("225.00"), toTestOrder.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), toTestOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("26.10"), toTestOrder.getTotalTax());
            assertEquals(new BigDecimal("461.10"), toTestOrder.getTotalCost());

        } catch (OrderDaoException ex) {
            fail();
        }
    }

    @Test
    public void testEditNullOrder() {
        try {
            Order editedOrder = null;
            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");
            toTest.editOrder(editedOrder);
            fail();
        } catch (OrderDaoException ex) {
            assertEquals("Order does not exist", ex.getMessage());
        }
    }

    @Test
    public void testRemoveGoldenPath() {
        try {
            OrdersDaoFileImpl toTest = new OrdersDaoFileImpl("TestOrders");
            Order toRemove;
            LocalDate userDate = LocalDate.of(2000, 1, 15);
            int orderId = 3;

            toTest.removeOrder(userDate, orderId);

            List<Order> orderstoCheck = toTest.getOrdersByDate(LocalDate.of(2000, 1, 15));
            assertEquals(2, orderstoCheck.size());

            Order o1 = orderstoCheck.get(1);
            assertEquals(2, o1.getOrderId());
            assertEquals("Mike", o1.getCustName());
            assertEquals("PA", o1.getState());
            assertEquals(new BigDecimal("6.75"), o1.getTaxRate());
            assertEquals("Carpet", o1.getProductType());
            assertEquals(new BigDecimal("2.25"), o1.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o1.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o1.getArea());
            assertEquals(new BigDecimal("225.00"), o1.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o1.getTotalLaborCost());
            assertEquals(new BigDecimal("29.36"), o1.getTotalTax());
            assertEquals(new BigDecimal("464.36"), o1.getTotalCost());

        } catch (OrderDaoException ex) {
            fail();
        }
    }

}
