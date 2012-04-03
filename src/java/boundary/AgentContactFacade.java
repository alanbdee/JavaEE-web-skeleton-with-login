/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;


//~--- JDK imports ------------------------------------------------------------

import entities.AgentContact;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alan
 */
@Stateless
public class AgentContactFacade extends AbstractFacade<AgentContact> {

    @PersistenceContext(unitName = "REMPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public AgentContactFacade() {
        super(AgentContact.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
