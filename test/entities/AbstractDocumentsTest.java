/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Documents.documentStatus;
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
public class AbstractDocumentsTest {
    
    public AbstractDocumentsTest() {
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
     * Test of setStatus method, of class AbstractDocuments.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        Documents instance = new Documents();
        for (documentStatus s : documentStatus.values()) {
            instance.setStatus(s);
            assertEquals("The status given should match that given", s, instance.getStatus());
        }
    }

}
