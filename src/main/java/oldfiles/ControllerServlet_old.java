///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.fuelorders.servlet;
//
//import com.fuelorders.entity.Company;
//import com.fuelorders.entity.FillingStation;
//import com.fuelorders.entity.FuelOrder;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Query;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author denis
// */
//@WebServlet(
//    name = "ControllerServlet_old")
////    urlPatterns = {
////        "/mainForm",
////        "/createCompany",
////        "/createFillingStation"},
////    loadOnStartup = 1
////)
//public class ControllerServlet_old extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//
//        String userPath = request.getServletPath();
//        if (userPath.equals("/mainForm")) {
//            // Obtain a database connection:
//            EntityManagerFactory emf
//                = (EntityManagerFactory) getServletContext().getAttribute("emf");
//            EntityManager em = emf.createEntityManager();
//
//            try {
//
//                // for company combo box
//                List<Company> companies
//                    = em.createNamedQuery("findAllCompanies", Company.class).getResultList();
////                request.setAttribute("companies", companies);
//                request.getSession().setAttribute("companies", companies);
//
//                // for filling stations combo box
//                String selectedCompanyId = request.getParameter("companyId");
//                System.out.println(selectedCompanyId);
////                System.out.println("companies: " + companies);
//                Company company = null;
//                if (selectedCompanyId != null) {
//                    Long id = Long.parseLong(selectedCompanyId);
//                    company = em.createNamedQuery("findCompanyById", Company.class)
//                        .setParameter("id", id).getSingleResult();
//                }
//                System.out.println(company);
//                if (company != null) {
//                    company.getFillingStations().size();
//                    request.setAttribute("fillingStations", company.getFillingStations());
//                }
//
////                List<FillingStation> fsList = companies
////                    .stream().filter(c -> c.getName().equals(selectedCompanyName))
////                    .findFirst().orElse(new Company()).getFillingStations();
////                fsList.size();
////                request.getSession().setAttribute("fillingStations", fsList);
////                System.out.println("filling stations: " + fsList);
//                // Display the list of fuel orders:
//                List<FuelOrder> fuelOrderList = em.createQuery(
//                    "SELECT fo FROM FuelOrder fo", FuelOrder.class).getResultList();
//                if (fuelOrderList != null) { // v dalneyshem mojno ubrat etu proverku
//                    request.setAttribute("fuelOrders", fuelOrderList);
//                }
//                request.getRequestDispatcher("/home.jsp")
//                    .forward(request, response);
//
//            } finally {
//                // Close the database connection:
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//                em.close();
//            }
//        } else if (userPath.equals("/createCompany")) { // add company if not exists
//            request.getRequestDispatcher("/createCompany.jsp")
//                .forward(request, response);
//        } else if (userPath.equals("/createFillingStation")) { // add filling station if not exists
//            // Obtain a database connection:
//            EntityManagerFactory emf
//                = (EntityManagerFactory) getServletContext().getAttribute("emf");
//            EntityManager em = emf.createEntityManager();
//
//            try {
//                // fill company combo box
////                List<Company> companies
////                    = em.createNamedQuery("findAllCompanies", Company.class).getResultList();
////                request.setAttribute("companies", companies);
//
//                request.getRequestDispatcher("/createFillingStation.jsp")
//                    .forward(request, response);
//
//            } finally {
//                // Close the database connection:
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//                em.close();
//            }
//
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//
//        EntityManagerFactory emf
//            = (EntityManagerFactory) getServletContext().getAttribute("emf");
//
//        String userPath = request.getServletPath();
//        if (userPath.equals("/mainForm")) {
//            // Obtain a database connection:
//
//            EntityManager em = emf.createEntityManager();
//
//            try {
////            // Handle a new guest (if any):
////            String name = request.getParameter("name");
////            if (name != null) {
////                em.getTransaction().begin();
////                em.persist(new Guest(name));
////                em.getTransaction().commit();
////            }
//
//                // create new order in database
//                Company company = new Company();
//                company.setName(request.getParameter("companyName"));
//                FillingStation fs = new FillingStation();
//                fs.setCode(request.getParameter("fillingStationCode"));
//                fs.setAddress(request.getParameter("fillingStationAddress"));
//                FuelOrder fo = new FuelOrder();
////            fo.setCreationDate();
//                fo.setFuelType(request.getParameter("fuelType"));
//                fo.setCostPerLiter(Float.parseFloat(request.getParameter("costPerLiter")));
//                fo.setDiscount(Float.parseFloat(request.getParameter("discount")));
//                fo.setAmountLiters(Float.parseFloat(request.getParameter("amountLiters")));
//                fo.setTotalCost(Float.parseFloat(request.getParameter("totalCost")));
//                // add order to filling station orders
//                fs.getOrders().add(fo);
//                // add filling station to company filling stations
//                company.getFillingStations().add(fs);
//
//                em.getTransaction().begin();
//                em.persist(company);
//                em.getTransaction().commit();
//
//                doGet(request, response);
//
//            } finally {
//                // Close the database connection:
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//                em.close();
//            }
//        } else if (userPath.equals("/createCompany")) { // save data with new company
//            EntityManager em = emf.createEntityManager();
//
//            try {
//                // create new company in database
//                Company company = new Company();
//                company.setName(request.getParameter("companyName"));
//
//                em.getTransaction().begin();
//                em.persist(company);
//                em.getTransaction().commit();
//
//                request.getRequestDispatcher("/createCompany.jsp")
//                    .forward(request, response);
//
//            } finally {
//                // Close the database connection:
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//                em.close();
//            }
//        } else if (userPath.equals("/createFillingStation")) { // save data with new company
//            EntityManager em = emf.createEntityManager();
//
//            try {
//
//                String companyName = request.getParameter("companyName");
//
//                Optional<Company> matchingResult = ((List<Company>) request.getSession().getAttribute("companies"))
//                    .stream().filter(c -> c.getName().equals(companyName)).findFirst();
//                Company company = matchingResult.get();
//
//                FillingStation fs = new FillingStation();
//                fs.setCode(request.getParameter("fillingStationCode"));
//                fs.setAddress(request.getParameter("fillingStationAddress"));
//
//                company.getFillingStations().add(fs);
//
//                em.getTransaction().begin();
//                em.merge(company);
//                em.getTransaction().commit();
//
//                request.getRequestDispatcher("/createFillingStation.jsp")
//                    .forward(request, response);
//
//            } finally {
//                // Close the database connection:
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//                em.close();
//            }
//        }
//
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
