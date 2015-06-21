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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Timesheet.findByEmpMonthYear", query = "SELECT t FROM Timesheet t WHERE t.employee = :emp AND t.month = :month AND t.year = :year"),
    @NamedQuery(name = "Timesheet.findByEmpWeekMonthYear", query = "SELECT t FROM Timesheet t WHERE t.employee = :emp AND t.week = :week AND t.month = :month AND t.year = :year")})
public class Timesheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer week;

    @NotNull
    @Column(name = "MONTH_MM")
    private Integer month;

    @NotNull
    @Column(name = "YEAR_YYYY")
    private Integer year;

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
    


    public Timesheet() {
    }

    public Timesheet(Integer week, Integer month, Integer year, User employee,
            Client client, Project project, Activity activity) {
        this.week = week;
        this.month = month;
        this.year = year;
        this.employee = employee;
        this.client = client;
        this.project = project;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.week);
        hash = 19 * hash + Objects.hashCode(this.month);
        hash = 19 * hash + Objects.hashCode(this.year);
        hash = 19 * hash + Objects.hashCode(this.employee);
        hash = 19 * hash + Objects.hashCode(this.client);
        hash = 19 * hash + Objects.hashCode(this.project);
        hash = 19 * hash + Objects.hashCode(this.activity);
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
        if (!Objects.equals(this.week, other.week)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        return true;
    }

}
