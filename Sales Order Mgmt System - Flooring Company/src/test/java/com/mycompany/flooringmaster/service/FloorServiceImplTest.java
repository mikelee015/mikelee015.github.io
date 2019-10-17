/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

import com.mycompany.flooringmaster.daos.AlwaysFailOrderDao;
import com.mycompany.flooringmaster.daos.OrderDaoException;
import com.mycompany.flooringmaster.daos.OrderDaoException;
import com.mycompany.flooringmaster.daos.OrdersDao;
import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.daos.OrdersDaoInMemo;
import com.mycompany.flooringmaster.daos.ProductDao;
import com.mycompany.flooringmaster.daos.ProductDaoFileImpl;
import com.mycompany.flooringmaster.daos.ProductDaoInMemo;
import com.mycompany.flooringmaster.daos.StateTaxDao;
import com.mycompany.flooringmaster.daos.StateTaxDaoFileImpl;
import com.mycompany.flooringmaster.daos.StateTaxDaoInMemo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
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
public class FloorServiceImplTest {

    public FloorServiceImplTest() {
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
     * Test of getOrdersByDate method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrdersByDateGoldenPath() {

        try {
            //1. Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2. Act - call the method.
            //List of all orders placed on 1/5/2000
            List<Order> allOrders1 = toTest.getOrdersByDate(LocalDate.of(2000, 01, 05));

            //3. Assert
            assertEquals(3, allOrders1.size());

            //Test all fields of all orders placed on 1/5/2000
            Order o1 = allOrders1.get(0);

            assertEquals(0001, o1.getOrderId());
            assertEquals(LocalDate.of(2000, 01, 05), o1.getOrderDate());
            assertEquals("John Smith", o1.getCustName());
            assertEquals("OH", o1.getState());
            assertEquals(new BigDecimal("6.25"), o1.getTaxRate());
            assertEquals("Carpet", o1.getProductType());
            assertEquals(new BigDecimal("2.25"), o1.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o1.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o1.getArea());
            assertEquals(new BigDecimal("225.00"), o1.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o1.getTotalLaborCost());
            assertEquals(new BigDecimal("27.19"), o1.getTotalTax());
            assertEquals(new BigDecimal("462.19"), o1.getTotalCost());

            Order o2 = allOrders1.get(1);

            assertEquals(0002, o2.getOrderId());
            assertEquals(LocalDate.of(2000, 01, 05), o2.getOrderDate());
            assertEquals("Jane Doe", o2.getCustName());
            assertEquals("PA", o2.getState());
            assertEquals(new BigDecimal("6.75"), o2.getTaxRate());
            assertEquals("Tile", o2.getProductType());
            assertEquals(new BigDecimal("3.50"), o2.getCostPSF());
            assertEquals(new BigDecimal("4.15"), o2.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o2.getArea());
            assertEquals(new BigDecimal("350.00"), o2.getTotalMatericalCost());
            assertEquals(new BigDecimal("415.00"), o2.getTotalLaborCost());
            assertEquals(new BigDecimal("51.64"), o2.getTotalTax());
            assertEquals(new BigDecimal("816.64"), o2.getTotalCost());

            Order o3 = allOrders1.get(2);

            assertEquals(0003, o3.getOrderId());
            assertEquals(LocalDate.of(2000, 01, 05), o3.getOrderDate());
            assertEquals("SpongeBob SquarePants", o3.getCustName());
            assertEquals("MI", o3.getState());
            assertEquals(new BigDecimal("5.75"), o3.getTaxRate());
            assertEquals("Wood", o3.getProductType());
            assertEquals(new BigDecimal("5.15"), o3.getCostPSF());
            assertEquals(new BigDecimal("4.75"), o3.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o3.getArea());
            assertEquals(new BigDecimal("515.00"), o3.getTotalMatericalCost());
            assertEquals(new BigDecimal("475.00"), o3.getTotalLaborCost());
            assertEquals(new BigDecimal("56.93"), o3.getTotalTax());
            assertEquals(new BigDecimal("1046.93"), o3.getTotalCost());

            //List of all orders placed on 2/28/2015
            List<Order> allOrders2 = toTest.getOrdersByDate(LocalDate.of(2015, 02, 28));
            assertEquals(2, allOrders2.size());

            Order o4 = allOrders2.get(0);

            assertEquals(0001, o4.getOrderId());
            assertEquals(LocalDate.of(2015, 02, 28), o4.getOrderDate());
            assertEquals("The Hulk", o4.getCustName());
            assertEquals("IN", o4.getState());
            assertEquals(new BigDecimal("6.00"), o4.getTaxRate());
            assertEquals("Laminate", o4.getProductType());
            assertEquals(new BigDecimal("1.75"), o4.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o4.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o4.getArea());
            assertEquals(new BigDecimal("175.00"), o4.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o4.getTotalLaborCost());
            assertEquals(new BigDecimal("23.10"), o4.getTotalTax());
            assertEquals(new BigDecimal("408.10"), o4.getTotalCost());

            Order o5 = allOrders2.get(1);

            assertEquals(0002, o5.getOrderId());
            assertEquals(LocalDate.of(2015, 02, 28), o5.getOrderDate());
            assertEquals("Captain America", o5.getCustName());
            assertEquals("PA", o5.getState());
            assertEquals(new BigDecimal("6.75"), o5.getTaxRate());
            assertEquals("Carpet", o5.getProductType());
            assertEquals(new BigDecimal("2.25"), o5.getCostPSF());
            assertEquals(new BigDecimal("2.10"), o5.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o5.getArea());
            assertEquals(new BigDecimal("225.00"), o5.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), o5.getTotalLaborCost());
            assertEquals(new BigDecimal("29.36"), o5.getTotalTax());
            assertEquals(new BigDecimal("464.36"), o5.getTotalCost());
        } catch (InvalidDateException | OrderServiceException ex) {
            fail();
        }
    }

