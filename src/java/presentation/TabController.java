/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabCloseEvent;

/**
 *
 * @author Alan
 */
@Named(value = "tabController")
@RequestScoped
public class TabController {
    public void onTabClose(){
        
    }
    
    public void onTabChange(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /** Creates a new instance of TabController */
    public TabController() {
    }
}
