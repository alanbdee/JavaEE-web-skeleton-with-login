/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alan
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "USER_TYPE", length = 10, discriminatorType = DiscriminatorType.STRING)
//@Table(name = "DOCUMENTS")
public class Documents implements Serializable {

    public static final String HASH_METHOD = "MD5";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
//    @ManyToOne
//    @JoinColumn(name = "DOCUMENT_ID")
//    private DocumentsData data;
//    @Column(length = 64)
//    @NotNull
//    private String docHash;
    @ManyToOne
    private Companies company;
    @ManyToOne
    private Agents agent;
    
    @OneToMany(mappedBy="document", targetEntity=DocumentPages.class, fetch= FetchType.LAZY, cascade={CascadeType.PERSIST})
    private Collection pages;
    
//    @Column(length = 10)
//    @NotNull
//    private String userType;
//    @NotNull
//    private long userID;
    @Column(length = 10)
    @NotNull
    private String status;
    @Column(length = 40)
    @NotNull
    private String title;
    @NotNull
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @Column (length=2)
    private int rank;

//    public String getTypeString() {
//        return userType;
//    }
//
//    public void setUserType(String type) {
//        this.userType = type;
//    }
    
    
    public enum documentTypes {COMPANY, AGENT, CLIENT, TEMPLATE, PROCEDURE, UNKNOWN}
    public enum documentStatus {TMP, NEW, REPLACED, DELETED, EDITED}

    public Documents() {
    }
    public Documents(Companies comp, documentStatus status){
        this.setCompany(comp);
        this.setStatus(status);
    }
    public Documents(Agents agent, documentStatus status){
        this.setAgent(agent);
        this.setStatus(status);
    }
    public Documents(documentStatus status){
        this.setStatus(status);
    }

    public Documents(byte[] image) {
        //generate hash
        //docHash = DigestUtils.md5Hex(image);
        this.setStatus(documentStatus.NEW);
        //create document data
        //data = new DocumentsData(image, docHash);
    }
    public void addPage(byte[] pagebin){
        List<DocumentPages> p = this.getPages();
        DocumentPages tmp = new DocumentPages(this, pagebin, p.size());
        p.add(tmp);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }
    

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<DocumentPages> getPages() {
        if(pages==null){
            pages = new ArrayList<DocumentPages>();
        }
        return (List<DocumentPages>) pages;
    }

    public void setPages(Collection pages) {
        this.pages = pages;
    }
    public void addPage(Collection page){
        this.pages.add(page);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public documentTypes getUserType() {
//        //COMPANY, AGENT, CLIENT, TEMPLATE, PROCEDURE
//        if("COMPANY".equals(userType)){
//            return documentTypes.COMPANY;
//        } else if("AGENT".equals(userType)){
//            return documentTypes.AGENT;
//        } else if("CLIENT".equals(userType)){
//            return documentTypes.CLIENT;
//        } else if("TEMPLATE".equals(userType)){
//            return documentTypes.TEMPLATE;
//        } else if("PROCEDURE".equals(userType)){
//            return documentTypes.PROCEDURE;
//        } else {
//            return documentTypes.UNKNOWN;
//        }
//    }
//
//    public void setUserType(documentTypes userType) {
//        if(userType==documentTypes.COMPANY){
//            this.userType="COMPANY";
//        } else if(userType==documentTypes.AGENT){
//            this.userType="AGENT";
//        } else if(userType==documentTypes.CLIENT){
//            this.userType="CLIENT";
//        } else if(userType==documentTypes.TEMPLATE){
//            this.userType="TEMPLATE";
//        } else if(userType==documentTypes.PROCEDURE){
//            this.userType="PROCEDURE";
//        } else if(userType==documentTypes.UNKNOWN){
//            this.userType="UNKNOWN";
//        }
//    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public documentStatus getStatus() {
        if("NEW".equals(this.status)){
            return documentStatus.NEW;
        } else if("EDITED".equals(this.status)){
            return documentStatus.EDITED;
        } else if("DELETED".equals(this.status)){
            return documentStatus.DELETED;
        } else if("REPLACED".equals(this.status)){
            return documentStatus.REPLACED;
        }
        return documentStatus.NEW;
    }

    public void setStatus(documentStatus Status) {
        if(Status==documentStatus.NEW){
            this.status = "NEW";
        } else if(Status==documentStatus.EDITED){
            this.status = "EDITED";
        } else if(Status==documentStatus.DELETED){
            this.status = "DELETED";
        } else if(Status==documentStatus.REPLACED){
            this.status = "REPLACED";
        }
    }
    

//    public DocumentsData getData() {
//        return data;
//    }
//
//    public void setData(DocumentsData data) {
//        this.data = data;
//    }
//
//    public String getDocHash() {
//        return docHash;
//    }
//
//    public void setDocHash(String docHash) {
//        this.docHash = docHash;
//    }
//
//    public Long getReplaceID() {
//        return replaceID;
//    }
//
//    public void setReplaceID(Long replaceID) {
//        this.replaceID = replaceID;
//    }


//    public Long getUserID() {
//        return userID;
//    }
//
//    public void setUserID(Long userID) {
//        this.userID = userID;
//    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentsMap[ id=" + id + " ]";
    }
}
