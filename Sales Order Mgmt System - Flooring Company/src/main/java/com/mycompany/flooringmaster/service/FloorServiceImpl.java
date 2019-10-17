/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

import com.mycompany.flooringmaster.controller.FloorController;
import com.mycompany.flooringmaster.daos.OrderDaoException;
import com.mycompany.flooringmaster.daos.OrderDaoException;
import com.mycompany.flooringmaster.daos.OrdersDao;
import com.mycompany.flooringmaster.daos.OrdersDaoFileImpl;
import com.mycompany.flooringmaster.daos.ProductDao;
import com.mycompany.flooringmaster.daos.ProductDaoException;
import com.mycompany.flooringmaster.daos.ProductDaoFileImpl;
import com.mycompany.flooringmaster.daos.StateTaxDao;
import com.mycompany.flooringmaster.daos.StateTaxDaoFileException;
import com.mycompany.flooringmaster.daos.StateTaxDaoFileImpl;
import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.Product;
import com.mycompany.flooringmaster.dtos.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class FloorServiceImpl implements FloorService {

    OrdersDao orderDao;
    StateTaxDao taxDao;
    ProductDao productDao;

    public FloorServiceImpl(OrdersDao orderDao, StateTaxDao taxDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.taxDao = taxDao;
        this.productDao = productDao;

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate userDate) throws InvalidDateException, OrderServiceException {

        if(userDate.isBefore(LocalDate.of(1950, 1, 1)) || userDate.isAfter(LocalDate.of(2099, 12, 31))) {
            throw new InvalidDateException("Cannot retrieve orders with date:  " + userDate);
        }
        
        //1. Get all orders from the dao.
        List<Order> allOrders = null;
        try {
            allOrders = orderDao.getOrdersByDate(userDate);
        } catch ( OrderDaoException ex) {
            throw new OrderServiceException("Could not read all orders from dao.", ex);
        } 

        //2. Return those orders.
        return allOrders;
    }

    private Order verifyOrderInfo(Order userOrder) throws InvalidStateException, InvalidProductException, ServiceException, InvalidAreaException {
        try {
            if (userOrder.getOrderDate() == null) {
                throw new ServiceException ("Order date was null.\n");
            }
            
            if (userOrder.getArea().compareTo(BigDecimal.ZERO) == -1) {
                throw new InvalidAreaException("Area cannot be negative. \n");
            }
            
            List<StateTax> taxes = taxDao.getTax();
            boolean match1 = false;
            for (StateTax toCheck : taxes) {
                if (toCheck.getState().equals(userOrder.getState())) {
                    userOrder.setTaxRate(toCheck.getTaxRate());
                    match1 = true;
                    break;
                }
            }
            if (!match1) {
                throw new InvalidStateException("State not supported. \n");
            }
            List<Product> product = productDao.getCosts();
            boolean match2 = false;
            for (Product toCheckProd : product) {
                if (toCheckProd.getProductType().equals(userOrder.getProductType())) {
                    userOrder.setCostPSF(toCheckProd.getCostPSF());
                    match2 = true;
                    break;
                }
            }
            if (!match2) {
                throw new InvalidProductException("Product not supported. \n");
            }
            List<Product> products = productDao.getCosts();
            boolean match3 = false;
            for (Product toCheckProduct : product) {
                if (toCheckProduct.getProductType().equals(userOrder.getProductType())) {
                    userOrder.setLaborCostPSF(toCheckProduct.getLaborCostPSF());
                    match3 = true;
                    break;
                }
            }
            if (!match3) {
                throw new InvalidProductException("Product not supported. \n");
            }
        } catch (StateTaxDaoFileException | ProductDaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return userOrder;
    }

    @Override
    public Order addNewOrder(String commit, Order orderToAdd) throws ServiceException, InvalidStateException, InvalidProductException, InvalidDateException, InvalidIdException, InvalidAreaException {
        Order toReturn = null;
        if (commit.equals("Y")) {
            if(orderToAdd.getOrderDate() == null || orderToAdd.getOrderDate().isBefore(LocalDate.now())) {
                throw new InvalidDateException("Cannot add order with date in past. \n");
            }
            if (orderToAdd.getArea().compareTo(BigDecimal.ZERO) == -1) {
                throw new InvalidAreaException("Area cannot be negative. \n");
            }
            try {
                orderToAdd = verifyOrderInfo(orderToAdd);
                toReturn = orderDao.addOrder(orderToAdd);
            } catch (OrderDaoException ex) {
                throw new ServiceException("Unable to add order. \n");
            }
        }
        return toReturn;
    }

    @Override
    public Order getOrderById(LocalDate orderDate, int orderId) throws OrderServiceException, InvalidDateException, InvalidIdException {
        Order byId;
        if (orderDate == null) {
            throw new OrderServiceException("Order date was null.");
        }
        if (orderId <= 0) {
            throw new OrderServiceException("ID too low.");
        }
        try {
            List<Order> allOrders = orderDao.getOrdersByDate(orderDate);
            byId = allOrders.stream()
                    .filter(i -> i.getOrderId() == orderId)
                    .findFirst()
                    .orElse(null);
        } catch (OrderDaoException ex) {
            throw new OrderServiceException("Could not load orders", ex);
        }
        return byId;
    }

    @Override
    public Order editOrder(Order editedOrder) throws InvalidStateException, InvalidProductException, ServiceException, InvalidAreaException {

        try {
            //1. verify order details
            editedOrder = verifyOrderInfo(editedOrder);

            //2. if verified, send to dao to be written.
            editedOrder = orderDao.editOrder(editedOrder);
            
            return editedOrder;
        } catch (OrderDaoException ex) {
            throw new ServiceException("Unable to edit order. \n", ex);
        }
    }

    @Override
    public void removeOrder(String commit, LocalDate userDate, int orderId) throws ServiceException, InvalidDateException, InvalidIdException {
        if(commit.equals("Y")) {
        try {    
            orderDao.removeOrder(userDate, orderId);
        } catch (OrderDaoException ex) {
            throw new ServiceException ("Could not load order.");
        }
        }
    }

}
