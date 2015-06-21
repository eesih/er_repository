/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.timesheet.boundary;

import com.er.business.TestBase;
import com.er.business.client.boundary.ClientFacade;
import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import com.er.business.user.boundary.EmployeeFacade;
import com.er.business.user.entity.User;
import com.er.business.user.entity.UserRoles;
import com.er.business.timesheet.entity.Timesheet;
import com.er.business.timesheet.entity.WeekHours;
import java.math.BigDecimal;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author eerosihvonen
 */
public class TimesheetFacadeITTest extends TestBase {
    
    private final static Integer YEAR = 2014;
    private final static Integer MONTH = 10;
    private final static Integer WEEK = 40;

    @Test
    public void testTimesheetFacade() throws Exception {

        Context ctx = ec.getContext();
        // Check JNDI dependencies (EJBs)        
        assertNotNull(ctx.lookup("java:global/classes/TimesheetFacade"));

        TimesheetFacade timesheetFacade = (TimesheetFacade) ctx.lookup("java:global/classes/TimesheetFacade");

        Assert.assertEquals(0, timesheetFacade.getTimesheets().size());
        User employee = getEmployee(ctx);
        ClientFacade clientFacade = getClientFacade(ctx);
        Client client = getClient(clientFacade);
        Project project = getProject(clientFacade);
        client.addProject(project);
        client = clientFacade.addOrUpdateClient(client);
        
        WeekHours weekHours = new WeekHours(new Double("7.5"), null, null, null, null);
        Activity activity = getActivity(clientFacade, weekHours);
        project.addActivity(activity);
        project = clientFacade.addOrUpdateProject(project);
        
        
        Timesheet timesheet = timesheetFacade.addOrUpdateTimesheet(getTimesheet(WEEK, MONTH, YEAR, employee, client, project, activity));
        assertNotNull(timesheet);
        
        List<Timesheet> timesheets = timesheetFacade.getTimesheets(employee);
        Assert.assertEquals(1, timesheets.size());
        timesheet = timesheets.get(0);
        client = timesheet.getClient();
        assertNotNull(client);
        
        List<Project> projects = client.getProjects();
        Assert.assertEquals(1, projects.size());
        
        project = projects.get(0);
        assertNotNull(project);
        
        List<Activity> activites = project.getActivites();
        Assert.assertEquals(1, activites.size());
        
        activity = activites.get(0);
        assertNotNull(activity);
        
        
        timesheets = timesheetFacade.getTimesheets(null);
        Assert.assertEquals(0, timesheets.size());
        
        timesheets = timesheetFacade.getTimesheetsMonthly(employee, MONTH, YEAR);        
        Assert.assertEquals(1, timesheets.size());
        
        timesheets = timesheetFacade.getTimesheetsMonthly(employee, MONTH, 2015);        
        Assert.assertEquals(0, timesheets.size());
        
        timesheets = timesheetFacade.getTimesheetsWeekly(employee, WEEK, MONTH, YEAR);
        Assert.assertEquals(1, timesheets.size());
        
        timesheets = timesheetFacade.getTimesheetsWeekly(employee, WEEK, MONTH, 2015);
        Assert.assertEquals(0, timesheets.size());
        
        weekHours = new WeekHours(new Double("7.5"), new Double("7.5"), null, null, null);
        activity.setWeekHours(weekHours);
        
        activity = clientFacade.addOrUpdateActivity(activity);
        Assert.assertEquals(1, project.getActivites().size());
        
        weekHours = new WeekHours(null, null, new Double("7.5"), new Double("2.5"), null);
        activity = getActivity(clientFacade, weekHours);
        project.addActivity(activity);
        project = clientFacade.addOrUpdateProject(project);
        Assert.assertEquals(2, project.getActivites().size());
        
        project = getProject(clientFacade);
        weekHours = new WeekHours(null, null, null, new Double("5.5"), new Double("7.5"));
        activity = getActivity(clientFacade, weekHours);
        project.addActivity(activity);
        project = clientFacade.addOrUpdateProject(project);
        Assert.assertEquals(1, project.getActivites().size());
        
        timesheetFacade.addOrUpdateTimesheet(new Timesheet(WEEK, MONTH, YEAR, employee, client, project, activity));
        Assert.assertEquals(2, timesheetFacade.getTimesheets(employee).size());
        

    }

    private User getEmployee(Context ctx) throws Exception {
        assertNotNull(ctx.lookup("java:global/classes/EmployeeFacade"));
        EmployeeFacade employeeFacade = (EmployeeFacade) ctx.lookup("java:global/classes/EmployeeFacade");
        return employeeFacade.addOrModifyEmployee(new User("Jack Coder", UserRoles.ARCHITECT));
    }
    
    private Project getProject(ClientFacade clientFacade) throws Exception   {
        return clientFacade.addOrUpdateProject(new Project("Ovi store", "Description: creating new application store"));
    }

    private Client getClient(ClientFacade clientFacade) throws Exception {
        return clientFacade.addOrUpdateClient(new Client("Nokia", "contact person", "Fifth street", "01500", "some@email.com", "+358123456789"));
    }

    private ClientFacade getClientFacade(Context ctx) throws NamingException {
        assertNotNull(ctx.lookup("java:global/classes/ClientFacade"));
        ClientFacade clientFacade = (ClientFacade) ctx.lookup("java:global/classes/ClientFacade");
        return clientFacade;
    }

    private Timesheet getTimesheet(Integer week, Integer month, Integer year, User employee, Client client, Project project, Activity activity) {
        return new Timesheet(week, month, year, employee, client, project, activity);
    }

    private Activity getActivity(ClientFacade clientFacade, WeekHours weekHours) {    
        return clientFacade.addOrUpdateActivity(new Activity("Implementation", "description: Implementation of ovi stote system", new BigDecimal("70"), weekHours));
    }

}
