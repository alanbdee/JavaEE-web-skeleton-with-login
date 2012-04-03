/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Documents.documentStatus;
import entities.Documents.documentTypes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alan
 */
public class DocumentsTest {
    
    public DocumentsTest() {
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
     * Test of getUserType method, of class Documents.
     */
    @Test
    public void testGetUserType() {
        System.out.println("getUserType");
        Documents instance = new Documents();
        documentTypes expResult = null;
        for (documentTypes type : documentTypes.values()) {
            //instance.setUserType(type);
            //expResult = instance.getUserType();
            assertEquals("the loop value should be the same as the one set and then gotten.", expResult, type);
        }
    }


    /**
     * Test of getStatus method, of class Documents.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Documents instance = new Documents();
        documentStatus expResult = null;
        for (documentStatus status : documentStatus.values()) {
            instance.setStatus(status);
            expResult = instance.getStatus();
            assertEquals("the loop value should be the same as the one set and then gotten.", expResult, status);
        }
    }

}
