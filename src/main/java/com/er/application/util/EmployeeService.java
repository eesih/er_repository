/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util;

import com.er.business.timesheet.entity.Timesheet;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author eerosihvonen
 */
public class EmployeeService {
    
    @Inject
    Logger logger;
    
    public void addTimesheet(@Observes Timesheet timesheet)  {
        logger.log(logger.getLevel(), "Timesheet added!!");
    }
    
}
