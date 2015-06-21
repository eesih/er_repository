/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.application.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author eerosihvonen
 */
public class LoggerProducer {
    
    @Inject
    private String loggingLevel;
    
    @Produces 
    private Logger createLogger(InjectionPoint injectionPoint) {
        Logger log = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        log.setLevel(readLevel());        
        return log;
    }

    private Level readLevel() {
        Level level = null;
        if(loggingLevel == null || loggingLevel.isEmpty())  {
            level = Level.INFO;
        } else  {
            try {
                level = Level.parse(loggingLevel);
            } catch(IllegalArgumentException e)    {
                level = Level.INFO;
            }
        }
        return level;
    }

}
