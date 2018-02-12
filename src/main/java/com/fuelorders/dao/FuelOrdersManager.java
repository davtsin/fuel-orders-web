/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.dao;

import com.fuelorders.util.AllTablesDTO;
import com.fuelorders.entity.Company;
import com.fuelorders.util.CompanyAndFillingStationDTO;
import com.fuelorders.entity.FillingStation;
import com.fuelorders.entity.FuelOrder;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author avtsin denis
 */
public class FuelOrdersManager {

    private final EntityManagerFactory emf;

    public FuelOrdersManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<FuelOrder> findAllOrders() {

        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery(FuelOrder.FIND_ALL_ORDERS, FuelOrder.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Company> findAllCompanies() {

        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery(Company.FIND_ALL_COMPANIES, Company.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public Company findCompanyById(long id) {
        
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    public Company createCompany(String companyName) {
        
        EntityManager em = emf.createEntityManager();
        Company company = new Company(companyName);
        // create new company in DB
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();

        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
        return company;
    }

    public FuelOrder createOrder(long companyId, long fillingStationId,
        Date orderDate, String fuelType, Float costPerLiter,
        Float discount, Float amountLiters, Float totalCost) {

        EntityManager em = emf.createEntityManager();
        try {
            // create new order in DB
            Company company = em.find(Company.class, companyId);
            FillingStation fs = em.find(FillingStation.class, fillingStationId);
            FuelOrder fo = new FuelOrder(orderDate, fuelType, costPerLiter, discount,
                amountLiters, totalCost);
            // add order to filling station orders
            fs.getOrders().add(fo);

            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();

            return fo;

        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    public void deleteAllTables() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("Delete From FuelOrder").executeUpdate();
            em.createQuery("Delete From FillingStation").executeUpdate();
            em.createQuery("Delete From Company").executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    public FillingStation createFillingStation(long companyId, String fillingStationCode,
        String fillingStationAddress) {

        EntityManager em = emf.createEntityManager();
        try {
            // create new filling station in DB
            Company company = findCompanyById(companyId);
            FillingStation fs = new FillingStation(fillingStationCode, fillingStationAddress);
            company.getFillingStations().add(fs);

            em.getTransaction().begin();
            em.persist(fs);
            em.merge(company);
            em.getTransaction().commit();

            return fs;

        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    public List<CompanyAndFillingStationDTO> findAllCompaniesAndFillingStations() {
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CompanyAndFillingStationDTO> query
                = em.createNamedQuery(Company.FIND_ALL_COMPANIES_AND_FILLING_STATIONS,
                    CompanyAndFillingStationDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<AllTablesDTO> findAllTables() {
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<AllTablesDTO> query
                = em.createNamedQuery(Company.FIND_ALL_TABLES, AllTablesDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
