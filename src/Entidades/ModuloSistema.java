/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "modulo_sistema")
@NamedQueries({
    @NamedQuery(name = "ModuloSistema.findAll", query = "SELECT m FROM ModuloSistema m")})
public class ModuloSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_modulo_sistema")
    private Integer idModuloSistema;
    @Basic(optional = false)
    @Column(name = "nome_modulo_sistema")
    private String nomeModuloSistema;
    @JoinTable(name = "modulo_sistema_has_tipo_usuario", joinColumns = {
        @JoinColumn(name = "modulo_sistema_id_modulo_sistema", referencedColumnName = "id_modulo_sistema")}, inverseJoinColumns = {
        @JoinColumn(name = "tipo_usuario_id_tipo_usuario", referencedColumnName = "id_tipo_usuario")})
    @ManyToMany
    private List<TipoUsuario> tipoUsuarioList;

    public ModuloSistema() {
    }

    public ModuloSistema(Integer idModuloSistema) {
        this.idModuloSistema = idModuloSistema;
    }

    public ModuloSistema(Integer idModuloSistema, String nomeModuloSistema) {
        this.idModuloSistema = idModuloSistema;
        this.nomeModuloSistema = nomeModuloSistema;
    }

    public Integer getIdModuloSistema() {
        return idModuloSistema;
    }

    public void setIdModuloSistema(Integer idModuloSistema) {
        this.idModuloSistema = idModuloSistema;
    }

    public String getNomeModuloSistema() {
        return nomeModuloSistema;
    }

    public void setNomeModuloSistema(String nomeModuloSistema) {
        this.nomeModuloSistema = nomeModuloSistema;
    }

    public List<TipoUsuario> getTipoUsuarioList() {
        return tipoUsuarioList;
    }

    public void setTipoUsuarioList(List<TipoUsuario> tipoUsuarioList) {
        this.tipoUsuarioList = tipoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModuloSistema != null ? idModuloSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloSistema)) {
            return false;
        }
        ModuloSistema other = (ModuloSistema) object;
        if ((this.idModuloSistema == null && other.idModuloSistema != null) || (this.idModuloSistema != null && !this.idModuloSistema.equals(other.idModuloSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ModuloSistema[ idModuloSistema=" + idModuloSistema + " ]";
    }
    
}
