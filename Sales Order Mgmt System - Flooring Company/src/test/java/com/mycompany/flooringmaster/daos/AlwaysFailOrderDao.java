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
public class AlwaysFailOrderDao implements OrdersDao {

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderDaoException {
        throw new OrderDaoException("ALWAYS FAIL DAO");
    }

    @Override
    public Order addOrder(Order orderToAdd) throws OrderDaoException {
        throw new OrderDaoException("ALWAYS FAIL DAO");
    }

    @Override
    public Order editOrder(Order editedOrder) throws OrderDaoException {
        throw new OrderDaoException("ALWAYS FAIL DAO");
    }

    @Override
    public void removeOrder(LocalDate userDate, int orderId) throws OrderDaoException {
        throw new OrderDaoException("ALWAYS FAIL DAO");
    }

}
