package controllers;

import clases.CrearPDF;
import clases.ListSalas;
import com.mycompany.interfazmuseo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import persistence.MuSalas;


public class MuSalasJpaController {

    private EntityManagerFactory emf = null;

    public MuSalasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MuseoAmericano");
    }

    public Collection<MuSalas> findSalaEntities() {
        return findSalaEntities(true, -1, -1);
    }

    public EntityManagerFactory getEmf() {

        return emf;
    }

    private Collection<MuSalas> findSalaEntities(boolean all, int maxResult, int firstResult) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuSalas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return (Collection<MuSalas>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(MuSalas salas) {
        EntityManager em = null;

        try {

            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(salas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuSalas salas) {
        EntityManager em = this.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(salas);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(MuSalas salas) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            em.getTransaction().begin();
            MuSalas porEliminar = em.find(MuSalas.class, salas.getIdMuseo());
            em.remove(porEliminar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public MuSalas findById(int id) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            MuSalas sala = em.find(MuSalas.class, id);
            if (sala != null) {
                em.refresh(sala);
            }
            return sala;
        } finally {
            em.close();
        }
    }
    
    public void generarReporteSalas(boolean mejores, String nombreArchivo) throws IOException {
    List<MuSalas> todasLasSalas = new ArrayList<>(findSalaEntities());
    List<MuSalas> seleccionadas;

    if (mejores) {
        seleccionadas = ListSalas.obtenerMejoresSalas(todasLasSalas);
    } else {
        seleccionadas = ListSalas.obtenerPeoresSalas(todasLasSalas);
    }

    String titulo = mejores ? "Top 10 Salas Mejor Valoradas" : "Top 10 Salas Peor Valoradas";

    CrearPDF.generarPDF(seleccionadas, titulo, nombreArchivo);
    System.out.println("PDF generado correctamente: " + nombreArchivo);
}
}
