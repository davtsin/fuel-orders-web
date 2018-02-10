///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.fuelorders.servlet;
//
//import com.fuelorders.dao.FuelOrdersManager;
//import com.fuelorders.entity.Company;
//import com.fuelorders.entity.FillingStation;
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
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
//@WebServlet(name = "AjaxServlet", loadOnStartup = 1, urlPatterns = {"/AjaxServlet"})
//public class AjaxServlet extends HttpServlet {
//
//    private FuelOrdersManager foManager;
//
//    @Override
//    public void init() {
//        // getting data access object from application context
//        foManager = (FuelOrdersManager) getServletContext().getAttribute("fuelOrdersManager");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//
//        String companyValue = request.getParameter("companyValue");
////        System.out.println(companyValue);
//        List<FillingStation> fsList = new ArrayList<>();
//
//        if (companyValue.equals("Select company")) {
//            FillingStation fs = new FillingStation();
//            fs.setCode("Select filling station");
//            fsList.add(fs);
//        } else {
//            long companyId = Long.parseLong(companyValue);
//            Company company = foManager.findCompanyById(companyId);
////            System.out.println(company.getFillingStations().size());
////            System.out.println(company.getFillingStations());
//            fsList = company.getFillingStations();
//        }
//
//        String json = new Gson().toJson(fsList);
////        System.out.println(json);
//        response.setContentType("application/json");
//        response.getWriter().write(json);
//
//    }
//}
