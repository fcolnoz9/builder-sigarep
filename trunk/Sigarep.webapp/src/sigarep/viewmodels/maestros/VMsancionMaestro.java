package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.SancionMaestroFiltros;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMsancionMaestro {
	@WireVariable
	ServicioSancionMaestro serviciosancionmaestro;
	private Integer id_sancion;
	private String nombre;
	private String descripcion;
	private Boolean estatus;
	private List<SancionMaestro> listaSancion;
	private SancionMaestro sancionSeleccionada;
	private SancionMaestroFiltros filtros = new SancionMaestroFiltros();

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

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}

	public SancionMaestro getSancionSeleccionada() {
		return sancionSeleccionada;
	}

	public void setSancionSeleccionada(SancionMaestro sancionSeleccionada) {
		this.sancionSeleccionada = sancionSeleccionada;
	}

	public SancionMaestroFiltros getFiltros() {
		return filtros;
	}

	public void setFiltros(SancionMaestroFiltros filtros) {
		this.filtros = filtros;
	}

	// Fin Métodos Sets y Gets

	// Otros Métodos

	@Init
	public void init() {
		listadoSancion();
	}

	// Método que guarda una Sanción
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaSancion" })
	public void guardarSancion() {
		if (nombre.equals("") || descripcion.equals("")) {
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			SancionMaestro sanm = new SancionMaestro(id_sancion, descripcion,
					true, nombre);
			serviciosancionmaestro.guardarSancion(sanm);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	// Método que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus", "listaSancion" })
	public void limpiar() {
		nombre = "";
		descripcion = "";
		listadoSancion();
	}

	// Método que trae todos los registros en una lista de sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listadoSanciones();
	}

	// Método que elimina una sanción dado el IdSancion
	@Command
	@NotifyChange({ "listaSancion", "nombre", "descripcion" })
	public void eliminarSancion() {
		serviciosancionmaestro.eliminarSancion(getSancionSeleccionada()
				.getIdSancion());
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
	}

	// Método que muestra una sanción seleccionada
	@Command
	@NotifyChange({ "nombre", "descripcion" })
	public void mostrarSeleccionada() {
		nombre = getSancionSeleccionada().getNombreSancion();
		descripcion = getSancionSeleccionada().getDescripcion();
	}

	// Método que busca y filtra las sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void filtros() {
		listaSancion = serviciosancionmaestro.buscarSancion(filtros);
	}

}
