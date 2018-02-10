/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author denis
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = FuelOrder.FIND_ALL_ORDERS, 
        query = "SELECT fo FROM FuelOrder fo")
})
@Table(name = "fuel_order")
public class FuelOrder {
    
    // @NamedQuery constants
    public static final String FIND_ALL_ORDERS = "FuelOrder.findAllOrders";

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "cost_per_liter")
    private Float costPerLiter;
    private Float discount;
    @Column(name = "amount_liters")
    private Float amountLiters;
    @Column(name = "total_cost")
    private Float totalCost;

    public FuelOrder() {
    }

    public FuelOrder(Date creationDate, String fuelType, Float costPerLiter, 
        Float discount, Float amountLiters, Float totalCost) {
        
        this.creationDate = creationDate;
        this.fuelType = fuelType;
        this.costPerLiter = costPerLiter;
        this.discount = discount;
        this.amountLiters = amountLiters;
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Float getCostPerLiter() {
        return costPerLiter;
    }

    public void setCostPerLiter(Float costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getAmountLiters() {
        return amountLiters;
    }

    public void setAmountLiters(Float amountLiters) {
        this.amountLiters = amountLiters;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "FuelOrder{" + "id=" + id + ", creationDate=" + creationDate + 
            ", fuelType=" + fuelType + ", costPerLiter=" + costPerLiter + 
            ", discount=" + discount + ", amountLiters=" + amountLiters + 
            ", totalCost=" + totalCost + '}';
    }
}
