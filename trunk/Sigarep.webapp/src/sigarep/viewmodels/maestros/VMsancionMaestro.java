package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.SancionMaestro;
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

	@Init
	public void init() {
		buscarSancion();
	}

	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaSancion" })
	public void guardarSancion() {
		if (nombre.equals("") || descripcion.equals("")) {
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			SancionMaestro sanm = new SancionMaestro(id_sancion, nombre,
					descripcion, true);
			serviciosancionmaestro.guardarSancion(sanm);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus" })
	public void limpiar() {
		nombre = "";
		descripcion = "";
		buscarSancion();
	}

	@Command
	@NotifyChange({ "listaSancion" })
	public void buscarSancion() {
		listaSancion = serviciosancionmaestro.buscarSan(nombre);
	}

	@Command
	@NotifyChange({ "listaSancion" })
	public void eliminarSancion() {
		serviciosancionmaestro.eliminarSancion(getSancionSeleccionada()
				.getIdSancion());
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	@Command
	@NotifyChange({ "nombre", "descripcion" })
	public void mostrarSeleccionada() {
		nombre = getSancionSeleccionada().getNombreSancion();
		descripcion = getSancionSeleccionada().getDescripcion();
	}

}
