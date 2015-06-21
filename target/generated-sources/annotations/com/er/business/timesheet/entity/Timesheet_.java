package com.er.business.timesheet.entity;

import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import com.er.business.user.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-21T12:51:53")
@StaticMetamodel(Timesheet.class)
public class Timesheet_ { 

    public static volatile SingularAttribute<Timesheet, Integer> week;
    public static volatile SingularAttribute<Timesheet, Integer> month;
    public static volatile SingularAttribute<Timesheet, Activity> activity;
    public static volatile SingularAttribute<Timesheet, Integer> year;
    public static volatile SingularAttribute<Timesheet, Client> client;
    public static volatile SingularAttribute<Timesheet, Project> project;
    public static volatile SingularAttribute<Timesheet, Long> id;
    public static volatile SingularAttribute<Timesheet, User> employee;

}