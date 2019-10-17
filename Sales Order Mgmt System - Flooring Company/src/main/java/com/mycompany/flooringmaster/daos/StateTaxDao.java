/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.StateTax;
import java.util.List;

/**
 *
 * @author mac
 */
public interface StateTaxDao {

    public List<StateTax> getTax() throws StateTaxDaoFileException;
    
//    public StateTax getTaxByState(String state);
    
    
}
