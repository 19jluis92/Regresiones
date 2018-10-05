/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.general;

import Repository.general.exceptions.NonexistentEntityException;
import entity.mysql.Lineal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jluis
 */
public class LinealJpaController implements Serializable {

    public LinealJpaController() {
         
        this.emf = Persistence.createEntityManagerFactory("RegresionesPU");
    }
    private EntityManagerFactory emf = null;

    public LinealJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lineal lineal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lineal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lineal lineal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lineal = em.merge(lineal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lineal.getId();
                if (findLineal(id) == null) {
                    throw new NonexistentEntityException("The lineal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lineal lineal;
            try {
                lineal = em.getReference(Lineal.class, id);
                lineal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineal with id " + id + " no longer exists.", enfe);
            }
            em.remove(lineal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lineal> findLinealEntities() {
        return findLinealEntities(true, -1, -1);
    }

    public List<Lineal> findLinealEntities(int maxResults, int firstResult) {
        return findLinealEntities(false, maxResults, firstResult);
    }

    private List<Lineal> findLinealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lineal.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Lineal findLineal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lineal.class, id);
        } finally {
            em.close();
        }
    }

    public int getLinealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lineal> rt = cq.from(Lineal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
