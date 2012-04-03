/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 *
 * @author Alan
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", length = 10, discriminatorType = DiscriminatorType.STRING)
@Table(name = "CONTACTS")
public abstract class AbstractContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(length = 10, nullable = false)
    protected String mapType;
    @Column(length = 120, nullable = false)
    protected String mapValue;

    public static enum contactTypes {

        PHONE, EMAIL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbstractContact)) {
            return false;
        }
        AbstractContact other = (AbstractContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContactMap[ id=" + id + " ]";
    }

    public contactTypes getMapType() {
        if (mapType.equals("PHONE")) {
            return contactTypes.PHONE;
        } else if (mapType.equals("EMAIL")) {
            return contactTypes.EMAIL;
        }
        return null;//should never be reached.
    }
    
    public void setMapType(contactTypes mapType) {
        if (mapType.equals(contactTypes.PHONE)) {
            this.mapType = "PHONE";
        } else if (mapType.equals(contactTypes.EMAIL)) {
            this.mapType = "EMAIL";
        }
    }

    public String getMapValue() {
        return mapValue;
    }

    public void setMapValue(String mapValue) {
        this.mapValue = mapValue;
    }
}
