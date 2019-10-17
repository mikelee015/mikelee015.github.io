/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.Order;
import com.mycompany.flooringmaster.dtos.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mac
 */
public interface ProductDao {
    
    public List<Product> getCosts() throws ProductDaoException; 

//    public BigDecimal getProductCostPSF(String product);
//    
//    public BigDecimal getProductLaborCostPSF(String product);
}
