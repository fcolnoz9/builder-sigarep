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
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.InstanciaApeladaFiltros;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
/**VM Instancia Apelada
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMinstanciaApelada {
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	private Integer idInstanciaApelada;
	private String instanciaApelada;
	private String nombreRecursoApelacion;
	private String descripcion;
	private String nombreIFiltro;
	private String nombreRFiltro;
	private String descripcionFiltro;
	private Boolean estatus;
	private InstanciaApeladaFiltros filtros = new InstanciaApeladaFiltros();
	private List<InstanciaApelada> listaInstanciaApelada;
	private InstanciaApelada instanciaApeladaseleccionada;
	private mensajes mensajeAlUsuario = new mensajes();
    @Wire Textbox txtcodigoInstacia;
    @Wire Window ventana;
    
    //Metodos Setters y Getters
    public String getInstanciaApelada(){
    	return instanciaApelada;
    }
    @NotifyChange({ "filtros" })
    public InstanciaApeladaFiltros getFiltros() {
		return filtros;
	}

	public void setFiltros(InstanciaApeladaFiltros filtros) {
		this.filtros = filtros;
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
	
	public String getNombreIFiltro() {
		return nombreIFiltro;
	}

	public void setNombreIFiltro(String nombreIFiltro) {
		this.nombreIFiltro = nombreIFiltro;
	}

	public String getNombreRFiltro() {
		return nombreRFiltro;
	}

	public void setNombreRFiltro(String nombreRFiltro) {
		this.nombreRFiltro = nombreRFiltro;
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
	
	//Metodo que permite guardar las instancias apeladas
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
	
	// Metodo que muestra la lista de todas las instancias
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
	}

	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void limpiar(){
		idInstanciaApelada = null;
		instanciaApelada = "";
		nombreRecursoApelacion = "";
		descripcion = "";
		listadoInstancia();
	}
	
	// Metodo que elimina una actividad tomando en cuenta el idActividad
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
	
	// Permite tomar los datos del objeto instanciaseleccionada
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void mostrarSeleccionada() {
		idInstanciaApelada = getInstanciaApeladaseleccionada().getIdInstanciaApelada();
		instanciaApelada = getInstanciaApeladaseleccionada().getInstanciaApelada();
		nombreRecursoApelacion = getInstanciaApeladaseleccionada().getNombreRecursoApelacion();
		descripcion = getInstanciaApeladaseleccionada().getDescripcion();
	}
	
	// Método que busca y filtra las instancias
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void filtros() {
		listaInstanciaApelada = servicioInstanciaApelada.buscarInstancia(filtros);
	}
}