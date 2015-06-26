/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.user.boundary;

import com.er.business.user.boundary.UserFacade;
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
public class UserFacadeITTest extends TestBase  {
   

    @Test
    public void testUserFacade() throws Exception {

        Context ctx = ec.getContext();
        // Check JNDI dependencies (EJBs) 
        assertNotNull(ctx.lookup("java:global/classes/UserFacade"));
        assertNotNull(ctx.lookup("java:global/classes/ClientFacade"));        
        
        UserFacade employeeFacade = (UserFacade) ctx.lookup("java:global/classes/UserFacade");

        User employee = employeeFacade.addOrModifyEmployee(new User("Test", UserRoles.PARTNER));      
        assertNotNull(employee);       
        assertEquals(1, employeeFacade.getUsers().size());
        
        User employeeById = employeeFacade.getUser(employee.getId());
        assertEquals(employee, employeeById);
        
        User employee2 = employeeFacade.addOrModifyEmployee(new User("Test2", UserRoles.EMPLOYEE));      
        assertNotNull(employee2);
        assertEquals(2, employeeFacade.getUsers().size());
        
        User employeeById2 = employeeFacade.getUser(employee2.getId());
        assertEquals(employee2, employeeById2);
        
        User employee3 = employeeFacade.addOrModifyEmployee(employee);
        assertEquals(employee, employee3);
        assertEquals(2, employeeFacade.getUsers().size());  
    }

}
