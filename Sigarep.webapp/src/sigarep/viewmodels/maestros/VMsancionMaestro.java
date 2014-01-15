package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.SancionMaestroFiltros;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;

/*
 * @ (#) SancionMaestro.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso est� sujeto a los t�rminos de la licencia.
 * Esta clase es del registro del maestro "Sancion"
 * @ Author Javier Chacon
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMsancionMaestro {
	@WireVariable
	ServicioSancionMaestro serviciosancionmaestro;
	
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private Integer id_sancion;
	private String nombre;
	private String descripcion;
	private Boolean estatus;
	private List<SancionMaestro> listaSancion;
	private SancionMaestro sancionSeleccionada;
	private SancionMaestroFiltros filtros = new SancionMaestroFiltros();

	// Inicion M�todos Sets y Gets
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

	// Fin M�todos Sets y Gets

	// Otros M�todos

	@Init
	public void init() {
		listadoSancion();
	}

	// M�todo que guarda una Sanci�n
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaSancion" })
	public void guardarSancion() {
		if (nombre == null || nombre.equals("") || descripcion.equals("") || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			SancionMaestro sanm = new SancionMaestro(id_sancion, descripcion,
					true, nombre);
			serviciosancionmaestro.guardarSancion(sanm);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	// M�todo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "id_sancion", "nombre", "descripcion", "estatus",
			"listaSancion" })
	public void limpiar() {
		nombre = "";
		descripcion = "";
		listadoSancion();
	}

	// M�todo que trae todos los registros en una lista de sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listadoSanciones();
	}

	// M�todo que elimina una sanci�n dado el IdSancion
	@Command
	@NotifyChange({ "listaSancion", "nombre", "descripcion" })
	public void eliminarSancion() {
		if (nombre == null || nombre.equals("") || descripcion.equals("") || descripcion == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			serviciosancionmaestro.eliminarSancion(getSancionSeleccionada()
					.getIdSancion());
			mensajeAlUsuario.informacionEliminarCorrecto();
			limpiar();
		}
	}

	// M�todo que muestra una sanci�n seleccionada
	@Command
	@NotifyChange({ "nombre", "descripcion" })
	public void mostrarSeleccionada() {
		nombre = getSancionSeleccionada().getNombreSancion();
		descripcion = getSancionSeleccionada().getDescripcion();
	}

	// M�todo que busca y filtra las sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void filtros() {
		listaSancion = serviciosancionmaestro.buscarSancion(filtros);
	}

}
