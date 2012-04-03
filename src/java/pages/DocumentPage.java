/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.DocumentPages;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alan
 */
@Named(value = "documentPage")
@RequestScoped
public class DocumentPage  extends Pages{
    
    private List<DocumentPages> documents;

    public List<DocumentPages> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentPages> documents) {
        this.documents = documents;
    }


    /** Creates a new instance of DocumentPage */
    public DocumentPage() {
    }
    @PostConstruct
    public void init() {
        System.out.println("Init");
        documents = new ArrayList<DocumentPages>();
        
    }    
    
    public void view(){
        
    }
}
