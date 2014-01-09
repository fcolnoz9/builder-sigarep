package sigarep.viewmodels.maestros;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.EstadoApelacionFiltros;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;

/*
 * @ (#) Momento.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 * Esta clase es del registro del maestro "Momento"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstadoApelacion {
	@WireVariable
	ServicioEstadoApelacion servicioestadoapelacion;
	private Integer idEstadoApelacion; // clave principal de la tabla EstadoApelacion
	private String nombreEstadoApelacion; // nombre del EstadoApelacion
	private String nombreEstadoApelacionfiltro; // filtro de busqueda por nombre
	private String descripcion; // descripcion del EstadoApelacion
	private String descripcionfiltro; // filtro de busqueda por descripcion
	private Boolean estatus; // estatus del EstadoApelacion
	private List<EstadoApelacion> listaEstadoApelacion; // lista de Estados de Apelacion registrados
	private EstadoApelacion estadoseleccionado;
	private EstadoApelacionFiltros filtros = new EstadoApelacionFiltros();

	@Wire
	Textbox txtnombreMomento;
	@Wire
	Window winRegistrarMomento;

	// Metodos GETS Y SETS
	public Integer getIdEstadoApelacion() {
		return idEstadoApelacion;
	}

	public void setIdEstadoApelacion(Integer idEstadoApelacion) {
		this.idEstadoApelacion = idEstadoApelacion;
	}

	public String getNombreEstadoApelacion() {
		return nombreEstadoApelacion;
	}

	public void setNombreEstadoApelacion(String nombreEstadoApelacion) {
		this.nombreEstadoApelacion = nombreEstadoApelacion;
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

	public String getNombreEstadoApelacionfiltro() {
		return nombreEstadoApelacionfiltro;
	}

	public String getDescripcionfiltro() {
		return descripcionfiltro;
	}

	public EstadoApelacionFiltros getFiltros() {
		return filtros;
	}

	public void setFiltros(EstadoApelacionFiltros filtros) {
		this.filtros = filtros;
	}

	public List<EstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(List<EstadoApelacion> ListaEstadoApelacion) {
		this.listaEstadoApelacion = ListaEstadoApelacion;
	}

	public EstadoApelacion getEstadoseleccionado() {
		return estadoseleccionado;
	}

	public void setEstadoseleccionado(EstadoApelacion momenseleccionado) {
		this.estadoseleccionado = momenseleccionado;
	}

	// Fin de los metodos gets y sets

	// OTROS METODOS
	@Init
	public void init() {
		listadoEstadoApelacion();
	}

	// Metodos que permite guardar los Estados de Apelacion
	@Command
	@NotifyChange({ "nombreEstadoApelacion", "descripcion", "listaEstadoApelacion" })
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es nombre y descripción se va a colocar en blanco
	// al guardar
	public void guardarEstadoApelacion() {
		if (nombreEstadoApelacion==null || descripcion==null) {
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			EstadoApelacion estadoApelacion = new EstadoApelacion(idEstadoApelacion, nombreEstadoApelacion,
					descripcion, true);
			servicioestadoapelacion.guardar(estadoApelacion);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	// Metodo que limpia todos los campos
	@Command
	@NotifyChange({ "nombreEstadoApelacion", "descripcion" })
	public void limpiar() {
		nombreEstadoApelacion = "";
		descripcion = "";
		listadoEstadoApelacion();
	}

	// Método que trae todos los registros en una lista de momentos
	@Command
	@NotifyChange({ "listaEstadoApelacion" })
	public void listadoEstadoApelacion() {
		listaEstadoApelacion = servicioestadoapelacion.listadoEstadoApelacion();
	}

	// Metodo que elimina un EstadoApelacion tomando en cuenta el idEstadoApelacion
	@Command
	@NotifyChange({ "nombreEstadoApelacion", "descripcion", "listaEstadoApelacion" })
	public void eliminarEstadoApelacion() {
		if (nombreEstadoApelacion==null || descripcion==null) {
			Messagebox.show("Debes Seleccionar un Estado de Apelacion", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			servicioestadoapelacion.eliminarEstadoApelacion(getEstadoseleccionado().getIdEstadoApelacion());
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
	}
	}
	// permite tomar los datos del objeto EstadoApelacion seleccionado
	@Command
	@NotifyChange({ "idEstadoApelacion", "nombreEstadoApelacion", "descripcion" })
	public void mostrarSeleccionado() {
		idEstadoApelacion = getEstadoseleccionado().getIdEstadoApelacion();
		nombreEstadoApelacion = getEstadoseleccionado().getNombreEstado();
		descripcion = getEstadoseleccionado().getDescripcion();
	}

	// Método que busca y filtra las sanciones
	@Command
	@NotifyChange({ "listaEstadoApelacion" })
	public void filtros() {
		listaEstadoApelacion = servicioestadoapelacion.buscarEstadoApelacion(filtros);
	}
}
