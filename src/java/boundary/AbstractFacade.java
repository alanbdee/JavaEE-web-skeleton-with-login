/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Alan
 * Basic criteria query template
        //Boilerplate
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Documents> cq = cb.createQuery(entityClass);
        //Interesting stuff happens here
        Root<Documents> root = cq.from(entityClass); //Step 2
        cq.select(root);
        results = qry.getResultList(); //Step 6
        Predicate status = cb.equal(root.get("status"), "DELETED");
        Expression<Integer> userId = root.get("company");//in this company refers to the "mappedBy=" in the annotation
        //Boilerplate code
        Query qry = em.createQuery(cq); //Step 6
        List<Documents> results = qry.getResultList(); //Step 6

 */
public abstract class AbstractFacade<T> {

    protected Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract EntityManager getEntityManager();

    public void create(T entity){
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
