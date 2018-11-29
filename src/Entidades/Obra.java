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
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra")
    private Integer idObra;
    @Basic(optional = false)
    @Column(name = "nome_obra")
    private String nomeObra;
    @Basic(optional = false)
    @Column(name = "ano_obra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anoObra;
    @Basic(optional = false)
    @Column(name = "quantidade_obra")
    private int quantidadeObra;
    @Column(name = "observacoes_obra")
    private String observacoesObra;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private Status statusIdStatus;
    @JoinColumn(name = "tipo_obra_id_tipoObra", referencedColumnName = "id_tipoObra")
    @ManyToOne(optional = false)
    private TipoObra tipoobraidtipoObra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obraIdObra")
    private List<Emprestimo> emprestimoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obraIdObra")
    private List<AutorPublicaObra> autorPublicaObraList;

    public Obra() {
    }

    public Obra(Integer idObra) {
        this.idObra = idObra;
    }

    public Obra(Integer idObra, String nomeObra, Date anoObra, int quantidadeObra) {
        this.idObra = idObra;
        this.nomeObra = nomeObra;
        this.anoObra = anoObra;
        this.quantidadeObra = quantidadeObra;
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    public String getNomeObra() {
        return nomeObra;
    }

    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }

    public Date getAnoObra() {
        return anoObra;
    }

    public void setAnoObra(Date anoObra) {
        this.anoObra = anoObra;
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

    public List<Emprestimo> getEmprestimoList() {
        return emprestimoList;
    }

    public void setEmprestimoList(List<Emprestimo> emprestimoList) {
        this.emprestimoList = emprestimoList;
    }

    public List<AutorPublicaObra> getAutorPublicaObraList() {
        return autorPublicaObraList;
    }

    public void setAutorPublicaObraList(List<AutorPublicaObra> autorPublicaObraList) {
        this.autorPublicaObraList = autorPublicaObraList;
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
