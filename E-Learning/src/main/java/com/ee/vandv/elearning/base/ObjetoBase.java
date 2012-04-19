/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.base;

import java.io.Serializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author stevenziggiz
 */
public class ObjetoBase implements Serializable{
    protected boolean seleccionado;

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    protected <T> T obtenerServicio(Class<T> clase){
        try {
            String nombre = clase.getSimpleName();
            InitialContext iniCtx = new InitialContext();
            Context ejbCtx = (Context) iniCtx.lookup("java:comp/env/ejb");
            T objeto = (T) ejbCtx.lookup(nombre);
            return objeto;
        } catch (NamingException ex) {
            //Logger.getLogger(ObjetoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
