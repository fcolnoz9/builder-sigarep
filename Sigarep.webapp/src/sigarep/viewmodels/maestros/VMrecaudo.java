package sigarep.viewmodels.maestros;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Combobox;


import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.RecaudoFiltro;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;

/*
 * @ (#) EstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 * Esta clase es del registro del maestro "Recaudos"
 * @ Author Beleanny Atacho
 * @ Version 1.0, 16/12/13
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMrecaudo {
	
	@WireVariable 
	ServicioRecaudo serviciorecaudo;
	@WireVariable 
	ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private Integer idRecaudo; // clave principal de la tabla recaudo
	@WireVariable
	private String descripcion;// descripcion del recaudo
	@WireVariable
	private Boolean estatus;//estatus del recaudo
	@WireVariable
	private String observacion; //Observacion referente al recaudo
	@WireVariable
	private TipoMotivo tipoMotivo;//variable que relaciona recaudo con el id de la tabla TipoMotivo
	@WireVariable
	private Recaudo recaudo;
	@WireVariable
	private Integer idTipoMotivo;
	
	@WireVariable
	private List<RecaudoEntregado> recaudoEntregados;
	@WireVariable
	private List<Recaudo> listaRecaudo;
	@WireVariable
	private List<TipoMotivo> listaTipoMotivo;
	@WireVariable
	private Recaudo recaudoSeleccionado;
	private RecaudoFiltro filtros = new RecaudoFiltro();
	mensajes msjs = new mensajes();
	
	private  @Wire Textbox txtCodigoRecaudo;
	private  @Wire Textbox txtDescripcionRecaudo;
	private  @Wire Textbox txtNombreRecaudo;
	private  @Wire Textbox txtObservacionRecaudo;
	private  @Wire Combobox cmbTipoMotivo;
	@WireVariable
	private List<Recaudo> listaRecaudos;
	private String nombreRecaudo="";
	private String nombreTipoMotivo="";
	
	@Init
	public void init() {
		// initialization code
		buscarRecaudos();
		buscarTiposMotivo();
	}
	// Metodos GETS Y SETS
	public Integer getIdRecaudo() {
		return idRecaudo;
	}
	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
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
	public String getNombreRecaudo() {
		return nombreRecaudo;
	}
	public void setNombreRecaudo(String nombreRecaudo) {
		this.nombreRecaudo = nombreRecaudo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public List<Recaudo> getListaRecaudo() {
		return listaRecaudo;
	}
	public void setListaRecaudo(List<Recaudo> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}
	public Recaudo getRecaudoSeleccionado() {
		return recaudoSeleccionado;
	}
	public void setRecaudoSeleccionado(Recaudo recaudoSeleccionado) {
		this.recaudoSeleccionado = recaudoSeleccionado;
	}
	
	
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}
	public Combobox getCmbTipoMotivo() {
		return cmbTipoMotivo;
	}
	public void setCmbTipoMotivo(Combobox cmbTipoMotivo) {
		this.cmbTipoMotivo = cmbTipoMotivo;
	}
	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	
	public List<RecaudoEntregado> getRecaudoEntregados() {
		return recaudoEntregados;
	}
	public void setRecaudoEntregados(List<RecaudoEntregado> recaudoEntregados) {
		this.recaudoEntregados = recaudoEntregados;
	}
	
	public Recaudo getRecaudo() {
		return recaudo;
	}
	public void setRecaudo(Recaudo recaudo) {
		this.recaudo = recaudo;
	}
	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	
	public RecaudoFiltro getFiltros() {
		return filtros;
	}
	public void setFiltros(RecaudoFiltro filtros) {
		this.filtros = filtros;
	}
	
	
	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	//Fin de los metodod gets y sets
	// OTROS METODOS
	//Metodos que perimite guardar un recaudo
	@Command
	@NotifyChange({"recaudo","tipoMotivo","idTipoMotivo","listaRecaudos"})
	public void guardarRecaudo(){
		if (recaudo==null||tipoMotivo==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
			//Recaudo recaudo= new Recaudo();
			
	        recaudo.setTipoMotivo(tipoMotivo);
	        System.out.println(""+tipoMotivo);
			serviciorecaudo.guardarRecaudo(recaudo);
			System.out.println(recaudo.getTipoMotivo().getNombreTipoMotivo());
			msjs.informacionRegistroCorrecto();
			
			limpiar();
		}
	}
	
	// Metodo que permite buscar recaudos
	@Command
	@NotifyChange({"nombreRecaudo","descripcion","nombreTipoMotivo","tipoMotivo","listaRecaudos"})
	public void buscarRecaudos(){
			listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
	}
	
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTiposMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
	}
	
	//Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombreRecaudo","nombreTipoMotivo","listaRecaudos"})
	public void limpiar(){
		nombreTipoMotivo="";
		nombreRecaudo="";
		buscarRecaudos();
	}
	
	//Metodo que elimina un recaudo tomando en cuenta el idRecaudo
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion","nombreTipoMotivo", "tipoMotivo","listaRecaudos"})
	public void eliminarRecaudo(){
		serviciorecaudo.eliminarRecaudo(getRecaudoSeleccionado().getIdRecaudo());
		limpiar();
		msjs.informacionEliminarCorrecto();
	}
	
	//permite tomar los datos del objeto recaudoseleccionado
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo","nombreTipoMotivo"})
	public void mostrarSeleccionado(){
		
		nombreRecaudo=getRecaudoSeleccionado().getNombreRecaudo();
		nombreTipoMotivo=getRecaudoSeleccionado().getTipoMotivo().getNombreTipoMotivo();
	
	}
	
	@Command
	@NotifyChange({"listaRecaudos","nombreRecaudo","nombreTipoMotivo"})
	public void filtros(){
		listaRecaudos = serviciorecaudo.buscarRecaudo(nombreRecaudo,nombreTipoMotivo);
	}
		
	@Command
	 @NotifyChange({"listaTipoMotivo"})
	public TipoMotivo objetoComboMotivo() {
		System.out.println(nombreTipoMotivo);
		return tipoMotivo;
	}
	@Command
	 @NotifyChange({"listaRecaudos"})
	public Recaudo objetoComboRecaudoGeneral() {
		System.out.println(nombreRecaudo);
		return recaudo;
	}
	
//Fin de los otros metodos.
}