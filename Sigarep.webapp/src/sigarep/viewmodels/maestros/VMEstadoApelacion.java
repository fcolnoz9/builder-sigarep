package sigarep.viewmodels.maestros;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
/*
 * @ (#) EstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 * Esta clase es del registro del maestro "EstadoApelacion"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstadoApelacion {
	@WireVariable
	ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private Integer idEstadoApelacion; // clave principal de la tabla EstadoApelacion
	@WireVariable
	private String nombreEstado; // nombre del EstadoApelacion
	@WireVariable
	private String descripcion; // descripcion del EstadoApelacion
	@WireVariable
	private Boolean estatus; // estatus del EstadoApelacion
	@WireVariable
	private InstanciaApelada instanciaApelada;
	@WireVariable
	private List<EstadoApelacion> listaEstadoApelacion; // lista de Estados de Apelacion registrados
	@WireVariable
	private EstadoApelacion estadoseleccionado;
	@WireVariable
	private List<InstanciaApelada> listaInstanciaApelada; 
	mensajes msjs = new mensajes();
	
    
	@Wire
	Window winRegistrarEstados;
	private  @Wire Combobox cmbInstanciaApelada;
	
	@Init
	public void init() {
		// initialization code
		buscarEstadoApelacion();
		buscarInstanciaApelada();
		
	}

	// Metodos GETS Y SETS
	public Integer getIdEstadoApelacion() {
		return idEstadoApelacion;
	}

	public void setIdEstadoApelacion(Integer idEstadoApelacion) {
		this.idEstadoApelacion = idEstadoApelacion;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
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

	public List<EstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(List<EstadoApelacion> ListaEstadoApelacion) {
		this.listaEstadoApelacion = ListaEstadoApelacion;
	}

	public EstadoApelacion getEstadoseleccionado() {
		return estadoseleccionado;
	}

	public void setEstadoseleccionado(EstadoApelacion estadoseleccionado) {
		this.estadoseleccionado = estadoseleccionado;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	
	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	
	public Combobox getCmbInstanciaApelada() {
		return cmbInstanciaApelada;
	}
	public void setCmbInstanciaApelada(Combobox cmbInstanciaApelada) {
		this.cmbInstanciaApelada = cmbInstanciaApelada;
	}
	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}
	public void setInstanciaApelada(InstanciaApelada instanciaapelada) {
		this.instanciaApelada = instanciaapelada;
	}
	
	// Fin de los metodos gets y sets

	// OTROS METODOS

	// Metodos que permite guardar los Estados de Apelacion
	@Command
	@NotifyChange({ "nombreEstado", "descripcion", "instanciaapelada" })
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es nombre y descripción se va a colocar en blanco
	// al guardar
	public void guardarEstadoApelacion() {
		if (nombreEstado==null || descripcion==null || instanciaApelada==null) {
			msjs.advertenciaLlenarCampos();
		} else {
			EstadoApelacion estadoapelacion = new EstadoApelacion();
			estadoapelacion.setNombreEstado(nombreEstado);
			estadoapelacion.setDescripcion(descripcion);
			estadoapelacion.setEstatus(true);
			estadoapelacion.setInstanciaApelada(instanciaApelada);
			servicioestadoapelacion.guardarEstadoApelacion(estadoapelacion);
			msjs.informacionRegistroCorrecto();
			limpiar();
		}
	}
	
	@Command
	@NotifyChange({"nombreEstado","descripcion","instanciaapelada","listaEstadoApelacion"})
	public void buscarEstadoApelacion(){
			listaEstadoApelacion  = servicioestadoapelacion.listadoEstadoApelacionActivas();
	}
	
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void buscarInstanciaApelada() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
		System.out.println("lista"+listaInstanciaApelada);
	}
	

	// Metodo que limpia todos los campos
	@Command
	@NotifyChange({ "nombreEstado", "descripcion","listaEstadoApelacion"})
	public void limpiar() {
		nombreEstado = "";
		descripcion = "";
		instanciaApelada=null;
		buscarEstadoApelacion();
	}

	// permite tomar los datos del objeto EstadoApelacion seleccionado
	@Command
	@NotifyChange({ "nombreEstado", "descripcion", "instanciaApelada" })
	public void mostrarSeleccionado() {
		nombreEstado = getEstadoseleccionado().getNombreEstado();
		descripcion = getEstadoseleccionado().getDescripcion();
		instanciaApelada = getEstadoseleccionado().getInstanciaApelada();
	}

	//Combo
	@Command
	 @NotifyChange({"listaInstanciaApelada"})
	public InstanciaApelada objetoComboEstadoApelacion() {
		System.out.println(instanciaApelada.getDescripcion());
		return instanciaApelada;
	}
	// Método que trae todos los registros en una lista de Estados de Apelacion
	@Command
	@NotifyChange({ "listaEstadoApelacion"  })
	public void listadoEstadoApelacion() {
		listaEstadoApelacion = servicioestadoapelacion.listadoEstadoApelacionActivas();
	
	}

}
