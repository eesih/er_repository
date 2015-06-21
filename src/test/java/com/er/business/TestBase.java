/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.business;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author eerosihvonen
 */
public class TestBase {
    
    protected static EJBContainer ec;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
                "/Applications/NetBeans/glassfish-4.0/glassfish");      
        ec = EJBContainer.createEJBContainer(properties);
       
    }

    @AfterClass
    public static void tearDownClass() {
        ec.close();
    }
    
}
