/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import testBase.IntegrationBase;
import testBase.MockObjects;
import java.util.Set;
import java.util.HashSet;
import java.math.BigInteger;
import java.security.SecureRandom;
import entities.Companies;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import entities.Agents;
import java.util.ArrayList;
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
public class AgentsFacadeTest extends IntegrationBase {

    public String absurdLargeString = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

    public AgentsFacadeTest() throws Exception {
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
     * Test of create method, of class AgentsFacade.
     */
    @Test
    public void testCreate() throws NamingException {
        System.out.println("Agents: Create Test Agent");
        int records = getAgentFacade().count();
        Agents createMockAgent = MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        assertEquals("The number of records should have increased by one", getAgentFacade().count(), ++records);

        String create_user = "testuser";
        Agents create_agent = new Agents(getMockCompany(), create_user, "passwordas", "Test", "User");
        Agents user = getAgentFacade().getUser(create_user);
        if (user == null) {
            getTransaction().begin();
            getAgentFacade().create(create_agent);
            getTransaction().commit();
            user = getAgentFacade().getUser(create_user);
        }
        assertEquals("The returned user should be the exact same as the inserted user", user, create_agent);


        try {
            //Null username
            Agents badAgent = new Agents();
            getTransaction().begin();
            getAgentFacade().create(badAgent);
            fail("NULL agent added!");//should never reach this point.
        } catch (Exception e) {
            //normal
        } finally {
            getTransaction().rollback();
        }

        try {
            //Short username
            Agents shortUsername = new Agents(getMockCompany(), "srtnm", "password", "First", "Last");
            getTransaction().begin();
            getAgentFacade().create(shortUsername);
            fail("Short username allowed?");//should never reach this point.
        } catch (Exception e) {
            //normal
        } finally {
            getTransaction().rollback();
        }
        try {
            //just the right size
            create_agent = new Agents(getMockCompany(), "goodag", "password", "First", "Last");
            getTransaction().begin();
            getAgentFacade().create(create_agent);
            getAgentFacade().remove(getAgentFacade().getUser("goodag"));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            getTransaction().rollback();
        }
    }

    /**
     * Test of edit method, of class AgentsFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("Agents: edit");
        Agents mockAgent = MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        String orgLastName = mockAgent.getLastName();
        Agents baseAgent = createAgentFacade().getUser(mockAgent.getUsername());
        mockAgent.setLastName("Modified");
        getTransaction().begin();
        getAgentFacade().edit(mockAgent);
        getTransaction().commit();
        assertNotSame("The base agents should be not the same object as the mock agent.", baseAgent, mockAgent);
        assertEquals("These are the same users", baseAgent, mockAgent);
        assertFalse("User's Last name should not be the same", (mockAgent.getLastName().equals(orgLastName)));
        Agents modifiedAgent = getAgentFacade().getUser(mockAgent.getUsername());
        assertEquals("The pulled users last name should be the same on the set one. ", "Modified", modifiedAgent.getLastName());
        assertEquals("The modified agent should be the same agent as the original", mockAgent, modifiedAgent);
    }

    /**
     * Test of find method, of class AgentsFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("Agents: find");
        String username = "testFind1";
        String password = "password";
        String fname = "test";
        String lname = "Find1";
        Agents mockAgent = new Agents(getMockCompany(), username, password, fname, lname);
        MockObjects.createMockAgent(mockAgent, getAgentFacade(), getTransaction());
        Long id = mockAgent.getId();
        Agents result = getAgentFacade().find(id);
        assertEquals("The found agents company should be the same as the one created.", getMockCompany(), result.getCompany());
        assertEquals("The found agents username should be the same as the one created.", username, result.getUsername());
        assertEquals("The found agents fname should be the same as the one created.", fname, result.getFirstName());
        assertEquals("The found agents lname should be the same as the one created.", lname, result.getLastName());
    }

    /**
     * Test of findAll method, of class AgentsFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("Agents: findAll");
        List<Agents> expResult = new ArrayList<Agents>();
        expResult.add(MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction()));
        expResult.add(MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction()));
        expResult.add(MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction()));
        expResult.add(MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction()));
        List<Agents> result = getAgentFacade().findAll();
        assertTrue("The list should have four or more in teh list", (result.size() >= 4));
        assertEquals("The count should be the same as the number of results pulled", getAgentFacade().count(), result.size());
    }

    /**
     * Test of count method, of class AgentsFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("Agents: count");
        List<Agents> findAll = getAgentFacade().findAll();
        int expResult = findAll.size();
        int result = getAgentFacade().count();
        assertEquals("The count and the size should be the same.", expResult, result);
        removeAll();
        assertEquals("The current count should be 0", getAgentFacade().count(), 0);
        MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        assertEquals("3 agents have just been created, the count should now be 3", 3, getAgentFacade().count());
    }

    /**
     * Test of getUser method, of class AgentsFacade.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("Agents: getUser");
        String username = "testFinduser";
        String password = "password";
        String fname = "test";
        String lname = "finduser";
        Agents mockAgent = new Agents(getMockCompany(), username, password, fname, lname);
        MockObjects.createMockAgent(mockAgent, getAgentFacade(), getTransaction());
        Agents result = getAgentFacade().getUser(username);
        assertEquals("The created and retrieved agent's username should be the same.", getMockCompany(), result.getCompany());
        assertEquals("The created and retrieved agent's username should be the same.", username, result.getUsername());
        assertEquals("The created and retrieved agent's username should be the same.", fname, result.getFirstName());
        assertEquals("The created and retrieved agent's username should be the same.", lname, result.getLastName());
        
        String shortName = "12345";
        result = getAgentFacade().getUser(shortName);
        assertNull("The given result should be null as this username is too short", result);
        shortName = "123456";
        result = getAgentFacade().getUser(shortName);
        assertNull("The given username should not exist.", result);
    }

    /**
     * Test of authenticate method, of class AgentsFacade.
     */
    @Test
    public void testAuthenticate() throws Exception {
        try {
            System.out.println("Agents: authenticate");
            String testUserName = "AuthUser";
            String testUserPass = "goodpassword";

            List<Agents> findAll = getAgentFacade().findAll();
            String loopuser;
            for (Agents a : findAll) {
                loopuser = a.getUsername();
                if(loopuser.matches("testuser[0-9]+")){
                    assertFalse("User Loop: Given good username and bad password for this user should return false", getAgentFacade().authenticate(a.getUsername(), "badpassword"));
                    assertTrue("User Loop: Given the good username and good password should return true: User: " + a.getUsername(), getAgentFacade().authenticate(a.getUsername(), "password"));
                }
            }

            Agents goodUser = new Agents(getMockCompany(), testUserName, testUserPass, "Auth", "User");
            Agents user = getAgentFacade().getUser(testUserName);
            if (user == null) {
                getTransaction().begin();
                getAgentFacade().create(goodUser);
                getTransaction().commit();
            }
            assertFalse("Given null strings is should return false", getAgentFacade().authenticate(null, null));
            assertFalse("Given a username with no password should be false", getAgentFacade().authenticate(testUserName, ""));
            assertFalse("Given a badusername and password should return false", getAgentFacade().authenticate("badUserName", "badPassword"));
            assertFalse("Given a good username but bad passwrod should return false", getAgentFacade().authenticate(testUserName, "badPassword"));
            assertFalse("Given an absured Large username and bad password it should return false", getAgentFacade().authenticate(absurdLargeString, "badPassword"));
            assertFalse("Given a good username but absurd large password should return false", getAgentFacade().authenticate(testUserName, absurdLargeString));
            assertTrue("Given the good username and password from created object should return true", getAgentFacade().authenticate(testUserName, testUserPass));

            String[] usernames = new String[5];
            String[] passwords = new String[5];
            SecureRandom srandom;
            String rand;
            Agents randUser;
            Set<String> strings = new HashSet<String>();
            for (int i = 0; i < 10; i++) {
                srandom = new SecureRandom();
                rand = new BigInteger(76, srandom).toString(32);
                if (strings.contains(rand)) {
                    continue;
                }
                strings.add(rand);
                randUser = new Agents(getMockCompany(), rand, rand, "Rand", "User");
                getTransaction().begin();
                getAgentFacade().create(randUser);
                getTransaction().commit();
                assertFalse("User Rand Loop: Given good username and bad password for this user should return false", getAgentFacade().authenticate(rand, "badpassword"));
                assertTrue("User Rand Loop: Given the good username and good password should return true: User: " + rand, getAgentFacade().authenticate(rand, rand));
            }

        } catch (EJBException e) {
            System.out.println(e);
        }
    }
    /**
     * Test records for relationship issues. make sure all agents belong to a valid company
     */
    @Test
    public void testRelationships(){
        List<Agents> findAll = getAgentFacade().findAll();
        long id;
        Companies find;
        CompaniesFacade altFacade = createCompaniesFacade();
        for (Agents agent : findAll) {
            id = agent.getCompany().getId();
            find = getCompanyFacade().find(id);
            assertEquals("Companies should be the same.", find, agent.getCompany());
            find = altFacade.find(id);
            assertEquals("Companies should be the same.", find, agent.getCompany());
            assertNotSame("the objects should be different instances.", find, agent.getCompany());
        }
        
    }

    /**
     * Test of remove method, of class AgentsFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("Agents: remove");
        Agents entity = new Agents(getMockCompany(), "removeuser", "removepass", "Remove", "User");
        List<Agents> findAll = getAgentFacade().findAll();
        int count = getAgentFacade().count();
        int newCount;
        for (Agents a : findAll) {
            getTransaction().begin();
            getAgentFacade().remove(a);
            getTransaction().commit();
            newCount = getAgentFacade().count();
            count--;
            assertEquals("Remove user loop. The new count should be one less then before.", count, newCount);
        }
        Agents user = getAgentFacade().getUser("removeuser");
        assertNull("User should be null", user);
    }

    protected void removeAll()  {
        List<Agents> findAll = getAgentFacade().findAll();
        getTransaction().begin();
        for (Agents agent : findAll) {
            getAgentFacade().remove(agent);
        }
        getTransaction().commit();
    }
}
