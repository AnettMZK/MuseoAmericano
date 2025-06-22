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
@javax.persistence.Table(name = "mu_fichas")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "MuFichas.findAll", query = "SELECT m FROM MuFichas m"),
    @javax.persistence.NamedQuery(name = "MuFichas.findByIdFicha", query = "SELECT m FROM MuFichas m WHERE m.idFicha = :idFicha"),
    @javax.persistence.NamedQuery(name = "MuFichas.findByValoracion", query = "SELECT m FROM MuFichas m WHERE m.valoracion = :valoracion")})
public class MuFichas implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "id_ficha")
    private Integer idFicha;
    @javax.persistence.Column(name = "valoracion")
    private Integer valoracion;
    @javax.persistence.Lob
    @javax.persistence.Column(name = "observacion")
    private String observacion;
    @javax.persistence.JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private MuSalas idSala;

    public MuFichas() {
    }

    public MuFichas(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuFichas)) {
            return false;
        }
        MuFichas other = (MuFichas) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MuFichas[ idFicha=" + idFicha + " ]";
    }
    
}
