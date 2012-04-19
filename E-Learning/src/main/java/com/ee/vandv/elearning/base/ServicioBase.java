/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.base;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author stevenziggiz
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicioBase implements Serializable{
    
    @PersistenceContext(unitName="persistence")
    protected EntityManager em;
    
    public void guardar(ObjetoBase objetoBase){
        em.persist(objetoBase);
    }
    
    public void actualizar(ObjetoBase objetoBase){
        em.merge(objetoBase);
    }
    
    public void eliminar(ObjetoBase objetoBase){
        em.remove(em.merge(objetoBase));
    }
    
    public <T> ArrayList<T> seleccionar(Class clase){
        Query query = em.createQuery("Select p FROM "+clase.getSimpleName()+" as p");
        return (ArrayList<T>) query.getResultList();
    }
}

