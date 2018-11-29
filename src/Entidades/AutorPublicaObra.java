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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "autor_publica_obra")
@NamedQueries({
    @NamedQuery(name = "AutorPublicaObra.findAll", query = "SELECT a FROM AutorPublicaObra a")})
public class AutorPublicaObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_autor_publica_obra")
    private Integer idAutorPublicaObra;
    @Basic(optional = false)
    @Column(name = "local_autor_publica_obra")
    private String localAutorPublicaObra;
    @Basic(optional = false)
    @Column(name = "data_autor_publica_obra")
    @Temporal(TemporalType.DATE)
    private Date dataAutorPublicaObra;
    @JoinColumn(name = "autor_id_autor", referencedColumnName = "id_autor")
    @ManyToOne(optional = false)
    private Autor autorIdAutor;
    @JoinColumn(name = "obra_id_obra", referencedColumnName = "id_obra")
    @ManyToOne(optional = false)
    private Obra obraIdObra;

    public AutorPublicaObra() {
    }

    public AutorPublicaObra(Integer idAutorPublicaObra) {
        this.idAutorPublicaObra = idAutorPublicaObra;
    }

    public AutorPublicaObra(Integer idAutorPublicaObra, String localAutorPublicaObra, Date dataAutorPublicaObra) {
        this.idAutorPublicaObra = idAutorPublicaObra;
        this.localAutorPublicaObra = localAutorPublicaObra;
        this.dataAutorPublicaObra = dataAutorPublicaObra;
    }

    public Integer getIdAutorPublicaObra() {
        return idAutorPublicaObra;
    }

    public void setIdAutorPublicaObra(Integer idAutorPublicaObra) {
        this.idAutorPublicaObra = idAutorPublicaObra;
    }

    public String getLocalAutorPublicaObra() {
        return localAutorPublicaObra;
    }

    public void setLocalAutorPublicaObra(String localAutorPublicaObra) {
        this.localAutorPublicaObra = localAutorPublicaObra;
    }

    public Date getDataAutorPublicaObra() {
        return dataAutorPublicaObra;
    }

    public void setDataAutorPublicaObra(Date dataAutorPublicaObra) {
        this.dataAutorPublicaObra = dataAutorPublicaObra;
    }

    public Autor getAutorIdAutor() {
        return autorIdAutor;
    }

    public void setAutorIdAutor(Autor autorIdAutor) {
        this.autorIdAutor = autorIdAutor;
    }

    public Obra getObraIdObra() {
        return obraIdObra;
    }

    public void setObraIdObra(Obra obraIdObra) {
        this.obraIdObra = obraIdObra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutorPublicaObra != null ? idAutorPublicaObra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorPublicaObra)) {
            return false;
        }
        AutorPublicaObra other = (AutorPublicaObra) object;
        if ((this.idAutorPublicaObra == null && other.idAutorPublicaObra != null) || (this.idAutorPublicaObra != null && !this.idAutorPublicaObra.equals(other.idAutorPublicaObra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AutorPublicaObra[ idAutorPublicaObra=" + idAutorPublicaObra + " ]";
    }
    
}
