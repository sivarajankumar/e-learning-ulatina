/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.base;

import java.io.Serializable;

/**
 *
 * @author stevenziggiz
 */
public abstract class BeanBase implements Serializable{
    
    public abstract void inicializar();
    
    public abstract void inicializarPost();
}
