/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.base;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stevenziggiz
 */
public abstract class ServicioBase implements Serializable{
    @PersistenceContext(unitName="persistence")
    protected EntityManager em;
    
    public void guardar(ObjetoBase objetoBase){
        em.persist(objetoBase);
    }
}
