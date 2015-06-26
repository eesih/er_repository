/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.timesheet.boundary;

import com.er.application.util.PerformanceInterceptor;
import com.er.business.user.entity.User;
import com.er.business.timesheet.entity.Timesheet;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import static com.er.application.util.EmployeeResourceUtil.*;

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
    
    public List<Timesheet> getAllTimesheets()  {
        return em.createNamedQuery("Timesheet.findAll", Timesheet.class).getResultList();
    }
    
    public List<Timesheet> getEmployeesAllTimesheets(User user)  {
        return em.createNamedQuery("Timesheet.findByEmp", Timesheet.class)
                .setParameter("user", user)
                .getResultList();
    }
    
    public List<Timesheet> getEmployeesTimesheetsMonthly(User user, Date date)  {
        return em.createNamedQuery("Timesheet.findByEmpDate", Timesheet.class)
                .setParameter("user", user)
                .setParameter("startDate", getMonthsFirstDate(date))
                .setParameter("endDate", getMonthsLastDate(date))
                .getResultList();
    }
    
    public List<Timesheet> getEmployeesTimesheetsFromThisMonth(User employee)  {
        return getEmployeesTimesheetsMonthly(employee, new Date());
    }
    
    public Timesheet addOrUpdateTimesheet(Timesheet timesheet)  {
        timesheetEvent.fire(timesheet);
        return em.merge(timesheet);
    }

}
