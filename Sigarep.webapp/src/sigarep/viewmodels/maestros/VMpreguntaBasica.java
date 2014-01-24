package sigarep.viewmodels.maestros;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
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
	MensajesAlUsuario mensajesusuario = new MensajesAlUsuario (); // Instancia de la Clase de mensajes 
    
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
		if (pregunta.equals("")||respuesta.equals(""))
			mensajesusuario.advertenciaLlenarCampos();
		else{
		PreguntaBasica preb = new PreguntaBasica (idPreguntaBasica,pregunta,respuesta,true);
		serviciopreguntabasica.guardarPregunta(preb);
		mensajesusuario.informacionRegistroCorrecto();
		limpiar();
		}
	}
	/** limpiar
	 * @param id_pregunta_basica, pregunta, respuesta, estatus, listaPregunta
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"id_pregunta_basica", "pregunta", "respuesta","estatus"})
	public void limpiar(){
		 pregunta = "";respuesta="";
		 buscarPregunta ();
	}

	/** buscarPreguntaBasica
	 * @return Devuelve una lista de preguntas.
	 */
	@Command
	@NotifyChange({"listaPregunta"})
	public void buscarPregunta(){
		listaPregunta =serviciopreguntabasica.buscarPr(pregunta);
	}
	/** eliminarPreguntaBasica
	 * @param id_pregunta_basica, pregunta, respuesta, estatus, listaPregunta.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que quiera eliminar con los campos vacios, sin seleccionar ningun registro
	 */
	@Command
	@NotifyChange({"listaPregunta","pregunta","respuesta"})
	public void eliminarPreguntaBasica(){
		if (pregunta.equals("")|| respuesta.equals("")){
			mensajesusuario.advertenciaSeleccionarParaEliminar();
  		}
		else{
		serviciopreguntabasica.eliminarPregunta(getPreguntaseleccionada().getIdPreguntaBasica());
		limpiar();
		mensajesusuario.informacionEliminarCorrecto();
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
}
