/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.beans;

import com.ee.vandv.elearning.base.BeanBase;
import com.ee.vandv.elearning.base.ServicioBase;
import com.ee.vandv.elearning.modelo.Categoria;
import com.ee.vandv.elearning.modelo.Pregunta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author stevenziggiz
 */
@ManagedBean
@ViewScoped
public class BeanQuestions extends BeanBase{
    private List<Categoria> listaCategorias;
    
    @Inject
    ServicioBase servicio;

    public BeanQuestions() {
        this.inicializar();
    }
    
    @Override
    public void inicializar() {
        listaCategorias = new ArrayList<Categoria>();
    }

    @Override
    @PostConstruct
    public void inicializarPost() {
        listaCategorias = servicio.seleccionar(Categoria.class);
    }

    /**
     * @return the listaCategorias
     */
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * @param listaCategorias the listaCategorias to set
     */
    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
}
