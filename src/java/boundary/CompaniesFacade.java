/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Companies;

//~--- JDK imports ------------------------------------------------------------

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alan
 */
@Stateless
public class CompaniesFacade extends AbstractFacade<Companies> {

    @PersistenceContext(unitName = "REMPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public CompaniesFacade() {
        super(Companies.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Companies entity) {
        System.out.println(getEntityManager());
        java.sql.Date d = new java.sql.Date(new java.util.Date().getTime());
        entity.setCreated(d);
        getEntityManager().persist(entity);

    }
     
}
