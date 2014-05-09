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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;

/** Clase VMTipoMotivo.
 * @author Equipo Builder
 * @version 2.5.2
 * @since 22-/01/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMTipoMotivo {
	//-----------------Servicios----------------------------
	@WireVariable ServicioTipoMotivo serviciotipomotivo;
	//-----------------Variables TipoMotivo ----------------
	private String nombreTipoMotivo;
	private String descripcion;
	private Integer idTipoMotivo;
	private Boolean estatus;
	//-----------------Variables Filtro---------------------
	private String nombreFiltro;
	private String descripcionfiltro;
	private String nombreTipoMotivofiltro;
	//-----------------Variables Lista----------------------
	private List<TipoMotivo> listaTipoMotivo;
	//-----------------Variables Objeto---------------------
	private TipoMotivo tiposeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@Wire Textbox txtnombreTipoMotivo;
	@Wire Window winTipoMotivo;

	//Metodos set y get

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public String getNombreTipoMotivofiltro() {
		return nombreTipoMotivofiltro;
	}
	public String getDescripcionfiltro() {
		return descripcionfiltro;
	}
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public TipoMotivo getTiposeleccionado() {
		return tiposeleccionado;
	}
	public void setNombreTipoMotivofiltro(String nombreTipoMotivofiltro) {
		this.nombreTipoMotivofiltro = nombreTipoMotivofiltro;
	}

	public void setDescripcionfiltro(String descripcionfiltro) {
		this.descripcionfiltro = descripcionfiltro;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	public void setTiposeleccionado(TipoMotivo tiposeleccionado) {
		this.tiposeleccionado = tiposeleccionado;
	}

	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	public String getNombreFiltro() {
		return nombreFiltro;
	}
	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}
	//Fin de los metodod gets y sets

	/**
	 * inicialización
	 *  Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init(){

		listaTipoMotivo();

	} 

	/**
	 * listaTipoMotivo
	 * 
	 * @param Ninguno
	 * @return Objeto tipoMotivo
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void listaTipoMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
	}	

	/**
	 * guardarTipoMotivo
	 * @param Ninguno
	 * @return Guarda el registro completo, el command indica a las variables el
	 *         cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"idTipoMotivo","nombreTipoMotivo", "descripcion","estatus","listaTipoMotivo"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarTipoMotivo(){

		if (nombreTipoMotivo == null || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {

			if(idTipoMotivo!=null) {
				if (idTipoMotivo == 1 ||idTipoMotivo == 2 ||idTipoMotivo == 3 ){
					mensajeAlUsuario.errorNoModificarMotivoGeneral();
				} else{
					TipoMotivo tipo = new TipoMotivo(idTipoMotivo, descripcion, true, nombreTipoMotivo, false);
					serviciotipomotivo.guardarTipoMotivo(tipo);
					mensajeAlUsuario.informacionRegistroCorrecto();
					limpiar();
				}

			}
			else{
				TipoMotivo tipo = new TipoMotivo(idTipoMotivo, descripcion, true, nombreTipoMotivo, false);
				serviciotipomotivo.guardarTipoMotivo(tipo);
				mensajeAlUsuario.informacionRegistroCorrecto();
				limpiar();
			}
		}

	}

	/**
	 * eliminarTipoMotivo
	 * 
	* @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({"listaTipoMotivo", "idTipoMotivo","nombreTipoMotivo", "descripcion"})
	public void eliminarTipoMotivo(@ContextParam(ContextType.BINDER) final Binder binder){
		if (nombreTipoMotivo==null  || descripcion==null ) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {


			if (idTipoMotivo == 1 ||idTipoMotivo == 2 ||idTipoMotivo == 3 ){
				mensajeAlUsuario.errorNoEliminarMotivoGeneral();
			} else{

				Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
						Messagebox.QUESTION,new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
						case YES:
							serviciotipomotivo.eliminarTipoMotivo(getTiposeleccionado().getIdTipoMotivo());
							mensajeAlUsuario.informacionEliminarCorrecto();
							binder.postCommand("limpiar", null);
						case NO:

							binder.postCommand("limpiar", null);
						}
					}
				});		

			}


		}
	}

	/**
	 * mostrarSeleccionada : Muestra en el área de datos el registro
	 * seleccionado
	 * 
	 * @param Ninguno
	 *            .
	 * @return Objeto sacionMaestro seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"idTipoMotivo","nombreTipoMotivo", "descripcion","estatus"})
	public void mostrarSeleccionado(){

		idTipoMotivo= getTiposeleccionado().getIdTipoMotivo();
		nombreTipoMotivo= getTiposeleccionado().getNombreTipoMotivo();
		descripcion=getTiposeleccionado().getDescripcion();	
	}

	/**
	 * filtros   Método que busca y filtra por nombre de motiv
	 * @param Ninguno
	 * @return Objeto tipoMotivo
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaTipoMotivo", "nombreFiltro" })
	public void filtros() {
		listaTipoMotivo = serviciotipomotivo.filtrarTipoMotivo(nombreFiltro);
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaTipoMotivo","idTipoMotivo","nombreTipoMotivo", "estatus","descripcion","nombreFiltro"})
	public void limpiar(){
		idTipoMotivo= null;
		nombreTipoMotivo = null;
		descripcion=null;
		nombreFiltro= "";
		listaTipoMotivo();
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaTipoMotivo", "idTipoMotivo","nombreTipoMotivo", "descripcion"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(nombreTipoMotivo !=null  || descripcion !=null )
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}
