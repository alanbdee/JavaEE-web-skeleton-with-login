package presentation;

import boundary.AgentsFacade;
import boundary.CompaniesFacade;
import boundary.DocumentsFacade;
import entities.Agents;
import entities.Companies;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Alan
 * Login Controller maintains session information related to user authentication.
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private boundary.CompaniesFacade companiesFacade;
    @EJB
    private boundary.AgentsFacade agentFacade;
    @EJB
    private boundary.DocumentsFacade documentsFacade;
    private boolean authenticated;
    private String username;
    private String password;
    private Agents agent;
    private Companies company;
    public static final String AUTH_ERROR_MESSAGE = "Authentication Error: ";
    public static final String AUTH_ERROR_DETAIL = "Either the username or password are incorrect.";
    public static final String SEVER_ERROR_MESSAGE = "Fatal error occured. Please contact the administrator.";

    public LoginController() {
    }

    /**
     * sends username and password to agentFacade for authentication
     * throws and displays proper error messages
     */
    public void authenticate() {
        setAuthenticated(false);
        System.out.println("Authenticate");
        if (agentFacade == null) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, "No EJBFacade found?");
            return;
        }
        try {
            setAuthenticated(agentFacade.authenticate(username, password));
            if (!this.isAuthenticated()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, AUTH_ERROR_MESSAGE, AUTH_ERROR_DETAIL);
                FacesContext.getCurrentInstance().addMessage("Authentication Error", fm);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, AUTH_ERROR_MESSAGE, SEVER_ERROR_MESSAGE);
            FacesContext.getCurrentInstance().addMessage("Error", fm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, AUTH_ERROR_MESSAGE, SEVER_ERROR_MESSAGE);
            FacesContext.getCurrentInstance().addMessage("Error", fm);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, AUTH_ERROR_MESSAGE, SEVER_ERROR_MESSAGE);
            FacesContext.getCurrentInstance().addMessage("Error", fm);
        }
    }
    /*
     * Logs out current user
     */

    public void logout() {
        System.out.println("Logout");
        setAuthenticated(false);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public AgentsFacade getAgentFacade() {
        return agentFacade;
    }

    public void setAgentFacade(AgentsFacade agentFacade) {
        this.agentFacade = agentFacade;
    }

    public CompaniesFacade getCompaniesFacade() {
        return companiesFacade;
    }

    public void setCompaniesFacade(CompaniesFacade companiesFacade) {
        this.companiesFacade = companiesFacade;
    }

    public DocumentsFacade getDocumentsFacade() {
        return documentsFacade;
    }

    public void setDocumentsFacade(DocumentsFacade documentsFacade) {
        this.documentsFacade = documentsFacade;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public boolean isAuthenticated() {
        //return userBean.isAuthenticated();
        //TODO: REMOVE THIS FOR PRODUCTION
        String username = "testuser0";
        System.out.println("########## Authentication test is automatically passing.");
        if (authenticated == false && (agentFacade != null && companiesFacade != null && documentsFacade != null)) {
            long companyid  = documentsFacade.getCompanyWithMostDocuments();
            company = companiesFacade.find(companyid);
            agent = agentFacade.getUser(username);
            authenticated = true;
        }
        //return authenticated;
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    //</editor-fold>
    /** Creates a new instance of LoginController */
}
