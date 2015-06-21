package com.er.business.user.entity;

import com.er.business.user.entity.UserRoles;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-21T12:51:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> birthday;
    public static volatile SingularAttribute<User, UserRoles> role;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Date> added;
    public static volatile SingularAttribute<User, String> postcode;
    public static volatile SingularAttribute<User, String> phonenumber;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Date> modified;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, BigDecimal> salary;
    public static volatile SingularAttribute<User, Integer> version;
    public static volatile SingularAttribute<User, String> email;

}