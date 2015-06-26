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
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserBoundary {
    
    @Inject
    UserFacade userFacade;
    
    @Inject
    Logger log;
   
    @GET
    public List<User> employees()  {
        return userFacade.getUsers();
    }    
    
    @GET
    @Path("user")
    public User user(@PathParam("id") Long id)  {
        return userFacade.getUser(id);
    }    
    
    @POST
    @Path("save")
    public Response saveUser(User user)    {
        user = userFacade.addOrModifyEmployee(user);
        return Response.ok(user).build();
    }
    
}
