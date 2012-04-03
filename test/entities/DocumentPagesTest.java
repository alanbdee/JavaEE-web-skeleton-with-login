/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import testBase.MockObjects;
import static org.junit.Assert.*;

/**
 *
 * @author alan
 */
public class DocumentPagesTest {

    public DocumentPagesTest() {
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
     * Test of getOCR method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetOCR() {
        System.out.println("getOCR");
        DocumentPages instance = new DocumentPages();
        String expResult = "";
        String result = instance.getOCR();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOCR method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testSetOCR() {
        System.out.println("setOCR");
        String OCR = "";
        DocumentPages instance = new DocumentPages();
        instance.setOCR(OCR);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDocHash method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetDocHash() {
        System.out.println("getDocHash");
        DocumentPages instance = new DocumentPages();
        String expResult = "";
        String result = instance.getDocHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDocHash method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testSetDocHash() {
        System.out.println("setDocHash");
        String docHash = "";
        DocumentPages instance = new DocumentPages();
        instance.setDocHash(docHash);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDocument method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetDocument() {
        System.out.println("getDocument");
        DocumentPages instance = new DocumentPages();
        Documents expResult = null;
        Documents result = instance.getDocument();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDocument method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testSetDocument() {
        System.out.println("setDocument");
        Documents document = null;
        DocumentPages instance = new DocumentPages();
        instance.setDocument(document);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetImage() {
        System.out.println("getImage");
        DocumentPages instance = new DocumentPages();
        byte[] expResult = null;
        byte[] result = instance.getImage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImage method, of class DocumentPages.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        byte[] image = null;
        try {
            image = MockObjects.readDocument();
        } catch (FileNotFoundException ex) {
            fail("file not found: "+ex);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        DocumentPages instance = new DocumentPages();
        instance.setImage(image);
        assertEquals(image, instance.getImage());
        String hash = DigestUtils.md5Hex(image);
        assertEquals("Hash code should be set and equal to: ", hash, instance.getDocHash());
    }

    /**
     * Test of getStatus method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetStatus() {
        System.out.println("getStatus");
        DocumentPages instance = new DocumentPages();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        DocumentPages instance = new DocumentPages();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testGetId() {
        System.out.println("getId");
        DocumentPages instance = new DocumentPages();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        DocumentPages instance = new DocumentPages();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testHashCode() {
        System.out.println("hashCode");
        DocumentPages instance = new DocumentPages();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        DocumentPages instance = new DocumentPages();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DocumentPages.
     */
    @Test
    @Ignore
    public void testToString() {
        System.out.println("toString");
        DocumentPages instance = new DocumentPages();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
