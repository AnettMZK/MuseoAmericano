/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_salas")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuSalas.findAll", query = "SELECT m FROM MuSalas m"),
    @javax.persistence.NamedQuery(name = "MuSalas.findByIdSala", query = "SELECT m FROM MuSalas m WHERE m.idSala = :idSala"),
    @javax.persistence.NamedQuery(name = "MuSalas.findByNombre", query = "SELECT m FROM MuSalas m WHERE m.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "MuSalas.findByDescripcion", query = "SELECT m FROM MuSalas m WHERE m.descripcion = :descripcion"),
    @javax.persistence.NamedQuery(name = "MuSalas.findByPromedioValoracion", query = "SELECT m FROM MuSalas m WHERE m.promedioValoracion = :promedioValoracion")})
public class MuSalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_sala")
    private Integer idSala;
    @javax.persistence.Column(name = "nombre")
    private String nombre;
    @javax.persistence.Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Column(name = "promedio_valoracion")
    private BigDecimal promedioValoracion;
    @javax.persistence.OneToMany(mappedBy = "idSala", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuFichas> muFichasCollection;
    @javax.persistence.OneToMany(mappedBy = "idSala", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuTematicas> muTematicasCollection;
    @javax.persistence.OneToMany(mappedBy = "idSala", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuColecciones> muColeccionesCollection;
    @javax.persistence.OneToMany(mappedBy = "idSala", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuPrecios> muPreciosCollection;
    @javax.persistence.JoinColumn(name = "id_museo", referencedColumnName = "id_museo")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuMuseos idMuseo;

    public MuSalas() {
    }

    public MuSalas(Integer idSala) {
        this.idSala = idSala;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPromedioValoracion() {
        return promedioValoracion;
    }

    public void setPromedioValoracion(BigDecimal promedioValoracion) {
        this.promedioValoracion = promedioValoracion;
    }

    public Collection<MuFichas> getMuFichasCollection() {
        return muFichasCollection;
    }

    public void setMuFichasCollection(Collection<MuFichas> muFichasCollection) {
        this.muFichasCollection = muFichasCollection;
    }

    public Collection<MuTematicas> getMuTematicasCollection() {
        return muTematicasCollection;
    }

    public void setMuTematicasCollection(Collection<MuTematicas> muTematicasCollection) {
        this.muTematicasCollection = muTematicasCollection;
    }

    public Collection<MuColecciones> getMuColeccionesCollection() {
        return muColeccionesCollection;
    }

    public void setMuColeccionesCollection(Collection<MuColecciones> muColeccionesCollection) {
        this.muColeccionesCollection = muColeccionesCollection;
    }

    public Collection<MuPrecios> getMuPreciosCollection() {
        return muPreciosCollection;
    }

    public void setMuPreciosCollection(Collection<MuPrecios> muPreciosCollection) {
        this.muPreciosCollection = muPreciosCollection;
    }

    public MuMuseos getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(MuMuseos idMuseo) {
        this.idMuseo = idMuseo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuSalas)) {
            return false;
        }
        MuSalas other = (MuSalas) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuSalas[ idSala=" + idSala + " ]";
    }
    
}
