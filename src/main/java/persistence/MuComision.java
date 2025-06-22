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
@javax.persistence.Table(name = "mu_comision")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuComision.findAll", query = "SELECT m FROM MuComision m"),
    @javax.persistence.NamedQuery(name = "MuComision.findByIdComision", query = "SELECT m FROM MuComision m WHERE m.idComision = :idComision"),
    @javax.persistence.NamedQuery(name = "MuComision.findByComision", query = "SELECT m FROM MuComision m WHERE m.comision = :comision"),
    @javax.persistence.NamedQuery(name = "MuComision.findByMontoFinal", query = "SELECT m FROM MuComision m WHERE m.montoFinal = :montoFinal")})
public class MuComision implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_comision")
    private Integer idComision;
    @javax.persistence.Column(name = "comision")
    private String comision;
    @javax.persistence.Column(name = "monto_final")
    private Integer montoFinal;
    @javax.persistence.JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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
