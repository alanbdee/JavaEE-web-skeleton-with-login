/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import org.junit.Ignore;
import java.util.LinkedList;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import entities.Companies;
import org.junit.Test;
import testBase.IntegrationBase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import testBase.MockObjects;
import static org.junit.Assert.*;

/**
 *
 * @author alan
 */
public class CompaniesFacadeIT extends IntegrationBase {


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
     * Test of create method, of class CompaniesFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("Create Companies");
        Companies mockCompany = MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        Companies find = getCompanyFacade().find(mockCompany.getId());
        assertEquals("Entities should be the same", mockCompany, find);
        
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        removeAllRecords(getCompanyFacade());
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        List<Companies> all = getCompanyFacade().findAll();
        all = getCompanyFacade().findAll();
        assertEquals("There should be only one record, using findAll().length", all.toArray().length, 1);
        assertEquals("There should be only one record, pulled with count()", getCompanyFacade().count(), 1);
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        List<Companies> findAll = getCompanyFacade().findAll();
        assertEquals("New list should have one more entry then previously", findAll.toArray().length, (all.toArray().length + 1));

        Companies badCo = new Companies();

        try {
            //Null name
            getTransaction().begin();
            getCompanyFacade().create(badCo);
            fail("NULL company added!");//should never reach this point.
        } catch (ConstraintViolationException e) {
        } finally {
            getTransaction().rollback();
        }
        try {
            //Null start date
            getTransaction().begin();
            badCo.setName("Bad Company");
            getCompanyFacade().create(badCo);
            fail("company added without start date!");//should never reach this point.
        } catch (ConstraintViolationException e) {
        } catch (Exception e) {
            fail("Unknown Exception found:");
        } finally {
            getTransaction().rollback();
        }
        try {
            //Null end date
            getTransaction().begin();
            badCo.setServiceStart(new Date());
            getCompanyFacade().create(badCo);
            fail("company added without end date!");//should never reach this point.
        } catch (ConstraintViolationException e) {
        } finally {
            getTransaction().rollback();
        }
    }
    /**
     * Test of edit method, of class CompaniesFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("Edit Companies");
        //EJBContainer container = getContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        Companies entity = MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        long id = entity.getId();
        Companies found = getCompanyFacade().find(id);
        assertEquals("The created entity should match the pulled entity.", entity, found);
        found = null;
        entity.setName("New Test Name");
        getTransaction().begin();
        getCompanyFacade().edit(entity);
        getTransaction().commit();
        found = getCompanyFacade().find(id);
        assertEquals("The names should match: ", entity.getName(), found.getName());
        assertNotSame("Entites should not be the same.", getCompanyFacade(), found);
    }

    /**
     * Test of remove method, of class CompaniesFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove Companies");
        Companies entity = null;
        //EJBContainer container = getContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        List<Companies> findAll = getCompanyFacade().findAll();
        int len = findAll.toArray().length;
        assertTrue("companyInstances should have been created (3)", (len > 0));
        for (Companies company : findAll) {
            getTransaction().begin();
            getCompanyFacade().remove(company);
            getTransaction().commit();
            len--;
            findAll = getCompanyFacade().findAll();
            assertEquals("One record should be removed, the length should be one less. count() ", getCompanyFacade().count(), len);
            assertEquals("One record should be removed, the length should be one less. findAll() ", findAll.toArray().length, len);
        }
        assertEquals("All records should have been removed: count()", getCompanyFacade().count(), 0);
        assertEquals("All records should have been removed: findAll length", getCompanyFacade().findAll().toArray().length, 0);
    }

    /**
     * Test of find method, of class CompaniesFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find Companies");
        //EJBContainer container = getContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        Companies expResult = MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        CompaniesFacade companyInstance2 = createCompaniesFacade();
        Companies result = companyInstance2.find(expResult.getId());
        assertEquals("The created mock should be the same on the one found", expResult, result);
        assertNotSame("Objects should not be the same item.", expResult, result);
        Companies find = getCompanyFacade().find(123456789L);
        assertNull("Given bad number it should be empty", find);

    }

    /**
     * Test of findAll method, of class CompaniesFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll Companies");
        //EJBContainer container = getContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        removeAllRecords(getCompanyFacade());
        List<Companies> expResult = new LinkedList();
        expResult.add(MockObjects.createMockCompany(getCompanyFacade(), getTransaction()));
        expResult.add(MockObjects.createMockCompany(getCompanyFacade(), getTransaction()));
        expResult.add(MockObjects.createMockCompany(getCompanyFacade(), getTransaction()));
        expResult.add(MockObjects.createMockCompany(getCompanyFacade(), getTransaction()));
        List<Companies> result = getCompanyFacade().findAll();
        assertEquals("The pulled results should be the same on the ones entered", expResult.size(), result.size());
        assertNotSame("Confirm that the comparing objects are not the same, however the results they hold should be.", expResult, result);
        assertTrue("element should exist in other list", result.containsAll(result));
        for (Companies company : result) {
            getTransaction().begin();
            getCompanyFacade().remove(company);
            getTransaction().commit();
        }
        result = getCompanyFacade().findAll();
        assertTrue("After removing all the enteries it should be empty now.", (result.isEmpty()));
        assertEquals("After removing all the enteries it should be empty now.", getCompanyFacade().count(), 0);
    }

    /**
     * Test of count method, of class CompaniesFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count Companies");
        //EJBContainer container = getContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        assertEquals("The count should be the same as findAll().toArray().length", getCompanyFacade().count(), getCompanyFacade().findAll().toArray().length);
        removeAllRecords(getCompanyFacade());
        assertEquals("After all records have been removed the count should be 0", getCompanyFacade().count(), 0);
        assertEquals("After all records have been removed the list of all records should also be empty", getCompanyFacade().findAll().toArray().length, 0);
        MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        assertEquals("After creating one record there should be one", getCompanyFacade().count(), 1);
        assertEquals("The count should be the same as findAll().toArray().length", getCompanyFacade().count(), getCompanyFacade().findAll().toArray().length);
    }


    private void removeAllRecords(CompaniesFacade companyInstance) {
        List<Companies> all = companyInstance.findAll();
        getTransaction().begin();
        for (Companies company : all) {
            companyInstance.remove(company);
        }
        getTransaction().commit();
    }

    /**
     * Test of findRange method, of class CompaniesFacade.
     */
    @Test
    @Ignore
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        //CompaniesFacade companyInstance = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        List expResult = null;
        List result = getCompanyFacade().findRange(range);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
