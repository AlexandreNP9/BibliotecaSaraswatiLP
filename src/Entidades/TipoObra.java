/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "tipo_obra")
@NamedQueries({
    @NamedQuery(name = "TipoObra.findAll", query = "SELECT t FROM TipoObra t")})
public class TipoObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipoObra")
    private Integer idtipoObra;
    @Basic(optional = false)
    @Column(name = "nome_tipoObra")
    private String nometipoObra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoobraidtipoObra")
    private List<Obra> obraList;

    public TipoObra() {
    }

    public TipoObra(Integer idtipoObra) {
        this.idtipoObra = idtipoObra;
    }

    public TipoObra(Integer idtipoObra, String nometipoObra) {
        this.idtipoObra = idtipoObra;
        this.nometipoObra = nometipoObra;
    }

    public Integer getIdtipoObra() {
        return idtipoObra;
    }

    public void setIdtipoObra(Integer idtipoObra) {
        this.idtipoObra = idtipoObra;
    }

    public String getNometipoObra() {
        return nometipoObra;
    }

    public void setNometipoObra(String nometipoObra) {
        this.nometipoObra = nometipoObra;
    }

    public List<Obra> getObraList() {
        return obraList;
    }

    public void setObraList(List<Obra> obraList) {
        this.obraList = obraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoObra != null ? idtipoObra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoObra)) {
            return false;
        }
        TipoObra other = (TipoObra) object;
        if ((this.idtipoObra == null && other.idtipoObra != null) || (this.idtipoObra != null && !this.idtipoObra.equals(other.idtipoObra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoObra[ idtipoObra=" + idtipoObra + " ]";
    }
    
}
