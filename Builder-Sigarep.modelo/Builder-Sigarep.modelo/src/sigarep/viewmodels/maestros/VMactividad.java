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
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;

/**
 * Clase VMactividad
 * 
 * @author BUILDER
 * @version 1.0
 * @since 19/12/2013
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMactividad {
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioActividad servicioactividad;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	//-----------------Variables Actividad -----------------
	private Integer id_actividad;
	private String nombre;
	private String descripcion;
	private Boolean estatus;
	//-----------------Variables Filtro---------------------
	private String nombreFiltro = "";
	private String responsableFiltro = "";
	//-----------------Variables Lista----------------------
	private List<Actividad> listaActividad;
	private List<InstanciaApelada> listaInstanciaApelada;
	//-----------------Variables Objeto---------------------
	private Actividad actividadSeleccionada;
	private InstanciaApelada instanciaApelada;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	// Métodos Set y Get
	public void setId_Actividad(Integer id_actividad) {
		this.id_actividad = id_actividad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;
	}

	public void setActividadSeleccionada(Actividad actividadSeleccionada) {
		this.actividadSeleccionada = actividadSeleccionada;
	}

	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}

	public void setResponsableFiltro(String responsableFiltro) {
		this.responsableFiltro = responsableFiltro;
	}

	public Integer getId_Actividad() {
		return id_actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public List<Actividad> getListaActividad() {
		return listaActividad;
	}

	public Actividad getActividadSeleccionada() {
		return actividadSeleccionada;
	}

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaapelada) {
		this.instanciaApelada = instanciaapelada;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(
			List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public String getNombreFiltro() {
		return nombreFiltro;
	}

	public String getResponsableFiltro() {
		return responsableFiltro;
	}

	// Fin de los métodos set y get



	/**
	 * inicialización
	 * 
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepción.
	 */
	@Init
	public void init() {
		listaActividad();
		buscarInstanciaApelada();
	}
	
	/**
	 * listaActividad tiene todas las actividades registradas
	 * 
	 * @param listaActividad
	 * @return listaActividad
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaActividad" })
	public void listaActividad() {
		listaActividad = servicioactividad.listadoActividad();
	}


	/**
	 * guardarActividad
	 * 
	 * @param Actividad actividad
	 * @return No devuelve ningún valor
	 * @throws No debe haber campos en blanco
	 */
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion",
			"instanciaApelada", "listaActividad" })
	public void guardarActividad() {
		if (nombre == null || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			Actividad actividad = new Actividad(id_actividad, nombre,
					descripcion, instanciaApelada, true);
			servicioactividad.guardar(actividad);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}


	/**
	 * eliminarActividad
	 * 
	 * @param Binder binder
	 * @return No devuelve ningún valor
	 * @throws Debe
	 *             seleccionar un registro para poder eliminarlo
	 */
	@Command
	@NotifyChange({ "listaActividad", "nombre", "instanciaApelada",
			"descripcion" })
	public void eliminarActividad(
			@ContextParam(ContextType.BINDER) final Binder binder) {
		if (nombre == null || descripcion == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?",
					"Confirmar", new Messagebox.Button[] {
							Messagebox.Button.YES, Messagebox.Button.NO },
					Messagebox.QUESTION, new EventListener<ClickEvent>() {
						@SuppressWarnings("incomplete-switch")
						public void onEvent(ClickEvent e) throws Exception {
							switch (e.getButton()) {
							case YES:
								// if you call super.delete here, since original
								// zk event is not control by binder
								// the change of viewmodel will not update to
								// the ui.
								// so, I post a delete to trigger to process it
								// in binder controll.
								// binder.postCommand("limpiar", null);
								servicioactividad.eliminar(id_actividad);
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
	 * mostrarSeleccionada
	 * 
	 * @param vacío
	 * @return Actividad seleccionada
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "id_actividad", "nombre", "instanciaApelada", "descripcion" })
	public void mostrarSeleccionada() {
		id_actividad = getActividadSeleccionada().getIdActividad();
		nombre = getActividadSeleccionada().getNombre();
		descripcion = getActividadSeleccionada().getDescripcion();
		instanciaApelada = getActividadSeleccionada().getInstanciaApelada();
	}

	/**
	 * buscarInstanciaApelada
	 * 
	 * @param vacío
	 * @return Instancia Apelada
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void buscarInstanciaApelada() {
		listaInstanciaApelada = servicioInstanciaApelada
				.listadoInstanciaApelada();
	}

	/**
	 * comboResponsable
	 * 
	 * @param vacío
	 * @return instanciaApelada
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public InstanciaApelada comboResponsable() {
		return instanciaApelada;
	}

	/**
	 * filtros
	 * 
	 * @param vacío
	 * @return nombreFiltro,
				responsableFiltro
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaActividad", "nombreFiltro", "responsableFiltro" })
	public void filtros() {
		listaActividad = servicioactividad.buscarActividad(nombreFiltro,
				responsableFiltro);
	}

	/**
	 * limpiar
	 * 
	 * @param vacío
	 * @return No devuelve ningún valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion",
			"instanciaApelada", "nombreFiltro", "responsableFiltro",
			"listaActividad" })
	public void limpiar() {
		id_actividad = null;
		nombre = null;
		descripcion = null;
		nombreFiltro = "";
		responsableFiltro = "";
		instanciaApelada = null;
		listaActividad();
		buscarInstanciaApelada();
	} 
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param Window ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listaActividad", "nombre", "instanciaApelada",
			"descripcion" })
	
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(nombre != null || descripcion != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}

}
