/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business.timesheet.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author eerosihvonen
 */
@Embeddable
public class WeekHours implements Serializable  {
    
    private static final long serialVersionUID = 1L;

    private Double monday;
    private Double tuesday;
    private Double wednesday;
    private Double thursday;
    private Double friday;
    private Double saturday;
    private Double sunday;

    public WeekHours() {
    }

    public WeekHours(Double monday, Double tuesday, Double wednesday, Double thursday, Double friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = null;
        this.sunday = null;
    }

    public WeekHours(Double monday, Double tuesday, Double wednesday, Double thursday, Double friday, Double saturday, Double sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Double getMonday() {
        return monday;
    }

    public void setMonday(Double monday) {
        this.monday = monday;
    }

    public Double getTuesday() {
        return tuesday;
    }

    public void setTuesday(Double tuesday) {
        this.tuesday = tuesday;
    }

    public Double getWednesday() {
        return wednesday;
    }

    public void setWednesday(Double wednesday) {
        this.wednesday = wednesday;
    }

    public Double getThursday() {
        return thursday;
    }

    public void setThursday(Double thursday) {
        this.thursday = thursday;
    }

    public Double getFriday() {
        return friday;
    }

    public void setFriday(Double friday) {
        this.friday = friday;
    }

    public Double getSaturday() {
        return saturday;
    }

    public void setSaturday(Double saturday) {
        this.saturday = saturday;
    }

    public Double getSunday() {
        return sunday;
    }

    public void setSunday(Double sunday) {
        this.sunday = sunday;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.monday);
        hash = 97 * hash + Objects.hashCode(this.tuesday);
        hash = 97 * hash + Objects.hashCode(this.wednesday);
        hash = 97 * hash + Objects.hashCode(this.thursday);
        hash = 97 * hash + Objects.hashCode(this.friday);
        hash = 97 * hash + Objects.hashCode(this.saturday);
        hash = 97 * hash + Objects.hashCode(this.sunday);
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
        final WeekHours other = (WeekHours) obj;
        if (!Objects.equals(this.monday, other.monday)) {
            return false;
        }
        if (!Objects.equals(this.tuesday, other.tuesday)) {
            return false;
        }
        if (!Objects.equals(this.wednesday, other.wednesday)) {
            return false;
        }
        if (!Objects.equals(this.thursday, other.thursday)) {
            return false;
        }
        if (!Objects.equals(this.friday, other.friday)) {
            return false;
        }
        if (!Objects.equals(this.saturday, other.saturday)) {
            return false;
        }
        if (!Objects.equals(this.sunday, other.sunday)) {
            return false;
        }
        return true;
    }
    
    

}
