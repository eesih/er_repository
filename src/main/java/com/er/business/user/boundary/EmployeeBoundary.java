/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.user.boundary;

import com.er.business.user.entity.User;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eerosihvonen
 */
@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeBoundary {
    
    @Inject
    EmployeeFacade employeeFacade;
    
    @Inject
    Logger log;
   
    @GET
    public List<User> employees()  {
        return employeeFacade.getEmployees();
    }    
    
    @GET
    @Path("employee")
    public User employee(@PathParam("id") Long id)  {
        return employeeFacade.getEmployee(id);
    }    
    
    @POST
    @Path("save")
    public Response saveEmployee(User employee)    {
        employee = employeeFacade.addOrModifyEmployee(employee);
        return Response.ok(employee).build();
    }
    
}
