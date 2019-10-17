/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author mac
 */
public class Order {

    private int orderId;
    private LocalDate orderDate;
    private String custName;

    private String state;
    private BigDecimal taxRate;

    private String productType;
    private BigDecimal area;
    private BigDecimal costPSF;
    private BigDecimal laborCostPSF;

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

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

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the totalCostPSF
     */
    public BigDecimal getTotalMatericalCost() {
        BigDecimal totalMaterialCost = new BigDecimal("0.00");

        totalMaterialCost = area.multiply(costPSF).setScale(2, RoundingMode.HALF_UP);

        return totalMaterialCost;
    }

    /**
     * @return the totalLaborCostPSF
     */
    public BigDecimal getTotalLaborCost() {
        BigDecimal totalLaborCost = new BigDecimal("0.00");

        totalLaborCost = area.multiply(laborCostPSF).setScale(2, RoundingMode.HALF_UP);

        return totalLaborCost;
    }

    /**
     * @return the totalTax
     */
    public BigDecimal getTotalTax() {
        BigDecimal totalTax = new BigDecimal("0.00");
        BigDecimal totMaterialCost = getTotalMatericalCost();
        BigDecimal totLaborCost = getTotalLaborCost();
        BigDecimal totalMCLC = totMaterialCost.add(totLaborCost);

        totalTax = taxRate.multiply(new BigDecimal("0.01")).multiply(totalMCLC).setScale(2, RoundingMode.HALF_UP);

        return totalTax;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        BigDecimal totalCost = new BigDecimal("0.00");
        BigDecimal tCostPSF = getTotalMatericalCost();
        BigDecimal tLaborCostPSF = getTotalLaborCost();
        BigDecimal tTax = getTotalTax();
        totalCost = getTotalTax().add(tCostPSF).add(tLaborCostPSF);

        return totalCost;
    }

}
