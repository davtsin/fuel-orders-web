/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.util;

/**
 *
 * @author denis
 */

// enumeration for fuel types
public enum FuelType {
    
    AI_92("92 RON"), AI_95("95 RON"), AI_98("98 RON"), DIESEL("Diesel"); 
    
    private final String description;

    private FuelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
