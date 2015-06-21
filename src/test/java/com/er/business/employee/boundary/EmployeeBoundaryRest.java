/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.employee.boundary;

import com.er.business.user.entity.User;
import com.er.business.user.entity.UserRoles;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.persistence.exceptions.JAXBException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author eerosihvonen
 */
public class EmployeeBoundaryRest {

    private static URI uri = UriBuilder.fromUri("http://localhost/employeeResources/resources/employees/save").port(8080).build();
    private static URI uri_get = UriBuilder.fromUri("http://localhost/employeeResources/resources/employees").port(8080).build();

    private static Client client = ClientBuilder.newClient();

    @Test
    public void shouldNotCreateNullEmployee() throws JAXBException {
        // POSTs an Null User
        Response response = client.target(uri).request().post(Entity.entity(null,
                MediaType.APPLICATION_JSON
        ));

        assertEquals(Response.Status.BAD_REQUEST.toString(), response.getStatusInfo().toString());
    }

    @Test
    public void createEmployee() throws JAXBException {
        // POSTs an User
        
        User employee = new User("Rest emp", UserRoles.CONSULTANT);
        
        Response response = client.target(uri).request().post(Entity.entity(employee,
                MediaType.APPLICATION_JSON
        ));
        
        assertEquals(Response.Status.OK.toString(), response.getStatusInfo().toString());
    }
    
    @Test
    public void getEmployees() throws JAXBException {
        // GETs an User
        
        
        Response response = client.target(uri_get).request().get();
        
        System.out.println("EERO " + response.getEntity());    
        
    }

}
