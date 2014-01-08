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

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMrecaudo {
	
	@WireVariable 
	ServicioRecaudo serviciorecaudo;
	@WireVariable 
	ServicioTipoMotivo serviciotipomotivo;
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
	private TipoMotivo tipoMotivo;
	
	@WireVariable
	private List<RecaudoEntregado> recaudoEntregados;
	@WireVariable
	private List<Recaudo> listaRecaudo;
	@WireVariable
	private List<TipoMotivo> listaTipoMotivo;
	@WireVariable
	private Recaudo recaudoSeleccionado;
	// Preguntar por si se va hace r la busqueda por filtros
	private RecaudoFiltro filtros = new RecaudoFiltro();
	mensajes msjs = new mensajes();
	
	private  @Wire Textbox txtCodigoRecaudo;
	private  @Wire Textbox txtDescripcionRecaudo;
	private  @Wire Textbox txtNombreRecaudo;
	private  @Wire Textbox txtObservacionRecaudo;
	private  @Wire Combobox cmbTipoMotivo;
	@WireVariable
	private List<Recaudo> listaRecaudos;
	
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
	
	
	//Fin de los metodod gets y sets
   
	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	// OTROS METODOS
	//Metodos que perimite guardar un recaudo
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo"})
	public void guardarRecaudo(){
		if (descripcion==""||nombreRecaudo==""||observacion==""|| tipoMotivo==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
			Recaudo recaudo= new Recaudo();
			recaudo.setDescripcion(descripcion);
			recaudo.setNombreRecaudo(nombreRecaudo);
			recaudo.setEstatus(true);
			recaudo.setObservacion(observacion);
			recaudo.setTipoMotivo(tipoMotivo);
			serviciorecaudo.guardarRecaudo(recaudo);
			msjs.informacionRegistroCorrecto();
			limpiar();
		}
	}
	
	
	@Command
	@NotifyChange({"nombreRecaudo","descripcion","tipoMotivo","listaRecaudos"})
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
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion","listaRecaudos"})
	public void limpiar(){
		descripcion="";nombreRecaudo="";observacion="";
		tipoMotivo=null;
		buscarRecaudos();
	}
	
	//Metodo que elimina un recaudo tomando en cuenta el idRecaudo
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo","listaRecaudos"})
	public void eliminarRecaudo(){
		serviciorecaudo.eliminarRecaudo(getRecaudoSeleccionado().getIdRecaudo());
		limpiar();
		msjs.informacionEliminarCorrecto();
	}
	
	//permite tomar los datos del objeto recaudoseleccionado
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo"})
	public void mostrarSeleccionado(){
		descripcion=getRecaudoSeleccionado().getDescripcion();
		nombreRecaudo=getRecaudoSeleccionado().getNombreRecaudo();
		observacion=getRecaudoSeleccionado().getObservacion();
		tipoMotivo=getRecaudoSeleccionado().getTipoMotivo();
	
	}
	
	// Método que busca y filtra los recaudos
		@Command
		@NotifyChange({ "listadoRecaudosActivos" })
		public void filtros() {
			listaRecaudo = serviciorecaudo.buscarRecaudo(filtros);
		}
	
	@Command
	 @NotifyChange({"listaTipoMotivo"})
	public TipoMotivo objetoComboRecaudo() {
		System.out.println(tipoMotivo.getNombreTipoMotivo());
		return tipoMotivo;
	}
//Fin de los otros metodos.
}