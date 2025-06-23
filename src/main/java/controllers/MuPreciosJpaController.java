package controllers;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.MuPrecios;

public class MuPreciosJpaController {

    private EntityManagerFactory emf = null;

    public MuPreciosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear nuevo precio
    public void create(MuPrecios precio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(precio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Editar precio existente
    public void edit(MuPrecios precio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(precio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Eliminar precio por objeto
    public void delete(MuPrecios precio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            MuPrecios toDelete = em.find(MuPrecios.class, precio.getIdPrecio());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Buscar todos los precios
    public Collection<MuPrecios> findPreciosEntities() {
        return findPreciosEntities(true, -1, -1);
    }

    public Collection<MuPrecios> findPreciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<MuPrecios> cq = em.getCriteriaBuilder().createQuery(MuPrecios.class);
            cq.select(cq.from(MuPrecios.class));
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

    // Buscar precio por ID
    public MuPrecios findPrecio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuPrecios.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener el total de precios
    public int getPreciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<MuPrecios> rt = cq.from(MuPrecios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}