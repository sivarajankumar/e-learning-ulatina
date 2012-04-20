/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.util;

/**
 *
 * @author stevenziggiz
 */
public enum Graficos {
    PROMEDIOS("/pages/evaluation/grafic/grafico_promedio.xhtml"),
    PROMEDIOS_AREA("/pages/evaluation/grafic/grafico_por_areas.xhtml");
    private final String dirGrafico;

    private Graficos(String dirGrafico) {
        this.dirGrafico = dirGrafico;
    }

    /**
     * @return the dirGrafico
     */
    public String getDirGrafico() {
        return dirGrafico;
    }

}
