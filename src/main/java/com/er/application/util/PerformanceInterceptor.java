/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author eerosihvonen
 */
public class PerformanceInterceptor {
    
    @Inject 
    Logger logger;
    
    @Inject
    EmployeeResource employeeResource;
    
    @AroundInvoke
    public Object logPerformance(InvocationContext ic) throws Exception {
        Long initTime = System.currentTimeMillis();
        String methodName = ic.getMethod().getName();
        String targetName = ic.getTarget().toString();
        logger.entering(targetName, methodName);     
        try {
           return ic.proceed();
        } catch(Exception e) {
            employeeResource.exceptionOccurred(methodName, e);
            logger.log(Level.SEVERE, "!!!During invocation of: {0} exception occured: {1}", new Object[]{methodName, e});
            throw new RuntimeException(e);
        } finally {           
            if(logger.isLoggable(Level.FINE))   {
                Long performance = System.currentTimeMillis() - initTime;
                employeeResource.add(methodName, performance);
                logger.log(logger.getLevel(), "Method {0} took {1} millsecs", new Object[]{methodName, performance});
                logger.exiting(targetName, methodName); 
            }
        }    
    
    }
    
}
