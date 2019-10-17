/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.StateTax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class StateTaxDaoFileImpl implements StateTaxDao {

    String path;

    public StateTaxDaoFileImpl(String path) {
        this.path = path;
    }

    List<StateTax> allTaxes = new ArrayList<>();
    
    @Override
    public List<StateTax> getTax() throws StateTaxDaoFileException {
        //1. make file reader
        //2. get all states and their tax rates
        //3. return list of states with tax rates

        FileReader reader = null;
        try {

            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            scn.nextLine();// skips first line 
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] cells = line.split(",");

                StateTax toAdd = new StateTax();
                toAdd.setState(cells[0]);
                toAdd.setTaxRate(new BigDecimal(cells[1]));

                allTaxes.add(toAdd);
            }
        } catch (FileNotFoundException ex) {
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new StateTaxDaoFileException("Could not close file reader during getTax()", ex);
            }
        }
        return allTaxes;
    }

//    @Override
//    public StateTax getTaxByState(String state) {
//        
//        return allTaxes.stream().filter(i -> i.getState().equalsIgnoreCase(state)).findAny().orElse(null);
//        
//    }

    
    
}
