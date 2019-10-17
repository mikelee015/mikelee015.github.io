/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

import com.mycompany.flooringmaster.dtos.StateTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */
public class StateTaxDaoInMemo implements StateTaxDao {

    List<StateTax> allTaxes = new ArrayList<>();

    public StateTaxDaoInMemo() {

        StateTax oh = new StateTax();
        oh.setState("OH");
        oh.setTaxRate(new BigDecimal("6.25"));
        allTaxes.add(oh);

        StateTax pa = new StateTax();
        pa.setState("PA");
        pa.setTaxRate(new BigDecimal("6.75"));
        allTaxes.add(pa);

        StateTax mi = new StateTax();
        mi.setState("MI");
        mi.setTaxRate(new BigDecimal("5.75"));
        allTaxes.add(mi);

        StateTax in = new StateTax();
        in.setState("IN");
        in.setTaxRate(new BigDecimal("6.00"));
        allTaxes.add(in);
    }

    @Override
    public List<StateTax> getTax() throws StateTaxDaoFileException {

        StateTax toAdd = new StateTax();
        toAdd.setState(allTaxes.get(0).getState());
        toAdd.setTaxRate(allTaxes.get(1).getTaxRate());
        allTaxes.add(toAdd);
        
        return allTaxes;
    }

//    @Override
//    public StateTax getTaxByState(String state) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
