/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.util;

import com.fuelorders.servlet.ControllerServlet;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author avtsin denis
 */

// class for populating data transfer objects (DTO) with request parameters
// using org.apache.commons.beanutils library
public class RequestParamHelper {

    // returns object with stored parameters from request about new order
    public static OrderInfoDTO getOrderParams(HttpServletRequest request) {

        OrderInfoDTO userInput = new OrderInfoDTO();
        // getting parameters from request
        try {
            BeanUtils.populate(userInput, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userInput;
    }

    // returns object with stored parameters from request about new filling station
    public static FillingStationDTO getFillingStationParams(HttpServletRequest request) {

        FillingStationDTO userInput = new FillingStationDTO();
        try {
            BeanUtils.populate(userInput, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userInput;
    }
}
