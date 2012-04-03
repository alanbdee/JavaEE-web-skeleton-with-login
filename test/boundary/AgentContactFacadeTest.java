/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import java.util.ArrayList;
import entities.AbstractContact.contactTypes;
import entities.Agents;
import entities.Companies;
import entities.AgentContact;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import testBase.IntegrationBase;
import testBase.MockObjects;
import static org.junit.Assert.*;

/**
 *
 * @author Alan
 */
public class AgentContactFacadeTest extends  IntegrationBase{

    public AgentContactFacadeTest() {
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
    private String mockPhone = "801-721-4796";
    private String mockEmail = "alan@allgo.com";

    /**
     * Test of create method, of class AgentContactFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("AgentContactFacade: create");
        AgentContact entity = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(entity);
        getTransaction().commit();
        Long id = entity.getId();
        AgentContact find = createAgentContactFacade().find(id);
        assertNotSame("The two comparing entities should not be the same object", entity, find);
        assertEquals("The two comparing entities should have the same values", entity, find);
        assertEquals("The entities agent should be the same.", getMockAgent(), find.getAgent());
        assertEquals("The entity's type shoudl be the same", contactTypes.PHONE, find.getMapType());
        assertEquals("The entity's value should be the same", mockPhone, find.getMapValue());
        assertEquals("The entities agent should be the same.", getMockAgent(), entity.getAgent());
        assertEquals("The entity's type shoudl be the same", contactTypes.PHONE, entity.getMapType());
        assertEquals("The entity's value should be the same", mockPhone, entity.getMapValue());
        AgentContact entity2 = new AgentContact(getMockAgent(), AgentContact.contactTypes.EMAIL, mockEmail);
        getTransaction().begin();
        getAgentContactFacade().create(entity2);
        getTransaction().commit();
        AgentContact find2 = createAgentContactFacade().find(entity2.getId());
        assertNotSame("The two comparing entities should not be the same object", entity2, find2);
        assertEquals("The two comparing entities should have the same values", entity2, find2);
        assertEquals("The entities agent should be the same.", getMockAgent(), find2.getAgent());
        assertEquals("The entity's type shoudl be the same", contactTypes.EMAIL, find2.getMapType());
        assertEquals("The entity's value should be the same", mockEmail, find2.getMapValue());
        assertEquals("The entities agent should be the same.", getMockAgent(), entity2.getAgent());
        assertEquals("The entity's type shoudl be the same", contactTypes.EMAIL, entity2.getMapType());
        assertEquals("The entity's value should be the same", mockEmail, entity2.getMapValue());
    }

    /**
     * Test of edit method, of class AgentContactFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("AgentContactFacade: edit");
        //EJBContainer container = getContainer();
        //AgentContactFacade instance = (AgentContactFacade) container.getContext().lookup("java:global/classes/AgentContactFacade");
        if (getAgentContactFacade().count() == 0) {
            AgentContact entity = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
            getTransaction().begin();
            getAgentContactFacade().create(entity);
            getTransaction().commit();
        }
        List<AgentContact> findAll = getAgentContactFacade().findAll();
        AgentContact entity = findAll.get(0);
        String orgValue = entity.getMapValue();
        String changeValue = "801-555-5455";
        entity.setMapValue(changeValue);
        getTransaction().begin();
        getAgentContactFacade().edit(entity);
        getTransaction().commit();
        AgentContact find = createAgentContactFacade().find(entity.getId());
        assertNotSame("Comparing objects should be different.", entity, find);
        assertEquals("The changed value should have been changed.", changeValue, find.getMapValue());

    }

    /**
     * Test of remove method, of class AgentContactFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("AgentContactFacade: remove");
        AgentContact agentContact1 = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(agentContact1);
        getAgentContactFacade().create(new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone));
        getAgentContactFacade().create(new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone));
        getTransaction().commit();
        int cnt = getAgentContactFacade().count();
        List<AgentContact> findAll = getAgentContactFacade().findAll();
        assertEquals("Count should be the same", cnt, findAll.size());
        for (AgentContact agentContact : findAll) {
            getTransaction().begin();
            getAgentContactFacade().remove(agentContact);
            getTransaction().commit();
            cnt--;
            assertEquals("Entity removed, count should have gone down by one (count())", cnt, getAgentContactFacade().count());
            assertEquals("Entity removed, count should have gone down by one fineAll.size()", cnt, getAgentContactFacade().findAll().size());
        }
        assertEquals("count should not be completely empty", 0, getAgentContactFacade().count());
        assertEquals("count should not be completely empty", 0, getAgentContactFacade().findAll().size());
    }

    /**
     * Test of find method, of class AgentContactFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("AgentContactFacade: find");
        //EJBContainer container = getContainer();
        //AgentContactFacade instance = (AgentContactFacade) container.getContext().lookup("java:global/classes/AgentContactFacade");
        AgentContact expResult = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(expResult);
        getTransaction().commit();
        AgentContact result = createAgentContactFacade().find(expResult.getId());
        assertNotSame("Confirm that the etwo entityes are not the same objects", expResult, result);
        assertEquals("the two entitys should contain the same information: Base.", expResult, result);
        assertEquals("the two entitys should contain the same information: Agent.", expResult.getAgent(), result.getAgent());
        assertEquals("the two entitys should contain the same information: ID", expResult.getId(), result.getId());
        assertEquals("the two entitys should contain the same information: maptype", expResult.getMapType(), result.getMapType());
        assertEquals("the two entitys should contain the same information: value", expResult.getMapValue(), result.getMapValue());
    }

    /**
     * Test of findAll method, of class AgentContactFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("AgentContactFacade: findAll");
        //EJBContainer container = getContainer();
        //AgentContactFacade instance = (AgentContactFacade) container.getContext().lookup("java:global/classes/AgentContactFacade");
        List<AgentContact> expResult = new ArrayList<AgentContact>();
        AgentContact agentContact = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(agentContact);
        getTransaction().commit();
        expResult.add(agentContact);
        AgentContact agentContact1 = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(agentContact1);
        getTransaction().commit();
        expResult.add(agentContact1);
        AgentContact agentContact2 = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(agentContact2);
        getTransaction().commit();
        expResult.add(agentContact2);
        List<AgentContact> result = getAgentContactFacade().findAll();
        assertTrue("result list should be populated", (result.size()>=3));
        getTransaction().begin();
        for (AgentContact agentc : result) {
            getAgentContactFacade().remove(agentc);
        }
        getTransaction().commit();
        assertTrue("the new list should be empty (list)", (getAgentContactFacade().findAll().isEmpty()));
        assertTrue("the new list should be empty (count)", (getAgentContactFacade().count()==0));
    }

    /**
     * Test of count method, of class AgentContactFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("AgentContactFacade: count");
        //EJBContainer container = getContainer();
        //AgentContactFacade instance = (AgentContactFacade) container.getContext().lookup("java:global/classes/AgentContactFacade");
        assertEquals("count and findall.size should be the same", getAgentContactFacade().findAll().size(), getAgentContactFacade().count());
        List<AgentContact> result = getAgentContactFacade().findAll();
        getTransaction().begin();
        for (AgentContact agentc : result) {
            getAgentContactFacade().remove(agentc);
        }
        getTransaction().commit();
        assertTrue("the new list should be empty (list)", (getAgentContactFacade().findAll().isEmpty()));
        assertTrue("the new list should be empty (count)", (getAgentContactFacade().count()==0));
        
        AgentContact agentContact = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getTransaction().begin();
        getAgentContactFacade().create(agentContact);
        AgentContact agentContact1 = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getAgentContactFacade().create(agentContact1);
        AgentContact agentContact2 = new AgentContact(getMockAgent(), AgentContact.contactTypes.PHONE, mockPhone);
        getAgentContactFacade().create(agentContact2);
        getTransaction().commit();
        assertEquals("Confirm that the count is 3", 3, getAgentContactFacade().count());
        assertEquals("count and findall.size should be the same", getAgentContactFacade().findAll().size(), getAgentContactFacade().count());
    }
}
