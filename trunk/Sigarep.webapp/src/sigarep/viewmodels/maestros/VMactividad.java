package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.ActividadFiltros;
import sigarep.modelos.servicio.maestros.ServicioActividad;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMactividad {

	@WireVariable
	ServicioActividad servicioactividad;

	private Integer id_actividad;
	private String nombre;
	private String descripcion;
	private String nombreFiltro;
	private String descripcionFiltro;
	private Boolean estatus;
	private List<Actividad> listaActividad;
	private Actividad actividadSeleccionada;
	private ActividadFiltros filtros = new ActividadFiltros();

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

	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}

	public void setFiltros(ActividadFiltros filtros) {
		this.filtros = filtros;
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

	public String getNombreFiltro() {
		return nombreFiltro;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	@NotifyChange({ "filtros" })
	public ActividadFiltros getFiltros() {
		return filtros;
	}

	// Fin de los metodos gets y sets

	// OTROS METODOS

	@Init
	public void init() {
		listadoActividad();
	}

	// Metodo que perimite guardar una Actividad
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion", "listaActividad" })
	public void guardarActividad() {
		if (nombre == null || descripcion == null) {
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			Actividad actividad = new Actividad(id_actividad, nombre, descripcion,
					true);
			servicioactividad.guardar(actividad);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	// Metodo que muestra la lista de todas las actividades
	@Command
	@NotifyChange({ "listaActividad" })
	public void listadoActividad() {
		listaActividad = servicioactividad.listadoActividad();
	}

	// Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombre", "descripcion", "listaActividad" })
	public void limpiar() {
		nombre = "";
		descripcion = "";
		listadoActividad();
	}

	// Metodo que elimina una actividad tomando en cuenta el idActividad
	@Command
	@NotifyChange({ "listaActividad", "nombre", "descripcion" })
	public void eliminarActividad() {
		if (nombre == null || descripcion == null) {
			Messagebox.show("Debe seleccionar una actividad", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			servicioactividad.eliminar(getActividadSeleccionada()
					.getIdActividad());
			Messagebox.show("Se ha eliminado correctamente", "Información",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	// Permite tomar los datos del objeto actividadseleccionada
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion" })
	public void mostrarSeleccionada() {
		id_actividad = getActividadSeleccionada().getIdActividad();
		nombre = getActividadSeleccionada().getNombre();
		descripcion = getActividadSeleccionada().getDescripcion();
	}

	// Método que busca y filtra las actividades
	@Command
	@NotifyChange({ "listaActividad" })
	public void filtros() {
		listaActividad = servicioactividad.buscarActividad(filtros);
	}
}
