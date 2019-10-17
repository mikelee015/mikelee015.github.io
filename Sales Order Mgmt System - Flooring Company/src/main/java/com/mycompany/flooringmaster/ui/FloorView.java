/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.ui;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.StateTax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mac
 */
public interface FloorView {

    public MenuChoice displayMenuAndGetChoice();

    public void displayUnknownCommandError();

    public void showExitMessage();

    public void displayAllOrders(List<Order> allOrders);

    public LocalDate getDate();

    public void displayErrorMsg(String message);

    public Order getOrderInfo();

    public void displayVerifiedOrder(Order verifiedOrder);

    public String confirm();
    
    public int getOrderId();

    public Order allEditedOrderDetails(Order editOrder);
    
    public void printDetails(Order toPrint);

    public void displayOrderDetails(Order verifiedOrder);
   
}
