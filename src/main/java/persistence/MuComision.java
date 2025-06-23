package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "mu_comision")
@NamedQueries({
    @NamedQuery(name = "MuComision.findAll", query = "SELECT m FROM MuComision m"),
    @NamedQuery(name = "MuComision.findByIdComision", query = "SELECT m FROM MuComision m WHERE m.idComision = :idComision"),
    @NamedQuery(name = "MuComision.findByComision", query = "SELECT m FROM MuComision m WHERE m.comision = :comision"),
    @NamedQuery(name = "MuComision.findByMontoFinal", query = "SELECT m FROM MuComision m WHERE m.montoFinal = :montoFinal")
})
public class MuComision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_comision")
    private Integer idComision;
    @javax.persistence.Column(name = "comision")
    private String comision;
    @javax.persistence.Column(name = "monto_final")
    private Integer montoFinal;
    @JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuTransaccion idTransaccion;

    public MuComision() {
    }

    public MuComision(Integer idComision) {
        this.idComision = idComision;
    }

    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public Integer getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(Integer montoFinal) {
        this.montoFinal = montoFinal;
    }

    public MuTransaccion getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(MuTransaccion idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComision != null ? idComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MuComision)) {
            return false;
        }
        MuComision other = (MuComision) object;
        if ((this.idComision == null && other.idComision != null) || (this.idComision != null && !this.idComision.equals(other.idComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuComision[ idComision=" + idComision + " ]";
    }
}