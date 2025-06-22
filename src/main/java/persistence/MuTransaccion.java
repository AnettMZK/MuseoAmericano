/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author PC
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "mu_transaccion")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuTransaccion.findAll", query = "SELECT m FROM MuTransaccion m"),
    @javax.persistence.NamedQuery(name = "MuTransaccion.findByIdTransaccion", query = "SELECT m FROM MuTransaccion m WHERE m.idTransaccion = :idTransaccion"),
    @javax.persistence.NamedQuery(name = "MuTransaccion.findByTipoTarjeta", query = "SELECT m FROM MuTransaccion m WHERE m.tipoTarjeta = :tipoTarjeta"),
    @javax.persistence.NamedQuery(name = "MuTransaccion.findByFecha", query = "SELECT m FROM MuTransaccion m WHERE m.fecha = :fecha")})
public class MuTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_transaccion")
    private Integer idTransaccion;
    @javax.persistence.Column(name = "tipo_tarjeta")
    private String tipoTarjeta;
    @javax.persistence.Column(name = "fecha")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @javax.persistence.JoinColumn(name = "id_entrada", referencedColumnName = "id_entrada")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuEntradas idEntrada;
    @javax.persistence.OneToMany(mappedBy = "idTransaccion", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuComision> muComisionCollection;

    public MuTransaccion() {
    }

    public MuTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MuEntradas getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(MuEntradas idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Collection<MuComision> getMuComisionCollection() {
        return muComisionCollection;
    }

    public void setMuComisionCollection(Collection<MuComision> muComisionCollection) {
        this.muComisionCollection = muComisionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuTransaccion)) {
            return false;
        }
        MuTransaccion other = (MuTransaccion) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuTransaccion[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
