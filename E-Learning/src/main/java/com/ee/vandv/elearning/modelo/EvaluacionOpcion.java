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
@Table(name = "evaluacion_opcion", catalog = "elearning", schema = "")
@XmlRootElement
public class EvaluacionOpcion extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private EvaluacionOpcionPK opcionUsuarioPK;
    
    @JoinColumn(name="IDOPCION", referencedColumnName="IDOPCION",nullable=false,insertable=false,updatable=false)
    @ManyToOne(optional=false,fetch= FetchType.EAGER)
    private Opcion opcion;
    
    @JoinColumn(name="IDEVALUACION", referencedColumnName="IDEVALUACION",nullable=false,insertable=false,updatable=false)
    @ManyToOne(optional=false,fetch= FetchType.EAGER)
    private Evaluacion evaluacion;

    public EvaluacionOpcion() {
    }

    public EvaluacionOpcion(Opcion opcion, Evaluacion evaluacion) {
        this.opcionUsuarioPK = new EvaluacionOpcionPK(opcion.getIdopcion(), evaluacion.getIdevaluacion());
        this.opcion = opcion;
        this.evaluacion = evaluacion;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionOpcion)) {
            return false;
        }
        EvaluacionOpcion other = (EvaluacionOpcion) object;
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
     * @return the evaluacion
     */
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the evaluacion to set
     */
    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
    
}
