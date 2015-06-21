/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.timesheet.boundary;

import com.er.business.timesheet.entity.Timesheet;
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
@Path("timesheets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimesheetBoundary {
    
    @Inject
    TimesheetFacade timesheetFacade;
    
    @Inject
    Logger log;
    
    @GET
    public List<Timesheet> timesheets() {
        return timesheetFacade.getTimesheets();
    }
    
    @POST
    @Path("save")
    public Timesheet save(Timesheet timesheet)  {
        return timesheetFacade.addOrUpdateTimesheet(timesheet);
    }
    
}
