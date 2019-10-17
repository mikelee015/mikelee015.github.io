/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.daos.OrdersDao;
import com.mycompany.flooringmaster.dtos.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mac
 */
public class OrdersDaoInMemo implements OrdersDao {

    List<Order> allOrders = new ArrayList<>();

    public OrdersDaoInMemo() {

        Order o1 = new Order();
        o1.setOrderId(0001);
        o1.setOrderDate(LocalDate.of(2000, 01, 05));
        o1.setCustName("John Smith");
        o1.setState("OH");
        o1.setTaxRate(new BigDecimal("6.25"));
        o1.setProductType("Carpet");
        o1.setCostPSF(new BigDecimal("2.25"));
        o1.setLaborCostPSF(new BigDecimal("2.10"));
        o1.setArea(new BigDecimal("100.00"));

        allOrders.add(o1);

        Order o2 = new Order();
        o2.setOrderId(0002);
        o2.setOrderDate(LocalDate.of(2000, 01, 05));
        o2.setCustName("Jane Doe");
        o2.setState("PA");
        o2.setTaxRate(new BigDecimal("6.75"));
        o2.setProductType("Tile");
        o2.setCostPSF(new BigDecimal("3.50"));
        o2.setLaborCostPSF(new BigDecimal("4.15"));
        o2.setArea(new BigDecimal("100.00"));

        allOrders.add(o2);

        Order o3 = new Order();
        o3.setOrderId(0003);
        o3.setOrderDate(LocalDate.of(2000, 01, 05));
        o3.setCustName("SpongeBob SquarePants");
        o3.setState("MI");
        o3.setTaxRate(new BigDecimal("5.75"));
        o3.setProductType("Wood");
        o3.setCostPSF(new BigDecimal("5.15"));
        o3.setLaborCostPSF(new BigDecimal("4.75"));
        o3.setArea(new BigDecimal("100.00"));

        allOrders.add(o3);

        Order o4 = new Order();
        o4.setOrderId(0001);
        o4.setOrderDate(LocalDate.of(2015, 02, 28));
        o4.setCustName("The Hulk");
        o4.setState("IN");
        o4.setTaxRate(new BigDecimal("6.00"));
        o4.setProductType("Laminate");
        o4.setCostPSF(new BigDecimal("1.75"));
        o4.setLaborCostPSF(new BigDecimal("2.10"));
        o4.setArea(new BigDecimal("100.00"));

        allOrders.add(o4);

        Order o5 = new Order();
        o5.setOrderId(0002);
        o5.setOrderDate(LocalDate.of(2015, 02, 28));
        o5.setCustName("Captain America");
        o5.setState("PA");
        o5.setTaxRate(new BigDecimal("6.75"));
        o5.setProductType("Carpet");
        o5.setCostPSF(new BigDecimal("2.25"));
        o5.setLaborCostPSF(new BigDecimal("2.10"));
        o5.setArea(new BigDecimal("100.00"));

        allOrders.add(o5);

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        List<Order> toReturn
                = allOrders
                        .stream()
                        .filter(i -> i.getOrderDate().equals(date))
                        .collect(Collectors.toList());
        return toReturn;

//1. change list to stream
//2. filter based on 'date'
//3. convert back to list
//4.return that list
    }

    @Override
    public Order addOrder(Order orderToAdd) {
        List<Order> ordersByDate = new ArrayList<>();
        ordersByDate = getOrdersByDate(orderToAdd.getOrderDate());

        int newOrderId = 0;
        for (Order toCheck : ordersByDate) {
            if (toCheck.getOrderId() > newOrderId) {
                newOrderId = toCheck.getOrderId();
            }
        }
        newOrderId++;
        orderToAdd.setOrderId(newOrderId);
        allOrders.add(orderToAdd);
        return orderToAdd;

    }

    @Override
    public Order editOrder(Order editedOrder) throws OrderDaoException {

        //1. get list of all orders by date.
        List<Order> ordersByDate = getOrdersByDate(editedOrder.getOrderDate());
        LocalDate date = editedOrder.getOrderDate();
        int matchingIndex = -1;
        for (int i = 0; i < ordersByDate.size(); i++) {
            Order toCheck = ordersByDate.get(i);
            if (toCheck.getOrderId() == editedOrder.getOrderId()) {
                matchingIndex = i;
                break;
            }
        }
        if (matchingIndex == -1) { //if you don't get a match.
            throw new OrderDaoException("ERROR: could not edit item with id " + editedOrder.getOrderId());
        }
        allOrders.remove(matchingIndex);
        allOrders.add(editedOrder);

        return editedOrder;
    }

    @Override
    public void removeOrder(LocalDate userDate, int orderId) throws OrderDaoException {
        
        Order toRemove;
        List<Order> getOrders = getOrdersByDate(userDate);
        toRemove = getOrders.stream()
                .filter(i -> i.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (toRemove == null) {
            throw new OrderDaoException("Could not find order with Id " + orderId);
        }
        allOrders.remove(toRemove);
    }

}
