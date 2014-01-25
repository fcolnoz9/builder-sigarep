package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
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
	@WireVariable
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
		if (nombre == null || nombre.equals("") || descripcion.equals("")
				|| descripcion == null) {
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
	 * listaActividad
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
	 * @param id_actividad, nombre
	 *            , descripcion, listaActividad, instanciaApelada
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion", "instanciaApelada",
			"nombreFiltro", "responsableFiltro", "listaActividad" })
	public void limpiar() {
		id_actividad = null;
		nombre = "";
		descripcion = "";
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
	@Command
	@NotifyChange({ "listaActividad", "nombre", "instanciaApelada",
			"descripcion" })
	public void eliminarActividad() {
		if (nombre == null || nombre.equals("") || descripcion.equals("")
				|| descripcion == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			servicioactividad.eliminar(getActividadSeleccionada()
					.getIdActividad());
			mensajeAlUsuario.informacionEliminarCorrecto();
			limpiar();
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
}
