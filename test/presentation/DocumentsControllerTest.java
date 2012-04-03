/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.DocumentsFacade;
import entities.Documents;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import presentation.util.PaginationHelper;

/**
 *
 * @author alan
 */
public class DocumentsControllerTest extends testBase.TestContainer {
    
    public DocumentsControllerTest() {
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
     * Test of getDocuments method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetDocuments() throws NamingException {
        System.out.println("getDocuments");
        EJBContainer container = getContainer();
        DocumentsFacade docFacade = (DocumentsFacade) container.getContext().lookup("java:global/classes/DocumentsFacade");
        DocumentsController instance = new DocumentsController();
        instance.setEjbFacade(docFacade);
        
        List<Documents> result = instance.getDocuments("COMPANY");
        List<Documents> expResult = docFacade.findAll();
        assertEquals("should have come content...", expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDocuments method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testSetDocuments() {
        System.out.println("setDocuments");
        Documents documents = null;
        DocumentsController instance = new DocumentsController();
        //instance.setDocuments(documents);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelected method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetSelected() {
        System.out.println("getSelected");
        DocumentsController instance = new DocumentsController();
        Documents expResult = null;
        Documents result = instance.getSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPagination method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetPagination() {
        System.out.println("getPagination");
        DocumentsController instance = new DocumentsController();
        PaginationHelper expResult = null;
        PaginationHelper result = instance.getPagination();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareList method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testPrepareList() {
        System.out.println("prepareList");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.prepareList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareView method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testPrepareView() {
        System.out.println("prepareView");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.prepareView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareCreate method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testPrepareCreate() {
        System.out.println("prepareCreate");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.prepareCreate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testCreate() {
        System.out.println("create");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareEdit method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testPrepareEdit() {
        System.out.println("prepareEdit");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.prepareEdit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testUpdate() {
        System.out.println("update");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.update();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testDestroy() {
        System.out.println("destroy");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.destroy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroyAndView method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testDestroyAndView() {
        System.out.println("destroyAndView");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.destroyAndView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetItems() {
        System.out.println("getItems");
        DocumentsController instance = new DocumentsController();
        DataModel expResult = null;
        DataModel result = instance.getItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testNext() {
        System.out.println("next");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.next();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of previous method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testPrevious() {
        System.out.println("previous");
        DocumentsController instance = new DocumentsController();
        String expResult = "";
        String result = instance.previous();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsAvailableSelectMany method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetItemsAvailableSelectMany() {
        System.out.println("getItemsAvailableSelectMany");
        DocumentsController instance = new DocumentsController();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.getItemsAvailableSelectMany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsAvailableSelectOne method, of class DocumentsController.
     */
    @Test
    @Ignore
    public void testGetItemsAvailableSelectOne() {
        System.out.println("getItemsAvailableSelectOne");
        DocumentsController instance = new DocumentsController();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.getItemsAvailableSelectOne();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
