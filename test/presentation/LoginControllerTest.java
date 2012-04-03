/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.regex.Pattern;
import boundary.DocumentsFacade;
import boundary.CompaniesFacade;
import boundary.AgentsFacade;
import entities.Agents;
import entities.Companies;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import testBase.MockObjects;
import testBase.TestContainer;
import static org.junit.Assert.*;

/**
 *
 * @author Alan
 */
public class LoginControllerTest extends TestContainer {

    public LoginControllerTest() {
    }
    private static String gooduser = "gooduser";
    private static String goodpass = "goodpass";

    @BeforeClass
    public static void setUpClass() throws Exception {
//        System.out.println("Setup");
//        EJBContainer container = getContainer();
//        AgentsFacade instance = (AgentsFacade)container.getContext().lookup("java:global/classes/AgentsFacade");
//        CompaniesFacade companyF = (CompaniesFacade)container.getContext().lookup("java:global/classes/CompaniesFacade");
//        Companies co = companyF.findAll().get(0);
//        Agents agent = new Agents(co.getId(), gooduser, goodpass, "Good", "User");
//        instance.create(agent);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //TODO confirm the login controller is creating and removing gooduser
//        EJBContainer container = getContainer();
//        AgentsFacade instance = (AgentsFacade)container.getContext().lookup("java:global/classes/AgentsFacade");
//        Agents agent = new Agents();
//        agent.setUsername(gooduser);
//        instance.remove(agent);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of authenticate method, of class LoginController.
     */
    @Test
    @Ignore
    public void testAuthenticate() {
        System.out.println("authenticate");
        LoginController instance = new LoginController();
        instance.authenticate();
        assertEquals("Null Values", false, instance.isAuthenticated());
        instance.setUsername("fakeuser");
        instance.authenticate();
        assertEquals("username set", false, instance.isAuthenticated());
        instance.setPassword("fakepassword");
        instance.authenticate();
        assertEquals("password set but wrong", false, instance.isAuthenticated());
        instance.setUsername(gooduser);
        instance.setPassword("badpassword");
        instance.authenticate();
        assertEquals("Good username, bad password", false, instance.isAuthenticated());
        instance.setPassword(goodpass);
        instance.authenticate();
        //NOTE: when calling the LoginController the EJB facade is not created, can't test from here.  
        assertEquals("Good username, good password", true, instance.isAuthenticated());
    }

    /**
     * Test of isAuthenticated method, of class LoginController.
     */
    @Test
    public void testIsAuthenticated() throws NamingException {
        System.out.println("isAuthenticated");
        EJBContainer container = getContainer();
        LoginController instance = new LoginController();
        AgentsFacade agtFacade = (AgentsFacade) container.getContext().lookup("java:global/classes/AgentsFacade");
        instance.setAgentFacade(agtFacade);
        CompaniesFacade compFacade = (CompaniesFacade) container.getContext().lookup("java:global/classes/CompaniesFacade");
        instance.setCompaniesFacade(compFacade);
        DocumentsFacade docFacade = (DocumentsFacade) container.getContext().lookup("java:global/classes/DocumentsFacade");
        instance.setDocumentsFacade(docFacade);
//        UserBean userBean = (UserBean) container.getContext().lookup("java:global/classes/UserBean");
//        instance.setUser(userBean);
        boolean authenticated = instance.isAuthenticated();
        assertTrue("The Mock authenticated should return true", authenticated);
        
    }

    /**
     * Test of setAuthenticated method, of class LoginController.
     */
    @Test
    @Ignore
    public void testSetAuthenticated() {
        System.out.println("setAuthenticated");
        boolean authenticated = false;
        LoginController instance = new LoginController();
        instance.setAuthenticated(authenticated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    /*
     * setup tables;
     */

    @Test
    public void testConstructor() {
        //TODO: this test case creates the sample data
        System.out.println("################ Create sample data: LoginControllerTest.testConstructor");
//        try {
////            Companies comp = this.createMockCompany();
////            this.createMockAgent(comp);
////            this.createMockAgent(comp);
////            this.createMockAgent(comp);
////            for (int i = 0; i < 53; i++) {
////                MockObjects.createMockDocument(comp);
////            }
////            Agents myMockAgent = this.createMockAgent(comp);
//            
//        } catch (NamingException ex) {
//            Logger.getLogger(LoginControllerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    /**
     * Test of logout method, of class LoginController.
     */
    @Test
    @Ignore
    public void testLogout() {
        System.out.println("logout");
        LoginController instance = new LoginController();
        instance.logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginController.
     */
    @Test
    @Ignore
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class LoginController.
     */
    @Test
    @Ignore
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        LoginController instance = new LoginController();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class LoginController.
     */
    @Test
    @Ignore
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class LoginController.
     */
    @Test
    @Ignore
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        LoginController instance = new LoginController();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
