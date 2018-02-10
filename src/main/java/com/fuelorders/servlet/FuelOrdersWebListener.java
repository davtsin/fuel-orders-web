/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.servlet;

import com.fuelorders.dao.FuelOrdersManager;
import com.fuelorders.util.FuelType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author denis
 */
@WebListener
public class FuelOrdersWebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext context = sce.getServletContext();

        EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("fuel-orders-web-eclipse-derby-PU");

        // set emf at application scope
        context.setAttribute("emf", emf);
        // set fuelOrders data acess object at application scope
        context.setAttribute("fuelOrdersManager", new FuelOrdersManager(emf));
        // set fuel type attribute for drop down list in home.jsp
        context.setAttribute("fuelType", FuelType.values());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        EntityManagerFactory emf
            = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
        emf.close();
    }
}
