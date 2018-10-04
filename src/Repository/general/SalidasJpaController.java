/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.general;

import BLL.general.exceptions.NonexistentEntityException;
import BLL.general.exceptions.PreexistingEntityException;
import entity.mysql.Salidas;
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
public class SalidasJpaController implements Serializable {

    public SalidasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ventasPU");
    }
     public SalidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salidas salidas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(salidas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalidas(salidas.getId()) != null) {
                throw new PreexistingEntityException("Salidas " + salidas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salidas salidas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            salidas = em.merge(salidas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salidas.getId();
                if (findSalidas(id) == null) {
                    throw new NonexistentEntityException("The salidas with id " + id + " no longer exists.");
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
            Salidas salidas;
            try {
                salidas = em.getReference(Salidas.class, id);
                salidas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salidas with id " + id + " no longer exists.", enfe);
            }
            em.remove(salidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salidas> findSalidasEntities() {
        return findSalidasEntities(true, -1, -1);
    }

    public List<Salidas> findSalidasEntities(int maxResults, int firstResult) {
        return findSalidasEntities(false, maxResults, firstResult);
    }

    private List<Salidas> findSalidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salidas.class));
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

    public Salidas findSalidas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salidas> rt = cq.from(Salidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
