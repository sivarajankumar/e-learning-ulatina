/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.beans;

import com.ee.vandv.elearning.base.BeanBase;
import com.ee.vandv.elearning.base.ServicioBase;
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
    private List<Pregunta> listaPreguntas;
    
    @Inject
    ServicioBase servicio;

    public BeanQuestions() {
        this.inicializar();
    }    
    
    @Override
    public void inicializar() {
        listaPreguntas = new ArrayList<Pregunta>();
    }

    @Override
    @PostConstruct
    public void inicializarPost() {
        listaPreguntas = servicio.seleccionar(Pregunta.class);
    }

    /**
     * @return the listaPreguntas
     */
    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * @param listaPreguntas the listaPreguntas to set
     */
    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }
    
}
