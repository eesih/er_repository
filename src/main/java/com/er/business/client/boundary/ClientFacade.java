/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.client.boundary;

import com.er.application.util.PerformanceInterceptor;
import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

/**
 *
 * @author eerosihvonen
 */
@Stateless
@Interceptors(PerformanceInterceptor.class)
public class ClientFacade {

   @Inject
   private EntityManager em;
   
   public Client addOrUpdateClient(Client client)   {
       return em.merge(client);
   }
   
   public Project addOrUpdateProject(Project project)   {
       return em.merge(project);
   }
      
   public Activity addOrUpdateActivity(Activity activity)   {
       return em.merge(activity);
   }
   
   public List<Client> getClients() {
       return em.createNamedQuery("Client.findALL", Client.class).getResultList();
   }
   
   public List<Project> getProjects() {
       return em.createNamedQuery("Project.findALL", Project.class).getResultList();
   }
   
   public List<Activity> getActivities() {
       return em.createNamedQuery("Activity.findALL", Activity.class).getResultList();
   }  
   
  
}
