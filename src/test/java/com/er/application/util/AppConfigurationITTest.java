/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.application.util;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author eerosihvonen
 */

@RunWith(Arquillian.class)
public class AppConfigurationITTest {
   
    @Inject
    AppConfiguration appconf;
    
    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(AppConfiguration.class)
                .addAsManifestResource(
                        new ByteArrayAsset("<beans/>".getBytes()),
                        ArchivePaths.create("beans.xml"));
    }
    
    @Test
    public void testAppConfiguration()  {
        assertNotNull(appconf);
    }
    
    @Test
    public void testAppConfigurationContentNotNull()   {
        assertNotNull(appconf.getConfiguration());
    }
    
    @Test
    public void testAppConfigurationContent()   {
        assertTrue(appconf.getConfiguration().contains("name=employeeResource"));
    }
    
    
}
