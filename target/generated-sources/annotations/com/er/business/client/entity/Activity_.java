package com.er.business.client.entity;

import com.er.business.timesheet.entity.WeekHours;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-21T12:51:53")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, BigDecimal> price;
    public static volatile SingularAttribute<Activity, Double> multiplier;
    public static volatile SingularAttribute<Activity, WeekHours> weekHours;
    public static volatile SingularAttribute<Activity, String> name;
    public static volatile SingularAttribute<Activity, String> description;
    public static volatile SingularAttribute<Activity, Long> id;

}