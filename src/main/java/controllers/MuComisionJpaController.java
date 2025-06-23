package controllers;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuComision;

public class MuComisionJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MuComisionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comision);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(MuComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comision);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(MuComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            MuComision toDelete = em.find(MuComision.class, comision.getIdComision());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Collection<MuComision> findComisionEntities() {
        return findComisionEntities(true, -1, -1);
    }

    public Collection<MuComision> findComisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<MuComision> cq = em.getCriteriaBuilder().createQuery(MuComision.class);
            cq.select(cq.from(MuComision.class));
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

    public MuComision findComision(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuComision.class, id);
        } finally {
            em.close();
        }
    }

    public int getComisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            javax.persistence.criteria.Root<MuComision> rt = cq.from(MuComision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}