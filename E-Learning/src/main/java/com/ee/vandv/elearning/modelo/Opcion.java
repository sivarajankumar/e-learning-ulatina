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
@Table(name = "opcion", catalog = "elearning", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByIdopcion", query = "SELECT o FROM Opcion o WHERE o.idopcion = ?"),
    @NamedQuery(name = "Opcion.findByDescripcion", query = "SELECT o FROM Opcion o WHERE o.descripcion = ?"),
    @NamedQuery(name = "Opcion.findByPuntaje", query = "SELECT o FROM Opcion o WHERE o.puntaje = ?"),
    @NamedQuery(name = "Opcion.findByIdPregunta",query = "SELECT o FROM Opcion o WHERE o.idpregunta.idpregunta = ?")})
public class Opcion extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idopcion", nullable = false)
    private Integer idopcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "descripcion", nullable = false, length = 400)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntaje", precision = 22)
    private Double puntaje;
    @JoinColumn(name = "idpregunta", referencedColumnName = "idpregunta")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pregunta idpregunta;

    public Opcion() {
    }

    public Opcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public Opcion(Integer idopcion, String descripcion) {
        this.idopcion = idopcion;
        this.descripcion = descripcion;
    }

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Pregunta getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Pregunta idpregunta) {
        this.idpregunta = idpregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopcion != null ? idopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ee.vandv.elearning.modelo.Opcion[ idopcion=" + idopcion + " ]";
    }
    
}
