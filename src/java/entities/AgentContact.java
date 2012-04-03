/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
 *
 * @author Alan
 */
@Entity
@DiscriminatorValue("Agent")
public class AgentContact extends AbstractContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})
    @JoinColumn(name="USER_ID")
    protected Agents agent;

    public AgentContact() {
    }

    public AgentContact(Agents agent, contactTypes type, String value) {
        this.agent = agent;
        this.setMapType(type);
        this.mapValue = value;
    }
    
    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    
    
}
