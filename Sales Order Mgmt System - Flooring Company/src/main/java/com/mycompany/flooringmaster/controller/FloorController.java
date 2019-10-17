/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.controller;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.service.FloorService;
import com.mycompany.flooringmaster.service.InvalidAreaException;
import com.mycompany.flooringmaster.service.InvalidProductException;
import com.mycompany.flooringmaster.service.InvalidStateException;
import com.mycompany.flooringmaster.service.OrderServiceException;
import com.mycompany.flooringmaster.service.ServiceException;
import com.mycompany.flooringmaster.service.InvalidDateException;
import com.mycompany.flooringmaster.service.InvalidIdException;
import com.mycompany.flooringmaster.ui.FloorView;
import com.mycompany.flooringmaster.ui.MenuChoice;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class FloorController {

    FloorView view;
    FloorService service;

    //Using a contructor that takes in the view makes the view variable not null anymore.
    public FloorController(FloorView view, FloorService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean userExit = false;
        while (!userExit) {
            try {
                MenuChoice userChoice = view.displayMenuAndGetChoice();
                switch (userChoice) {
                    case Add:
                        add();
                        break;
                    case DisplayByDate:
                        displayByDate();
                        break;
                    case Edit:
                        edit();
                        break;
                    case Remove:
                        remove();
                        break;
                    case Exit:
                        userExit = true;
                        break;
                    default:
                        view.displayUnknownCommandError();
                        break;
                }
            } catch (OrderServiceException | InvalidStateException | InvalidProductException | ServiceException | InvalidDateException | InvalidIdException | InvalidAreaException ex) {
                view.displayErrorMsg(ex.getMessage());
            }
        }

        view.showExitMessage();

    }

    private void add() throws InvalidStateException, InvalidProductException, ServiceException, InvalidDateException, InvalidIdException, InvalidAreaException {
        //1. get new order info.
        Order newOrder = view.getOrderInfo();
        //2. display all new order info.
        view.displayVerifiedOrder(newOrder);
        //3. ask if they want to commit.
        String commit = view.confirm();
        //4. write to file.
        Order addedOrder = service.addNewOrder(commit, newOrder);
        view.displayOrderDetails(addedOrder);
    }

    private void displayByDate() throws OrderServiceException, InvalidDateException {
        LocalDate userDate = view.getDate();

        List<Order> allOrders = service.getOrdersByDate(userDate);

        view.displayAllOrders(allOrders);
    }

    private void edit() throws OrderServiceException, ServiceException, InvalidDateException, InvalidIdException, InvalidStateException, InvalidProductException, InvalidAreaException {
        //1. get order date and orderID 
        LocalDate userDate = view.getDate();
        int orderId = view.getOrderId();

        //2. use getOrdersByDate to get orders by date.
        List<Order> allOrdersByDate = service.getOrdersByDate(userDate);

        //3. get order by ID from the list above.
        Order editedOrder = service.getOrderById(userDate, orderId);

        //4. display order fields to edit.
        view.allEditedOrderDetails(editedOrder);

        //5. verify and then persist those orders.
        service.editOrder(editedOrder);

        //6. display edited order.
        view.printDetails(editedOrder);
    }

    private void remove() throws OrderServiceException, ServiceException, InvalidDateException, InvalidIdException {
        LocalDate userDate = view.getDate();
        int orderId = view.getOrderId();
        Order toRemove = service.getOrderById(userDate, orderId);
        if (toRemove == null) {
            throw new ServiceException ("Order does not exist. \n ");
        }
        view.printDetails(toRemove);
        String commit = view.confirm();
        service.removeOrder(commit, userDate, orderId);
    }

}
