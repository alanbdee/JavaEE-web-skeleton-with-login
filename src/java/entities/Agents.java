/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alan
 */
@Entity
public class Agents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @Column(unique = true, length = 40)
    @Size(min = 6, max = 40)
    @NotNull
    private String username;
    @Column(length = 64)
    @NotNull
    private String password;
    @Column(length = 40)
    @NotNull
    private String firstName;
    @Column(length = 40)
    @NotNull
    private String lastName;
    @Column(length = 64)
    private String title;
    @Column(length = 10)
    private String locale;
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date created;
    @Column(length = 64)
    private String salt;
    @Column(length = 10)
    private String status;
    @Column(length = 10)
    private String userRole;

    enum UserStatus {ACTIVE, NONACTIVE};
    enum UserRoles {AGENT, MANAGER, ADMIN};
    @OneToMany(mappedBy = "agent", targetEntity = AgentContact.class, cascade=CascadeType.PERSIST)
    private List<AgentContact> contacts;
    @OneToMany(mappedBy = "agent", targetEntity = Documents.class, cascade= CascadeType.PERSIST)
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="COMPANYID")
    private Companies company;
    public Companies getCompany(){
        return company;
    }
    public void setCompany(Companies company) {
        this.company = company;
    }
    
    public Agents() {
    }

    public Agents(String username) {
        this.username = username;

    }

    public Agents(Companies company, String username, String password, String fname, String lname) {
        this.username = username;
        this.company = company;
        this.password = password;
        this.firstName = fname;
        this.lastName = lname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    //<editor-fold defaultstate="collapsed" desc="Mundane Getters and Setters">
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        if (password.length() != 28) {
            return;
        }
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public String getLocale() {
        return locale;
    }
    
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long Id) {
        this.id = Id;
    }
    
    public List<AgentContact> getContacts() {
        return contacts;
    }
    
    public void setContacts(List<AgentContact> contacts) {
        this.contacts = contacts;
    }
    //</editor-fold>
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agents)) {
            return false;
        }
        Agents other = (Agents) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Agents[ username=" + username + " ]";
    }
}
