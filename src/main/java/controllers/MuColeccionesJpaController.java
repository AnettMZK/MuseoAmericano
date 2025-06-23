package controllers;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuColecciones;



public class MuColeccionesJpaController implements Serializable {
   
    private EntityManagerFactory emf = null;

    public MuColeccionesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public Collection<MuColecciones> findColeccionEntities() {
        return findColeccionEntities(true, -1, -1);
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    private Collection<MuColecciones> findColeccionEntities(boolean all, int maxResult, int firstResult) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuColecciones.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return (Collection<MuColecciones>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(MuColecciones coleccion) {
        EntityManager em = null;

        try {

            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(coleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuColecciones coleccion) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(coleccion);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(MuColecciones coleccion) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            em.getTransaction().begin();
            MuColecciones porEliminar = em.find(MuColecciones.class, coleccion.getIdColeccion());
            em.remove(porEliminar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
