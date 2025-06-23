package persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mu_transaccion")
@NamedQueries({
    @NamedQuery(name = "MuTransaccion.findAll", query = "SELECT m FROM MuTransaccion m"),
    @NamedQuery(name = "MuTransaccion.findByIdTransaccion", query = "SELECT m FROM MuTransaccion m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MuTransaccion.findByTipoTarjeta", query = "SELECT m FROM MuTransaccion m WHERE m.tipoTarjeta = :tipoTarjeta"),
    @NamedQuery(name = "MuTransaccion.findByFecha", query = "SELECT m FROM MuTransaccion m WHERE m.fecha = :fecha")
})
public class MuTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_transaccion")
    private Integer idTransaccion;
    @javax.persistence.Column(name = "tipo_tarjeta")
    private String tipoTarjeta;
    @javax.persistence.Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_entrada", referencedColumnName = "id_entrada")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuEntradas idEntrada;
    @OneToMany(mappedBy = "idTransaccion", fetch = javax.persistence.FetchType.LAZY)
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