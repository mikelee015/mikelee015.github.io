/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mac
 */
public interface OrdersDao {

    public List<Order> getOrdersByDate(LocalDate date) throws OrderDaoException;  //change input parameter to id

    public Order addOrder(Order orderToAdd) throws OrderDaoException;

    public Order editOrder(Order editedOrder) throws OrderDaoException;

    public void removeOrder(LocalDate userDate, int orderId) throws OrderDaoException ;

    
}
