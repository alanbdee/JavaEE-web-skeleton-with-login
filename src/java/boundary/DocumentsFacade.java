/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Companies;
import entities.DocumentPages;
import entities.Documents;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ValidationException;

/**
 *
 * @author alan
 */
@Stateless
public class DocumentsFacade extends AbstractFacade<Documents> {

    @PersistenceContext(unitName = "REMPU")
    private EntityManager em;
    private Documents tempDocument;

    public Documents getTempDocument() {
        if (tempDocument == null) {
            tempDocument = new Documents();
        }
        return tempDocument;
    }

    public void setTempDocument(Documents newDocument) {
        this.tempDocument = newDocument;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public DocumentsFacade() {
        super(Documents.class);
    }

    /**
     * creates an document. A perent must be set, with Company, Agent etc. 
     * @param entity 
     */
    @Override
    public void create(Documents entity) throws ValidationException {
        boolean hasParent = false;
        if (entity.getCompany() != null) {
            hasParent = true;
        } else if (entity.getAgent() != null) {
            hasParent = true;
        }
        if (hasParent) {
            java.sql.Date d = new java.sql.Date(new java.util.Date().getTime());
            entity.setCreated(d);
            getEntityManager().persist(entity);
        } else {
            throw new ValidationException("Document must have a set parent before it can be created.");
        }

    }

    @Override
    public void remove(Documents entity) {
        entity.setStatus("DELETED");
        getEntityManager().merge(entity);
    }

    @Override
    public List<Documents> findAll() {
        //Boilerplate
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        //Interesting stuff happens here
        Root<Documents> root = cq.from(entityClass); //Step 2
        cq.select(root);
        Predicate status = cb.notEqual(root.get("status"), "DELETED");
        cq.where(status);
        //Boilerplate code
        Query qry = em.createQuery(cq); //Step 6
        return qry.getResultList(); //Step 6
    }

    @Override
    public List<Documents> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        //Boilerplate
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Documents> cq = cb.createQuery(entityClass);
        //Interesting stuff happens here
        Root<Documents> root = cq.from(entityClass); //Step 2
        Expression count = cb.count(root);
        cq.select(count);
        Predicate status = cb.notEqual(root.get("status"), "DELETED");
        cq.where(status);
        //Boilerplate code
        Query qry = em.createQuery(cq); //Step 6
        Object singleResult = qry.getSingleResult();
        return ((Long) singleResult).intValue();
    }

    public List<Documents> findRange(int[] range, Companies company) {
        List<Documents> result = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Documents> query = cb.createQuery(entityClass);
        Root<Documents> root = query.from(entityClass);
        Predicate status = cb.notEqual(root.get("status"), "DELETED");
        query.select(root).where(cb.equal(root.get("company"), company), status);
        TypedQuery<Documents> q = em.createQuery(query);
        int maxResults = q.getMaxResults();
        if (range[1] > maxResults) {
            range[1] = maxResults;
        }
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public long getCompanyWithMostDocuments() {
        Long result = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<Documents> root = cq.from(entityClass);
        Expression<Integer> userId = root.get("company");
        Expression<Long> count = cb.count(userId);

        cq.multiselect(userId.alias("userID"), count.alias("CNT"));
        Predicate status = cb.notEqual(root.get("status"), "DELETED");
        cq.where(status);
        cq.groupBy(userId);
        cq.orderBy(cb.desc(count));


        TypedQuery<Tuple> tq = em.createQuery(cq);
        tq.setMaxResults(1);
        Tuple singleResult = tq.getSingleResult();
        result = (Long) singleResult.get("userID");
        return result;
    }

//    public Long getGroupWithMostDocuments(String usertype) throws ValidationException {
//        Long result = null;
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//
//        Root<Documents> root = cq.from(entityClass);
//        Expression<Integer> userId = root.get("userID");
//        Expression<String> userType = root.get("userType");
//        Expression<Long> count = cb.count(userId);
//
//        cq.multiselect(userId.alias("userID"), count.alias("CNT"));
//        Predicate status = cb.notEqual(root.get("status"), "DELETED");
//        cq.where(cb.equal(userType, usertype), status);
//        cq.groupBy(userId);
//        cq.orderBy(cb.desc(count));
//
//
//        TypedQuery<Tuple> tq = em.createQuery(cq);
//        tq.setMaxResults(1);
//        Tuple singleResult = tq.getSingleResult();
//        result = (Long) singleResult.get("userID");
//        return result;
//    }
//    public List<Documents> findAll(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Required group was not given.");
//        }
//        System.out.println("DocumentsFacade, query table for Documents data");
//        //Boilerplate
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery();
//
//        //Interesting stuff happens here
//        Root<Documents> root = cq.from(entityClass); //Step 2
//        cq.select(root);
//        if(group.equals("COMPANY")){
//            Expression<String> userType = root.get("COMPANY_ID");
//        }
//        
//        Predicate status = cb.notEqual(root.get("status"), "DELETED");
//        cq.where(cb.notEqual(userType, null), status);
//
//        //Boilerplate code
//        Query qry = em.createQuery(cq); //Step 6
//        return qry.getResultList(); //Step 6
//    }
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
