/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.timesheet.boundary;

import com.er.application.util.PerformanceInterceptor;
import com.er.business.user.entity.User;
import com.er.business.timesheet.entity.Timesheet;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

/**
 *
 * @author eerosihvonen
 */
@Stateless
@Interceptors(PerformanceInterceptor.class)
public class TimesheetFacade {
    
    @Inject
    private EntityManager em;
    
    @Inject
    Event<Timesheet> timesheetEvent;
    
    public List<Timesheet> getTimesheets()  {
        return em.createNamedQuery("Timesheet.findAll", Timesheet.class).getResultList();
    }
    
    public List<Timesheet> getTimesheets(User employee)  {
        return em.createNamedQuery("Timesheet.findByEmp", Timesheet.class)
                .setParameter("emp", employee)
                .getResultList();
    }
    
    public List<Timesheet> getTimesheetsMonthly(User employee, Integer month, Integer year)  {
        return em.createNamedQuery("Timesheet.findByEmpMonthYear", Timesheet.class)
                .setParameter("emp", employee)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
    }
    public List<Timesheet> getTimesheetsWeekly(User employee, Integer week, Integer month, Integer year)  {
        return em.createNamedQuery("Timesheet.findByEmpWeekMonthYear", Timesheet.class)
                .setParameter("emp", employee)
                .setParameter("week", week)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
    }
    
    public Timesheet addOrUpdateTimesheet(Timesheet timesheet)  {
        timesheetEvent.fire(timesheet);
        return em.merge(timesheet);
    }

 
}
