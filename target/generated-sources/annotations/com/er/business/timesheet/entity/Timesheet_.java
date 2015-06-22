package com.er.business.timesheet.entity;

import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import com.er.business.user.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-22T18:02:58")
@StaticMetamodel(Timesheet.class)
public class Timesheet_ { 

    public static volatile SingularAttribute<Timesheet, Date> date;
    public static volatile SingularAttribute<Timesheet, Double> hours;
    public static volatile SingularAttribute<Timesheet, Activity> activity;
    public static volatile SingularAttribute<Timesheet, Client> client;
    public static volatile SingularAttribute<Timesheet, Project> project;
    public static volatile SingularAttribute<Timesheet, Long> id;
    public static volatile SingularAttribute<Timesheet, User> employee;

}