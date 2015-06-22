/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.application.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author eerosihvonen
 */
public class EmployeeResourceUtil {
    
    public static LocalDate convertDatetoLocalDate(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
    }
    
    public static Date getDate(int year, int month, int dayOfMonth) {
        LocalDate ld = LocalDate.of(year, month, dayOfMonth);
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);     
    }
    
    public static Date getMonthsFirstDate(Date date) {
        if(date == null) return null;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
        LocalDate monthsFirst = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
        Instant instant = monthsFirst.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
     public static Date getMonthsLastDate(Date date) {
        if(date == null) return null;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
        Month month = localDate.getMonth();
        LocalDate monthsLast = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), month.maxLength());
        Instant instant = monthsLast.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
}
