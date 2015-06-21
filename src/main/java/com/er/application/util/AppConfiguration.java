/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author eerosihvonen
 */
@Startup
@Singleton
@Path("configuration")
public class AppConfiguration {

   private static final Map<String, String> properties = new HashMap<>();
   
   private static ResourceBundle resourceBundle;
   
   @PostConstruct
    public void init()  {
       resourceBundle = ResourceBundle.getBundle("appConf");
       
       readResourceBundleValues();       
   }

    private static void readResourceBundleValues() {
        
        for(String key : resourceBundle.keySet())   {
            properties.put(key, resourceBundle.getString(key));
        }
    }
    
    @Produces
    public String getString(InjectionPoint injectionPoint)  {
        String key = injectionPoint.getMember().getName();  
        if(properties.containsKey(key)) {
            return properties.get(key);
        } 
        return null;
    }
    
    @GET
    @javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public String getConfiguration()    {
        return properties.toString();
    }

}