    @Test
    public void testGetOrdersByInvalidDate() {
        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);
            //2.Act
            List<Order> allOrders = toTest.getOrdersByDate(LocalDate.of(2000, 01, 04));
            //3.Assert
            assertEquals(0, allOrders.size());

        } catch (InvalidDateException | OrderServiceException ex) {
            fail(ex.getMessage()); //the test fails.
        }
    }

    @Test
    public void testGetOrdersByDateGBadDao() {

        try {
            //1. Arrange
            OrdersDao ordersDao = new AlwaysFailOrderDao();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2. Act - call the method.
            //List of all orders placed on 1/5/2000
            List<Order> allOrders1 = toTest.getOrdersByDate(LocalDate.of(2000, 01, 05));
            fail();
        } catch (InvalidDateException | OrderServiceException ex) {
        }

    }

    @Test
    public void addNewOrderGoldenPath() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";

            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2020, 02, 28));
            toAdd.setCustName("Ironman");
            toAdd.setState("IN");
            toAdd.getTaxRate();
            toAdd.setProductType("Carpet");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);

            //3.Assert
            assertEquals(1, orderToAdd.getOrderId());
            assertEquals(LocalDate.of(2020, 02, 28), orderToAdd.getOrderDate());
            assertEquals("Ironman", orderToAdd.getCustName());
            assertEquals("IN", orderToAdd.getState());
            assertEquals(new BigDecimal("6.00"), orderToAdd.getTaxRate());
            assertEquals("Carpet", orderToAdd.getProductType());
            assertEquals(new BigDecimal("2.25"), orderToAdd.getCostPSF());
            assertEquals(new BigDecimal("2.10"), orderToAdd.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), orderToAdd.getArea());
            assertEquals(new BigDecimal("225.00"), orderToAdd.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), orderToAdd.getTotalLaborCost());
            assertEquals(new BigDecimal("26.10"), orderToAdd.getTotalTax());
            assertEquals(new BigDecimal("461.10"), orderToAdd.getTotalCost());

        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidDateException | InvalidIdException | InvalidAreaException ex) {
            fail(ex.getMessage());
        }

    }

    @Test
    public void addNewOrderDateInPast() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";

            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2000, 1, 10));
            toAdd.setCustName("Ironman");
            toAdd.setState("IN");
            toAdd.getTaxRate();
            toAdd.setProductType("Carpet");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);
            fail();

        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidIdException | InvalidAreaException ex) {
            fail();
        } catch (InvalidDateException ex) {

        }
    }

    @Test
    public void addNewOrderNullDate() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";

            Order toAdd = new Order();
            toAdd.setOrderDate(null);
            toAdd.setCustName("Ironman");
            toAdd.setState("IN");
            toAdd.getTaxRate();
            toAdd.setProductType("Carpet");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);
            fail();

        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidIdException | InvalidAreaException ex) {
            fail();
        } catch (InvalidDateException ex) {
            
        }
    }

    @Test
    public void addNewOrderInvalidState() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";

            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2020, 02, 28));
            toAdd.setCustName("Ironman");
            toAdd.setState("IL");
            toAdd.getTaxRate();
            toAdd.setProductType("Carpet");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);
            fail();
        } catch (ServiceException | InvalidProductException | InvalidDateException | InvalidIdException | InvalidAreaException ex) {
            fail(ex.getMessage());
        } catch (InvalidStateException ex) {

        }
    }

    @Test
    public void addNewOrderInvalidProductType() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";
            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2020, 02, 28));
            toAdd.setCustName("Ironman");
            toAdd.setState("IN");
            toAdd.getTaxRate();
            toAdd.setProductType("Concrete");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);
            fail();
        } catch (ServiceException | InvalidStateException | InvalidDateException | InvalidIdException | InvalidAreaException ex) {
            fail(ex.getMessage());
        } catch (InvalidProductException ex) {
        }

    }

    @Test
    public void addNewOrderBadDao() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new AlwaysFailOrderDao();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            //2.Act
            String commit = "Y";

            Order toAdd = new Order();
            toAdd.setOrderDate(LocalDate.of(2015, 02, 28));
            toAdd.setCustName("Ironman");
            toAdd.setState("IN");
            toAdd.getTaxRate();
            toAdd.setProductType("Carpet");
            toAdd.setArea(new BigDecimal("100.00"));
            toAdd.getCostPSF();
            toAdd.getLaborCostPSF();
            Order orderToAdd = toTest.addNewOrder(commit, toAdd);
            fail();

            //3.Assert
            assertEquals(3, orderToAdd.getOrderId());
            assertEquals(LocalDate.of(2015, 02, 28), orderToAdd.getOrderDate());
            assertEquals("Ironman", orderToAdd.getCustName());
            assertEquals("IN", orderToAdd.getState());
            assertEquals(new BigDecimal("6.00"), orderToAdd.getTaxRate());
            assertEquals("Carpet", orderToAdd.getProductType());
            assertEquals(new BigDecimal("2.25"), orderToAdd.getCostPSF());
            assertEquals(new BigDecimal("2.10"), orderToAdd.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), orderToAdd.getArea());
            assertEquals(new BigDecimal("225.00"), orderToAdd.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), orderToAdd.getTotalLaborCost());
            assertEquals(new BigDecimal("26.10"), orderToAdd.getTotalTax());
            assertEquals(new BigDecimal("461.10"), orderToAdd.getTotalCost());
            fail();
        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidDateException | InvalidIdException | InvalidAreaException ex) {
        }

    }

    @Test
    public void getOrderByIdGoldenPath() {

        try {
            //1.Arrange
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2000, 01, 05);
            int orderId = 3;

            //2. Act
            List<Order> allOrders = toTest.getOrdersByDate(orderDate);
            byId = allOrders.stream()
                    .filter(i -> i.getOrderId() == orderId)
                    .findFirst()
                    .orElse(null);

            //3. Assert
            Order o1 = allOrders.get(2);

            assertEquals(0003, o1.getOrderId());
            assertEquals(LocalDate.of(2000, 01, 05), o1.getOrderDate());
            assertEquals("SpongeBob SquarePants", o1.getCustName());
            assertEquals("MI", o1.getState());
            assertEquals(new BigDecimal("5.75"), o1.getTaxRate());
            assertEquals("Wood", o1.getProductType());
            assertEquals(new BigDecimal("5.15"), o1.getCostPSF());
            assertEquals(new BigDecimal("4.75"), o1.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), o1.getArea());
            assertEquals(new BigDecimal("515.00"), o1.getTotalMatericalCost());
            assertEquals(new BigDecimal("475.00"), o1.getTotalLaborCost());
            assertEquals(new BigDecimal("56.93"), o1.getTotalTax());
            assertEquals(new BigDecimal("1046.93"), o1.getTotalCost());

        } catch (OrderServiceException | InvalidDateException ex) {
            fail();
        } 
    }

    @Test
    public void getOrderByIdInvalidId() {
        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2000, 01, 05);
            int orderId = 17;
            List<Order> allOrders = toTest.getOrdersByDate(orderDate);
            byId = allOrders.stream()
                    .filter(i -> i.getOrderId() == orderId)
                    .findFirst()
                    .orElse(null);

            assertNull(byId);

        } catch (InvalidDateException | OrderServiceException ex) {
        }
    }

    @Test
    public void getOrderByIdInvalidDate() {
        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(1949, 01, 02);
            int orderId = 1;
            List<Order> allOrders = toTest.getOrdersByDate(orderDate);
            fail();

        } catch (InvalidDateException | OrderServiceException ex) {

        }
    }

    @Test
    public void getOrderByIdBadDao() {
        try {
            //1.Arrange
            OrdersDao ordersDao = new AlwaysFailOrderDao();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2000, 01, 05);
            int orderId = 3;

            //2. Act
            List<Order> allOrders = toTest.getOrdersByDate(orderDate);
            fail();

        } catch (InvalidDateException | OrderServiceException ex) {

        }
    }

    @Test
    public void testEditGoldenPath() {

        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            Order eOrder = new Order();

            eOrder.setOrderDate(orderDate);
            eOrder.setOrderId(orderId);
            eOrder.setCustName("The She Hulk");
            eOrder.setState("IN");
            eOrder.setProductType("Laminate");
            eOrder.setArea(new BigDecimal("100.00"));

            eOrder = toTest.editOrder(eOrder);

            assertEquals(1, eOrder.getOrderId());
            assertEquals("The She Hulk", eOrder.getCustName());
            assertEquals(new BigDecimal("6.00"), eOrder.getTaxRate());
            assertEquals("Laminate", eOrder.getProductType());
            assertEquals(new BigDecimal("1.75"), eOrder.getCostPSF());
            assertEquals(new BigDecimal("2.10"), eOrder.getLaborCostPSF());
            assertEquals(new BigDecimal("100.00"), eOrder.getArea());
            assertEquals(new BigDecimal("175.00"), eOrder.getTotalMatericalCost());
            assertEquals(new BigDecimal("210.00"), eOrder.getTotalLaborCost());
            assertEquals(new BigDecimal("23.10"), eOrder.getTotalTax());
            assertEquals(new BigDecimal("408.10"), eOrder.getTotalCost());
        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidAreaException ex) {
            fail();
        }
    }

    @Test
    public void testEditInvalidState() {
        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            Order eOrder = new Order();

            eOrder.setOrderDate(orderDate);
            eOrder.setOrderId(orderId);
            eOrder.setCustName("The She Hulk");
            eOrder.setState("MA");
            eOrder.setProductType("Laminate");
            eOrder.setArea(new BigDecimal("100.00"));

            toTest.editOrder(eOrder);
            fail();

        } catch (InvalidStateException ex) {

        } catch (ServiceException | InvalidProductException | InvalidAreaException ex) {
            fail();
        }
    }

    @Test
    public void testEditInvalidProductType() {
        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            Order eOrder = new Order();

            eOrder.setOrderDate(orderDate);
            eOrder.setOrderId(orderId);
            eOrder.setCustName("The She Hulk");
            eOrder.setState("IN");
            eOrder.setProductType("Steel");
            eOrder.setArea(new BigDecimal("100.00"));

            eOrder = toTest.editOrder(eOrder);
            fail();

        } catch (ServiceException | InvalidStateException | InvalidAreaException ex) {
            fail();
        } catch (InvalidProductException ex) {
        }
    }

    @Test
    public void testEditBadDao() {

        try {
            OrdersDao ordersDao = new AlwaysFailOrderDao();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            Order eOrder = new Order();

            eOrder.setOrderDate(orderDate);
            eOrder.setOrderId(orderId);
            eOrder.setCustName("The She Hulk");
            eOrder.setState("IN");
            eOrder.setProductType("Laminate");
            eOrder.setArea(new BigDecimal("100.00"));

            toTest.editOrder(eOrder);
            fail();

        } catch (ServiceException | InvalidStateException | InvalidProductException | InvalidAreaException ex) {

        }
    }

    @Test
    public void testRemoveGoldenPath() {

        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            String commit = "Y";

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            toTest.removeOrder(commit, orderDate, orderId);
            List<Order> allOrders = toTest.getOrdersByDate(orderDate);

            assertEquals(1, allOrders.size());

        } catch (ServiceException | InvalidDateException | InvalidIdException | OrderServiceException ex) {
            fail();
        }

    }

    @Test
    public void testRemoveInvalidDate() {

        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            String commit = "Y";

            Order byId;
            LocalDate orderDate = LocalDate.of(2020, 01, 02);
            int orderId = 1;

            toTest.removeOrder(commit, orderDate, orderId);
            fail();
        } catch (ServiceException | InvalidIdException ex) {

        } catch (InvalidDateException ex) {
            fail();
        }
    }

    @Test
    public void testRemoveInvalidId() {

        try {
            OrdersDao ordersDao = new OrdersDaoInMemo();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            String commit = "Y";

            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 19;

            toTest.removeOrder(commit, orderDate, orderId);
            fail();
        } catch (ServiceException | InvalidDateException ex) {

        } catch (InvalidIdException ex) {
            fail();
        }

    }

    @Test
    public void testRemoveBadDao() {

        try {
            OrdersDao ordersDao = new AlwaysFailOrderDao();
            ProductDao productDao = new ProductDaoInMemo();
            StateTaxDao stateTaxDao = new StateTaxDaoInMemo();
            FloorService toTest = new FloorServiceImpl(ordersDao, stateTaxDao, productDao);

            String commit = "Y";

            Order byId;
            LocalDate orderDate = LocalDate.of(2015, 02, 28);
            int orderId = 1;

            toTest.removeOrder(commit, orderDate, orderId);
            fail();
        } catch (ServiceException | InvalidDateException | InvalidIdException ex) {

        }

    }

}
