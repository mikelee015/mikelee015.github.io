/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.Product;
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
public class ProductDaoFileImpl implements ProductDao {

    String path;

    public ProductDaoFileImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Product> getCosts() throws ProductDaoException {

        List<Product> allProducts = new ArrayList<>();
        FileReader reader = null;
        try {
            //1. make file reader
            //2. get product types, materialCostPSF and laborCostPSF
            //3. return list of all costs

            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            scn.nextLine();// skips first line 
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] cells = line.split(",");

                Product toAdd = new Product();
                toAdd.setProductType(cells[0]);
                toAdd.setCostPSF(new BigDecimal(cells[1]));
                toAdd.setLaborCostPSF(new BigDecimal(cells[2]));

                allProducts.add(toAdd);
            }
        } catch (FileNotFoundException ex) {
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new ProductDaoException("Could not close file reader during getCosts()", ex);
            }
        }
        return allProducts;
    }

//    @Override
//    public BigDecimal getProductCostPSF(String product) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public BigDecimal getProductLaborCostPSF(String product) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }


    
}
