package controllers;

import com.mycompany.interfazmuseo.*;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuEspecie;



public class MuEspecieJpaController {
        private EntityManagerFactory emf = null;

    public MuEspecieJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public Collection<MuEspecie> findEspecieEntities() {
        return findEspecieEntities(true, -1, -1);
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    private Collection<MuEspecie> findEspecieEntities(boolean all, int maxResult, int firstResult) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuEspecie.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return (Collection<MuEspecie>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(MuEspecie especie) {
        EntityManager em = null;

        try {

            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(especie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuEspecie especie) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(especie);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(MuEspecie especie) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            em.getTransaction().begin();
            MuEspecie porEliminar = em.find(MuEspecie.class, especie.getIdEspecie());
            em.remove(porEliminar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    
}
