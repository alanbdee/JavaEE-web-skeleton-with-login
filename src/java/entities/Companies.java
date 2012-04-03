/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alan
 */
@Entity
public class Companies implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy="company", targetEntity=Agents.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<Agents> agents;
    @OneToMany(mappedBy="company", targetEntity=Documents.class, cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<Documents> documents;
    
    private String CompanySchema;
    @Column(length = 45)
    @NotNull
    private String Name;
    @Column(length = 100)
    private String Slogan;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date Created;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date ServiceStart;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date ServiceEnd;

    public Companies() {
    }
    
    public Companies(long id){
        this.id=id;
    }

    public Companies(String name, Date start, Date end) {
        this.Name = name;
        this.ServiceStart = start;
        this.ServiceEnd = end;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public Long getId() {
        return id;
    }
    
    public Collection<Agents> getAgents(){
      return agents;  
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreated() {
        return Created;
    }
    
    public void setServiceStart(Date d){
        this.ServiceStart = d;
    }
    public void setServiceEnd(Date d){
        this.ServiceEnd = d;
    }

    public void setCreated(Date CompanyCreated) {
        this.Created = CompanyCreated;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String CompanyName) {
        this.Name = CompanyName;
    }
    
    public String getSchema() {
        return CompanySchema;
    }
    
    public void setSchema(String CompanySchema) {
        this.CompanySchema = CompanySchema;
    }
    
    public Date getServiceEnd() {
        return ServiceEnd;
    }
    
    public Date getServiceStart() {
        return ServiceStart;
    }
    
    public String getSlogan() {
        return Slogan;
    }
    
    public void setSlogan(String CompanySlogan) {
        this.Slogan = CompanySlogan;
    }

    public String getCompanySchema() {
        return CompanySchema;
    }

    public void setCompanySchema(String CompanySchema) {
        this.CompanySchema = CompanySchema;
    }

    public void setAgents(Collection agents) {
        this.agents = agents;
    }
    
    //</editor-fold>

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Companies)) {
            return false;
        }
        Companies other = (Companies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Company[ id=" + id + " ]";
    }
    
}
