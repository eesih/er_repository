/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util;

import com.er.application.util.entity.Invocation;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author eerosihvonen
 */
@Singleton
@Startup
@LocalBean
@Path("monitoring")
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class EmployeeResource implements EmployeeResourceMXBean {
    
    private MBeanServer platformMBeanServer;
    private ObjectName objectName = null;
    private final ConcurrentHashMap<String, Invocation> methods = new ConcurrentHashMap<>();
    private AtomicLong exceptionCount;

    @Resource
    SessionContext sc;
    
    @PostConstruct
    public void registerJMXBean()   {
        this.exceptionCount = new AtomicLong();
        try {
            objectName = new ObjectName("ERMonitoring:type=" + this.getClass().getName());
            platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
            platformMBeanServer.registerMBean(sc.getBusinessObject(EmployeeResource.class), objectName);        
        } catch (IllegalStateException | InstanceAlreadyExistsException | MBeanRegistrationException | MalformedObjectNameException | NotCompliantMBeanException e) {
              throw new IllegalStateException("Problem during registration of Monitoring into JMX:" + e);
        }
    }
    
    @Override
    public List<Invocation> getSlowestMethods() {
        return getSlowestMethods(5);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invocation> getSlowestMethods(@QueryParam("max") @DefaultValue("5")
        int maxResult)  {
        List<Invocation> list = new ArrayList<>(methods.values());
        Collections.sort(list);
        Collections.reverse(list);
        if(list.size() > maxResult) {
            return list.subList(0, maxResult);
        }
        return list;
    }
    
    
    @GET
    @Path("exceptionCount")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String getNumberOfExceptions() {
        return String.valueOf(exceptionCount.get());
    }

    @DELETE
    @Override
    public void clear() {
        methods.clear();
        exceptionCount.set(0);
    }
    
    public void add(Invocation invocation) {
        String methodName = invocation.getMethodName();
        if (methods.containsKey(methodName)) {
            Invocation existing = methods.get(methodName);
            if (existing.isSlowerThan(invocation)) {
                return;
            }
        }
        methods.put(methodName, invocation);
    }

    public void add(String methodName, long performance) {
        Invocation invocation = new Invocation(methodName, performance);
        this.add(invocation);
    }

    public void exceptionOccurred(String methodName, Exception e) {
        exceptionCount.incrementAndGet();
    }
    
    @PreDestroy
    public void unregisterMXBean()  {
        try {
            platformMBeanServer.unregisterMBean(this.objectName);
        } catch (InstanceNotFoundException | MBeanRegistrationException e) {
              throw new IllegalStateException("Problem during unregistration of Monitoring into JMX:" + e);
        }
    }


}
