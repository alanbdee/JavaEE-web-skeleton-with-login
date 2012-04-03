/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Agents;
import entities.Documents.documentTypes;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import entities.Documents;
import entities.Companies;
import testBase.MockObjects;
import java.util.List;
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
public class DocumentsFacadeTest extends testBase.IntegrationBase {

    

    public DocumentsFacadeTest() {
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
     * Test of create method, of class DocumentsFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Documents doc = MockObjects.getMockDocument(getMockCompany());
        getTransaction().begin();
        getDocumentsFacade().create(doc);
        getTransaction().commit();
        Documents result = createDocumentsFacade().find(doc.getId());
        assertEquals("the created document should be the same one entered", doc, result);
        assertNotSame("confirm that the two objects are not the same.", doc, result);
        Companies company = result.getCompany();
        assertEquals("the parent company should be the same as the mock one", company, getMockCompany());
        
        doc = MockObjects.getMockDocument(getMockAgent());
        getTransaction().begin();
        getDocumentsFacade().create(doc);
        getTransaction().commit();
        result = createDocumentsFacade().find(doc.getId());
        assertEquals("the created document should be the same one entered", doc, result);
        assertNotSame("confirm that the two objects are not the same.", doc, result);
        Agents agent = result.getAgent();
        assertEquals("the parent agent should be the same as the mock one", agent, getMockAgent());
    }

    /**
     * Test of edit method, of class DocumentsFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Documents doc = MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany());
        Documents result = getDocumentsFacade().find(doc.getId());
        assertEquals("At this point they should be the same.", doc, result);
        String newTitle = "New Mock Title";
        doc.setTitle(newTitle);
        getTransaction().begin();
        getDocumentsFacade().edit(doc);
        getTransaction().commit();
        Documents changed = getDocumentsFacade().find(doc.getId());
        assertEquals("these should be the same", doc, changed);
        assertEquals("the title should be the same (1)", doc.getTitle(), changed.getTitle());
        assertEquals("the title should be the same (2)", newTitle, changed.getTitle());
        String newDesc = "New Description";
        doc.setDescription(newDesc);
        getTransaction().begin();
        getDocumentsFacade().edit(doc);
        getTransaction().commit();
        changed = getDocumentsFacade().find(doc.getId());
        assertEquals("the desc should be the same (1)", doc.getDescription(), changed.getDescription());
        assertEquals("the desc should be the same (2)", newDesc, changed.getDescription());
    }

    /**
     * Test of remove method, of class DocumentsFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        //Companies comp = createMockCompany();
        Documents entity = MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany());
        int currentCount = getDocumentsFacade().count();
        getTransaction().begin();
        getDocumentsFacade().remove(entity);
        getTransaction().commit();
        assertTrue("the new count should be one less then the initial count.", (--currentCount == getDocumentsFacade().count()));
        List<Documents> findAll = getDocumentsFacade().findAll();
        for (Documents document : findAll) {
            getTransaction().begin();
            getDocumentsFacade().remove(document);
            getTransaction().commit();
            assertTrue("in the loop, the count should decrement one each cycle", (--currentCount == getDocumentsFacade().count()));
        }
        assertEquals("all elements should have been removed by now", 0, getDocumentsFacade().count());
    }

    /**
     * Test of find method, of class DocumentsFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Documents doc = MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany());
        Long id = doc.getId();
        Documents result = createDocumentsFacade().find(id);
        assertEquals("The generated doc should be the same as the pulled one", doc, result);
        assertNotSame("compared objects should not be the same.", doc, result);
    }

    /**
     * Test of findAll method, of class DocumentsFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        List<Documents> documents = new ArrayList<Documents>();
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany()));
        List<Documents> findAll = getDocumentsFacade().findAll();
        assertEquals("the count should equal the number of elements pulled as an array", getDocumentsFacade().count(), findAll.toArray().length);
        for (Documents doc : documents) {
            assertTrue("all the created documents should exist in the findAll result set", (findAll.contains(doc)));
        }
    }

    /**
     * Test of findRange method, of class DocumentsFacade.
     */
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int numOfDocs = 32;
        //EJBContainer container = getContainer();
        //DocumentsFacade instance = (DocumentsFacade) container.getContext().lookup("java:global/classes/DocumentsFacade");
        //Companies comp = createMockCompany();
        List<Documents> documents = new ArrayList<Documents>();
        for (int i = 0; i < numOfDocs; i++) {
            documents.add(MockObjects.createMockDocument(getDocumentsFacade(), getMockCompany(), "#" + i));
        }
        int[] range = new int[]{0, 10};
        List expResult = null;
        List result = getDocumentsFacade().findRange(range);
        assertEquals("pulled result set should have a size of 10", 10, result.size());

