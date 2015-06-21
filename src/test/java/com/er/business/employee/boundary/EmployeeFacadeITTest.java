/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.employee.boundary;

import com.er.business.user.boundary.EmployeeFacade;
import com.er.business.TestBase;
import com.er.business.user.entity.User;
import com.er.business.user.entity.UserRoles;
import javax.naming.Context;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 *
 * @author eerosihvonen
 */
public class EmployeeFacadeITTest extends TestBase  {
   

    @Test
    public void testEmployeeFacade() throws Exception {

        Context ctx = ec.getContext();
        // Check JNDI dependencies (EJBs) 
        assertNotNull(ctx.lookup("java:global/classes/EmployeeFacade"));
        assertNotNull(ctx.lookup("java:global/classes/ClientFacade"));        
        
        EmployeeFacade employeeFacade = (EmployeeFacade) ctx.lookup("java:global/classes/EmployeeFacade");

        User employee = employeeFacade.addOrModifyEmployee(new User("Test", UserRoles.ARCHITECT));      
        assertNotNull(employee);       
        assertEquals(1, employeeFacade.getEmployees().size());
        
        User employeeById = employeeFacade.getEmployee(employee.getId());
        assertEquals(employee, employeeById);
        
        User employee2 = employeeFacade.addOrModifyEmployee(new User("Test2", UserRoles.DEVELOPER));      
        assertNotNull(employee2);
        assertEquals(2, employeeFacade.getEmployees().size());
        
        User employeeById2 = employeeFacade.getEmployee(employee2.getId());
        assertEquals(employee2, employeeById2);
        
        User employee3 = employeeFacade.addOrModifyEmployee(employee);
        assertEquals(employee, employee3);
        assertEquals(2, employeeFacade.getEmployees().size());  
    }

}
