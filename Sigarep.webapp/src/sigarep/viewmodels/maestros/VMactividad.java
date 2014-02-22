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
 * Actividades UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMactividad {

	@WireVariable
	ServicioActividad servicioactividad;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private Integer id_actividad;
	private String nombre;
	private String descripcion;
	private String nombreFiltro = "";
	private String responsableFiltro = "";
	private Boolean estatus;
	private List<Actividad> listaActividad;
	private Actividad actividadSeleccionada;
	private InstanciaApelada instanciaApelada;
	private List<InstanciaApelada> listaInstanciaApelada;

	
	// Metodos GETS Y SETS
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

	// Fin de los metodos gets y sets

	// OTROS METODOS

	@Init
	public void init() {
		listaActividad();
		buscarInstanciaApelada();
	}

	/**
	 * guardarActividad
	 * 
	 * @param id_actividad
	 *            , nombre, descripcion, listaActividad, instanciaApelada
	 * @return No devuelve ningun valor
	 * @throws No
	 *             debe haber campos en blanco
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
	 * listaActividad tiene todas las actividades registradas
	 * 
	 * @param listaActividad
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaActividad" })
	public void listaActividad() {
		listaActividad = servicioactividad.listadoActividad();
	}

	/**
	 * limpiar
	 * 
	 * @param id_actividad
	 *            , nombre , descripcion, listaActividad, instanciaApelada
	 * @return No devuelve ningun valor
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
	 * eliminarActividad
	 * 
	 * @param nombre
	 *            , descripcion, listaActividad, instanciaApelada
	 * @return No devuelve ningun valor
	 * @throws Debe
	 *             seleccionar un registro para poder eliminarlo
	 */

	@SuppressWarnings("unchecked")
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
	 * @param nombre
	 *            , descripcion, id_actividad, instanciaApelada
	 * @return No devuelve ningun valor
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
	 * @param listaInstanciaApelada
	 * @return No devuelve ningun valor
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
	 * @param listaInstanciaApelada
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
	 * @param listaActividad
	 * @return No devuelve ningun valor
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
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
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
