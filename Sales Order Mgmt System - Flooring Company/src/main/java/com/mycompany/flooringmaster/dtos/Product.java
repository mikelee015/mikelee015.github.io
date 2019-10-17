/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dtos;

import java.math.BigDecimal;

/**
 *
 * @author mac
 */
public class Product {
    
    private String productType;
    private BigDecimal costPSF;
    private BigDecimal laborCostPSF;

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the costPSF
     */
    public BigDecimal getCostPSF() {
        return costPSF;
    }

    /**
     * @param costPSF the costPSF to set
     */
    public void setCostPSF(BigDecimal costPSF) {
        this.costPSF = costPSF;
    }

    /**
     * @return the laborCostPSF
     */
    public BigDecimal getLaborCostPSF() {
        return laborCostPSF;
    }

    /**
     * @param laborCostPSF the laborCostPSF to set
     */
    public void setLaborCostPSF(BigDecimal laborCostPSF) {
        this.laborCostPSF = laborCostPSF;
    }
    
}
