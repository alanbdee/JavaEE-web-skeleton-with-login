/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

/**
 *
 * @author alan
 */
public class ControlsBean {

    /** Creates a new instance of ControlsBean */
    public ControlsBean() {
    }
    private String openNew;
    public String getOpenNew(){
        System.out.println("Create New from controlsBean");
        return "documents/new.xhtml";
    }
    public void setOpenNew(String on){
        openNew = on;
    }
    public void createNew2(){
        System.out.println("Create New 2");
    }
}
