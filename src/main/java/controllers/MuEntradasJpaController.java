package controllers;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuEntradas;

public class MuEntradasJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MuEntradasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuEntradas entrada) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entrada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(MuEntradas entrada) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entrada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(MuEntradas entrada) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            MuEntradas toDelete = em.find(MuEntradas.class, entrada.getIdEntrada());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Collection<MuEntradas> findEntradasEntities() {
        return findEntradasEntities(true, -1, -1);
    }

    public Collection<MuEntradas> findEntradasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<MuEntradas> cq = em.getCriteriaBuilder().createQuery(MuEntradas.class);
            cq.select(cq.from(MuEntradas.class));
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

    public MuEntradas findEntrada(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuEntradas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntradasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            javax.persistence.criteria.Root<MuEntradas> rt = cq.from(MuEntradas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
