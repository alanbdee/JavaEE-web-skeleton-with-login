/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.DocumentPages;
import entities.Documents;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author alan
 */
@Stateless
public class DocumentPagesFacade extends AbstractFacade<DocumentPages> {

    @PersistenceContext(unitName = "REMPU")
    private EntityManager em;
    public DocumentPagesFacade() {
        super(DocumentPages.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void remove(DocumentPages entity) {
        entity.setStatus("DELETED");
        getEntityManager().merge(entity);
    }

    @Override
    public List<DocumentPages> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    /*
     * given the partents document id this returns all the pages for that document
     */
    public List<DocumentPages> findAll(Documents document) {
        List<DocumentPages> results = null;
        //Boilerplate
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocumentPages> cq = cb.createQuery(entityClass);
        //Interesting stuff happens here
        Root<DocumentPages> root = cq.from(entityClass); //Step 2
        cq.select(root);
        //Predicate status = cb.notEqual(root.get("status"), "DELETED");
        Predicate status = cb.equal(root.get("document"), document);
        cq.where(status);
        Order order = cb.asc(root.get("pageNum"));
        cq.orderBy(order);
        //Boilerplate code
        Query qry = em.createQuery(cq); //Step 6
        results = qry.getResultList(); //Step 6
        return results;
        
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<DocumentPages> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void setEntityManager(EntityManager newEntityManager) {
        this.em = newEntityManager;
    }

}
