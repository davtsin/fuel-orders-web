/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.util;

import java.util.Date;

/**
 *
 * @author avtsin denis
 */
// data transfer object, which recieves data from database containing result
// of JOIN operation between Company, FillingStation and Order tables
public class AllTablesDTO {
    
    private long id;
    private String companyName;
    private String fillingStationCode;
    private String fillingStationAddress;
    private Date orderDate;
    private String orderDateHr; // human readable representation of orderDate in dd.mm.yyyy format
    private String fuelType;
    private float costPerLiter;
    private float discount;
    private float amountLiters;
    private float totalCost;

    public AllTablesDTO(long id, String companyName, String fillingStationCode, 
        String fillingStationAddress, Date orderDate, String fuelType, float costPerLiter, 
        float discount, float amountLiters, float totalCost) {
        
        this.id = id;
        this.companyName = companyName;
        this.fillingStationCode = fillingStationCode;
        this.fillingStationAddress = fillingStationAddress;
        this.orderDate = orderDate;
        this.fuelType = fuelType;
        this.costPerLiter = costPerLiter;
        this.discount = discount;
        this.amountLiters = amountLiters;
        this.totalCost = totalCost;
        this.orderDateHr = DateConverter.convertFromDb(orderDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFillingStationCode() {
        return fillingStationCode;
    }

    public void setFillingStationCode(String fillingStationCode) {
        this.fillingStationCode = fillingStationCode;
    }

    public String getFillingStationAddress() {
        return fillingStationAddress;
    }

    public void setFillingStationAddress(String fillingStationAddress) {
        this.fillingStationAddress = fillingStationAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(float costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getAmountLiters() {
        return amountLiters;
    }

    public void setAmountLiters(float amountLiters) {
        this.amountLiters = amountLiters;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderDateHr() {
        return orderDateHr;
    }

    @Override
    public String toString() {
        return "AllTablesDTO{" + "id=" + id + ", companyName=" + companyName + 
            ", fillingStationCode=" + fillingStationCode + 
            ", fillingStationAddress=" + fillingStationAddress + ", orderDate=" + orderDate + 
            ", fuelType=" + fuelType + ", costPerLiter=" + costPerLiter + 
            ", discount=" + discount + ", amountLiters=" + amountLiters + 
            ", totalCost=" + totalCost + ", orderDateHr=" + orderDateHr + '}';
    }
}
