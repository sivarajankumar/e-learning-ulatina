/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.modelo;

import com.ee.vandv.elearning.base.ObjetoBase;
import com.ee.vandv.elearning.base.ServicioBase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stevenziggiz
 */
@Entity
@Table(name = "pregunta", catalog = "elearning", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdpregunta", query = "SELECT p FROM Pregunta p WHERE p.idpregunta = :idpregunta"),
    @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta")})
public class Pregunta extends ObjetoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpregunta", nullable = false)
    private Integer idpregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "pregunta", nullable = false, length = 400)
    private String pregunta;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria idcategoria;
    @Transient
    private List<Opcion> opcionList;
    @Transient
    private List<SelectItem> listaSelectItems;
    @Transient
    private int indexOpcionSeleccionada = -1;

    public Pregunta() {
    }

    public Pregunta(Integer idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Pregunta(Integer idpregunta, String pregunta) {
        this.idpregunta = idpregunta;
        this.pregunta = pregunta;
    }
    
    public Opcion obtenerRespuestaUsuario(){
        return opcionList.get(indexOpcionSeleccionada);
    }
    
    public void cargarRespuestas(ServicioBase servicio){
        opcionList = servicio.seleccionar("Opcion.findByIdPregunta",idpregunta);
        listaSelectItems = new ArrayList<SelectItem>();
        SelectItem selectItem;
        for (Opcion opcion : opcionList) {
            selectItem = new SelectItem(opcionList.indexOf(opcion), opcion.getDescripcion());
            listaSelectItems.add(selectItem);
        }
    }

    public Integer getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Integer idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    @XmlTransient
    public List<Opcion> getOpcionList() {
        
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpregunta != null ? idpregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idpregunta == null && other.idpregunta != null) || (this.idpregunta != null && !this.idpregunta.equals(other.idpregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ee.vandv.elearning.modelo.Pregunta[ idpregunta=" + idpregunta + " ]";
    }

    /**
     * @return the listaSelectItems
     */
    public List<SelectItem> getListaSelectItems() {
        return listaSelectItems;
    }

    /**
     * @param listaSelectItems the listaSelectItems to set
     */
    public void setListaSelectItems(List<SelectItem> listaSelectItems) {
        this.listaSelectItems = listaSelectItems;
    }

    /**
     * @return the indexOpcionSeleccionada
     */
    public int getIndexOpcionSeleccionada() {
        return indexOpcionSeleccionada;
    }

    /**
     * @param indexOpcionSeleccionada the indexOpcionSeleccionada to set
     */
    public void setIndexOpcionSeleccionada(int indexOpcionSeleccionada) {
        this.indexOpcionSeleccionada = indexOpcionSeleccionada;
    }
    
}
