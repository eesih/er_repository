/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util.entity;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eerosihvonen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Invocation implements Comparable<Invocation>{
    
    @XmlAttribute
    private String methodName;
    @XmlAttribute
    private Long invocationPerformance;

    public Invocation() {
    }

    public Invocation(String methodName, Long invocationPerformance) {
        this.methodName = methodName;
        this.invocationPerformance = invocationPerformance;
    }
    
    public boolean isSlowerThan(Invocation invocation){
        return this.compareTo(invocation) > 0;
    }

    @Override
    public int compareTo(Invocation anotherInvocation) {
        return this.invocationPerformance.compareTo(anotherInvocation.invocationPerformance);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getInvocationPerformance() {
        return invocationPerformance;
    }

    public void setInvocationPerformance(Long invocationPerformance) {
        this.invocationPerformance = invocationPerformance;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.methodName);
        hash = 89 * hash + Objects.hashCode(this.invocationPerformance);
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
        final Invocation other = (Invocation) obj;
        if (!Objects.equals(this.methodName, other.methodName)) {
            return false;
        }
        if (!Objects.equals(this.invocationPerformance, other.invocationPerformance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invocation{" + "methodName=" + methodName + ", invocationPerformance=" + invocationPerformance + '}';
    }
    
    
    
    
}
