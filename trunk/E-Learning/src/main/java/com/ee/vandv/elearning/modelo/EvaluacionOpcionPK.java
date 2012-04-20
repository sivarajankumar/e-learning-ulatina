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
public class EvaluacionOpcionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDOPCION", nullable = false)
    private Integer idopcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEVALUACION", nullable = false)
    private Integer idevaluacion;

    public EvaluacionOpcionPK() {
    }

    public EvaluacionOpcionPK(Integer idopcion, Integer idevaluacion) {
        this.idopcion = idopcion;
        this.idevaluacion = idevaluacion;
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

        
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionOpcionPK)) {
            return false;
        }
        EvaluacionOpcionPK other = (EvaluacionOpcionPK) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        if ((this.idevaluacion == null && other.idevaluacion != null) || (this.idevaluacion != null && !this.idevaluacion.equals(other.idevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idopcion != null ? this.idopcion.hashCode() : 0);
        hash = 97 * hash + (this.idevaluacion != null ? this.idevaluacion.hashCode() : 0);
        return hash;
    }

    /**
     * @return the idevaluacion
     */
    public Integer getIdevaluacion() {
        return idevaluacion;
    }

    /**
     * @param idevaluacion the idevaluacion to set
     */
    public void setIdevaluacion(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }
}
