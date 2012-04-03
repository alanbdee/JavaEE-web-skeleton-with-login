package testBase;

import boundary.AgentsFacade;
import boundary.AgentsFacadeTest;
import boundary.CompaniesFacade;
import entities.Agents;
import entities.Companies;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import javax.ejb.EJBException;
import org.junit.Test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alan
 */
abstract public class TestContainer {

    private static EJBContainer container;

    public static EJBContainer getContainer() {
        if (container == null) {
            System.out.println("################################################# Get EJB Container: start ##############################################################");
            try {
                boolean useConfig = true;
                if (useConfig) {
                    Map<String, Object> conf = new HashMap<String, Object>();
                    //conf.put("org.glassfish.ejb.embedded.glassfish.installation.root", "/home/alan/Java/glassfish-3.1.1/glassfish");
                    //conf.put("org.glassfish.ejb.embedded.glassfish.instance.root", "/home/alan/Java/glassfish-3.1.1/glassfish/domains/domain1");
                    //conf.put("org.glassfish.ejb.embedded.glassfish.configuration.file", "/home/alan/Java/glassfish-3.1.1/glassfish/domains/domain1/config/domain.xml");
                    //conf.put("org.glassfish.ejb.embedded.glassfish.keep-temporary-files", "true");
                    //conf.put("org.glassfish.ejb.embedded.glassfish.instance.reuse", "true");
                    conf.put(EJBContainer.MODULES, new File("build/jar"));
                    conf.put("org.glassfish.ejb.embedded.glassfish.skip-client-modules", "true");
                    container = javax.ejb.embeddable.EJBContainer.createEJBContainer(conf);
                } else {
                    container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
                }

            } catch (EJBException e) {
                System.out.println(outputEJBException(e));
            }

            System.out.println("################################################# Get EJB Container: finished ##############################################################");
        }
        return container;
    }

    @BeforeClass
    public static void parentSetUpClass() throws Exception {
        //container = getContainer();
    }

    @AfterClass
    public static void parentTearDownClass() throws Exception {
    }

    protected Companies createMockCompany(CompaniesFacade instance) throws NamingException {
        Date start = new Date();
        Calendar end = Calendar.getInstance();
        end.setTime(start);
        end.add(Calendar.YEAR, 1);
        int count = instance.count();
        Companies entity = new Companies("Test Company (" + count + ")", start, end.getTime());
        instance.create(entity);
        return entity;
    }

    protected Companies createMockCompany() throws NamingException {
        EJBContainer c = getContainer();
        CompaniesFacade instance = (CompaniesFacade) c.getContext().lookup("java:global/classes/CompaniesFacade");
        return this.createMockCompany(instance);
    }
    protected Companies company;

    public Companies getCompany() throws NamingException {
        if (company == null) {
            this.company = createMockCompany();
        }
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    protected Agents createMockAgent(Companies comp) throws NamingException {
        Agents local_agent = null;
        EJBContainer ejbc = getContainer();
        AgentsFacade instance = (AgentsFacade) ejbc.getContext().lookup("java:global/classes/AgentsFacade");
        int records = instance.count();
        String newusername = "testuser" + records;
        if (instance.getUser(newusername) == null) {
            local_agent = new Agents(comp, newusername, "password", "Mock", "User(" + records + ")");
            instance.create(local_agent);
        }
        return local_agent;
    }

    protected Agents createMockAgent() throws NamingException {
        return createMockAgent(getCompany());
    }
    protected Agents agent;

    public Agents getAgent() throws NamingException {
        if (agent == null && company == null) {
            agent = createMockAgent();
        } else if (agent == null) {
            agent = createMockAgent(company);
        }
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public static String outputEJBException(EJBException e) {
        System.out.println("##### EJB Exception Caught: Decoding....");
        Throwable cause = e.getCause();
        System.out.println("Localized Message: " + cause.getLocalizedMessage());
        System.out.println("Message: " + cause.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        System.out.println("----Line---    ----------Declaring Class ---------------------------------    ----------Method Name --------    ----------File--------");
        for (StackTraceElement element : stackTrace) {
            System.out.println(rightPad(Integer.toString(element.getLineNumber()), 12) + "   " + rightPad(element.getClassName(), 60) + "    " + rightPad(element.getMethodName(), 30) + "    " + element.getFileName());
        }
        return e.toString();
    }
    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;

    /**
     * <p>Right pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad("", 3)     = "   "
     * StringUtils.rightPad("bat", 3)  = "bat"
     * StringUtils.rightPad("bat", 5)  = "bat  "
     * StringUtils.rightPad("bat", 1)  = "bat"
     * StringUtils.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * <p>Right pad a String with a specified character.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad("", 3, 'z')     = "zzz"
     * StringUtils.rightPad("bat", 3, 'z')  = "bat"
     * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtils.rightPad("bat", 1, 'z')  = "bat"
     * StringUtils.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    /**
     * <p>Right pad a String with a specified String.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     *
     * <p>Note: this method doesn't not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of <code>char</code>s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using {@link #repeat(String, int)} instead. 
     * </p>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar  character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     * @see #repeat(String, int)
     */
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
