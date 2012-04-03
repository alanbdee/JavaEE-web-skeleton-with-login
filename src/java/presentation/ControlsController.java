/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Alan
 */
@ManagedBean
@SessionScoped

public class ControlsController implements Serializable {
    private String testText = "does this work?";

    public String getTestText() {
        return testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }
    
    
    /** Creates a new instance of ControlsController */
    public ControlsController() {
    }

    public void displayMessage(ActionEvent actionEvent) {
        addMessage("You said:'" + testText + "'");
    }

    public void save() {
        addMessage("Data saved");
    }

    public void update() {
        addMessage("Data updated");
    }

    public void delete() {
        addMessage("Data deleted");
    }
    
    public String open(){
        System.out.println("Open Document");
        return "/admin/companies/List";
    }
    public String createNew(ActionEvent event){
        System.out.println("New Document");
        return "main";
    }
    
    public String outcome(){
        System.out.println("outcome run");
        return "index";
    }
    
    public void addMessage(String summary) {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
