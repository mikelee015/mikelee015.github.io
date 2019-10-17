/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

import com.mycompany.flooringmaster.dtos.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mac
 */
public interface FloorService {

    public List<Order> getOrdersByDate(LocalDate userDate) throws InvalidDateException, OrderServiceException ;

    //public Order verifyOrderInfo(Order userOrder) throws InvalidStateException, InvalidProductException, ServiceException;
    
    public Order addNewOrder(String commit, Order orderToAdd) throws ServiceException, InvalidStateException, InvalidProductException, InvalidDateException, InvalidIdException, InvalidAreaException;

    public Order getOrderById(LocalDate orderDate, int orderId) throws OrderServiceException, InvalidDateException, InvalidIdException ;

    public Order editOrder(Order editedOrder) throws InvalidStateException, InvalidProductException, ServiceException, InvalidAreaException;

    public void removeOrder(String commit, LocalDate userDate, int orderId) throws ServiceException, InvalidDateException, InvalidIdException  ;


    
}
