/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuelorders.util;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author denis
 */
public class DateConverter {
    
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // convert user input string to Date object for saving in database
    public static Date convertToDb(String input) {
        
        LocalDate localDate = LocalDate.parse(input, FORMATTER);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    // convert from Date object to human readable string for output to the user
    public static String convertFromDb(Date input) {
        
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.format(FORMATTER);
    }
}
