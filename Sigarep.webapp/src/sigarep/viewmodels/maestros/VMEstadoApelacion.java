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
	ServicioEstadoApelacion serviciomomento;
	private Integer idMomento; // clave principal de la tabla Momento
	private String nombreMomento; // nombre del Momento
	private String nombreMomentofiltro; // filtro de busqueda por nombre
	private String descripcion; // descripcion del momento
	private String descripcionfiltro; // filtro de busqueda por descripcion
	private Boolean estatus; // estatus del momento
	private List<EstadoApelacion> listaMomento; // lista de Momentos registrados
	private EstadoApelacion momenseleccionado;
	private EstadoApelacionFiltros filtros = new EstadoApelacionFiltros();

	@Wire
	Textbox txtnombreMomento;
	@Wire
	Window winRegistrarMomento;

	// Metodos GETS Y SETS
	public Integer getIdMomento() {
		return idMomento;
	}

	public void setIdMomento(Integer idMomento) {
		this.idMomento = idMomento;
	}

	public String getNombreMomento() {
		return nombreMomento;
	}

	public void setNombreMomento(String nombreMomento) {
		this.nombreMomento = nombreMomento;
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

	public String getNombreMomentofiltro() {
		return nombreMomentofiltro;
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

	public List<EstadoApelacion> getListaMomento() {
		return listaMomento;
	}

	public void setListaMomento(List<EstadoApelacion> ListaMomento) {
		this.listaMomento = ListaMomento;
	}

	public EstadoApelacion getMomenseleccionado() {
		return momenseleccionado;
	}

	public void setMomenseleccionado(EstadoApelacion momenseleccionado) {
		this.momenseleccionado = momenseleccionado;
	}

	// Fin de los metodos gets y sets

	// OTROS METODOS
	@Init
	public void init() {
		listadoMomento();
	}

	// Metodos que permite guardar los momentos
	@Command
	@NotifyChange({ "nombreMomento", "descripcion", "listaMomento" })
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es nombre y descripción se va a colocar en blanco
	// al guardar
	public void guardarMomento() {
		if (nombreMomento==null || descripcion==null) {
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			EstadoApelacion momento = new EstadoApelacion(idMomento, nombreMomento,
					descripcion, true);
			serviciomomento.guardar(momento);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	// Metodo que limpia todos los campos
	@Command
	@NotifyChange({ "nombreMomento", "descripcion" })
	public void limpiar() {
		nombreMomento = "";
		descripcion = "";
		listadoMomento();
	}

	// Método que trae todos los registros en una lista de momentos
	@Command
	@NotifyChange({ "listaMomento" })
	public void listadoMomento() {
		listaMomento = serviciomomento.listadoEstadoApelacion();
	}

	// Metodo que elimina un momento tomando en cuenta el idMomento
	@Command
	@NotifyChange({ "nombreMomento", "descripcion", "listaMomento" })
	public void eliminarMomento() {
		if (nombreMomento==null || descripcion==null) {
			Messagebox.show("Debes Seleccionar un Momento", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			serviciomomento.eliminarEstadoApelacion(getMomenseleccionado().getIdEstadoApelacion());
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
	}
	}
	// permite tomar los datos del objeto momento seleccionado
	@Command
	@NotifyChange({ "idMomento", "nombreMomento", "descripcion" })
	public void mostrarSeleccionado() {
		idMomento = getMomenseleccionado().getIdEstadoApelacion();
		nombreMomento = getMomenseleccionado().getNombreEstado();
		descripcion = getMomenseleccionado().getDescripcion();
	}

	// Método que busca y filtra las sanciones
	@Command
	@NotifyChange({ "listaMomento" })
	public void filtros() {
		listaMomento = serviciomomento.buscarEstadoApelacion(filtros);
	}
}
