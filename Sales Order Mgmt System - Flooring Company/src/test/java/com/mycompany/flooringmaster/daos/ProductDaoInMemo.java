/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class ProductDaoInMemo implements ProductDao {

    List<Product> allProducts = new ArrayList<>();
    
    public ProductDaoInMemo() {
        Product carpet = new Product();
        carpet.setProductType("Carpet");
        carpet.setCostPSF(new BigDecimal("2.25"));
        carpet.setLaborCostPSF(new BigDecimal("2.10"));
        allProducts.add(carpet);

        Product laminate = new Product();
        laminate.setProductType("Laminate");
        laminate.setCostPSF(new BigDecimal("1.75"));
        laminate.setLaborCostPSF(new BigDecimal("2.10"));
        allProducts.add(laminate);

        Product tile = new Product();
        tile.setProductType("Tile");
        tile.setCostPSF(new BigDecimal("3.50"));
        tile.setLaborCostPSF(new BigDecimal("4.15"));
        allProducts.add(tile);

        Product wood = new Product();
        wood.setProductType("Wood");
        wood.setCostPSF(new BigDecimal("5.15"));
        wood.setLaborCostPSF(new BigDecimal("4.75"));
        allProducts.add(wood);
    }

    @Override
    public List<Product> getCosts() throws ProductDaoException {
        
         Product toAdd = new Product();
         toAdd.setProductType(allProducts.get(0).getProductType());
         toAdd.setCostPSF(allProducts.get(0).getCostPSF());
         toAdd.setLaborCostPSF(allProducts.get(0).getLaborCostPSF());
         
         allProducts.add(toAdd);
        
         return allProducts;
    }

    
    
}
