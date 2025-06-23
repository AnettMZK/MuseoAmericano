package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mu_entradas")
@NamedQueries({
    @NamedQuery(name = "MuEntradas.findAll", query = "SELECT m FROM MuEntradas m"),
    @NamedQuery(name = "MuEntradas.findByIdEntrada", query = "SELECT m FROM MuEntradas m WHERE m.idEntrada = :idEntrada"),
    @NamedQuery(name = "MuEntradas.findByNombreCliente", query = "SELECT m FROM MuEntradas m WHERE m.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "MuEntradas.findByFecha", query = "SELECT m FROM MuEntradas m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MuEntradas.findByCodigoQr", query = "SELECT m FROM MuEntradas m WHERE m.codigoQr = :codigoQr"),
    @NamedQuery(name = "MuEntradas.findByPrecioTotal", query = "SELECT m FROM MuEntradas m WHERE m.precioTotal = :precioTotal")
})
public class MuEntradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_entrada")
    private Integer idEntrada;
    @javax.persistence.Column(name = "nombre_cliente")
    private String nombreCliente;
    @javax.persistence.Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @javax.persistence.Column(name = "codigo_qr")
    private String codigoQr;
    @javax.persistence.Column(name = "precio_total")
    private Integer precioTotal;
    @javax.persistence.Column(name = "qr_image_path") // New field
    private String qrImagePath;
    @JoinColumn(name = "id_museo", referencedColumnName = "id_museo")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuMuseos idMuseo;

    public MuEntradas() {
    }

    public Integer getIdEntrada() { return idEntrada; }
    public void setIdEntrada(Integer idEntrada) { this.idEntrada = idEntrada; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getCodigoQr() { return codigoQr; }
    public void setCodigoQr(String codigoQr) { this.codigoQr = codigoQr; }
    public Integer getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(Integer precioTotal) { this.precioTotal = precioTotal; }
    public String getQrImagePath() { return qrImagePath; } // New getter
    public void setQrImagePath(String qrImagePath) { this.qrImagePath = qrImagePath; } // New setter
    public MuMuseos getIdMuseo() { return idMuseo; }
    public void setIdMuseo(MuMuseos idMuseo) { this.idMuseo = idMuseo; }

    @Override
    public int hashCode() { int hash = 0; hash += (idEntrada != null ? idEntrada.hashCode() : 0); return hash; }
    @Override
    public boolean equals(Object object) { if (!(object instanceof MuEntradas)) return false; MuEntradas other = (MuEntradas) object; return (this.idEntrada == null ? other.idEntrada == null : this.idEntrada.equals(other.idEntrada)); }
    @Override
    public String toString() { return "persistence.MuEntradas[ idEntrada=" + idEntrada + " ]"; }
}