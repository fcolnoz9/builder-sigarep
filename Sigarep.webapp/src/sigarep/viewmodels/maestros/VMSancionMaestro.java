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
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;

/** Clase VMSancionMaestro.
 * @author Equipo Builder
 * @version 2.5.2
 * @since 22-/01/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMSancionMaestro {
	// -----------------Servicios----------------------------
	@WireVariable
	ServicioSancionMaestro serviciosancionmaestro;
	// -----------------Variables SancionMaestro ------------
	private Integer id_sancion;
	private String nombre;
	private String nombreFiltro;
	private String descripcion;
	private Boolean estatus;
	// -----------------Variables Lista----------------------
	private List<SancionMaestro> listaTipoSancion;
	// -----------------Variables Objeto---------------------
	private SancionMaestro tipoSancionSeleccionada;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Inicion Métodos Sets y Gets
	public Integer getIdSancion() {
		return id_sancion;
	}

	public void setIdSancion(Integer id_sancion) {
		this.id_sancion = id_sancion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<SancionMaestro> getListaTipoSancion() {
		return listaTipoSancion;
	}

	public void setListaTipoSancion(List<SancionMaestro> listaTipoSancion) {
		this.listaTipoSancion = listaTipoSancion;
	}

	public SancionMaestro getTipoSancionSeleccionada() {
		return tipoSancionSeleccionada;
	}

	public void setTipoSancionSeleccionada(
			SancionMaestro tipoSancionSeleccionada) {
		this.tipoSancionSeleccionada = tipoSancionSeleccionada;
	}

	public String getNombreFiltro() {
		return nombreFiltro;
	}

	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}// Fin Métodos Sets y Gets

	/**
	 * inicialización
	 *  Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		listaTipoSancion();
	}

	/**
	 * listaTipoSancion
	 * 
	 * @param Ninguno
	 * @return Sancion
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaTipoSancion" })
	public void listaTipoSancion() {
		listaTipoSancion = serviciosancionmaestro.listaTipoSanciones();
	}

	/**
	 * guardar sancionMaestro
	 * 
	 * @param Ninguno
	 * @return Guarda el registro completo, el command indica a las variables el
	 *         cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaTipoSancion" })
	public void guardarTipoSancion() {
		if (nombre == null || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			SancionMaestro sanm = new SancionMaestro(id_sancion, descripcion,
					true, nombre);
			serviciosancionmaestro.guardarSancion(sanm);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/**
	 * eliminarTipoSancion
	* @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaTipoSancion" })
	public void eliminarTipoSancion(
			@ContextParam(ContextType.BINDER) final Binder binder) {
		if (nombre == null || descripcion == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			System.out.println(id_sancion);
			if (id_sancion == 1 || id_sancion == 2) {
				Messagebox.show("¡No puede eliminar este registro!",
						"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
			} else {
				Messagebox.show("¿Desea eliminar el registro realmente?",
						"Confirmar", new Messagebox.Button[] {
								Messagebox.Button.YES, Messagebox.Button.NO },
						Messagebox.QUESTION, new EventListener<ClickEvent>() {
							@SuppressWarnings("incomplete-switch")
							public void onEvent(ClickEvent e) throws Exception {
								switch (e.getButton()) {
								case YES:
									serviciosancionmaestro
											.eliminarSancion(id_sancion);
									mensajeAlUsuario
											.informacionEliminarCorrecto();
									binder.postCommand("limpiar", null);
								case NO:
									binder.postCommand("limpiar", null);
								}
							}
						});
			}
		}
	}

	/**
	 * mostrarSeleccionada : Muestra en el área de datos el registro
	 * seleccionado
	 * 
	 * @param Ninguno
	 *            .
	 * @return Objeto sacionMaestro seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus" })
	public void mostrarSeleccionada() {
		id_sancion = getTipoSancionSeleccionada().getIdSancion();
		nombre = getTipoSancionSeleccionada().getNombreSancion();
		descripcion = getTipoSancionSeleccionada().getDescripcion();
	}

	/**
	 * filtros   Método que busca y filtra por nombre de la sancion
	 * 
	 * @param Ninguno
	 * @return Objeto sancion
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaTipoSancion", "nombreFiltro" })
	public void filtros() {
		listaTipoSancion = serviciosancionmaestro.filtrarSancion(nombreFiltro);
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"nombreFiltro", "listaTipoSancion" })
	public void limpiar() {
		id_sancion = null;
		nombre = null;
		nombreFiltro = "";
		descripcion = null;
		listaTipoSancion();
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaTipoSancion", "nombre", "descripcion", "estatus" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = false;
		if (nombre != null || descripcion != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);
	}
}
