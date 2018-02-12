/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author avtsin denis
 */
@Entity
@Table(name = "filling_station")
public class FillingStation {

    public FillingStation() {
        orders = new ArrayList<>();
    }

    public FillingStation(String code, String address) {
        this();
        this.code = code;
        this.address = address;
    }
    
    @Id @GeneratedValue
    private Long id;
    private String code;
    private String address;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "filling_station_fk")
    private List<FuelOrder> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FuelOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<FuelOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "FillingStation{" + "id=" + id + ", code=" + code + 
            ", address=" + address + ", orders=" + orders + '}';
    }
}
