/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.application.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eerosihvonen
 */
public class PersistenceProvider {
   
   @Produces 
   @PersistenceContext(unitName = "erpu")
   private EntityManager em;
    
}
