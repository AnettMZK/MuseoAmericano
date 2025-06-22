/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_tematicas")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuTematicas.findAll", query = "SELECT m FROM MuTematicas m"),
    @javax.persistence.NamedQuery(name = "MuTematicas.findByIdTematica", query = "SELECT m FROM MuTematicas m WHERE m.idTematica = :idTematica"),
    @javax.persistence.NamedQuery(name = "MuTematicas.findByNombre", query = "SELECT m FROM MuTematicas m WHERE m.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "MuTematicas.findByEpoca", query = "SELECT m FROM MuTematicas m WHERE m.epoca = :epoca"),
    @javax.persistence.NamedQuery(name = "MuTematicas.findByImagenDir", query = "SELECT m FROM MuTematicas m WHERE m.imagenDir = :imagenDir")})
public class MuTematicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_tematica")
    private Integer idTematica;
    @javax.persistence.Column(name = "nombre")
    private String nombre;
    @javax.persistence.Lob
    @javax.persistence.Column(name = "caracteristicas")
    private String caracteristicas;
    @javax.persistence.Column(name = "epoca")
    private String epoca;
    @javax.persistence.Column(name = "imagen_dir")
    private String imagenDir;
    @javax.persistence.JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuSalas idSala;

    public MuTematicas() {
    }

    public MuTematicas(Integer idTematica) {
        this.idTematica = idTematica;
    }

    public Integer getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(Integer idTematica) {
        this.idTematica = idTematica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getImagenDir() {
        return imagenDir;
    }

    public void setImagenDir(String imagenDir) {
        this.imagenDir = imagenDir;
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
        hash += (idTematica != null ? idTematica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuTematicas)) {
            return false;
        }
        MuTematicas other = (MuTematicas) object;
        if ((this.idTematica == null && other.idTematica != null) || (this.idTematica != null && !this.idTematica.equals(other.idTematica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuTematicas[ idTematica=" + idTematica + " ]";
    }
    
}
