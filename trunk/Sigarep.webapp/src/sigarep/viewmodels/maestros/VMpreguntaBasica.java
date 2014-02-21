package sigarep.viewmodels.maestros;
import java.util.List;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.servicio.maestros.ServicioPreguntaBasica;

/**PreguntaBasica
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since -/12/13
 */

@SuppressWarnings("serial") //anotación se utiliza para evitar un error en tiempo de compilación al implementar la interfaz java.io.Serializable
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMpreguntaBasica {
	@WireVariable ServicioPreguntaBasica serviciopreguntabasica;
	private Integer idPreguntaBasica;private String pregunta; private String respuesta;private Boolean estatus;
    private List<PreguntaBasica> listaPregunta;
	private PreguntaBasica preguntaseleccionada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario (); // Instancia de la Clase de mensajes 
    
	@Wire("#winActualizarPreguntasBasicas")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	 
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
	@Init
	public void init(){
	    //initialization code
		buscarPregunta (); 
    }
// Metodos de la clase
	/** guardarPreguntaBasica
	 * @param id_pregunta_basica, pregunta, respuesta, estatus, listaPregunta
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones ocurren cuando se quiera registrar una PreguntaBasica y haya campos en blanco.
	 */
	@Command // Permite manipular la propiedad de ViewModel
	@NotifyChange({"id_pregunta_basica", "pregunta", "respuesta","estatus","listaPregunta"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es los atributos de la pantalla se va a colocar en blanco al guardar!!
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
	/** limpiar
	 * @param id_pregunta_basica, pregunta, respuesta, estatus, listaPregunta
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"listaPregunta", "idPreguntaBasica ", "pregunta", "respuesta","estatus"})
	public void limpiar(){
		idPreguntaBasica = null;
		pregunta = null; 
		 respuesta =null;
		 buscarPregunta ();
	}

	/** buscarPreguntaBasica
	 * @return Devuelve una lista de preguntas.
	 */
	@Command
	@NotifyChange({"listaPregunta"})
	public void buscarPregunta(){
		listaPregunta =serviciopreguntabasica.filtrarPreguntaBasica(pregunta);
	}
	/** eliminarPreguntaBasica
	 * @param id_pregunta_basica, pregunta, respuesta, estatus, listaPregunta.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que quiera eliminar con los campos vacios, sin seleccionar ningun registro
	 */
	
	@SuppressWarnings("unchecked")
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
	/** mostrarSeleccionada
	 * @param pregunta, respuesta.
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"pregunta","respuesta"})
	public void mostrarSeleccionada(){
		idPreguntaBasica = getPreguntaseleccionada().getIdPreguntaBasica();
		pregunta=getPreguntaseleccionada().getPregunta();
		respuesta=getPreguntaseleccionada().getRespuesta();
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({"listaPregunta","pregunta","respuesta"})
	public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
			
		if (pregunta != null|| respuesta != null) 
		{
			Messagebox.show("¿Realmente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					}
				}
			});		
		}
		else{
		Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					
					}
				}
			});		
		}
	}
	
	
	
}
