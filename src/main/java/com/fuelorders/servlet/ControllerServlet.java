/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.servlet;

import com.fuelorders.util.FillingStationDTO;
import com.fuelorders.util.OrderInfoDTO;
import com.fuelorders.dao.FuelOrdersManager;
import com.fuelorders.util.RequestParamHelper;
import com.fuelorders.entity.Company;
import com.fuelorders.entity.FillingStation;
import com.fuelorders.entity.FuelOrder;
import com.fuelorders.util.DateConverter;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author denis
 */
@WebServlet(name = "Controller", loadOnStartup = 1,
    urlPatterns = {
        "",
        "/createCompany",
        "/createFillingStation",
        "/createOrder",
        "/showOrdersAjax",
        "/createOrderAjax",
        "/dropDownAjax",
        "/deleteAllCompanies"
    })
public class ControllerServlet extends HttpServlet {

    private FuelOrdersManager foManager;

    @Override
    public void init() {
        // getting data access object from application context
        foManager = (FuelOrdersManager) getServletContext().getAttribute("fuelOrdersManager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String userPath = request.getServletPath();
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        //----------------------------------------------------------------------
        // Handle AJAX (JSON or XML) response.
        if (ajax) {

            // sending data for table with all orders in home.jsp page
            if (userPath.equals("/showOrdersAjax")) {

                String json = new Gson().toJson(foManager.findAllTables());
                response.setContentType("application/json");
                response.getWriter().write(json);
            } 
            // sending data for filling station dropdown list
            // in home.jsp page
            else if (userPath.equals("/dropDownAjax")) {

                String companyValue = request.getParameter("companyValue");
                long companyId = Long.parseLong(companyValue);
                Company company = foManager.findCompanyById(companyId);
                List<FillingStation> fsList = company.getFillingStations();

                String json = new Gson().toJson(fsList);
                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } 
        //----------------------------------------------------------------------
        // Handle regular (JSP) response.
        else {

            // root path
            if (userPath.equals("")) {

                userPath = "/home";
            }
            // delete all tables from DB (delet all tables link)
            if (userPath.equals("/deleteAllCompanies")) {

                foManager.deleteAllTables();
                userPath = "/home";
            } 
            // go to create filling station form ("Add filling station..." link)
            else if (userPath.equals("/createFillingStation")) {

                request.setAttribute("companiesAndFillingStations",
                    foManager.findAllCompaniesAndFillingStations());
            }

            String url = "/WEB-INF/view" + userPath + ".jsp";
            request.getRequestDispatcher(url)
                .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        //----------------------------------------------------------------------
        // Handle AJAX (JSON or XML) response.
        if (ajax) {

            if (userPath.equals("/createOrderAjax")) {

                OrderInfoDTO userInput = RequestParamHelper.getOrderParams(request);
                // saving order information to DB
                FuelOrder fuelOrder = foManager.createOrder(
                    userInput.getCompanyId(), userInput.getFillingStationId(),
                    DateConverter.convertToDb(userInput.getOrderDate()),
                    userInput.getFuelType(), userInput.getCostPerLiter(),
                    userInput.getDiscount(), userInput.getAmountLiters(),
                    userInput.getTotalCost());

                String json = new Gson().toJson(fuelOrder);
                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } 
        //----------------------------------------------------------------------
        // Handle regular (JSP) response.
        else {

            // create new company in DB
            if (userPath.equals("/createCompany")) {

                Company company = foManager.createCompany(request.getParameter("companyName"));
                // retrieve new company from DB for display
                request.setAttribute("company", foManager.findCompanyById(company.getId()));
            } 
            // create new filling station in DB
            else if (userPath.equals("/createFillingStation")) {

                FillingStationDTO userInput = RequestParamHelper.getFillingStationParams(request);
                FillingStation fs = foManager.createFillingStation(userInput.getCompanyId(),
                    userInput.getFillingStationCode(), userInput.getFillingStationAddress());
                request.setAttribute("company", foManager.findCompanyById(userInput.getCompanyId()));
                request.setAttribute("fillingStation", fs);
                request.setAttribute("companiesAndFillingStations",
                    foManager.findAllCompaniesAndFillingStations());
            }

            String url = "/WEB-INF/view" + userPath + ".jsp";
            request.getRequestDispatcher(url)
                .forward(request, response);
        }
        //----------------------------------------------------------------------
    }
}
