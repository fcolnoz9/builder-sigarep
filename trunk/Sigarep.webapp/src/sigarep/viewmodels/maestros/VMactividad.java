package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.servicio.maestros.ServicioActividad;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMactividad {

	@WireVariable
	ServicioActividad servicioactividad;

	private Integer id_actividad;
	private String nombre;
	private String descripcion;
	private byte[] imagen;
	private Boolean estatus;
	private List<Actividad> listaActividad;
	private Actividad actividadSeleccionada;

	// Metodos GETS Y SETS
	public Integer getId_Actividad() {
		return id_actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public byte[] getImagen() {
		return imagen;
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

	public void setId_Actividad(Integer id_actividad) {
		this.id_actividad = id_actividad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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

	// Fin de los metodos gets y sets

	// OTROS METODOS
	// Metodos que perimite guardar una Actividad
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion", "imagen",
			"listaActividad" })
	public void guardarActividad() {
		if (nombre.equals("") || descripcion.equals("")) {
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			Actividad actividad = new Actividad(id_actividad, nombre,
					descripcion, imagen, true);
			servicioactividad.guardar(actividad);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	@Init
	public void init() {
		// initialization code
		buscarActividad();
	}

	// Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({ "listaActividad" })
	public void buscarActividad() {
		listaActividad = servicioactividad.buscarActividad(nombre);
	}

	// Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombre", "descripcion", "imagen" })
	public void limpiar() {
		nombre = "";
		descripcion = "";
		imagen = null;
		buscarActividad();
	}

	// Metodo que elimina una actividad tomando en cuenta el idActividad
	@Command
	@NotifyChange({ "listaActividad" })
	public void eliminarActividad() {
		servicioactividad.eliminar(getActividadSeleccionada().getIdActividad());
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
	}

	// permite tomar los datos del objeto actividadseleccionada
	@Command
	@NotifyChange({ "id_actividad", "nombre", "descripcion", "imagen" })
	public void mostrarSeleccionada() {
		id_actividad = getActividadSeleccionada().getIdActividad();
		nombre = getActividadSeleccionada().getNombre();
		descripcion = getActividadSeleccionada().getDescripcion();
		imagen = getActividadSeleccionada().getImagen();
	}
}
