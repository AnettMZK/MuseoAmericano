/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.interfazmuseo.*;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuFichas;
import persistence.MuSalas;

/**
 *
 * @author PC
 */
public class MuFichasJpaController {
        private EntityManagerFactory emf = null;

    public MuFichasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public Collection<MuFichas> findMuseoEntities() {
        return findMuseoEntities(true, -1, -1);
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    private Collection<MuFichas> findMuseoEntities(boolean all, int maxResult, int firstResult) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuFichas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return (Collection<MuFichas>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(MuFichas fichas) {
        EntityManager em = null;

        try {

            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(fichas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<MuFichas> findFichasBySala(MuSalas sala) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM MuFichas f WHERE f.idSala = :sala", MuFichas.class)
                     .setParameter("sala", sala)
                     .getResultList();
        } finally {
            em.close();
        }
    }

}
