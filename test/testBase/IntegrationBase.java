/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testBase;

import boundary.AgentContactFacade;
import boundary.AgentsFacade;
import boundary.CompaniesFacade;
import boundary.DocumentPagesFacade;
import boundary.DocumentsFacade;
import entities.Agents;
import entities.Companies;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author alan
 */
public class IntegrationBase {

    public static final String PERSISTENCE_UNIT = "IntegrationPU";
    protected EntityTransaction transaction;

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
    //<editor-fold defaultstate="collapsed" desc="Entity Manager Code">
    protected EntityManagerFactory emf;
    protected EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = getEmf().createEntityManager();
        }
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getNewEntityManager() {
        EntityManagerFactory nemf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager nem = nemf.createEntityManager();
        return nem;
    }
    //</editor-fold>
    
    public EntityTransaction getTransaction() {
        if(transaction==null){
            transaction = getCompanyFacade().getEm().getTransaction();
        }
        return transaction;
    }

    public void setTransaction(EntityTransaction transaction) {
        this.transaction = transaction;
    }
    
    /*Companies*/
    protected CompaniesFacade companyFacade;
    protected Companies mockCompany;

    public Companies getMockCompany() {
        if(mockCompany==null){
            mockCompany = MockObjects.createMockCompany(getCompanyFacade(), getTransaction());
        }
        return mockCompany;
    }

    protected CompaniesFacade getCompanyFacade() {
        if (companyFacade == null) {
            companyFacade = new CompaniesFacade();
            companyFacade.setEm(getEm());
        }
        return companyFacade;
    }
    protected CompaniesFacade createCompaniesFacade() {
        CompaniesFacade instance = new CompaniesFacade();
        instance.setEm(getNewEntityManager());
        return instance;
    }
    /*Agents*/
    protected AgentsFacade agentsFacade;
    private Agents mockAgent;

    public Agents getMockAgent() {
        if(mockAgent==null){
            mockAgent = MockObjects.createMockAgent(getMockCompany(), getAgentFacade(), getTransaction());
        }
        return mockAgent;
    }
    
    protected AgentsFacade createAgentFacade() {
        AgentsFacade af = new AgentsFacade();
        af.setEm(getNewEntityManager());
        return af;
    }

    protected AgentsFacade getAgentFacade() {
        if (agentsFacade == null) {
            agentsFacade = new AgentsFacade();
            agentsFacade.setEm(getEm());
        }
        return agentsFacade;
    }
    /*Agents Contact*/
    protected AgentContactFacade agentContactFacade;

    protected AgentContactFacade createAgentContactFacade() {
        AgentContactFacade acf = new AgentContactFacade();
        acf.setEm(getNewEntityManager());
        return acf;
    }

    protected AgentContactFacade getAgentContactFacade() {
        if (agentContactFacade == null) {
            agentContactFacade = new AgentContactFacade();
            agentContactFacade.setEm(getEm());
        }
        return agentContactFacade;
    }
    /*
     * Documents
     */
    protected DocumentsFacade documentsFacade;

    protected DocumentsFacade createDocumentsFacade() {
        DocumentsFacade facade = new DocumentsFacade();
        facade.setEm(getNewEntityManager());
        return facade;
    }

    protected DocumentsFacade getDocumentsFacade() {
        if (documentsFacade == null) {
            documentsFacade = new DocumentsFacade();
            documentsFacade.setEntityManager(getEm());
        }
        return documentsFacade;
    }
    /*
     * Documents Pages
     */
    protected DocumentPagesFacade documentPagesFacade;

    protected DocumentPagesFacade createDocumentPagesFacade() {
        DocumentPagesFacade facade = new DocumentPagesFacade();
        facade.setEntityManager(getNewEntityManager());
        return facade;
    }

    protected DocumentPagesFacade getDocumentPagesFacade() {
        if (documentPagesFacade == null) {
            documentPagesFacade = new DocumentPagesFacade();
            documentPagesFacade.setEntityManager(getEm());
        }
        return documentPagesFacade;
    }
}
