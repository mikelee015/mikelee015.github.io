/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.ui;

import com.mycompany.flooringmaster.daos.OrderDaoException;
import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mac
 */
public class FloorViewEnglish implements FloorView {

    UserIO io;

    public FloorViewEnglish(UserIO io) {
        this.io = io;
    }

    @Override
    public MenuChoice displayMenuAndGetChoice() {
        MenuChoice userChoice;

        //1. display menu
        displayMenu();
        //2. get userchoice
        userChoice = getUserChoice();

        return userChoice;
    }

    @Override
    public void displayUnknownCommandError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showExitMessage() {
        io.print("Nice knowing you!");
    }

    private void displayMenu() {
        io.print("<< Flooring Program >>\n");
        io.print("[1] Display Orders\n");
        io.print("[2] Add an Order\n");
        io.print("[3] Edit an Order\n");
        io.print("[4] Remove an Order\n");
        io.print("[5] Exit\n");
    }

    private MenuChoice getUserChoice() {
        MenuChoice choice = MenuChoice.DisplayByDate;

        int userNum = io.readInt("Please select your choice: ", 1, 5);

        switch (userNum) {
            case 1:
                choice = MenuChoice.DisplayByDate;
                break;
            case 2:
                choice = MenuChoice.Add;
                break;
            case 3:
                choice = MenuChoice.Edit;
                break;
            case 4:
                choice = MenuChoice.Remove;
                break;
            case 5:
                choice = MenuChoice.Exit;
                break;
        }
        return choice;
    }

    @Override
    public void displayAllOrders(List<Order> allOrders) {
        //1. loop through all objects in our list.
        for (Order toPrint : allOrders) { //enhanced for loop = (declare variable and type : collection)
            //2. print details for each object.
            printDetails(toPrint);
        }
    }

    @Override
    public LocalDate getDate() {
        LocalDate toReturn = io.readDate("Please enter order date: ", LocalDate.of(1950, 01, 01), LocalDate.of(2100, 12, 31));
        return toReturn;
    }

    @Override
    public int getOrderId() {
        int toReturn = io.readInt("Enter order ID: ", 1, 100);
        return toReturn;
    }

    @Override
    public void displayErrorMsg(String message) {
        io.print("Error: " + message);
    }

    @Override
    public void printDetails(Order toPrint) {
        io.print("ID: " + toPrint.getOrderId() + "\n");
        io.print("Customer Name: " + toPrint.getCustName() + "\n");
        io.print("State: " + toPrint.getState() + "\n");
        io.print("State Tax Rate: " + toPrint.getTaxRate() + "\n");
        io.print("Product Type: " + toPrint.getProductType() + "\n");
        io.print("Area: " + toPrint.getArea() + "\n");
        io.print("Product Cost Per Sq. Ft.: " + toPrint.getCostPSF() + "\n");
        io.print("Product Labor Cost Per Sq. Ft.: " + toPrint.getLaborCostPSF() + "\n");
        io.print("Total Material Cost: " + toPrint.getTotalMatericalCost() + "\n");
        io.print("Total Labor Cost: " + toPrint.getTotalLaborCost() + "\n");
        io.print("Total Tax: " + toPrint.getTotalTax() + "\n");
        io.print("Total Order Cost: " + toPrint.getTotalCost() + "\n");
    }

    @Override
    //used for ADD()
    public Order getOrderInfo() { 
        io.print("=== Enter New Order Info ===\n");
        LocalDate orderDate = io.readDate("Enter Order Date: ", LocalDate.of(1950, 01, 01), LocalDate.of(2100, 12, 31));
        String custName = io.reqReadString("Enter Customer Name: ");
        String state = io.reqReadString("Enter State: ");
        String prodType = io.reqReadString("Enter Product Type: ");
        BigDecimal area = io.readBd("Enter Area: ");

        Order newOrder = new Order();
        newOrder.setOrderDate(orderDate);
        newOrder.setCustName(custName);
        newOrder.setState(state);
        newOrder.setProductType(prodType);
        newOrder.setArea(area);

        return newOrder;
    }

    @Override
    public void displayVerifiedOrder(Order verifiedOrder) {
        io.print("=== New Order Summary ===\n");
        io.print("Customer Name: " + verifiedOrder.getCustName() + "\n");
        io.print("State: " + verifiedOrder.getState() + "\n");
        io.print("Product Type: " + verifiedOrder.getProductType() + "\n");
        io.print("Area: " + verifiedOrder.getArea() + "\n");
    }

    @Override
    public String confirm() {
        String userPick = io.readString("ARE YOU SURE? (Enter: Y or N): ");
        return userPick;
    }

    @Override
    public Order allEditedOrderDetails(Order editOrder) {
        io.print("=== ORDER SUMMARY ===\n");
        io.print("Current Customer Name: " + editOrder.getCustName() + "\n");

        String newName = io.readString("Enter new customer name: ");
        if (newName == null || newName.isBlank()) {
            editOrder.setCustName(editOrder.getCustName());
        } else {
            editOrder.setCustName(newName);
        }

        io.print("Current State: " + editOrder.getState() + "\n");
        String newState = io.readString("Enter new state: ");
        if (newState == null || newState.isBlank()) {
            editOrder.setState(editOrder.getState());
        } else {
            editOrder.setState(newState);
        }

        io.print("Current Product Type: " + editOrder.getProductType() + "\n");
        String newProduct = io.readString("Enter new product type: ");
        if (newProduct == null || newProduct.isBlank()) {
            editOrder.setProductType(editOrder.getProductType());
        } else {
            editOrder.setProductType(newProduct);
        }

        io.print("Current Area: " + editOrder.getArea() + "\n");
        String newArea = io.readString("Enter new area: ");
        if (newArea == null || newArea.isBlank()) {
            editOrder.setArea(editOrder.getArea());
        } else {
            editOrder.setArea(new BigDecimal(newArea));
        }

        editOrder.getTotalMatericalCost();
        editOrder.getTotalLaborCost();
        editOrder.getTotalTax();
        editOrder.getTotalCost();

        return editOrder;
    }

    @Override
    public void displayOrderDetails(Order verifiedOrder) {
        io.print("=== New Order Added ===\n");
        io.print("Order Date: " + verifiedOrder.getOrderDate() + "\n");
        io.print("Customer Name: " + verifiedOrder.getCustName() + "\n");
        io.print("State: " + verifiedOrder.getState() + "\n");
        io.print("State Tax Rate: " + verifiedOrder.getTaxRate() + "\n");
        io.print("Product Type: " + verifiedOrder.getProductType() + "\n");
        io.print("Area: " + verifiedOrder.getArea() + "\n");
        io.print("Product Cost Per Sq. Ft.: " + verifiedOrder.getCostPSF() + "\n");
        io.print("Product Labor Cost Per Sq. Ft.: " + verifiedOrder.getLaborCostPSF() + "\n");
        io.print("Total Material Cost: " + verifiedOrder.getTotalMatericalCost() + "\n");
        io.print("Total Labor Cost: " + verifiedOrder.getTotalLaborCost() + "\n");
        io.print("Total Tax: " + verifiedOrder.getTotalTax() + "\n");
        io.print("Total Order Cost: " + verifiedOrder.getTotalCost() + "\n");
    }

}
