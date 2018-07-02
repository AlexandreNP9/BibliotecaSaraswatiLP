/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "obra")
@NamedQueries({
    @NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o")})
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ano_obra")
    @Temporal(TemporalType.DATE)
    private Date anoObra;
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra")
    private String idObra;
    @Basic(optional = false)
    @Column(name = "titulo_obra")
    private String tituloObra;
    @Basic(optional = false)
    @Column(name = "quantidade_obra")
    private int quantidadeObra;
    @Column(name = "observacoes_obra")
    private String observacoesObra;
    @ManyToMany(mappedBy = "obraList")
    private List<Autor> autorList;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private Status statusIdStatus;
    @JoinColumn(name = "tipo_obra_id_tipoObra", referencedColumnName = "id_tipoObra")
    @ManyToOne(optional = false)
    private TipoObra tipoobraidtipoObra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obra")
    private List<UsuarioExecutaObra> usuarioExecutaObraList;

    public Obra() {
    }

    public Obra(String idObra) {
        this.idObra = idObra;
    }

    public Obra(String idObra, Date anoObra, String tituloObra, int quantidadeObra) {
        this.idObra = idObra;
        this.anoObra = anoObra;
        this.tituloObra = tituloObra;
        this.quantidadeObra = quantidadeObra;
    }

    public Date getAnoObra() {
        return anoObra;
    }

    public void setAnoObra(Date anoObra) {
        this.anoObra = anoObra;
    }

    public String getIdObra() {
        return idObra;
    }

    public void setIdObra(String idObra) {
        this.idObra = idObra;
    }

    public String getTituloObra() {
        return tituloObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    public int getQuantidadeObra() {
        return quantidadeObra;
    }

    public void setQuantidadeObra(int quantidadeObra) {
        this.quantidadeObra = quantidadeObra;
    }

    public String getObservacoesObra() {
        return observacoesObra;
    }

    public void setObservacoesObra(String observacoesObra) {
        this.observacoesObra = observacoesObra;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    public Status getStatusIdStatus() {
        return statusIdStatus;
    }

    public void setStatusIdStatus(Status statusIdStatus) {
        this.statusIdStatus = statusIdStatus;
    }

    public TipoObra getTipoobraidtipoObra() {
        return tipoobraidtipoObra;
    }

    public void setTipoobraidtipoObra(TipoObra tipoobraidtipoObra) {
        this.tipoobraidtipoObra = tipoobraidtipoObra;
    }

    public List<UsuarioExecutaObra> getUsuarioExecutaObraList() {
        return usuarioExecutaObraList;
    }

    public void setUsuarioExecutaObraList(List<UsuarioExecutaObra> usuarioExecutaObraList) {
        this.usuarioExecutaObraList = usuarioExecutaObraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObra != null ? idObra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
        if ((this.idObra == null && other.idObra != null) || (this.idObra != null && !this.idObra.equals(other.idObra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Obra[ idObra=" + idObra + " ]";
    }
    
}
