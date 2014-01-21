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
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.data.maestros.RecaudoFiltro;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMrecaudoGeneral {
	
	@WireVariable 
	ServicioRecaudo serviciorecaudo;
	@WireVariable
	private Integer idRecaudo;
	@WireVariable
	private String descripcion;
	@WireVariable
	private Boolean estatus;
	@WireVariable
	private String nombreRecaudo;
	@WireVariable
	private String observacion;
	@WireVariable
	private List<RecaudoEntregado> recaudoEntregados;
	@WireVariable
	private List<Recaudo> listaRecaudo;
	@WireVariable
	private Recaudo recaudoSeleccionado;
	private RecaudoFiltro filtros = new RecaudoFiltro();
	mensajes mensajeAlUsuario = new mensajes();
	
	private String nombreRecaudoFiltro="";
	
	private  @Wire Textbox txtCodigoRecaudo;
	private  @Wire Textbox txtDescripcionRecaudo;
	private  @Wire Textbox txtNombreRecaudo;
	private  @Wire Textbox txtObservacionRecaudo;

	@WireVariable
	private List<Recaudo> listaRecaudos;
	
	@Init
	public void init() {
		// initialization code
		buscarRecaudos();
	}
	// Metodos GETS Y SETS
	
	
	public Integer getIdRecaudo() {
		return idRecaudo;
	}
	public RecaudoFiltro getFiltros() {
		return filtros;
	}

	public void setFiltros(RecaudoFiltro filtros) {
		this.filtros = filtros;
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
	public List<RecaudoEntregado> getRecaudoEntregados() {
		return recaudoEntregados;
	}
	public void setRecaudoEntregados(List<RecaudoEntregado> recaudoEntregados) {
		this.recaudoEntregados = recaudoEntregados;
	}
	
	//Fin de los métodos gets y sets
   
	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	
	//Método que perimite guardar un recaudo
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo", "observacion", "listaRecaudos"})
	public void guardarRecaudoGeneral(){
		if (descripcion==null||nombreRecaudo==null||observacion==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
			else{
	    		Recaudo recaudoNuevo= new Recaudo(idRecaudo, descripcion, true,
	    				nombreRecaudo, observacion);
	    		serviciorecaudo.guardarRecaudo(recaudoNuevo);
	    		mensajeAlUsuario.informacionRegistroCorrecto();
                }
	    		limpiar();
	    }
	//Permite buscar los recaudos.
	@Command
	@NotifyChange({"listaRecaudos"})
	public void buscarRecaudos(){
			listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
	}
	
	//Método que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion","listaRecaudos","nombreRecaudoFiltro"})
	public void limpiar(){
		nombreRecaudo=""; descripcion="";  observacion=""; nombreRecaudoFiltro="";
		buscarRecaudos();
	}
	
	//Metodo que elimina un recaudo tomando en cuenta el idRecaudo
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion","listaRecaudos"})
	public void eliminarRecaudo(){
		if (descripcion==null||nombreRecaudo==null||observacion==null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
		serviciorecaudo.eliminarRecaudo(getRecaudoSeleccionado().getIdRecaudo());
		limpiar();
		mensajeAlUsuario.informacionEliminarCorrecto();
	}
	}
	
	//permite tomar los datos del objeto recaudoseleccionado
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "listaRecaudos"})
	public void mostrarSeleccionado(){
		descripcion=getRecaudoSeleccionado().getDescripcion();
		nombreRecaudo=getRecaudoSeleccionado().getNombreRecaudo();
		observacion=getRecaudoSeleccionado().getObservacion();
	}
	
	// Método que busca y filtra los recaudos
		@Command
		@NotifyChange({ "listaRecaudos" })
		public void filtros() {
			listaRecaudos = serviciorecaudo.filtrarRecaudos(nombreRecaudoFiltro);
		}

		public String getNombreRecaudoFiltro() {
			return nombreRecaudoFiltro;
		}

		public void setNombreRecaudoFiltro(String nombreRecaudoFiltro) {
			this.nombreRecaudoFiltro = nombreRecaudoFiltro;
		}
	

}//Fin de VMrecaudoGeneral