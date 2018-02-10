///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.fuelorders.servlet;
//
//import com.fuelorders.bean.OrderInfo;
//import com.fuelorders.dao.FuelOrdersManager;
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.beanutils.BeanUtils;
//
///**
// *
// * @author davtsin
// */
//@WebServlet(name = "CreateOrderAjaxServlet", urlPatterns = {"/CreateOrderAjaxServlet"})
//public class CreateOrderAjaxServlet extends HttpServlet {
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
//        response.getWriter().println("Hello");
//        
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//
//        OrderInfo userInput = new OrderInfo();
//        // getting parameters from request
//        try {
//            BeanUtils.populate(userInput, request.getParameterMap());
//        } catch (IllegalAccessException | InvocationTargetException ex) {
//            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println(userInput);
//        
//        // saving order information to DB
//        // new Date() for testing
//        foManager.createOrder(
//            userInput.getCompanyName(), userInput.getFillingStationCode(),
//            userInput.getFillingStationAddress(), new Date(), userInput.getFuelType(),
//            userInput.getCostPerLiter(), userInput.getDiscount(),
//            userInput.getAmountLiters(), userInput.getTotalCost());
//        // set attribute with all orders from DB for displing after adding order
////            request.setAttribute("fuelOrders", foManager.findAllOrders());
//        
//        String json = new Gson().toJson(foManager.findAllOrders());
//        System.out.println(json);
//        response.setContentType("application/json");
//        response.getWriter().write(json);
//
//    }
//}
