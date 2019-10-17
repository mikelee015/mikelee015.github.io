/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.service.FloorService;
import com.mycompany.flooringmaster.service.ServiceException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class OrdersDaoFileImpl implements OrdersDao {

    String folder;
//    String path;
//    FloorService service;

    public OrdersDaoFileImpl(String folder) {
        this.folder = folder; //folder name is defined in applicationContext.xml, i.e., ProductionOrders

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws OrderDaoException {
        if (date == null) {
            throw new OrderDaoException("No orders with null date");
        }

        List<Order> allOrders = new ArrayList<>();
        FileReader reader = null;
        try {
            String filePath = buildPath(date); //calls the full path including the folder and file name. - see line 77.
            reader = new FileReader(filePath);
            Scanner scn = new Scanner(reader);
            scn.nextLine();// skips first line 

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] cells = line.split(",");

                Order toAdd = new Order();
                toAdd.setOrderDate(date);
                toAdd.setOrderId(Integer.parseInt(cells[0]));
                toAdd.setCustName(cells[1]);
                toAdd.setState(cells[2]);
                toAdd.setTaxRate(new BigDecimal(cells[3]));
                toAdd.setProductType(cells[4]);
                toAdd.setArea(new BigDecimal(cells[5]));
                toAdd.setCostPSF(new BigDecimal(cells[6]));
                toAdd.setLaborCostPSF(new BigDecimal(cells[7]));
                //totalMatericalCost called on from the view bc we have a getter that computes it.
                //totalLaborCost = same as above.
                //totalTax = same as above.
                //totalCost = same as above.
                allOrders.add(toAdd);
            }
        } catch (FileNotFoundException ex) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    throw new OrderDaoException("Could not close file reader during getOrdersByDate()");
                }
            }
        }
        return allOrders;
    }

    private String buildPath(LocalDate date) { //this method gets the folder name with the file name + date.
        String path = Paths.get(folder, buildFileName(date)).toString();
        return path;
    }

    private String buildFileName(LocalDate date) { //this method builds the file name.
        String toReturn = "Orders_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        return toReturn;
    }

    @Override
    public Order addOrder(Order orderToAdd) throws OrderDaoException {
        if (orderToAdd == null) {
            throw new OrderDaoException("Order does not exist");
        }

        //1. Check if order file w. date already exists, if not, create new file in orders directory.
        List<Order> allOrders = getOrdersByDate(orderToAdd.getOrderDate());

        //2. get date of orderToAdd
        LocalDate date = orderToAdd.getOrderDate();

        //3.. Add order to orders text file. (Add ID # - need to increase by 1 based on last order #.)
        int newOrderId = 0;
        for (Order toCheck : allOrders) {
            if (toCheck.getOrderId() > newOrderId) {
                newOrderId = toCheck.getOrderId();
            }
        }
        newOrderId++;
        orderToAdd.setOrderId(newOrderId);
        allOrders.add(orderToAdd);
        writeFile(allOrders, date);
        return orderToAdd;
    }

    private void writeFile(List<Order> allOrders, LocalDate date) throws OrderDaoException {
        //1. get date
        //2. check if order file already exists. If so, write to that file.
        //3. if not, create a new file and write to that file.
        String path = buildPath(date);
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);
            pw.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost,PerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            for (Order toWrite : allOrders) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new OrderDaoException("Could not write to file " + path);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                throw new OrderDaoException("Could not close file reader during writeFile()");
            }
        }
    }

    private String convertToLine(Order toWrite) {
        String line
                = toWrite.getOrderId() + ","
                + toWrite.getCustName() + ","
                + toWrite.getState() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getCostPSF() + ","
                + toWrite.getLaborCostPSF() + ","
                + toWrite.getTotalMatericalCost() + ","
                + toWrite.getTotalLaborCost() + ","
                + toWrite.getTotalTax() + ","
                + toWrite.getTotalCost();
        return line;
    }

    @Override
    public Order editOrder(Order editedOrder) throws OrderDaoException {
        if (editedOrder == null) {
            throw new OrderDaoException("Order does not exist");
        }

        try {
            //1. get list of all orders by date.
            List<Order> allOrders = getOrdersByDate(editedOrder.getOrderDate());
            LocalDate date = editedOrder.getOrderDate();
            int matchingIndex = -1;
            for (int i = 0; i < allOrders.size(); i++) {
                Order toCheck = allOrders.get(i);
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

            //2. write to file.
            writeFile(allOrders, date);

        } catch (OrderDaoException ex) {
            throw new OrderDaoException("ERROR: Could not write edited order to file.");
        }
        return editedOrder;
    }

    @Override
    public void removeOrder(LocalDate userDate, int orderId) throws OrderDaoException {
        if (userDate == null) {
            throw new OrderDaoException("Order date cannot be null.");
        }
        if (orderId <= 0) {
            throw new OrderDaoException("OrderID too low.");
        }

        Order toRemove;
        List<Order> allOrders = getOrdersByDate(userDate);
        toRemove = allOrders.stream()
                .filter(i -> i.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (toRemove == null) {
            throw new OrderDaoException("Could not find order with Id " + orderId);
        }

        allOrders.remove(toRemove);
        writeFile(allOrders, userDate);

    }

}
