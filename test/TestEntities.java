/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author alan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({boundary.AgentsFacadeTest.class, boundary.DocumentsFacadeTest.class, boundary.CompaniesFacadeIT.class, boundary.AgentContactFacadeTest.class})
public class TestEntities {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
