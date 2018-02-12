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

// data transfer object, which recieves data from database containing result
// of JOIN operation between Company table and FillingStation table
public class CompanyAndFillingStationDTO {

    private final long id;
    private final String code;
    private final String address;
    private final String companyName;

    public CompanyAndFillingStationDTO(long id, String code, String address, String companyName) {
        this.id = id;
        this.code = code;
        this.address = address;
        this.companyName = companyName;
    }
    
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder;
        toStringBuilder = new StringBuilder("CompanyAndFillingStationDTO{");
        toStringBuilder.append("id=").append(this.id);
        toStringBuilder.append(",code=").append(this.code);
        toStringBuilder.append(",address=").append(this.address);
        toStringBuilder.append(",companyName=").append(this.companyName);
        toStringBuilder.append('}');
        return toStringBuilder.toString();
    }
}
