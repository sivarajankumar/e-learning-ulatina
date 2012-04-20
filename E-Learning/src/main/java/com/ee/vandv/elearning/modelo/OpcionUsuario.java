/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.modelo;

import com.ee.vandv.elearning.base.ObjetoBase;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stevenziggiz
 */
@Entity
@Table(name = "opcion_usuario", catalog = "elearning", schema = "")
@XmlRootElement
public class OpcionUsuario extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private OpcionUsuarioPK opcionUsuarioPK;
    
    @JoinColumn(name="IDOPCION", referencedColumnName="IDOPCION",nullable=false,insertable=false,updatable=false)
    @ManyToOne(optional=false,fetch= FetchType.EAGER)
    private Opcion opcion;
    
    @JoinColumn(name="IDUSUARIO", referencedColumnName="IDUSUARIO",nullable=false,insertable=false,updatable=false)
    @ManyToOne(optional=false,fetch= FetchType.EAGER)
    private Usuario usuario;

    public OpcionUsuario() {
    }

    public OpcionUsuario(Opcion opcion, Usuario usuario) {
        this.opcionUsuarioPK = new OpcionUsuarioPK(opcion.getIdopcion(), usuario.getIdusuario());
        this.opcion = opcion;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionUsuario)) {
            return false;
        }
        OpcionUsuario other = (OpcionUsuario) object;
        if ((this.opcionUsuarioPK == null && other.opcionUsuarioPK != null) || (this.opcionUsuarioPK != null && !this.opcionUsuarioPK.equals(other.opcionUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.opcionUsuarioPK != null ? this.opcionUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.ee.vandv.elearning.modelo.OpcionUsuario[ id=" + opcionUsuarioPK + " ]";
    }

    /**
     * @return the opcion
     */
    public Opcion getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
