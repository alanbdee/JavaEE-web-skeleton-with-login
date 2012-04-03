/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testBase;

import boundary.AgentsFacade;
import boundary.CompaniesFacade;
import boundary.DocumentPagesFacade;
import boundary.DocumentsFacade;
import entities.Agents;
import entities.Companies;
import entities.DocumentPages;
import entities.Documents;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityTransaction;

/**
 *
 * @author alan
 */
public class MockObjects {

    public static Companies createMockCompany(CompaniesFacade instance, EntityTransaction transaction) {
        Date start = new Date();
        Calendar end = Calendar.getInstance();
        end.setTime(start);
        end.add(Calendar.YEAR, 1);
        int count = instance.count();
        Companies entity = new Companies("Test Company (" + count + ")", start, end.getTime());
        transaction.begin();
        instance.create(entity);
        transaction.commit();
        return entity;
    }

    //<editor-fold defaultstate="collapsed" desc="Agents">
    public static Agents createMockAgent(Agents agent, AgentsFacade instance, EntityTransaction transaction) {
        transaction.begin();
        instance.create(agent);
        transaction.commit();
        return agent;
    }
    
    public static Agents createMockAgent(Companies comp, AgentsFacade instance, EntityTransaction transaction) {
        Agents local_agent = null;
        int records = instance.count();
        String newusername = "testuser" + records;
        if (instance.getUser(newusername) == null) {
            local_agent = new Agents(comp, newusername, "password", "Mock", "User(" + records + ")");
            createMockAgent(local_agent, instance, transaction);
        }
        return local_agent;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Documents">
    public static final String LOREM_IPSUM = "Short but long description of this document set. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam iaculis, dolor hendrerit egestas mattis, urna lacus elementum velit, non ultrices mi lectus non nibh.";
    public static Documents getMockDocument(Companies comp) {
        return getMockDocument(comp, "");
    }
    public static Documents getMockDocument(Agents agent){
        Documents doc = new Documents(agent, Documents.documentStatus.NEW);
        doc.setTitle("Mock Document");
        doc.setDescription(LOREM_IPSUM);
        return doc;
    }
    
    public static Documents getMockDocument(Companies comp, String title) {
        Documents doc = new Documents(comp, Documents.documentStatus.NEW);
        doc.setTitle("Mock Document " + title);
        doc.setDescription(LOREM_IPSUM);
        return doc;
    }
    
    public static Documents createMockDocument(DocumentsFacade instance, Companies comp) {
        return createMockDocument(instance, comp, "");
    }
    
    
    public static Documents createMockDocument(DocumentsFacade instance, Companies comp, String title) {
        Documents doc = getMockDocument(comp, title);
        EntityTransaction transaction = instance.getEntityManager().getTransaction();
        transaction.begin();
        instance.create(doc);
        transaction.commit();
        return doc;
    }
    //</editor-fold>
    
    
    public static DocumentPages createMockDocumentPage(DocumentPagesFacade instance) {
        EntityTransaction transaction = instance.getEntityManager().getTransaction();
        DocumentPages page = null;
        return page;
    }

    public static byte[] readDocument(String fName) throws FileNotFoundException, IOException {
        byte[] data = null;
        try {
            File f = new File(fName);
            FileInputStream in = new FileInputStream(f);
            data = new byte[(int) f.length()];
            int read = in.read(data);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Testing Image: " + fName + " Not Found");
        }
        return data;

    }

    public static byte[] readDocument() throws FileNotFoundException, IOException {
        String fName = "test/testImages/Document1.jpg";
        return readDocument(fName);
    }
}
