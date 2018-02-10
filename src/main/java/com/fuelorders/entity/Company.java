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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author denis
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = Company.FIND_ALL_COMPANIES, 
        query = "SELECT c FROM Company c"
    ),
    
    @NamedQuery(
        name = Company.FIND_ALL_COMPANIES_AND_FILLING_STATIONS, 
        query = "Select NEW com.fuelorders.util.CompanyAndFillingStationDTO("
            + "fs.id, fs.code, fs.address, c.name) "
            + "From Company c JOIN c.fillingStations fs"
    ),
    
    @NamedQuery(
        name = Company.FIND_ALL_TABLES, 
        query = "Select NEW com.fuelorders.util.AllTablesDTO("
            + "o.id, c.name, fs.code, fs.address, o.creationDate, o.fuelType, "
            + "o.costPerLiter, o.discount, o.amountLiters, o.totalCost) "
            + "From Company c JOIN c.fillingStations fs JOIN fs.orders o"
    )
})
public class Company {
    
    // @NamedQuery constants
    public static final String FIND_ALL_COMPANIES = "Company.findAllCompanies";
    public static final String FIND_ALL_COMPANIES_AND_FILLING_STATIONS = 
        "Company.findAllCompaniesAndFillingStations";
    public static final String FIND_ALL_TABLES = "Company.findAllTables";

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_fk")
    private List<FillingStation> fillingStations;
    
    public Company() {
        fillingStations = new ArrayList<>();
    }

    public Company(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FillingStation> getFillingStations() {
        return fillingStations;
    }

    public void setFillingStations(List<FillingStation> fillingStations) {
        this.fillingStations = fillingStations;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + 
            ", fillingStations=" + fillingStations + '}';
    }
}
