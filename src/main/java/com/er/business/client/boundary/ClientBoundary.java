/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.client.boundary;

import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author eerosihvonen
 */
@Path("clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientBoundary {
    
    @Inject
    ClientFacade clientFacade;
    
    @Inject
    Logger log;
    
    @GET
    public List<Client> clients()   {
        return clientFacade.getClients();
    }
    
    @GET
    @Path("projects")
    public List<Project> projects() {
        return clientFacade.getProjects();
    }
    
    @GET
    @Path("activities")
    public List<Activity> activities()  {
        return clientFacade.getActivities();
    }
    
    @POST
    @Path("client")
    public Client saveClient(Client client) {
        return clientFacade.addOrUpdateClient(client);
    }
    
    @POST
    @Path("project")
    public Project saveProject(Project project) {
        return clientFacade.addOrUpdateProject(project);
    }
    
    @POST
    @Path("activity")
    public Activity saveActivity(Activity activity) {
        return clientFacade.addOrUpdateActivity(activity);
    }
    
}
