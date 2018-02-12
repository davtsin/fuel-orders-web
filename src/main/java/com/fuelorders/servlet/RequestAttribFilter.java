/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.servlet;

import com.fuelorders.dao.FuelOrdersManager;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author avtsin denis
 */

// Attribute "companies" which contains list of all companies is frequently
// used by whole application, therefore it was placed in ServletContext and
// updates in this filter. This solution has reduced the size of the ControllerServlet.
// Filter updates state of "companies" attribute in application context.
// Updates needs only for forward requests, not for direct requests,
// therefore dispatecherTypes parameter is setted to the FORWARD.

@WebFilter(
    filterName = "RequestAttribFilter",
    urlPatterns = {"/*"},
    dispatcherTypes = DispatcherType.FORWARD
)
public class RequestAttribFilter implements Filter {

    private ServletContext context;
    private FilterConfig filterConfig;
    private FuelOrdersManager foManager;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        context = filterConfig.getServletContext();
        foManager = (FuelOrdersManager) context.getAttribute("fuelOrdersManager");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain)
        throws IOException, ServletException {

        context.log("statrt filter ************");
        
        String path = ((HttpServletRequest) request).getServletPath();
        request.setAttribute("companies", foManager.findAllCompanies());
        chain.doFilter(request, response);
        
        context.log(path);
        context.log("companies attribute changed");
        context.log("end filter ************\n");
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
