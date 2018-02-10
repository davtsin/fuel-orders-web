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

// data transfer object, which recieves user input parameters from 
// filling station form (createFillingStation.jsp)
public class FillingStationDTO {
    
    private long companyId;
    private String fillingStationCode;
    private String fillingStationAddress;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    @Override
    public String toString() {
        return "FillingStationDTO{" + "companyId=" + companyId + 
            ", fillingStationCode=" + fillingStationCode + 
            ", fillingStationAddress=" + fillingStationAddress + '}';
    }
}
