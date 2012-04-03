/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import org.junit.Ignore;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import entities.Documents;
import entities.Agents;
import entities.Companies;
import javax.persistence.EntityManager;
import testBase.MockObjects;
import entities.DocumentPages;
import java.util.List;
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
public class DocumentPagesFacadeTest extends testBase.IntegrationBase {

    private Companies mockCompany;
    private Agents mockAgent;
    private Documents document;

    public DocumentPagesFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    @Override
    public void setUp() {
        createDocumentPagesFacade();
        documentPagesFacade = getDocumentPagesFacade();
        this.transaction = documentPagesFacade.getEntityManager().getTransaction();
        //createCompanyFacade();
        this.mockCompany = MockObjects.createMockCompany(companyFacade, transaction);
        createAgentFacade();
        this.mockAgent = MockObjects.createMockAgent(mockCompany, agentsFacade, transaction);
        createDocumentsFacade();
        this.document = MockObjects.createMockDocument(documentsFacade, mockCompany, "Document Data Test");
    }

    @After
    @Override
    public void tearDown() {
    }

    /**
     * Test of create method, of class DocumentsDataFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        byte[] image = null;
        Documents doc = null;
        DocumentPages entity = null;
        DocumentPages find = null;
        long id;
        List<String> testImages = new ArrayList<String>();
        testImages.add("test/testImages/SinglePage.jpg");
        testImages.add("test/testImages/SinglePagePNG.png");
        testImages.add("test/testImages/SinglePagePDF.pdf");
        testImages.add("test/testImages/MultiPagePDF.pdf");
        testImages.add("test/testImages/javaeetutorial6.pdf");
        for (String file : testImages) {
            doc = MockObjects.createMockDocument(documentsFacade, mockCompany, "Test Create Images");
            image = readDocument(file);
            entity = new DocumentPages(document, image);
            transaction.begin();
            documentPagesFacade.create(entity);
            transaction.commit();
            id = entity.getId();
            find = getDocumentPagesFacade().find(id);
            assertEquals("the one entered should be the same as the one found", find, entity);
            assertNotSame("Checked entities should NOT be the same class", find, entity);
        }
        doc = MockObjects.createMockDocument(documentsFacade, mockCompany, "Test multi Images (png)");
        DocumentPages page1 = new DocumentPages(doc, readDocument("test/testImages/MultipagePNG.png"), 0);
        DocumentPages page2 = new DocumentPages(doc, readDocument("test/testImages/MultipagePNG-1.png"), 1);
        DocumentPages page3 = new DocumentPages(doc, readDocument("test/testImages/MultipagePNG-2.png"), 2);
        transaction.begin();
        documentPagesFacade.create(page1);
        documentPagesFacade.create(page2);
        documentPagesFacade.create(page3);
        transaction.commit();
        id = doc.getId();
        List<DocumentPages> pages = documentPagesFacade.findAll(doc);
        assertEquals("The recrieved Document whould have three document pages", 3, pages.size());
        assertEquals("Page1 should be the same", page1, pages.get(0));
        assertEquals("Page2 should be the same", page2, pages.get(1));
        assertEquals("Page3 should be the same", page3, pages.get(2));

        doc = MockObjects.createMockDocument(documentsFacade, mockCompany, "Test multi Images (JPG)");
        page1 = new DocumentPages(doc, readDocument("test/testImages/MultiPage.jpg"), 0);
        page2 = new DocumentPages(doc, readDocument("test/testImages/MultiPage-1.jpg"), 1);
        page3 = new DocumentPages(doc, readDocument("test/testImages/MultiPage-2.jpg"), 2);
        transaction.begin();
        documentPagesFacade.create(page1);
        documentPagesFacade.create(page2);
        documentPagesFacade.create(page3);
        transaction.commit();
        id = doc.getId();
        pages = documentPagesFacade.findAll(doc);
        assertEquals("The recrieved Document whould have three document pages", 3, pages.size());
        assertEquals("Page1 should be the same", page1, pages.get(0));
        assertEquals("Page2 should be the same", page2, pages.get(1));
        assertEquals("Page3 should be the same", page3, pages.get(2));


    }

    /**
     * Test of edit method, of class DocumentsDataFacade.
     */
    @Test
    @Ignore
    public void testEdit() throws Exception {
        System.out.println("edit");
        DocumentPages entity = null;
        documentPagesFacade.edit(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class DocumentsDataFacade.
     */
    @Test
    @Ignore
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = null;
        DocumentPages expResult = null;
        DocumentPages result = documentPagesFacade.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class DocumentsDataFacade.
     */
    @Test
    @Ignore
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        List expResult = null;
        List result = documentPagesFacade.findRange(range);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DocumentsDataFacade.
     */
    @Test
    @Ignore
    public void testRemove() throws Exception {
        System.out.println("remove");
        DocumentPages entity = null;
        documentPagesFacade.remove(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class DocumentsDataFacade.
     */
    @Test
    @Ignore
    public void testCount() throws Exception {
        System.out.println("count");
        int expResult = 0;
        int result = documentPagesFacade.count();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private byte[] readDocument(String fName) {
        byte[] data = null;
        try {
            data = MockObjects.readDocument(fName);
        } catch (FileNotFoundException e) {
            fail("Testing Image: " + fName + " Not Found");
        } catch (IOException e) {
            fail("IO Error Reading File");
        }
        return data;

    }

    /**
     * Test of getEntityManager method, of class DocumentPagesFacade.
     */
    @Test
    @Ignore
    public void testGetEntityManager() throws Exception {
        System.out.println("getEntityManager");
        EntityManager expResult = null;
        EntityManager result = documentPagesFacade.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class DocumentPagesFacade.
     */
    @Test
    @Ignore
    public void testFindAll_0args() throws Exception {
        System.out.println("findAll");
        List expResult = null;
        List result = documentPagesFacade.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class DocumentPagesFacade.
     */
    @Test
    @Ignore
    public void testFindAll_long() throws Exception {
        System.out.println("findAll");
        long documentID = document.getId();
        List expResult = null;
        List result = documentPagesFacade.findAll(document);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEntityManager method, of class DocumentPagesFacade.
     */
    @Test
    @Ignore
    public void testSetEntityManager() throws Exception {
        System.out.println("setEntityManager");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
