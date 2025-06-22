/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_colecciones")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuColecciones.findAll", query = "SELECT m FROM MuColecciones m"),
    @javax.persistence.NamedQuery(name = "MuColecciones.findByIdColeccion", query = "SELECT m FROM MuColecciones m WHERE m.idColeccion = :idColeccion"),
    @javax.persistence.NamedQuery(name = "MuColecciones.findByNombre", query = "SELECT m FROM MuColecciones m WHERE m.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "MuColecciones.findBySiglo", query = "SELECT m FROM MuColecciones m WHERE m.siglo = :siglo")})
public class MuColecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_coleccion")
    private Integer idColeccion;
    @javax.persistence.Column(name = "nombre")
    private String nombre;
    @javax.persistence.Column(name = "siglo")
    private String siglo;
    @javax.persistence.Lob
    @javax.persistence.Column(name = "descripcion")
    private String descripcion;
    @javax.persistence.OneToMany(mappedBy = "idColeccion", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuEspecie> muEspecieCollection;
    @javax.persistence.JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuSalas idSala;

    public MuColecciones() {
    }

    public MuColecciones(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglo() {
        return siglo;
    }

    public void setSiglo(String siglo) {
        this.siglo = siglo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<MuEspecie> getMuEspecieCollection() {
        return muEspecieCollection;
    }

    public void setMuEspecieCollection(Collection<MuEspecie> muEspecieCollection) {
        this.muEspecieCollection = muEspecieCollection;
    }

    public MuSalas getIdSala() {
        return idSala;
    }

    public void setIdSala(MuSalas idSala) {
        this.idSala = idSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColeccion != null ? idColeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuColecciones)) {
            return false;
        }
        MuColecciones other = (MuColecciones) object;
        if ((this.idColeccion == null && other.idColeccion != null) || (this.idColeccion != null && !this.idColeccion.equals(other.idColeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuColecciones[ idColeccion=" + idColeccion + " ]";
    }
    
}
