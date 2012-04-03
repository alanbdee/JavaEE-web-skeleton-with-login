/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
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
public class AgentsTest {
    
    public AgentsTest() {
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
     * Test of hashCode method, of class Agents.
     */
    @Test
    public void testHashCode() {
        System.out.println("Agents: hashCode");
        Agents instance = new Agents();
        instance.setId(2L);
        int expResult = 2;
        int result = instance.hashCode();
        assertEquals("The hash code shoudl be simply based on the ID", expResult, result);
    }

    /**
     * Test of getPassword method, of class Agents.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Agents: getPassword");
        Agents instance = new Agents();
        instance.setPassword("1234567890123456789012345678");
        String expResult = instance.getPassword();
        String result = instance.getPassword();
        assertEquals("Set password should be the same", expResult, result);
    }

    /**
     * Test of setPassword method, of class Agents.
     */
    @Test
    public void testSetPassword() {
        System.out.println("Agents: setPassword");
        String password = "shortPassword";
        Agents testAgent;
        testAgent = new Agents();
        testAgent.setPassword("shortPassword");
        assertNull("Given short password, it should be null", testAgent.getPassword());
    }

    /**
     * Test of getUsername method, of class Agents.
     */
//    @Test
//    public void testGetUsername() {
//        System.out.println("Agents: getUsername");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getUsername();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUsername method, of class Agents.
//     */
//    @Test
//    public void testSetUsername() {
//        System.out.println("Agents: setUsername");
//        String username = "";
//        Agents instance = new Agents();
//        instance.setUsername(username);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCreated method, of class Agents.
//     */
//    @Test
//    public void testGetCreated() {
//        System.out.println("Agents: getCreated");
//        Agents instance = new Agents();
//        Date expResult = null;
//        Date result = instance.getCreated();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCreated method, of class Agents.
//     */
//    @Test
//    public void testSetCreated() {
//        System.out.println("Agents: setCreated");
//        Date created = null;
//        Agents instance = new Agents();
//        instance.setCreated(created);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLocale method, of class Agents.
//     */
//    @Test
//    public void testGetLocale() {
//        System.out.println("Agents: getLocale");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getLocale();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLocale method, of class Agents.
//     */
//    @Test
//    public void testSetLocale() {
//        System.out.println("Agents: setLocale");
//        String locale = "";
//        Agents instance = new Agents();
//        instance.setLocale(locale);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirstName method, of class Agents.
//     */
//    @Test
//    public void testGetFirstName() {
//        System.out.println("Agents: getFirstName");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getFirstName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFirstName method, of class Agents.
//     */
//    @Test
//    public void testSetFirstName() {
//        System.out.println("Agents: setFirstName");
//        String firstName = "";
//        Agents instance = new Agents();
//        instance.setFirstName(firstName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLastName method, of class Agents.
//     */
//    @Test
//    public void testGetLastName() {
//        System.out.println("Agents: getLastName");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getLastName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLastName method, of class Agents.
//     */
//    @Test
//    public void testSetLastName() {
//        System.out.println("Agents: setLastName");
//        String lastName = "";
//        Agents instance = new Agents();
//        instance.setLastName(lastName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStatus method, of class Agents.
//     */
//    @Test
//    public void testGetStatus() {
//        System.out.println("Agents: getStatus");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getStatus();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStatus method, of class Agents.
//     */
//    @Test
//    public void testSetStatus() {
//        System.out.println("Agents: setStatus");
//        String status = "";
//        Agents instance = new Agents();
//        instance.setStatus(status);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserRole method, of class Agents.
//     */
//    @Test
//    public void testGetUserRole() {
//        System.out.println("Agents: getUserRole");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getUserRole();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUserRole method, of class Agents.
//     */
//    @Test
//    public void testSetUserRole() {
//        System.out.println("Agents: setUserRole");
//        String userRole = "";
//        Agents instance = new Agents();
//        instance.setUserRole(userRole);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTitle method, of class Agents.
//     */
//    @Test
//    public void testGetTitle() {
//        System.out.println("Agents: getTitle");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getTitle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSalt method, of class Agents.
//     */
//    @Test
//    public void testGetSalt() {
//        System.out.println("Agents: getSalt");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.getSalt();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSalt method, of class Agents.
//     */
//    @Test
//    public void testSetSalt() {
//        System.out.println("Agents: setSalt");
//        String salt = "";
//        Agents instance = new Agents();
//        instance.setSalt(salt);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTitle method, of class Agents.
//     */
//    @Test
//    public void testSetTitle() {
//        System.out.println("Agents: setTitle");
//        String title = "";
//        Agents instance = new Agents();
//        instance.setTitle(title);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getId method, of class Agents.
//     */
//    @Test
//    public void testGetId() {
//        System.out.println("Agents: getId");
//        Agents instance = new Agents();
//        Long expResult = null;
//        Long result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setId method, of class Agents.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("Agents: setId");
//        Long Id = null;
//        Agents instance = new Agents();
//        instance.setId(Id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCompanyID method, of class Agents.
//     */
//    @Test
//    public void testGetCompanyID() {
//        System.out.println("Agents: getCompanyID");
//        Agents instance = new Agents();
//        Long expResult = null;
//        Long result = instance.getCompanyID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCompanyID method, of class Agents.
//     */
//    @Test
//    public void testSetCompanyID() {
//        System.out.println("Agents: setCompanyID");
//        Long companyID = null;
//        Agents instance = new Agents();
//        instance.setCompanyID(companyID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getContacts method, of class Agents.
//     */
//    @Test
//    public void testGetContacts() {
//        System.out.println("Agents: getContacts");
//        Agents instance = new Agents();
//        List expResult = null;
//        List result = instance.getContacts();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setContacts method, of class Agents.
//     */
//    @Test
//    public void testSetContacts() {
//        System.out.println("Agents: setContacts");
//        List<AgentContact> contacts = null;
//        Agents instance = new Agents();
//        instance.setContacts(contacts);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class Agents.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("Agents: equals");
//        Object object = null;
//        Agents instance = new Agents();
//        boolean expResult = false;
//        boolean result = instance.equals(object);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Agents.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("Agents: toString");
//        Agents instance = new Agents();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
