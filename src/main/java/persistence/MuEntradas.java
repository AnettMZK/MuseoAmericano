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
@javax.persistence.Table(name = "mu_entradas")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuEntradas.findAll", query = "SELECT m FROM MuEntradas m"),
    @javax.persistence.NamedQuery(name = "MuEntradas.findByIdEntrada", query = "SELECT m FROM MuEntradas m WHERE m.idEntrada = :idEntrada"),
    @javax.persistence.NamedQuery(name = "MuEntradas.findByNombreCliente", query = "SELECT m FROM MuEntradas m WHERE m.nombreCliente = :nombreCliente"),
    @javax.persistence.NamedQuery(name = "MuEntradas.findByFecha", query = "SELECT m FROM MuEntradas m WHERE m.fecha = :fecha"),
    @javax.persistence.NamedQuery(name = "MuEntradas.findByCodigoQr", query = "SELECT m FROM MuEntradas m WHERE m.codigoQr = :codigoQr"),
    @javax.persistence.NamedQuery(name = "MuEntradas.findByPrecioTotal", query = "SELECT m FROM MuEntradas m WHERE m.precioTotal = :precioTotal")})
public class MuEntradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_entrada")
    private Integer idEntrada;
    @javax.persistence.Column(name = "nombre_cliente")
    private String nombreCliente;
    @javax.persistence.Column(name = "fecha")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @javax.persistence.Column(name = "codigo_qr")
    private String codigoQr;
    @javax.persistence.Column(name = "precio_total")
    private Integer precioTotal;
    @javax.persistence.JoinColumn(name = "id_museo", referencedColumnName = "id_museo")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuMuseos idMuseo;
    @javax.persistence.OneToMany(mappedBy = "idEntrada", fetch = javax.persistence.FetchType.LAZY)
    private Collection<MuTransaccion> muTransaccionCollection;

    public MuEntradas() {
    }

    public MuEntradas(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public MuMuseos getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(MuMuseos idMuseo) {
        this.idMuseo = idMuseo;
    }

    public Collection<MuTransaccion> getMuTransaccionCollection() {
        return muTransaccionCollection;
    }

    public void setMuTransaccionCollection(Collection<MuTransaccion> muTransaccionCollection) {
        this.muTransaccionCollection = muTransaccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrada != null ? idEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuEntradas)) {
            return false;
        }
        MuEntradas other = (MuEntradas) object;
        if ((this.idEntrada == null && other.idEntrada != null) || (this.idEntrada != null && !this.idEntrada.equals(other.idEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuEntradas[ idEntrada=" + idEntrada + " ]";
    }
    
}
