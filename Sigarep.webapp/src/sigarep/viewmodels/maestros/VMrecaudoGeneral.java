package sigarep.viewmodels.maestros;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMrecaudoGeneral {
	
	
	private Integer idRecaudo;
	private String descripcion;
    private Boolean estatus;
    private String nombreRecaudo;
    private String observacion;
    private List<RecaudoEntregado> recaudoEntregados;
    private List<Recaudo> listaRecaudo;
	private Recaudo recaudoSeleccionado;
	private String nombreRecaudoFiltro="";
    MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@WireVariable
	private List<Recaudo> listaRecaudos;
	@WireVariable 
	private ServicioRecaudo serviciorecaudo;
	
	
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
	public List<RecaudoEntregado> getRecaudoEntregados() {
		return recaudoEntregados;
	}
	public void setRecaudoEntregados(List<RecaudoEntregado> recaudoEntregados) {
		this.recaudoEntregados = recaudoEntregados;
	}
	
	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	
	public String getNombreRecaudoFiltro() {
		return nombreRecaudoFiltro;
	}

	public void setNombreRecaudoFiltro(String nombreRecaudoFiltro) {
		this.nombreRecaudoFiltro = nombreRecaudoFiltro;
	}
	
	//Fin de los métodos gets y sets
	
	
	/**
	 * inicialización
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Init
	public void init() {
		// initialization code
		buscarRecaudos();
	}
	
	/**
	 * Guardar Recaudo
	 * @param guardarRecaudoGeneral
	 * @return Guarda un recaudo.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo", "observacion", "listaRecaudos"})
	public void guardarRecaudoGeneral(){
		if (descripcion==null||nombreRecaudo==null||observacion==null)
			mensajeAlUsuario.advertenciaLlenarCampos();
			else{
	    		Recaudo recaudoNuevo= new Recaudo(idRecaudo, descripcion, true,
	    				nombreRecaudo, observacion); 
	    		serviciorecaudo.guardarRecaudo(recaudoNuevo);
	    		limpiar();
	    		mensajeAlUsuario.informacionRegistroCorrecto();
                }
	    		
	    }
	
	/**
	 * Buscar Recaudos
	 * @param buscarRecaudos
	 * @return busca un recaudo, los muestra en la lista.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaRecaudos"})
	public void buscarRecaudos(){
			listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
	}
	
	/**
	 * Limpiar
	 * @param limpiar
	 * @return inicializa las cajas de texto.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion","listaRecaudos","nombreRecaudoFiltro"})
	public void limpiar(){
		nombreRecaudo=null; descripcion=null;  observacion=null; nombreRecaudoFiltro="";
		buscarRecaudos();
	}
	
	/**
	 * Eliminar Recaudo
	 * @param eliminarRecaudo
	 * @return Elimina un recaudo por  idRecaudo
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "idRecaudo","descripcion", "nombreRecaudo", "observacion","listaRecaudos"})
	public void eliminarRecaudo(){
		if (descripcion==null||nombreRecaudo==null||observacion==null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
		serviciorecaudo.eliminarRecaudo(idRecaudo);
		limpiar();
		mensajeAlUsuario.informacionEliminarCorrecto();
	}
	}
	
	/**
	 * Mostrar  Recaudo Seleccionado
	 * @param mostrarSeleccionado
	 * @return permite tomar los datos del objeto recaudoSeleccionado
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "listaRecaudos"})
	public void mostrarSeleccionado(){
		idRecaudo=recaudoSeleccionado.getIdRecaudo();
		descripcion=recaudoSeleccionado.getDescripcion();
		nombreRecaudo=recaudoSeleccionado.getNombreRecaudo();
		observacion=recaudoSeleccionado.getObservacion();
	}
	
	/**
	 * Filtro de recaudo por nombre
	 * @param filtros
	 * @return permite filtrar un  recaudo, por nombre.
	 * @throws No dispara ninguna excepcion.
	 */
		@Command
		@NotifyChange({ "listaRecaudos" })
		public void filtros() {
			listaRecaudos = serviciorecaudo.filtrarRecaudos(nombreRecaudoFiltro);
		}

		
}//Fin de VMrecaudoGeneral