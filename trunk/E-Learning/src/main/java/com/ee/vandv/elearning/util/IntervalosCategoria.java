/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stevenziggiz
 */
public enum IntervalosCategoria {
    
    USABILIDAD(new Integer[]{0,200,400,600,800}),
    FUNCIONALIDAD(new Integer[]{0,200,400,600,800}),
    EFICIENCIA(new Integer[] {0,100,200,300,400,500}),
    CONFIABILIDAD(new Integer[]{0,100,200,300,400});
    
    
    private final Integer[] intervalo;

    private IntervalosCategoria(Integer [] intervalo) {
        this.intervalo = intervalo;
    }

    /**
     * @return the intervalo
     */
    public Integer[] getIntervalo() {
        return intervalo;
    }
    
}
