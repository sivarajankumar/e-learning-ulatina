/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stevenziggiz
 */
@Embeddable
public class OpcionUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDOPCION", nullable = false)
    private Integer idopcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSUARIO", nullable = false)
    private Integer idusuario;

    public OpcionUsuarioPK() {
    }

    public OpcionUsuarioPK(Integer idopcion, Integer idusuario) {
        this.idopcion = idopcion;
        this.idusuario = idusuario;
    }
    
    /**
     * @return the idopcion
     */
    public Integer getIdopcion() {
        return idopcion;
    }

    /**
     * @param idopcion the idopcion to set
     */
    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    /**
     * @return the idusuario
     */
    public Integer getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionUsuarioPK)) {
            return false;
        }
        OpcionUsuarioPK other = (OpcionUsuarioPK) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idopcion != null ? this.idopcion.hashCode() : 0);
        hash = 97 * hash + (this.idusuario != null ? this.idusuario.hashCode() : 0);
        return hash;
    }
}
