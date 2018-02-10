/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.main;

import com.fuelorders.dao.FuelOrdersManager;
import com.fuelorders.entity.Company;
import com.fuelorders.entity.FillingStation;
import com.fuelorders.entity.FuelOrder;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author denis
 */
public class Testing {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("fuel-orders-web-PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    FuelOrdersManager foManager = new FuelOrdersManager(emf);

    public static void main(String[] args) {
        Testing testing = new Testing();
//        main.fillDatabase();

//        main.test1("Company_0");
//        main.test2();
//        main.test3("Company_1");
//        main.test4();
//        main.clearTables();
//        main.findCompanyById(225L);
//        main.findAllFillingStationsJoinCompanies();
//        main.findAllCompaniesAndFillingStations();
//        main.findAllTables();
        testing.convert("01.02.2018");

        testing.em.close();
        testing.emf.close();
    }

    // convert date
    public void convert(String input) {
        DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        System.out.printf("%s%n", date);
    }

    public void findAllTables() {
        System.out.println("##########");
        System.out.println(foManager.findAllTables());
        System.out.println("##########");
    }

    public void findAllCompaniesAndFillingStations() {
        System.out.println("##########");
        System.out.println(foManager.findAllCompaniesAndFillingStations());
        System.out.println("##########");
    }

    public void findCompanyById(Long id) {
        Company company = em.createNamedQuery("findCompanyById", Company.class)
            .setParameter("id", id).getSingleResult();
        System.out.println(company);
    }

    public void clearTables() {
        Query query = em.createQuery("Delete From Company c");
        tx.begin();
        query.executeUpdate();
        tx.commit();
    }

    // select through domain model
    public void test4() {
        TypedQuery<Company> query = em.createNamedQuery("findAllCompanies", Company.class);
        List<Company> companies = query.getResultList();

        // sorting and out company names
        companies.stream()
            .sorted(Comparator.comparing(Company::getName))
            .forEach(c -> System.out.println(c.getName()));

        // trying to out a list of company in string parameter
        Company cmp = companies.stream()
            .filter(c -> c.getName().equals("Company_0"))
            .findFirst().get();

        // initialization of lazy list by invoking size()
//        cmp.getFillingStations().size();
        System.out.println(cmp.getFillingStations());

        // right sintax, but it don't work !!!
        cmp.getFillingStations().forEach((fs) -> {
            System.out.println(fs.getCode());
        });
        // the same thing, but it work !!!
        for (FillingStation fs : cmp.getFillingStations()) {
            System.out.println(fs.getCode());
        }

    }

    // select all filling stations belonging to concrete Company
    public void test3(String companyName) {
        TypedQuery<Long> query = em.createNamedQuery("getCompanyIdByName", Long.class);
        query.setParameter("name", companyName);
        long cmpId = query.getSingleResult();
        System.out.println(cmpId);
    }

    // named query with select all companies
    public void test2() {
        Query query = em.createNamedQuery("findAllCompanyNames");
        List list = query.getResultList();
        System.out.println(list);
    }

    // select Company by name
    public void test1(String companyName) {
        TypedQuery<Company> query = em.createQuery("Select c From Company c Where c.name = :name", Company.class);
        query.setParameter("name", companyName);
        Company result = query.getSingleResult();
        System.out.println(result);
    }

    public void fillDatabase() {
        tx.begin();
        for (int i = 0; i < 10; i++) {
            Company company = new Company("Company_" + i);
            for (int j = 0; j < 5; j++) {
                FillingStation fs = new FillingStation("Company_" + i + "_AZS_" + j, "address_" + j);
                for (int k = 0; k < 10; k++) {
                    fs.getOrders().add(new FuelOrder(new Date(), "AI-95-" + k, 25.5F, 25.5F, 25.5F, 25.5F));
                }
                company.getFillingStations().add(fs);
            }
            em.persist(company);
        }
        tx.commit();
    }

//    class FillingStationJoinCompany {
//        
//        private long id;
//        private String code;
//        private String address;
//        private String companyName;
//
//        public long getId() {
//            return id;
//        }
//
//        public void setId(long id) {
//            this.id = id;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public String getCompanyName() {
//            return companyName;
//        }
//
//        public void setCompanyName(String companyName) {
//            this.companyName = companyName;
//        }
//
//        @Override
//        public String toString() {
//            return "FillingStationJoinCompany{" + "id=" + id + ", code=" + code + ", address=" + address + ", companyName=" + companyName + '}';
//        }
//    }
//    public void findAllFillingStationsJoinCompanies() {
//        List list = foManager.findAllFillingStationsJoinCompanies();
//        System.out.println("###################");
//        System.out.println(list);
//        // array of FillingStationJoinCompanies fields
//        for (Object arr : list) {
//            System.out.println("----------------");
//            // each field of concrete object
//            for (Object o : (Object[]) arr) {
//                try {
//                    System.out.println(Arrays.asList(o.getClass().getSimpleName()));
//                    String className = o.getClass().getName();
//                    Class classObj = Class.forName(className);
//                    System.out.println(o.getClass().cast(o));
//                    
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//            System.out.println("----------------");
//        }
//        
//        List<FillingStationJoinCompany> fsjcList = new ArrayList<>();
//        
//        for (Object objArr : list) {
//            Object[] fields = (Object[])objArr;
//            FillingStationJoinCompany fsjc = new FillingStationJoinCompany();
//            fsjc.setId((long)fields[0]);
//            fsjc.setCode((String)fields[1]);
//            fsjc.setAddress((String)fields[2]);
//            fsjc.setCompanyName((String)fields[3]);
//            fsjcList.add(fsjc);
//            System.out.println(fsjc);
//        }
//        
//        System.out.println(fsjcList);
//        
//        System.out.println("###################");
//    }
}
