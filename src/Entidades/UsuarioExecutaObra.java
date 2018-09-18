/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author a1602896
 */
@Entity
@Table(name = "usuario_executa_obra")
@NamedQueries({
    @NamedQuery(name = "UsuarioExecutaObra.findAll", query = "SELECT u FROM UsuarioExecutaObra u")})
public class UsuarioExecutaObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioExecutaObraPK usuarioExecutaObraPK;
    @Basic(optional = false)
    @Column(name = "dia_execucao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diaExecucao;
    @JoinColumn(name = "obra_id_obra", referencedColumnName = "id_obra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Obra obra;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public UsuarioExecutaObra() {
    }

    public UsuarioExecutaObra(UsuarioExecutaObraPK usuarioExecutaObraPK) {
        this.usuarioExecutaObraPK = usuarioExecutaObraPK;
    }

    public UsuarioExecutaObra(UsuarioExecutaObraPK usuarioExecutaObraPK, Date diaExecucao) {
        this.usuarioExecutaObraPK = usuarioExecutaObraPK;
        this.diaExecucao = diaExecucao;
    }

    public UsuarioExecutaObra(int usuarioIdUsuario, String obraIdObra) {
        this.usuarioExecutaObraPK = new UsuarioExecutaObraPK(usuarioIdUsuario, obraIdObra);
    }

    public UsuarioExecutaObraPK getUsuarioExecutaObraPK() {
        return usuarioExecutaObraPK;
    }

    public void setUsuarioExecutaObraPK(UsuarioExecutaObraPK usuarioExecutaObraPK) {
        this.usuarioExecutaObraPK = usuarioExecutaObraPK;
    }

    public Date getDiaExecucao() {
        return diaExecucao;
    }

    public void setDiaExecucao(Date diaExecucao) {
        this.diaExecucao = diaExecucao;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioExecutaObraPK != null ? usuarioExecutaObraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioExecutaObra)) {
            return false;
        }
        UsuarioExecutaObra other = (UsuarioExecutaObra) object;
        if ((this.usuarioExecutaObraPK == null && other.usuarioExecutaObraPK != null) || (this.usuarioExecutaObraPK != null && !this.usuarioExecutaObraPK.equals(other.usuarioExecutaObraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.UsuarioExecutaObra[ usuarioExecutaObraPK=" + usuarioExecutaObraPK + " ]";
    }

}
