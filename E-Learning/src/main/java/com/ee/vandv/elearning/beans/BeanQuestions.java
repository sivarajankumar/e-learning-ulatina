package com.ee.vandv.elearning.beans;

import com.ee.vandv.elearning.base.BeanBase;
import com.ee.vandv.elearning.base.ServicioBase;
import com.ee.vandv.elearning.modelo.Categoria;
import com.ee.vandv.elearning.modelo.Evaluacion;
import com.ee.vandv.elearning.modelo.EvaluacionOpcion;
import com.ee.vandv.elearning.modelo.Pregunta;
import com.ee.vandv.elearning.modelo.Usuario;
import com.ee.vandv.elearning.util.Graficos;
import com.ee.vandv.elearning.util.IntervalosCategoria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.chart.MeterGaugeChartModel;
import sun.java2d.pipe.RenderBuffer;

/**
 *
 * @author stevenziggiz
 */
@ManagedBean
@SessionScoped
public class BeanQuestions extends BeanBase {

    private boolean verBotonNuevo;
    private List<Evaluacion> listaEvaluaciones;
    private Evaluacion evaluacionSeleccionada;
    private Evaluacion evaluacionNueva;
    private String grafic;
    private Graficos grafico;
    private MeterGaugeChartModel meterGaugeModel;
    //Variable donde se almacena el promedio
    Number promedio;
    //Listado de graficos por area
    private List<MeterGaugeChartModel> listaGraficosArea;
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
        List<Number> intervals = new ArrayList<Number>() {

            {
                add(0);
                add(500);
                add(1000);
                add(1500);
                add(2000);
                add(2500);
            }
        };
        meterGaugeModel = new MeterGaugeChartModel("Promedio", promedio, intervals);
    }

    private MeterGaugeChartModel createMeterGaugeModel(String leyenda, Number promedio) {
        List<Number> intervals = null;
        if (leyenda.equals("Usabilidad")) {
            intervals = convertirANumber(IntervalosCategoria.USABILIDAD.getIntervalo());
        } else {
            if (leyenda.equals("Funcionalidad")) {
                intervals = convertirANumber(IntervalosCategoria.FUNCIONALIDAD.getIntervalo());
            } else {
                if (leyenda.equals("Eficiencia")) {
                    intervals = convertirANumber(IntervalosCategoria.EFICIENCIA.getIntervalo());
                } else {
                    if (leyenda.equals("Confiabilidad")) {
                        intervals = convertirANumber(IntervalosCategoria.CONFIABILIDAD.getIntervalo());
                    }
                }
            }
        }
        return new MeterGaugeChartModel(leyenda, promedio, intervals);
    }

    private List<Number> convertirANumber(Integer[] intervalos) {
        Integer number;
        List<Number> retorno = new ArrayList<Number>();
        List listaIntervalos = Arrays.asList(intervalos);
        for (Object object : listaIntervalos) {
            number = (Integer) object;
            retorno.add(number);
        }
        return retorno;
    }

    private Usuario obtenerUsuario(String usuario, String clave) {
        List<Usuario> usuarios = servicio.seleccionar("Usuario.findByUsuarioClave", usuario, clave);
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }

    private List<Categoria> cargarCategorias() {
        return servicio.seleccionar(Categoria.class);
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

    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%--------GRAFICOS-------%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    private void procesarPromedio() {
        List<EvaluacionOpcion> listaEvaluacionOpciones;
        listaEvaluacionOpciones = servicio.seleccionar("EvaluacionOpcion.findByIdEvaluacion", evaluacionSeleccionada.getIdevaluacion());
        promedio = 0;
        for (EvaluacionOpcion evaluacionOpcion : listaEvaluacionOpciones) {
            promedio = promedio.doubleValue() + evaluacionOpcion.getOpcion().getPuntaje();
        }
    }

    private List<MeterGaugeChartModel> procesarGraficosArea(int numeroEvaluaciones) {
        Number promedioTemp = 0;
        List<Categoria> listaCategoriasTemp;
        List<EvaluacionOpcion> listaEvaluacionOpciones;
        List<MeterGaugeChartModel> listaGraficosTemp = new ArrayList<MeterGaugeChartModel>();
        listaCategoriasTemp = cargarCategorias();
        for (Categoria categoria : listaCategoriasTemp) {
            listaEvaluacionOpciones = servicio.seleccionar("EvaluacionOpcion.findByOpcionPorEvaluacionCategoria",evaluacionSeleccionada.getIdevaluacion(), categoria.getIdcategoria());
            for (EvaluacionOpcion evaluacionOpcion : listaEvaluacionOpciones) {
                promedioTemp = promedioTemp.doubleValue() + evaluacionOpcion.getOpcion().getPuntaje();
            }
            listaGraficosTemp.add(createMeterGaugeModel(categoria.getDescripcion(), promedioTemp.intValue() /numeroEvaluaciones));
            promedioTemp = 0;
        }
        return listaGraficosTemp;
    }
    
    private List<MeterGaugeChartModel> procesarGraficosAreaTotal(int numeroEvaluaciones) {
        Number promedioTemp = 0;
        List<Categoria> listaCategoriasTemp;
        List<EvaluacionOpcion> listaEvaluacionOpciones;
        List<MeterGaugeChartModel> listaGraficosTemp = new ArrayList<MeterGaugeChartModel>();
        listaCategoriasTemp = cargarCategorias();
        for (Categoria categoria : listaCategoriasTemp) {
            listaEvaluacionOpciones = servicio.seleccionar("EvaluacionOpcion.findByOpcionPorCategoria", categoria.getIdcategoria());
            for (EvaluacionOpcion evaluacionOpcion : listaEvaluacionOpciones) {
                promedioTemp = promedioTemp.doubleValue() + evaluacionOpcion.getOpcion().getPuntaje();
            }
            listaGraficosTemp.add(createMeterGaugeModel(categoria.getDescripcion(), promedioTemp.intValue() /numeroEvaluaciones));
            promedioTemp = 0;
        }
        return listaGraficosTemp;
    }

    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%--------EVENTOS-------%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    public String accionBotonMostrarEstadisticas() {
        if (evaluacionSeleccionada != null) {
            return irEstadisticas();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Seleccione una Evaluacion!", "Error"));
            FacesContext.getCurrentInstance().renderResponse();
            return irListadoFormulacionEvaluaciones();
        }
    }

    public void accionBotonCrearNuevaEvaluacionNuevo(ActionEvent event) {
        evaluacionNueva = new Evaluacion();
        FacesContext.getCurrentInstance().renderResponse();
    }

    /**
     * Crear la nueva evaluacion
     *
     * @param event
     */
    public String accionBotonCrearNuevaEvaluacionAceptar() {
        evaluacionNueva.setUsuario(usuarioLogueado);
        servicio.guardar(evaluacionNueva);
        listaCategorias = cargarCategorias();
        cargarRespuestas();
        return irResponderPreguntas();
    }

    public String accionBotonFormularioAceptar() {
        botonFormularioAceptar();
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
    }

    public String accionBotonCrearNuevaEvaluacionCancelar() {
        servicio.eliminar(evaluacionNueva);
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
    }

    public void accionMenuGraficoPromedio(ActionEvent event) {
        grafico = Graficos.PROMEDIOS;
        procesarPromedio();
        createMeterGaugeModel();
//        FacesContext context = FacesContext.getCurrentInstance();          
//        context.addMessage(null, new FacesMessage("Gr�fico", "Promedio"));
//        FacesContext.getCurrentInstance().renderResponse();
    }

    public void accionMenuGraficoPromedioArea(ActionEvent event) {
        grafico = Graficos.PROMEDIOS_AREA;      
        listaGraficosArea = procesarGraficosArea(1);
    }
    
    public void accionMenuGraficoPromedioAreaTotal(ActionEvent event) {
        grafico = Graficos.PROMEDIOS_AREA;
        List<Evaluacion> listaTemporalEvaluacion = servicio.seleccionar(Evaluacion.class);
        listaGraficosArea = procesarGraficosAreaTotal(listaTemporalEvaluacion.size());
    }

    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%-------NAVEGACION-------%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    /**
     * %%%%%%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%######%%%%%%*
     */
    public String evaluarNav() {
        verBotonNuevo = true;
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
    }

    public String estadisticasNav() {
        listaGraficosArea = null;
        meterGaugeModel = null;
        evaluacionNueva = null;
        verBotonNuevo = false;
        listaEvaluaciones = servicio.seleccionar("Evaluacion.findAll");
        return irListadoFormulacionEvaluaciones();
    }

    /**
     * Redirecciona a la pagina de iniciar una nueva formulacion de evaluacion
     *
     * @return
     */
    public String irListadoFormulacionEvaluaciones() {
        return "Formular Evaluacion";
    }

    /**
     * Redirecciona a la pagina donde se encuentra el formulario con todas las
     * preguntas.
     *
     * @return
     */
    public String irResponderPreguntas() {
        return "Responder Preguntas";
    }

    /**
     * Redirecciona a la pagina de bienvenida
     *
     * @return
     */
    public String irBienvenida() {
        return "welcome";
    }

    public String irEstadisticas() {
        return "Estadisticas";
    }
    
    public String salir(){
        listaCategorias = null;
        return "salir";
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
        if (evaluacionNueva == null) {
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

    /**
     * @return the grafico
     */
    public Graficos getGrafico() {
        return grafico;
    }

    /**
     * @param grafico the grafico to set
     */
    public void setGrafico(Graficos grafico) {
        this.grafico = grafico;
    }

    /**
     * @return the listaGraficosArea
     */
    public List<MeterGaugeChartModel> getListaGraficosArea() {
        return listaGraficosArea;
    }

    /**
     * @param listaGraficosArea the listaGraficosArea to set
     */
    public void setListaGraficosArea(List<MeterGaugeChartModel> listaGraficosArea) {
        this.listaGraficosArea = listaGraficosArea;
    }

    /**
     * @return the verBotonNuevo
     */
    public boolean isVerBotonNuevo() {
        return verBotonNuevo;
    }

    /**
     * @param verBotonNuevo the verBotonNuevo to set
     */
    public void setVerBotonNuevo(boolean verBotonNuevo) {
        this.verBotonNuevo = verBotonNuevo;
    }
}
