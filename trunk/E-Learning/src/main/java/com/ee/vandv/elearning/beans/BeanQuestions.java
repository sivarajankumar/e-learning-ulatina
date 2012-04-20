/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ee.vandv.elearning.beans;

import com.ee.vandv.elearning.base.BeanBase;
import com.ee.vandv.elearning.base.ServicioBase;
import com.ee.vandv.elearning.modelo.Categoria;
import com.ee.vandv.elearning.modelo.Evaluacion;
import com.ee.vandv.elearning.modelo.OpcionUsuario;
import com.ee.vandv.elearning.modelo.Pregunta;
import com.ee.vandv.elearning.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author stevenziggiz
 */
@ManagedBean
@ViewScoped
public class BeanQuestions extends BeanBase {

    private List<Evaluacion> listaEvaluaciones;
    private Evaluacion evaluacionSeleccionada;
    private Evaluacion evaluacionNueva;
    
    private List<Categoria> listaCategorias;
    private Usuario usuarioLogueado;
    
    @Inject
    ServicioBase servicio;

    public BeanQuestions() {
        this.inicializar();
    }

    @Override
    public void inicializar() {
        listaCategorias = new ArrayList<Categoria>();
        listaEvaluaciones = new ArrayList<Evaluacion>();
    }

    @Override
    @PostConstruct
    public void inicializarPost() {
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        usuarioLogueado = obtenerUsuario("admin", "adminadmin");
    }

    private Usuario obtenerUsuario(String usuario, String clave) {
        List<Usuario> usuarios = servicio.seleccionar("Usuario.findByUsuarioClave", usuario, clave);
        if(!usuarios.isEmpty()){
            return usuarios.get(0);
        }
        return null;
    }

    private void cargarRespuestas() {
        for (Categoria categoria : listaCategorias) {
            for (Pregunta pregunta : categoria.getPreguntaList()) {
                pregunta.cargarRespuestas(servicio);
            }
        }
    }

    private void botonFormularioAceptar() {
        OpcionUsuario opcionUsuario;
        List<OpcionUsuario> listaRespuestasUsuario = new ArrayList<OpcionUsuario>();
        for (Categoria categoria : listaCategorias) {
            for (Pregunta pregunta : categoria.getPreguntaList()) {
                opcionUsuario = new OpcionUsuario(pregunta.obtenerRespuestaUsuario(), usuarioLogueado);
                listaRespuestasUsuario.add(opcionUsuario);
            }
        }
        guardarRespuestaUsuario(listaRespuestasUsuario);
    }

    private void guardarRespuestaUsuario(List<OpcionUsuario> listaRespuestasUsuario) {
        for (OpcionUsuario opcionUsuario : listaRespuestasUsuario) {
            servicio.guardar(opcionUsuario);
        }
    }

    public void accionBotonFormularioAceptar(ActionEvent event) {
        botonFormularioAceptar();
        FacesContext.getCurrentInstance().renderResponse();
    }
    
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%--------EVENTOS-------%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    public void accionBotonCrearNuevaEvaluacionNuevo(ActionEvent event){
        evaluacionNueva = new Evaluacion();
        FacesContext.getCurrentInstance().renderResponse();
    }
    /**
     * Crear la nueva evaluacion
     * @param event 
     */
    public void accionBotonCrearNuevaEvaluacionAceptar(ActionEvent event){
        servicio.guardar(evaluacionNueva);
        FacesContext.getCurrentInstance().renderResponse();
    }
    
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%-------NAVEGACION-------%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    /**%%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%**/
    
    /**
     * Redirecciona a la pagina de iniciar una nueva formulacion de evaluacion
     * @return 
     */
    public String irListadoFormulacionEvaluaciones(){
        return "Formular Evaluacion";
    }
    
    /**
     * Redirecciona a la pagina donde se encuentra el formulario con todas las preguntas.
     * @return 
     */
    public String irResponderPreguntas(){
        return "Responder Preguntas";
    }
    
    /**
     * Redirecciona a la pagina de bienvenida
     * @return 
     */
    public String irBienvenida(){
        return "welcome";
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

    /**
     * @return the usuarioLogueado
     */
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * @param usuarioLogueado the usuarioLogueado to set
     */
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * @return the listaEvaluaciones
     */
    public List<Evaluacion> getListaEvaluaciones() {
        return listaEvaluaciones;
    }

    /**
     * @param listaEvaluaciones the listaEvaluaciones to set
     */
    public void setListaEvaluaciones(List<Evaluacion> listaEvaluaciones) {
        this.listaEvaluaciones = listaEvaluaciones;
    }

    /**
     * @return the evaluacionSeleccionada
     */
    public Evaluacion getEvaluacionSeleccionada() {
        return evaluacionSeleccionada;
    }

    /**
     * @param evaluacionSeleccionada the evaluacionSeleccionada to set
     */
    public void setEvaluacionSeleccionada(Evaluacion evaluacionSeleccionada) {
        this.evaluacionSeleccionada = evaluacionSeleccionada;
    }

    /**
     * @return the evaluacionNueva
     */
    public Evaluacion getEvaluacionNueva() {
        return evaluacionNueva;
    }

    /**
     * @param evaluacionNueva the evaluacionNueva to set
     */
    public void setEvaluacionNueva(Evaluacion evaluacionNueva) {
        this.evaluacionNueva = evaluacionNueva;
    }
}
