package sigarep.viewmodels.maestros;
import java.util.List;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.servicio.maestros.ServicioPreguntaBasica;

/** Clase VMPreguntaBasica : Clase ViewModels 
relacionada con el Maestro PreguntaBasica.

 * @author Equipo Builder
 * @version 1.0
 * @since 02-/12/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPreguntaBasica {
	//-----------------Servicios----------------------------
	@WireVariable ServicioPreguntaBasica serviciopreguntabasica;
	//-----------------Variables PreguntaBasica ------------
	private Integer idPreguntaBasica;
	private String pregunta; 
	private String respuesta;
	private Boolean estatus;
	//-----------------Variables Lista----------------------
	private List<PreguntaBasica> listaPregunta;
	//-----------------Variables Objeto---------------------
	private PreguntaBasica preguntaseleccionada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario (); // Instancia de la Clase de mensajes 

	//Metodos Get y Set de la clase 
	public Integer getIdPreguntaBasica() {
		return idPreguntaBasica;
	}

	public void setIdPreguntaBasica(Integer idPreguntaBasica) {
		this.idPreguntaBasica = idPreguntaBasica;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus){
		this.estatus = estatus;
	}

	public List<PreguntaBasica> getListaPregunta() {
		return listaPregunta;
	}

	public void setListaPregunta(List<PreguntaBasica> listaPregunta) {
		this.listaPregunta = listaPregunta;
	}
	public PreguntaBasica getPreguntaseleccionada() {
		return preguntaseleccionada;
	}
	public void setPreguntaseleccionada(PreguntaBasica preguntaseleccionada) {
		this.preguntaseleccionada = preguntaseleccionada;
	}
	
	//Fin de los metodos get y set

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init(){
		
		buscarPregunta (); 
	}

	/** guardarPreguntaBasica
	 * 
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command 
	@NotifyChange({"id_pregunta_basica", "pregunta", "respuesta","estatus","listaPregunta"})
	public void guardarPregunta(){
		if (pregunta==null||respuesta==null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else{
			PreguntaBasica preb = new PreguntaBasica (idPreguntaBasica,pregunta,respuesta,true);
			serviciopreguntabasica.guardarPregunta(preb);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/** eliminarPreguntaBasica
	 * 
	 * @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({"listaPregunta","pregunta","respuesta"})
	public void  eliminarPreguntaBasica(@ContextParam(ContextType.BINDER) final Binder binder){
		if (pregunta == null|| respuesta == null){
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
					case YES:
						//if you call super.delete here, since original zk event is not control by binder
						//the change of viewmodel will not update to the ui.
						//so, I post a delete to trigger to process it in binder controll.
						//binder.postCommand("limpiar", null);
						serviciopreguntabasica.eliminarPregunta(getPreguntaseleccionada().getIdPreguntaBasica());
						mensajeAlUsuario.informacionEliminarCorrecto();
						binder.postCommand("limpiar", null);
					case NO:

						binder.postCommand("limpiar", null);
					}
				}
			});		
		}
	}

	/**
	 * mostrarSeleccionada : Muestra en el área de datos el registro
	 * seleccionado
	 * 
	 * @param Ninguno
	 * @return Objeto PreguntaBasica seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"pregunta","respuesta"})
	public void mostrarSeleccionada(){
		idPreguntaBasica = getPreguntaseleccionada().getIdPreguntaBasica();
		pregunta=getPreguntaseleccionada().getPregunta();
		respuesta=getPreguntaseleccionada().getRespuesta();
	}

	/** buscarPreguntaBasica
	 * 
	 * @param Ninguno
	 * @return Objeto PreguntaBasica. 
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaPregunta"})
	public void buscarPregunta(){
		listaPregunta =serviciopreguntabasica.filtrarPreguntaBasica(pregunta);
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaPregunta", "idPreguntaBasica ", "pregunta", "respuesta","estatus"})
	public void limpiar(){
		idPreguntaBasica = null;
		pregunta = null; 
		respuesta =null;
		buscarPregunta ();
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaPregunta","pregunta","respuesta"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(pregunta != null|| respuesta != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}