        List<Documents> findRange = getDocumentsFacade().findRange(range, getMockCompany());
        assertEquals("the number of companies should be 10", 10, findRange.size());

        range = new int[]{0, numOfDocs};
        findRange = getDocumentsFacade().findRange(range, getMockCompany());
        assertEquals("It should pull all the documents of the company", numOfDocs, findRange.size());

        range = new int[]{0, (numOfDocs + 10)};
        findRange = getDocumentsFacade().findRange(range, getMockCompany());
        assertEquals("It tried to pull 40 but should only have pulled 30", numOfDocs, findRange.size());
    }

    /**
     * Test of count method, of class DocumentsFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        int result = getDocumentsFacade().count();
        List<Documents> findAll = getDocumentsFacade().findAll();
        assertEquals("The size of the count should equals the size of all elements", findAll.size(), result);
    }

    /**
     * Test of getCompanyWithMostDocuments method, of class CompaniesFacade.
     */
    @Test
    public void testGetCompanyWithMostDocuments() throws Exception {
        System.out.println("getGroupWithMostDocuments");
        Map<Long, Integer> compCnt = new TreeMap<Long, Integer>();
        List<Documents> findAll = getDocumentsFacade().findAll();
        int i;
        for (Documents documents : findAll) {
            if (compCnt.containsKey(documents.getCompany().getId())) {
                i = compCnt.get(documents.getCompany().getId());
                i++;
                compCnt.put(documents.getCompany().getId(), i);
            } else {
                compCnt.put(documents.getCompany().getId(), 1);
            }
        }
        Long CompanyWMostDocs = null;
        Set<Long> keySet = compCnt.keySet();
        for (Long coid : keySet) {
            CompanyWMostDocs = coid;
        }
        Long result = getDocumentsFacade().getCompanyWithMostDocuments();
        assertEquals("The id of the compnay with the most docs should match that returned.", CompanyWMostDocs, result);
        Companies comp = createCompaniesFacade().find(result);
        assertNotNull("The resulting ID should be a valid company", comp);
    }

//    /**
//     * Test of findAll method, of class DocumentsFacade.
//     */
//    @Test
//    public void testFindAll_String() throws Exception {
//        System.out.println("findAll");
//        String group = "COMPANY";
//        List<Documents> expResult = new ArrayList<Documents>();
//        List<Documents> allDocs = getDocumentsFacade().findAll();
//        documentTypes type, 
//        for (Documents doc : allDocs) {
//            if (doc.getUserType() == Documents.documentTypes.COMPANY) {
//                expResult.add(doc);
//            }
//        }
//        List result = getDocumentsFacade().findAll(group);
//        assertEquals("Both elements should have the same length", expResult.size(), result.size());
//    }
    
//    /**
//     * Test relationship standing
//     */
//    @Test
//    public void testRelationships(){
//        List<Documents> findAll = getDocumentsFacade().findAll();
//        long id;
//        Documents find;
//        DocumentsFacade altFacade = createDocumentsFacade();
//        for (Documents document : findAll) {
//            if(document.getCompany()!=null){
//                assertEquals("The found object should match the documents parent", find.getId(), document.getId());
//                find = altFacade.find(id);
//                assertNotNull("the found parent should exist", find);
//                assertEquals("The found object should match the documents parent", find.getId(), document.getId());
//            }
//        }
//    }
}
