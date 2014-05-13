package sigarep.viewmodels.maestros;

import java.util.List;


import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;

/**
 * Clase VMEstadoApelacion : Clase ViewModels 
relacionada con el Maestro EstadoApelacion. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 19/12/2013
 * @last 09/05/2014
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstadoApelacion {
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	//-----------------Variables EstadoApelacion-----------------
	private Integer idEstadoApelacion; // clave principal de la tabla EstadoApelacion
	private String nombreEstado; // nombre del EstadoApelacion
	private String descripcion; // descripcion del EstadoApelacion
	private Boolean estatus; // estatus del EstadoApelacion
	private Integer prioridadEjecucion; //Prioridad de ejecucion del estado de apelacion
	//-----------------Variables Lista----------------------
	private List<EstadoApelacion> listaEstadoApelacion; // lista de Estados de Apelacion registrados
	private List<InstanciaApelada> listaInstanciaApelada; 
	//-----------------Variables Objeto---------------------
	private EstadoApelacion estadoSeleccionado;
	private InstanciaApelada instanciaApelada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	@Wire("#cmbInstanciaApelada")
	Combobox cmbInstanciaApelada;

	// Métodos Set y Get
	public Integer getIdEstadoApelacion() {
		return idEstadoApelacion;
	}

	public void setIdEstadoApelacion(Integer idEstadoApelacion) {
		this.idEstadoApelacion = idEstadoApelacion;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(
			List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	
	public EstadoApelacion getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(EstadoApelacion estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}
	
	public List<EstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(List<EstadoApelacion> listaEstadoApelacion) {
		this.listaEstadoApelacion = listaEstadoApelacion;
	}

	public Integer getPrioridadEjecucion() {
		return prioridadEjecucion;
	}

	public void setPrioridadEjecucion(Integer prioridadEjecucion) {
		this.prioridadEjecucion = prioridadEjecucion;
	}
	// Fin Métodos Set y Get
	
	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno
	 * @return código de inicialización
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		buscarEstadoApelacion();
		buscarInstanciaApelada();
	}

	/**Guardar Estado de Apelación : Guarda el registro completo, el command indica a las variables el
	 * cambio que se hará en el objeto.
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaEstadoApelacion", "nombreEstado", "descripcion", "instanciaapelada", "prioridadEjecucion" })
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es nombre y descripción se va a colocar en blanco
	// al guardar
	public void guardarEstadoApelacion() {
		if (nombreEstado==null || descripcion==null || instanciaApelada==null || prioridadEjecucion==null) {
			mensajeAlUsuario.advertenciaSeleccionarEstadoApelacion();
		} else {
			//EstadoApelacion estadoapelacion = new EstadoApelacion(idEstadoApelacion,nombreEstado,descripcion,true,instanciaApelada);
			EstadoApelacion estadoApelacion = new EstadoApelacion(idEstadoApelacion, nombreEstado, descripcion, true, prioridadEjecucion);
			estadoApelacion.setInstanciaApelada(instanciaApelada);
			servicioestadoapelacion.guardarEstadoApelacion(estadoApelacion);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}
	
	/** Buscar Estado Apelacion
	 *  @param Ninguno
	 *  @return Objeto EstadoApelacion.
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({"nombreEstado","descripcion","instanciaapelada","listaEstadoApelacion"})
	public void buscarEstadoApelacion(){
			listaEstadoApelacion  = servicioestadoapelacion.listadoEstadoApelacionActivas();
	}
	
	/** buscar Instancia Apelada
	 * 
	 *  @param Ninguno
	 *  @return Objeto listadoInstanciaApelada.
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void buscarInstanciaApelada() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
	}
	
	/** Metodo que limpia todos los campos
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "nombreEstado", "descripcion","listaEstadoApelacion","cmbInstanciaApelada", "prioridadEjecucion"})
	public void limpiar() {
		cmbInstanciaApelada.setDisabled(false);
		idEstadoApelacion = null;
		nombreEstado =null;
		descripcion = null;
		instanciaApelada=null;
		prioridadEjecucion=null;
		buscarEstadoApelacion();
	}

	/**
	 * mostrarSeleccionada : Muestra en el área de datos el registro seleccionado 
	 * 
	 * @param Ninguno. 
	 * @return Objeto Estado Apelacion seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "idEstadoApelacion","nombreEstado", "descripcion", "instanciaApelada", "prioridadEjecucion" })
	public void mostrarSeleccionado() {
		idEstadoApelacion = estadoSeleccionado.getIdEstadoApelacion();
		nombreEstado = estadoSeleccionado.getNombreEstado();
		descripcion = estadoSeleccionado.getDescripcion();
		instanciaApelada = estadoSeleccionado.getInstanciaApelada();
		prioridadEjecucion = estadoSeleccionado.getPrioridadEjecucion();
	}
	
	/**
	 * bloquear : 
	 * 
	 * @param Ninguno. 
	 * @return Objeto combobox
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"cmbInstanciaApelada" })
	public void bloquear(){
		cmbInstanciaApelada.setDisabled(true);
	}

	/**
	 * combo Estado Apelacion
	 * 
	 * @param Ninguno
	 * @return Objeto instanciaApelada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	 @NotifyChange({"listaInstanciaApelada"})
	public InstanciaApelada objetoComboEstadoApelacion() {
		return instanciaApelada;
	}

	/** listado Estado Apelacion :  Método que trae todos los registros en una lista de Estados de Apelacion
	 * 
	 * @param Ninguno
	 * @return Objeto EstadoApelacion.
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "listaEstadoApelacion"  })
	public void listadoEstadoApelacion() {
		listaEstadoApelacion = servicioestadoapelacion.listadoEstadoApelacionActivas();
	}
	
	/**
	 * Cerrar Ventana : cierra el .zul asociado al VM
	 * 
	 * @param  Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaEstadoApelacion", "nombreEstado", "descripcion", "instanciaapelada" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(nombreEstado !=null || descripcion!=null || instanciaApelada!=null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);	
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		Selectors.wireComponents(cmbInstanciaApelada, this, false);
	}
}
