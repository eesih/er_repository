/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.application.util;

import com.er.application.util.entity.Invocation;
import java.util.List;

/**
 *
 * @author eerosihvonen
 */
public interface EmployeeResourceMXBean {
    List<Invocation> getSlowestMethods();
    String getNumberOfExceptions();
    void clear();   
}
