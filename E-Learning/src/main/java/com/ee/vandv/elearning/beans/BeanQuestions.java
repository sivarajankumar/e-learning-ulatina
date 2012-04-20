package com.ee.vandv.elearning.beans;

import com.ee.vandv.elearning.base.BeanBase;
import com.ee.vandv.elearning.base.ServicioBase;
import com.ee.vandv.elearning.modelo.Categoria;
import com.ee.vandv.elearning.modelo.Evaluacion;
import com.ee.vandv.elearning.modelo.EvaluacionOpcion;
import com.ee.vandv.elearning.modelo.Pregunta;
import com.ee.vandv.elearning.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.chart.MeterGaugeChartModel; 

/**
 *
 * @author stevenziggiz
 */
@ManagedBean
@SessionScoped
public class BeanQuestions extends BeanBase {

    private List<Evaluacion> listaEvaluaciones;
    private Evaluacion evaluacionSeleccionada;
    private Evaluacion evaluacionNueva;
    private String grafic = "/pages/evaluation/grafic/grafico_promedio.xhtml";
    private MeterGaugeChartModel meterGaugeModel; 
    
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

    private void createMeterGaugeModel() {  
  
        List<Number> intervals = new ArrayList<Number>(){{  
            add(20);  
            add(50);  
            add(120);  
            add(220);  
        }};  
  
        meterGaugeModel = new MeterGaugeChartModel("Promedio", 140, intervals);  
    }  
    
    private Usuario obtenerUsuario(String usuario, String clave) {
        List<Usuario> usuarios = servicio.seleccionar("Usuario.findByUsuarioClave", usuario, clave);
        if(!usuarios.isEmpty()){
            return usuarios.get(0);
        }
        return null;
    }
    
    private void cargarCategorias(){
        listaCategorias = servicio.seleccionar(Categoria.class);
    }

    private void cargarRespuestas() {
        for (Categoria categoria : listaCategorias) {
            for (Pregunta pregunta : categoria.getPreguntaList()) {
                pregunta.cargarRespuestas(servicio);
            }
        }
    }

    private void botonFormularioAceptar() {
        EvaluacionOpcion evaluacionOpcion;
        List<EvaluacionOpcion> listaRespuestasEvaluacion = new ArrayList<EvaluacionOpcion>();
        for (Categoria categoria : listaCategorias) {
            for (Pregunta pregunta : categoria.getPreguntaList()) {
                evaluacionOpcion = new EvaluacionOpcion(pregunta.obtenerRespuestaUsuario(), evaluacionNueva);
                listaRespuestasEvaluacion.add(evaluacionOpcion);
            }
        }
        guardarRespuestaUsuario(listaRespuestasEvaluacion);        
    }

    private void guardarRespuestaUsuario(List<EvaluacionOpcion> listaRespuestasUsuario) {
        evaluacionNueva.setIndicadorTerminada(true);
        servicio.actualizar(evaluacionNueva);
        for (EvaluacionOpcion EvaluacionOpcion : listaRespuestasUsuario) {
            servicio.guardar(EvaluacionOpcion);
        }
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
    public String accionBotonCrearNuevaEvaluacionAceptar(){
        evaluacionNueva.setUsuario(usuarioLogueado);
        servicio.guardar(evaluacionNueva);
        cargarCategorias();
        cargarRespuestas();
        return irResponderPreguntas();
    }
    
    public String accionBotonFormularioAceptar() {
        botonFormularioAceptar();
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
    }
    
    public String accionBotonCrearNuevaEvaluacionCancelar(){
        servicio.eliminar(evaluacionNueva);
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
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
    
    public String irEstadisticas(){
        createMeterGaugeModel();
        return "Estadisticas";
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
        if(evaluacionNueva == null){
            evaluacionNueva = new Evaluacion();
        }
        return evaluacionNueva;
    }

    /**
     * @param evaluacionNueva the evaluacionNueva to set
     */
    public void setEvaluacionNueva(Evaluacion evaluacionNueva) {
        this.evaluacionNueva = evaluacionNueva;
    }

    /**
     * @return the grafic
     */
    public String getGrafic() {
        return grafic;
    }

    /**
     * @param grafic the grafic to set
     */
    public void setGrafic(String grafic) {
        this.grafic = grafic;
    }

    /**
     * @return the meterGaugeModel
     */
    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }

    /**
     * @param meterGaugeModel the meterGaugeModel to set
     */
    public void setMeterGaugeModel(MeterGaugeChartModel meterGaugeModel) {
        this.meterGaugeModel = meterGaugeModel;
    }
}
