package controllers;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuTransaccion;

public class MuTransaccionJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MuTransaccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuTransaccion transaccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(transaccion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(MuTransaccion transaccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(transaccion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(MuTransaccion transaccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            MuTransaccion toDelete = em.find(MuTransaccion.class, transaccion.getIdTransaccion());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Collection<MuTransaccion> findTransaccionEntities() {
        return findTransaccionEntities(true, -1, -1);
    }

    public Collection<MuTransaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<MuTransaccion> cq = em.getCriteriaBuilder().createQuery(MuTransaccion.class);
            cq.select(cq.from(MuTransaccion.class));
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

    public MuTransaccion findTransaccion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuTransaccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransaccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            javax.persistence.criteria.Root<MuTransaccion> rt = cq.from(MuTransaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}