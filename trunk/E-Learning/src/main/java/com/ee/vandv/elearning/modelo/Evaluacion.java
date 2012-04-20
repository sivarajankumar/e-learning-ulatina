/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.modelo;

import com.ee.vandv.elearning.base.ObjetoBase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stevenziggiz
 */
@Entity
@Table(name = "evaluacion", catalog = "elearning", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdevaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idevaluacion = :idevaluacion"),
    @NamedQuery(name = "Evaluacion.findByDescripcionSitio", query = "SELECT e FROM Evaluacion e WHERE e.descripcionSitio = :descripcionSitio"),
    @NamedQuery(name = "Evaluacion.findByIndicadorTerminada", query = "SELECT e FROM Evaluacion e WHERE e.indicadorTerminada = :indicadorTerminada")})
public class Evaluacion extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idevaluacion")
    private Integer idevaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "descripcion_sitio", nullable = false, length = 400)
    private String descripcionSitio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indicador_terminada", nullable = false)
    private boolean indicadorTerminada;
    @Transient
    private String estado;
    @JoinColumn(name="IDUSUARIO", referencedColumnName="IDUSUARIO",nullable=false)
    @ManyToOne(optional=false,fetch= FetchType.EAGER)
    private Usuario usuario;

    public Evaluacion() {
    }

    public Evaluacion(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public Evaluacion(Integer idevaluacion, String descripcionSitio, boolean indicadorTerminada) {
        this.idevaluacion = idevaluacion;
        this.descripcionSitio = descripcionSitio;
        this.indicadorTerminada = indicadorTerminada;
    }

    public Integer getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public String getDescripcionSitio() {
        return descripcionSitio;
    }

    public void setDescripcionSitio(String descripcionSitio) {
        this.descripcionSitio = descripcionSitio;
    }

    public boolean getIndicadorTerminada() {
        return indicadorTerminada;
    }

    public void setIndicadorTerminada(boolean indicadorTerminada) {
        this.indicadorTerminada = indicadorTerminada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluacion != null ? idevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idevaluacion == null && other.idevaluacion != null) || (this.idevaluacion != null && !this.idevaluacion.equals(other.idevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ee.vandv.elearning.modelo.Evaluacion[ idevaluacion=" + idevaluacion + " ]";
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        if(indicadorTerminada){
            estado = "Terminada";
        }else{
            estado = "Pendiente";
        }
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {        
        this.estado = estado;
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
