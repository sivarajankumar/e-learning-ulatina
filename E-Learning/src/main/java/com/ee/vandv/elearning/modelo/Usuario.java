/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.modelo;

import com.ee.vandv.elearning.base.ObjetoBase;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stevenziggiz
 */
@Entity
@Table(name = "usuario", catalog = "elearning", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = ?"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = ?"),
    @NamedQuery(name = "Usuario.findByUsuarioClave", query = "SELECT u FROM Usuario u WHERE u.usuario = ? AND u.clave = ?"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = ?")})
public class Usuario extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;
    @Size(max = 45)
    @Column(name = "usuario", length = 45)
    private String usuario;
    @Size(max = 45)
    @Column(name = "clave", length = 45)
    private String clave;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(fetch = FetchType.EAGER)
    private Perfil idperfil;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ee.vandv.elearning.modelo.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
