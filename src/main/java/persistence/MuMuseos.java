/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_museos")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuMuseos.findAll", query = "SELECT m FROM MuMuseos m"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByIdMuseo", query = "SELECT m FROM MuMuseos m WHERE m.idMuseo = :idMuseo"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByNombre", query = "SELECT m FROM MuMuseos m WHERE m.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByTipo", query = "SELECT m FROM MuMuseos m WHERE m.tipo = :tipo"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByUbicacion", query = "SELECT m FROM MuMuseos m WHERE m.ubicacion = :ubicacion"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByFechaFundacion", query = "SELECT m FROM MuMuseos m WHERE m.fechaFundacion = :fechaFundacion"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findByNombreDirector", query = "SELECT m FROM MuMuseos m WHERE m.nombreDirector = :nombreDirector"),
    @javax.persistence.NamedQuery(name = "MuMuseos.findBySitioWeb", query = "SELECT m FROM MuMuseos m WHERE m.sitioWeb = :sitioWeb")})
public class MuMuseos implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_museo")
    private Integer idMuseo;
    @javax.persistence.Column(name = "nombre")
    private String nombre;
    @javax.persistence.Column(name = "tipo")
    private String tipo;
    @javax.persistence.Column(name = "ubicacion")
    private String ubicacion;
    @javax.persistence.Column(name = "fecha_fundacion")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFundacion;
    @javax.persistence.Column(name = "nombre_director")
    private String nombreDirector;
    @javax.persistence.Column(name = "sitio_web")
    private String sitioWeb;
    @javax.persistence.OneToMany(mappedBy = "idMuseo", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuEntradas> muEntradasCollection;
    @javax.persistence.OneToMany(mappedBy = "idMuseo", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuSalas> muSalasCollection;

    public MuMuseos() {
    }

    public MuMuseos(Integer idMuseo) {
        this.idMuseo = idMuseo;
    }

    public Integer getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(Integer idMuseo) {
        this.idMuseo = idMuseo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Collection<MuEntradas> getMuEntradasCollection() {
        return muEntradasCollection;
    }

    public void setMuEntradasCollection(Collection<MuEntradas> muEntradasCollection) {
        this.muEntradasCollection = muEntradasCollection;
    }

    public Collection<MuSalas> getMuSalasCollection() {
        return muSalasCollection;
    }

    public void setMuSalasCollection(Collection<MuSalas> muSalasCollection) {
        this.muSalasCollection = muSalasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuseo != null ? idMuseo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuMuseos)) {
            return false;
        }
        MuMuseos other = (MuMuseos) object;
        if ((this.idMuseo == null && other.idMuseo != null) || (this.idMuseo != null && !this.idMuseo.equals(other.idMuseo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuMuseos[ idMuseo=" + idMuseo + " ]";
    }
    
}
