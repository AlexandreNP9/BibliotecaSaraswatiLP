/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "tipo_usuario_has_modulo_sistema")
@NamedQueries({
    @NamedQuery(name = "TipoUsuarioHasModuloSistema.findAll", query = "SELECT t FROM TipoUsuarioHasModuloSistema t")})
public class TipoUsuarioHasModuloSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_usuario_has_modulo_sistema")
    private Integer idTipoUsuarioHasModuloSistema;
    @JoinColumn(name = "modulo_sistema_id_modulo_sistema", referencedColumnName = "id_modulo_sistema")
    @ManyToOne(optional = false)
    private ModuloSistema moduloSistemaIdModuloSistema;
    @JoinColumn(name = "tipo_usuario_id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuarioIdTipoUsuario;

    public TipoUsuarioHasModuloSistema() {
    }

    public TipoUsuarioHasModuloSistema(Integer idTipoUsuarioHasModuloSistema) {
        this.idTipoUsuarioHasModuloSistema = idTipoUsuarioHasModuloSistema;
    }

    public Integer getIdTipoUsuarioHasModuloSistema() {
        return idTipoUsuarioHasModuloSistema;
    }

    public void setIdTipoUsuarioHasModuloSistema(Integer idTipoUsuarioHasModuloSistema) {
        this.idTipoUsuarioHasModuloSistema = idTipoUsuarioHasModuloSistema;
    }

    public ModuloSistema getModuloSistemaIdModuloSistema() {
        return moduloSistemaIdModuloSistema;
    }

    public void setModuloSistemaIdModuloSistema(ModuloSistema moduloSistemaIdModuloSistema) {
        this.moduloSistemaIdModuloSistema = moduloSistemaIdModuloSistema;
    }

    public TipoUsuario getTipoUsuarioIdTipoUsuario() {
        return tipoUsuarioIdTipoUsuario;
    }

    public void setTipoUsuarioIdTipoUsuario(TipoUsuario tipoUsuarioIdTipoUsuario) {
        this.tipoUsuarioIdTipoUsuario = tipoUsuarioIdTipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuarioHasModuloSistema != null ? idTipoUsuarioHasModuloSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuarioHasModuloSistema)) {
            return false;
        }
        TipoUsuarioHasModuloSistema other = (TipoUsuarioHasModuloSistema) object;
        if ((this.idTipoUsuarioHasModuloSistema == null && other.idTipoUsuarioHasModuloSistema != null) || (this.idTipoUsuarioHasModuloSistema != null && !this.idTipoUsuarioHasModuloSistema.equals(other.idTipoUsuarioHasModuloSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoUsuarioHasModuloSistema[ idTipoUsuarioHasModuloSistema=" + idTipoUsuarioHasModuloSistema + " ]";
    }
    
}
