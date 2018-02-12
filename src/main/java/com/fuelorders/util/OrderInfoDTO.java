/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.util;

/**
 *
 * @author avtsin denis
 */

// data transfer object, which recieves user input parameters from 
// order form (home.jsp)
public class OrderInfoDTO {

    private long companyId;
    private long fillingStationId;
    private String orderDate;
    private String fuelType;
    private float costPerLiter;
    private float discount;
    private float amountLiters;
    private float totalCost;

    public OrderInfoDTO() {
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getFillingStationId() {
        return fillingStationId;
    }

    public void setFillingStationId(long fillingStationId) {
        this.fillingStationId = fillingStationId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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

    @Override
    public String toString() {
        StringBuilder toStringBuilder;
        toStringBuilder = new StringBuilder("OrderInfoDTO{");
        toStringBuilder.append("companyId=").append(this.companyId);
        toStringBuilder.append(",fillingStationId=").append(this.fillingStationId);
        toStringBuilder.append(",orderDate=").append(this.orderDate);
        toStringBuilder.append(",fuelType=").append(this.fuelType);
        toStringBuilder.append(",costPerLiter=").append(this.costPerLiter);
        toStringBuilder.append(",discount=").append(this.discount);
        toStringBuilder.append(",amountLiters=").append(this.amountLiters);
        toStringBuilder.append(",totalCost=").append(this.totalCost);
        toStringBuilder.append('}');
        return toStringBuilder.toString();
    }
}
