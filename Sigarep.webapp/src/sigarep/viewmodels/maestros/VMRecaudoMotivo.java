package sigarep.viewmodels.maestros;
import java.util.List;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;


/**
 * VMrecaudoMotivo por XML UCLA DCYT Sistemas de Información.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRecaudoMotivo {
	//-----------------Servicios----------------------------
	@WireVariable 
	private ServicioRecaudo serviciorecaudo;
	@WireVariable 
	ServicioTipoMotivo serviciotipomotivo;
	//-----------------Variables RecaudoMotivo -------------
	private Integer idRecaudo;
	private String descripcion;
	private Boolean estatus;
	private String nombreRecaudo;
	private String observacion;
	@WireVariable
	private Integer idTipoMotivo;
	@WireVariable
	private String nombreTipoMotivo;
	//-----------------Variables Filtro---------------------
	private String nombreRecaudofiltro="";
	private String nombreTipoMotivofiltro="";
	//-----------------Variables Lista----------------------
	@WireVariable
	private List<Recaudo> listaRecaudos;
	@WireVariable
	private List<TipoMotivo> listaTipoMotivo;
	//-----------------Variables Objeto---------------------
	@WireVariable
	private TipoMotivo tipoMotivo;//variable que relaciona recaudo con el id de la tabla TipoMotivo
	@WireVariable
	private Recaudo recaudoSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	private  @Wire Textbox txtCodigoRecaudo;
	private  @Wire Textbox txtDescripcionRecaudo;
	private  @Wire Textbox txtNombreRecaudo;
	private  @Wire Textbox txtObservacionRecaudo;
	private  @Wire Combobox cmbTipoMotivo;

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
	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	public Recaudo getRecaudoSeleccionado() {
		return recaudoSeleccionado;
	}
	public void setRecaudoSeleccionado(Recaudo recaudoSeleccionado) {
		this.recaudoSeleccionado = recaudoSeleccionado;
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


	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}
	//Fin de los métodos gets y sets

	/**
	 * inicialización
	 * 
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Init
	public void init() {
		// initialization code
		buscarRecaudos();
		buscarTiposMotivo();
	}

	/**
	 * Guardar Recaudo
	 * 
	 * @param guardarRecaudo
	 * @return guarda un objeto recaudo
	 * @throws No
	 * dispara ninguna excepcion.
	 */	
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo", "observacion","tipoMotivo", "listaRecaudos"})
	public void guardarRecaudo(){
		if (nombreRecaudo==null|| nombreTipoMotivo==null || descripcion ==null || observacion ==null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else{
			Recaudo recaudo= new Recaudo(idRecaudo, descripcion, true, nombreRecaudo, observacion);
			recaudo.setTipoMotivo(tipoMotivo);
			serviciorecaudo.guardarRecaudo(recaudo);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/**
	 * Eliminar recaudo
	 * 
	 * @param binder
	 * @returPermite Eliminar un Recaudo.
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo", "observacion","nombreTipoMotivo", "tipoMotivo","listaRecaudos"})
	public void eliminarRecaudo(@ContextParam(ContextType.BINDER) final Binder binder){
		if (nombreRecaudo==null|| nombreTipoMotivo==null || descripcion ==null || observacion ==null){
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
					case YES:
						//if you call super.delete here, since original zk event is not control by binder
						//the change of viewmodel will not update to the ui.
						//so, I post a delete to trigger to process it in binder controll.
						//binder.postCommand("limpiar", null);
						serviciorecaudo.eliminarRecaudo(getRecaudoSeleccionado().getIdRecaudo());
						mensajeAlUsuario.informacionEliminarCorrecto();
						binder.postCommand("limpiar", null);
					case NO:

						binder.postCommand("limpiar", null);
					}
				}
			});		
		}
	}

	/**
	 * Mostrar Seleccionado
	 * 
	 * @param mostrarSeleccionado
	 * @return muestra en los campos correspondientes los datos del registro seleccionado
	 * @throws No
	 * dispara ninguna excepcion.
	 */			
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo","nombreTipoMotivo","listaRecaudos"})
	public void mostrarSeleccionado(){

		idRecaudo=recaudoSeleccionado.getIdRecaudo();
		descripcion=recaudoSeleccionado.getDescripcion();
		observacion=recaudoSeleccionado.getObservacion();
		tipoMotivo = getRecaudoSeleccionado().getTipoMotivo();
		nombreRecaudo=getRecaudoSeleccionado().getNombreRecaudo();
		nombreTipoMotivo=getRecaudoSeleccionado().getTipoMotivo().getNombreTipoMotivo();
	}

	/**
	 * Buscar Recaudos
	 * 
	 * @param buscar Recaudos, listaRecaudos
	 * @return listaRecaudos
	 * @throws No
	 * dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({"listaRecaudos"})
	public void buscarRecaudos(){
		listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
	}

	/**
	 * Buscar Tipo Motivo
	 * 
	 * @param buscarTiposMotivo, listaTipoMotivo
	 * @return listaRecaudos
	 * @throws No
	 * dispara ninguna excepcion.
	 */	
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTiposMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
	}

	/**
	 * Filtros
	 * 
	 * @param filtros
	 * @return Método que busca y filtra los recaudos por nombre de recaudo y tipo del motivo
	 * @throws No
	 * dispara ninguna excepcion.
	 */	
	@Command
	@NotifyChange({"listaRecaudos","listaTipoMotivo","nombreRecaudofiltro","nombreTipoMotivofiltro"})
	public void filtros(){
		listaRecaudos = serviciorecaudo.buscarRecaudo(nombreRecaudofiltro,nombreTipoMotivofiltro);
	}

	/**
	 * Objeto ComboMotivo
	 * 
	 * @param objetoComboMotivo
	 * @return Método que busca los tipos de motivos y los carga en el combobox de motivos
	 * @throws No
	 * dispara ninguna excepcion.
	 */	
	@Command
	@NotifyChange({"listaTipoMotivo"})
	public TipoMotivo objetoComboMotivo() {
		//System.out.println(nombreTipoMotivo);
		return tipoMotivo;
	}

	/**
	 * limpiar
	 * 
	 * @param limpiar
	 * @return Metodo que limpia todos los campos de la pantalla
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo","observacion","nombreTipoMotivo","tipoMotivo","nombreRecaudofiltro","nombreTipoMotivofiltro","listaRecaudos"})
	public void limpiar(){
		idRecaudo = null;
		nombreRecaudo= null; 
		nombreTipoMotivo=null;
		descripcion=null; 
		observacion=null; 
		nombreRecaudofiltro=null;
		nombreTipoMotivofiltro=null;
		nombreRecaudo=null;
		tipoMotivo= null;
		buscarRecaudos();		
	}

	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo", "observacion","tipoMotivo", "listaRecaudos"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(nombreRecaudo!=null|| nombreTipoMotivo!=null || descripcion !=null || observacion !=null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}

	