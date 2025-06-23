package controllers;

import com.mycompany.interfazmuseo.*;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuTematicas;



public class MuTematicasJpaController {
    
    private EntityManagerFactory emf = null;

    public MuTematicasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public Collection<MuTematicas> findTematicaEntities() {
        return findTematicaEntities(true, -1, -1);
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    private Collection<MuTematicas> findTematicaEntities(boolean all, int maxResult, int firstResult) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuTematicas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return (Collection<MuTematicas>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(MuTematicas tematica) {
        EntityManager em = null;

        try {

            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(tematica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuTematicas tematica) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tematica);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(MuTematicas tematica) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            em.getTransaction().begin();
            MuTematicas porEliminar = em.find(MuTematicas.class, tematica.getIdTematica());
            em.remove(porEliminar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
