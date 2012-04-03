/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.AbstractContact.contactTypes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alan
 */
public class AbstractContactTest {
    
    public AbstractContactTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getMapType method, of class AbstractContact.
     */
    @Test
    public void testGetMapType() {
        System.out.println("getMapType");
        AbstractContact instance = new AbstractContactImpl();
        instance.setMapType(contactTypes.PHONE);
        contactTypes expResult = contactTypes.PHONE;
        contactTypes result = instance.getMapType();
        assertEquals("Returned contact type (PHONE) should be the enum", expResult, result);
        expResult = contactTypes.EMAIL;
        instance.setMapType(contactTypes.EMAIL);
        result = instance.getMapType();
        assertEquals("Returned contact type (EMAIL) should be the enum", expResult, result);
    }

    /**
     * Test of setMapType method, of class AbstractContact.
     */
    @Test
    public void testSetMapType() {
        System.out.println("setMapType");
        contactTypes mapType = contactTypes.EMAIL;
        AbstractContact instance = new AbstractContactImpl();
        instance.setMapType(mapType);
        assertEquals("Set enum should match", instance.getMapType(), mapType);
    }


    public class AbstractContactImpl extends AbstractContact {
    }
}
