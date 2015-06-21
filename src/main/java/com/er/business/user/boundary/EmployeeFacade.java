/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.user.boundary;

import com.er.application.util.PerformanceInterceptor;
import com.er.business.user.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

/**
 *
 * @author eerosihvonen
 */
@Stateless
@Interceptors(PerformanceInterceptor.class)
public class EmployeeFacade {
    
    @Inject
    private EntityManager em;

    public List<User> getEmployees()    {
        return em.createNamedQuery("Employee.findAll", User.class).getResultList();
    }
    
    public User getEmployee(Long id)    {
        return em.createNamedQuery("Employee.findById", User.class)
                .setParameter("id", id).getSingleResult();
    }
       
    public User addOrModifyEmployee(User employee) {
        return em.merge(employee);
    }
}
