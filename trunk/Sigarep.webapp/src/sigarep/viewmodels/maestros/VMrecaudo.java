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


/**CargarEstudiante por XML
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
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
	private String nombreRecaudo;
	@WireVariable
	private String nombreTipoMotivo;
	
	@WireVariable
	private List<RecaudoEntregado> recaudoEntregados;
	@WireVariable
	private List<Recaudo> listaRecaudo; //Lista de recaudos
	@WireVariable
	private List<TipoMotivo> listaTipoMotivo;
	@WireVariable
	private Recaudo recaudoSeleccionado;
	private RecaudoFiltro filtros = new RecaudoFiltro();
	//mensajes msjs = new mensajes();
	mensajes mensajeAlUsuario = new mensajes();
	
	private  @Wire Textbox txtCodigoRecaudo;
	private  @Wire Textbox txtDescripcionRecaudo;
	private  @Wire Textbox txtNombreRecaudo;
	private  @Wire Textbox txtObservacionRecaudo;
	private  @Wire Combobox cmbTipoMotivo;
	@WireVariable
	private List<Recaudo> listaRecaudos;
	private String nombreRecaudofiltro="";
	private String nombreTipoMotivofiltro="";
	
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
	/** listaRecaudo
	  * @param listaRecaudo
	  * @return La listaRecaudo cargada con los recaudos
	  * 
	  */
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
	
	public String getNombreRecaudofiltro() {
		return nombreRecaudofiltro;
	}
	public void setNombreRecaudofiltro(String nombreRecaudofiltro) {
		this.nombreRecaudofiltro = nombreRecaudofiltro;
	}
	public String getNombreTipoMotivofiltro() {
		return nombreTipoMotivofiltro;
	}
	public void setNombreTipoMotivofiltro(String nombreTipoMotivofiltro) {
		this.nombreTipoMotivofiltro = nombreTipoMotivofiltro;
	}
	//Fin de los metodod gets y sets
	// OTROS METODOS
	//Metodos que perimite guardar un recaudo
	@Command
	@NotifyChange({"recaudo","tipoMotivo","nombreRecaudo","nombreTipoMotivo","listaRecaudos"})
	public void guardarRecaudo(){
		if (recaudo==null||tipoMotivo==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
			//Recaudo recaudo= new Recaudo();
			
	        recaudo.setTipoMotivo(tipoMotivo);//Asocia el idTipoMotivo al idRecaudo
	        System.out.println(""+tipoMotivo);
			serviciorecaudo.guardarRecaudo(recaudo);
			System.out.println(recaudo.getTipoMotivo().getNombreTipoMotivo());
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}
	
	// Metodo que permite buscar recaudos
	@Command
	@NotifyChange({"nombreRecaudo","descripcion","nombreTipoMotivo","tipoMotivo","listaRecaudos"})
	public void buscarRecaudos(){
			listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
	}
	// Metodo que permite buscar los tipos de motivos
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
		tipoMotivo= null;
		recaudo= null;
		buscarRecaudos();
	}
		
	//Metodo que elimina un recaudo tomando en cuenta el idRecaudo
		@Command
		@NotifyChange({"descripcion", "nombreRecaudo", "observacion","nombreTipoMotivo", "tipoMotivo","listaRecaudos"})	
		public void eliminarRecaudo(){
			if (nombreRecaudo==null||nombreTipoMotivo==null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
			} else {
			serviciorecaudo.eliminarRecaudo(getRecaudoSeleccionado().getIdRecaudo());
			limpiar();
			mensajeAlUsuario.informacionEliminarCorrecto();
		}
		}
		
	
	//permite tomar los datos del objeto recaudoseleccionado
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo","nombreTipoMotivo","reacaudo"})
	public void mostrarSeleccionado(){
		recaudo = getRecaudoSeleccionado();
		tipoMotivo = getRecaudoSeleccionado().getTipoMotivo();
		nombreRecaudo=getRecaudoSeleccionado().getNombreRecaudo();
		nombreTipoMotivo=getRecaudoSeleccionado().getTipoMotivo().getNombreTipoMotivo();
	
	}
	// Método que busca y filtra los recaudos
	@Command
	@NotifyChange({"listaRecaudos","nombreRecaudofiltro","nombreTipoMotivofiltro"})
	public void filtros(){
		listaRecaudos = serviciorecaudo.buscarRecaudo(nombreRecaudofiltro,nombreTipoMotivofiltro);
	}
	// Metodo que busca los tipos de motivos y los carga en el combobox de motivos	
	@Command
	 @NotifyChange({"listaTipoMotivo"})
	public TipoMotivo objetoComboMotivo() {
		System.out.println(nombreTipoMotivo);
		return tipoMotivo;
	}
	// Metodo que busca los recaudos y las carga en el combobox de recaudos	
	@Command
	 @NotifyChange({"listaRecaudos"})
	public Recaudo objetoComboRecaudoGeneral() {
		System.out.println(nombreRecaudo);
		return recaudo;
	}
	
//Fin de los otros metodos.
}