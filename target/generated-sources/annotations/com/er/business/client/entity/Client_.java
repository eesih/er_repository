package com.er.business.client.entity;

import com.er.business.client.entity.Project;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-21T12:51:53")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> contactPersonName;
    public static volatile ListAttribute<Client, Project> projects;
    public static volatile SingularAttribute<Client, String> companyName;
    public static volatile SingularAttribute<Client, String> postcode;
    public static volatile SingularAttribute<Client, String> phonenumber;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> email;
    public static volatile SingularAttribute<Client, String> adderss;
    public static volatile SingularAttribute<Client, Date> timestamp;

}