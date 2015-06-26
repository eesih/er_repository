/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.timesheet.entity;

import com.er.business.client.entity.Activity;
import com.er.business.client.entity.Client;
import com.er.business.client.entity.Project;
import com.er.business.user.entity.User;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eerosihvonen
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timesheet.findAll", query = "SELECT t FROM Timesheet t"),
    @NamedQuery(name = "Timesheet.findByEmp", query = "SELECT t FROM Timesheet t WHERE t.employee = :emp"),
    @NamedQuery(name = "Timesheet.findByEmpDate", query = "SELECT t FROM Timesheet t WHERE t.employee = :emp AND t.date >= :startDate AND t.date <= :endDate")
})
public class Timesheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private User employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ACTIVITY_ID")
    private Activity activity;
    
    private Double hours;
    
    public Timesheet() {
    }
    
    public Timesheet(User employee,
            Client client, Project project, Activity activity, Double hours) {
        this.date = new Date();
        this.employee = employee;
        this.client = client;
        this.project = project;
        this.activity = activity;
        this.hours = hours;
    }

    public Timesheet(Date date, User employee,
            Client client, Project project, Activity activity, Double hours) {
        this.date = date;
        this.employee = employee;
        this.client = client;
        this.project = project;
        this.activity = activity;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.activity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Timesheet other = (Timesheet) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

}
