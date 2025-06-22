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
@javax.persistence.Table(name = "mu_precios")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuPrecios.findAll", query = "SELECT m FROM MuPrecios m"),
    @javax.persistence.NamedQuery(name = "MuPrecios.findByIdPrecio", query = "SELECT m FROM MuPrecios m WHERE m.idPrecio = :idPrecio"),
    @javax.persistence.NamedQuery(name = "MuPrecios.findByNombrePrecio", query = "SELECT m FROM MuPrecios m WHERE m.nombrePrecio = :nombrePrecio"),
    @javax.persistence.NamedQuery(name = "MuPrecios.findByMonto", query = "SELECT m FROM MuPrecios m WHERE m.monto = :monto")})
public class MuPrecios implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_precio")
    private Integer idPrecio;
    @javax.persistence.Column(name = "nombre_precio")
    private String nombrePrecio;
    @javax.persistence.Column(name = "monto")
    private Integer monto;
    @javax.persistence.JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuSalas idSala;

    public MuPrecios() {
    }

    public MuPrecios(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public String getNombrePrecio() {
        return nombrePrecio;
    }

    public void setNombrePrecio(String nombrePrecio) {
        this.nombrePrecio = nombrePrecio;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
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
        hash += (idPrecio != null ? idPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuPrecios)) {
            return false;
        }
        MuPrecios other = (MuPrecios) object;
        if ((this.idPrecio == null && other.idPrecio != null) || (this.idPrecio != null && !this.idPrecio.equals(other.idPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuPrecios[ idPrecio=" + idPrecio + " ]";
    }
    
}
