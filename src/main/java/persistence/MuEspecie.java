/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_especie")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuEspecie.findAll", query = "SELECT m FROM MuEspecie m"),
    @javax.persistence.NamedQuery(name = "MuEspecie.findByIdEspecie", query = "SELECT m FROM MuEspecie m WHERE m.idEspecie = :idEspecie"),
    @javax.persistence.NamedQuery(name = "MuEspecie.findByNombreCientifico", query = "SELECT m FROM MuEspecie m WHERE m.nombreCientifico = :nombreCientifico"),
    @javax.persistence.NamedQuery(name = "MuEspecie.findByNombreComun", query = "SELECT m FROM MuEspecie m WHERE m.nombreComun = :nombreComun"),
    @javax.persistence.NamedQuery(name = "MuEspecie.findByFechaExtincion", query = "SELECT m FROM MuEspecie m WHERE m.fechaExtincion = :fechaExtincion"),
    @javax.persistence.NamedQuery(name = "MuEspecie.findByEpoca", query = "SELECT m FROM MuEspecie m WHERE m.epoca = :epoca")})
public class MuEspecie implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_especie")
    private Integer idEspecie;
    @javax.persistence.Column(name = "nombre_cientifico")
    private String nombreCientifico;
    @javax.persistence.Column(name = "nombre_comun")
    private String nombreComun;
    @javax.persistence.Column(name = "fecha_extincion")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaExtincion;
    @javax.persistence.Column(name = "epoca")
    private String epoca;
    @javax.persistence.Lob
    @javax.persistence.Column(name = "descripcion")
    private String descripcion;
    @javax.persistence.JoinColumn(name = "id_coleccion", referencedColumnName = "id_coleccion")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuColecciones idColeccion;

    public MuEspecie() {
    }

    public MuEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public Date getFechaExtincion() {
        return fechaExtincion;
    }

    public void setFechaExtincion(Date fechaExtincion) {
        this.fechaExtincion = fechaExtincion;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MuColecciones getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(MuColecciones idColeccion) {
        this.idColeccion = idColeccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecie != null ? idEspecie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuEspecie)) {
            return false;
        }
        MuEspecie other = (MuEspecie) object;
        if ((this.idEspecie == null && other.idEspecie != null) || (this.idEspecie != null && !this.idEspecie.equals(other.idEspecie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuEspecie[ idEspecie=" + idEspecie + " ]";
    }
    
}
