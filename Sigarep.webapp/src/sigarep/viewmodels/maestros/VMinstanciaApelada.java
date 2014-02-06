package sigarep.viewmodels.maestros;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;

/**Clase VMInstanciaApelada
* ViewModel para la interfaz RegistrarInstanciaApelada.zul
* @author Builder 
* @version 1.0
* @since 20/12/13
*/
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMinstanciaApelada {
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	private Integer idInstanciaApelada; //Clave principal de la tabla InstanciApelada
	private String instanciaApelada; //Nombre de la InstanciaApelada
	private String nombreRecursoApelacion; //Nombre del Recurso de la InstanciaApelada
	private String descripcion; //Descripcion de la InstanciaApelada
	private String instanciaFiltro = "";
	private String recursoFiltro = "";
	private String descripcionFiltro;
	private Boolean estatus; //Estatus de la InstanciaApelada
	private List<InstanciaApelada> listaInstanciaApelada; //Lista de InstanciaApelada
	private InstanciaApelada instanciaApeladaseleccionada;
	private mensajes mensajeAlUsuario = new mensajes();
    @Wire Textbox txtcodigoInstacia;
    @Wire Window ventana;
    
    //Metodos Setters y Getters
    public String getInstanciaApelada(){
    	return instanciaApelada;
    }

	public String getInstanciaFiltro() {
		return instanciaFiltro;
	}

	public void setInstanciaFiltro(String instanciaFiltro) {
		this.instanciaFiltro = instanciaFiltro;
	}

	public String getRecursoFiltro() {
		return recursoFiltro;
	}

	public void setRecursoFiltro(String recursoFiltro) {
		this.recursoFiltro = recursoFiltro;
	}

	public String getNombreRecursoApelacion() {
		return nombreRecursoApelacion;
	}

	public void setNombreRecursoApelacion(String nombreRecurso) {
		this.nombreRecursoApelacion = nombreRecurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setInstanciaApelada(String nombreInstancia){
    	this.instanciaApelada = nombreInstancia;
    }
    
    public Integer getIdInstanciaApelada() {
		return idInstanciaApelada;
	}
	
    public void setIdInstanciaApelada(Integer codigoInstancia) {
		this.idInstanciaApelada = codigoInstancia;
	}
	
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public InstanciaApelada getInstanciaApeladaseleccionada() {
		return instanciaApeladaseleccionada;
	}
	public void setInstanciaApeladaseleccionada(InstanciaApelada instanciaApeladaseleccionada) {
		this.instanciaApeladaseleccionada = instanciaApeladaseleccionada;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}
	//Fin de los metodos setters y getters
	
	//Metodo que inicializa el codigo del VM
	@Init
	public void init(){
        //initialization code
		listadoInstancia();
    }
	
	/** Guardar InstaciaApelada 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void guardarInstancia(){
		if (instanciaApelada == null || instanciaApelada.equals("")
				|| nombreRecursoApelacion == null || nombreRecursoApelacion.equals("")
				|| descripcion == null || descripcion.equals(""))
					mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			InstanciaApelada inst = new InstanciaApelada(idInstanciaApelada,descripcion,
					true,instanciaApelada,nombreRecursoApelacion);
			servicioInstanciaApelada.guardar(inst);
			limpiar();
		}
	}
	
	/** Listado de InstaciaApelada registradas y activas 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
	}

	/** Método que limpia los campos en la interfaz RegistrarInstanciaApelada.zul 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void limpiar(){
		idInstanciaApelada = null;
		instanciaApelada = "";
		nombreRecursoApelacion = "";
		descripcion = "";
		listadoInstancia();
	}
	
	/** Eliminar logicamente InstaciaApelada 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void eliminarInstancia() {
		if (instanciaApelada == null || instanciaApelada.equals("")
				|| descripcion.equals("") || descripcion == null
				|| nombreRecursoApelacion == null || nombreRecursoApelacion.equals("")) {
					mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
					servicioInstanciaApelada.eliminar(getInstanciaApeladaseleccionada().getIdInstanciaApelada());
					mensajeAlUsuario.informacionEliminarCorrecto();
					limpiar();
		}
	}
	
	/** Metodo que indica la InstanciaApelada seleccionada del Listbox 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void mostrarSeleccionada() {
		idInstanciaApelada = getInstanciaApeladaseleccionada().getIdInstanciaApelada();
		instanciaApelada = getInstanciaApeladaseleccionada().getInstanciaApelada();
		nombreRecursoApelacion = getInstanciaApeladaseleccionada().getNombreRecursoApelacion();
		descripcion = getInstanciaApeladaseleccionada().getDescripcion();
	}

	/** Metodo que filtra el Listbox de las InstanciaApelada registradas 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "listaInstanciaApelada", "instanciaFiltro", "recursoFiltro" })
	public void filtros() {
		listaInstanciaApelada = servicioInstanciaApelada.buscarInstancia(instanciaFiltro,
				recursoFiltro);
	}
}