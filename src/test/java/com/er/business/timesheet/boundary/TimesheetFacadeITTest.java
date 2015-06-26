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
import com.er.business.user.boundary.UserFacade;
import com.er.business.user.entity.User;
import com.er.business.user.entity.UserRoles;
import com.er.business.timesheet.entity.Timesheet;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static com.er.application.util.EmployeeResourceUtil.*;
/**
 *
 * @author eerosihvonen
 */
public class TimesheetFacadeITTest extends TestBase {
    
    private final static Date now = new Date();

    @Test
    public void testTimesheetFacade() throws Exception {

        Context ctx = ec.getContext();
        // Check JNDI dependencies (EJBs)        
        assertNotNull(ctx.lookup("java:global/classes/TimesheetFacade"));

        TimesheetFacade timesheetFacade = (TimesheetFacade) ctx.lookup("java:global/classes/TimesheetFacade");

        Assert.assertEquals(0, timesheetFacade.getAllTimesheets().size());
        User employee = getEmployee(ctx);
        ClientFacade clientFacade = getClientFacade(ctx);
        Client client = getClient(clientFacade);
        Project project = getProject(clientFacade);
        client.addProject(project);
        client = clientFacade.addOrUpdateClient(client);
        
        Activity activity = getActivity(clientFacade);
        project.addActivity(activity);
        project = clientFacade.addOrUpdateProject(project);
        
        Double hours = 7.5;
        Timesheet timesheet = timesheetFacade.addOrUpdateTimesheet(getTimesheet(now, employee, client, project, activity, hours));
        assertNotNull(timesheet);
        
        List<Timesheet> timesheets = timesheetFacade.getEmployeesAllTimesheets(employee);
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
              
        timesheets = timesheetFacade.getEmployeesAllTimesheets(null);
        Assert.assertEquals(0, timesheets.size());
        
        timesheets = timesheetFacade.getEmployeesTimesheetsFromThisMonth(employee);
        Assert.assertEquals(1, timesheets.size());
           
        timesheets = timesheetFacade.getEmployeesTimesheetsMonthly(employee, getDate(2000, 1, 1));
        Assert.assertEquals(0, timesheets.size());
              
        activity = clientFacade.addOrUpdateActivity(activity);
        Assert.assertEquals(1, project.getActivites().size());
                
        timesheetFacade.addOrUpdateTimesheet(new Timesheet(now, employee, client, project, activity, 4.5));
        Assert.assertEquals(2, timesheetFacade.getEmployeesAllTimesheets(employee).size());

    }

    private User getEmployee(Context ctx) throws Exception {
        assertNotNull(ctx.lookup("java:global/classes/UserFacade"));
        UserFacade employeeFacade = (UserFacade) ctx.lookup("java:global/classes/UserFacade");
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

    private Timesheet getTimesheet(Date date, User employee, Client client, Project project, Activity activity, Double hours) {
        return new Timesheet(date, employee, client, project, activity, hours);
    }

    private Activity getActivity(ClientFacade clientFacade) {    
        return clientFacade.addOrUpdateActivity(new Activity("Implementation", "description: Implementation of ovi stote system", new BigDecimal("70")));
    }

}
