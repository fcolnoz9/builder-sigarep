package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.LinkedList;
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
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.transacciones.ServicioInstanciaMiembro;

/**Clase VMInstanciaApelada
 * ViewModel para la interfaz RegistrarInstanciaApelada.zul
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/13
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInstanciaApelada {
	//-----------------Servicios----------------------------
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	//-----------------Variables InstanciaApelada ----------
	private Integer idInstanciaApelada; //Clave principal de la tabla InstanciApelada
	private String instanciaApelada; //Nombre de la InstanciaApelada
	private String nombreRecursoApelacion; //Nombre del Recurso de la InstanciaApelada
	private String descripcion; //Descripcion de la InstanciaApelada
	private Boolean estatus; //Estatus de la InstanciaApelada
	//-----------------Variables Filtro---------------------
	private String instanciaFiltro = "";
	private String recursoFiltro = "";
	private String descripcionFiltro;
	//-----------------Variables Lista----------------------
	private List<InstanciaApelada> listaInstanciaApelada; //Lista de InstanciaApelada
	private List<InstanciaMiembro> listaInstanciaMiembro; //Lista de InstanciaMiembro
	//-----------------Variables Objeto---------------------
	private InstanciaApelada instanciaApeladaseleccionada;
	private InstanciaMiembro instanciaMiembro = new InstanciaMiembro();
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@WireVariable 
	private ServicioInstanciaMiembro servicioInstanciaMiembro;
	@Wire Textbox txtcodigoInstacia;
	
	//Metodos Setters y Getters
	public String getInstanciaApelada(){
		return instanciaApelada;
	}

	public String getInstanciaFiltro() {
		return instanciaFiltro;
	}

	public void setInstanciaFiltro(String instanciaFiltro) {
		this.instanciaFiltro = instanciaFiltro;
	}

	public String getRecursoFiltro() {
		return recursoFiltro;
	}

	public void setRecursoFiltro(String recursoFiltro) {
		this.recursoFiltro = recursoFiltro;
	}

	public String getNombreRecursoApelacion() {
		return nombreRecursoApelacion;
	}

	public void setNombreRecursoApelacion(String nombreRecurso) {
		this.nombreRecursoApelacion = nombreRecurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setInstanciaApelada(String nombreInstancia){
		this.instanciaApelada = nombreInstancia;
	}

	public Integer getIdInstanciaApelada() {
		return idInstanciaApelada;
	}

	public void setIdInstanciaApelada(Integer codigoInstancia) {
		this.idInstanciaApelada = codigoInstancia;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public InstanciaApelada getInstanciaApeladaseleccionada() {
		return instanciaApeladaseleccionada;
	}
	public void setInstanciaApeladaseleccionada(InstanciaApelada instanciaApeladaseleccionada) {
		this.instanciaApeladaseleccionada = instanciaApeladaseleccionada;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}
	//Fin de los metodos setters y getters

	/**
	 * inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepción.
	 */
	@Init
	public void init(){
		//initialization code
		listadoInstancia();
	}

	/** Listado de InstaciaApelada registradas y activas 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
	}

	/** Guardar InstaciaApelada 
	 * @return nada
	 * @parameters vacío
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void guardarInstancia(){
		if (instanciaApelada == null || nombreRecursoApelacion == null || descripcion == null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			InstanciaApelada inst = new InstanciaApelada(idInstanciaApelada,descripcion,
					true,instanciaApelada,nombreRecursoApelacion);
			servicioInstanciaApelada.guardar(inst);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/**
	 * eliminar instancia : Elimina un registro físicamente.
	 * 
	 * @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void eliminarInstancia(
			@ContextParam(ContextType.BINDER) final Binder binder) {
		if (instanciaApelada == null || nombreRecursoApelacion == null || descripcion == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			//System.out.println(id_sancion);
			if ( idInstanciaApelada == 1 || idInstanciaApelada == 2 || idInstanciaApelada == 3) {
				mensajeAlUsuario.advertenciaNoPuedeEliminarRegistro();
			} else {
				Messagebox.show("¿Desea eliminar el registro realmente?",
						"Confirmar", new Messagebox.Button[] {
								Messagebox.Button.YES, Messagebox.Button.NO },
						Messagebox.QUESTION, new EventListener<ClickEvent>() {
							@SuppressWarnings("incomplete-switch")
							public void onEvent(ClickEvent e) throws Exception {
								switch (e.getButton()) {
								case YES:{
									servicioInstanciaApelada.eliminarInstancia(idInstanciaApelada);
									listaInstanciaMiembro = servicioInstanciaMiembro.buscarPorInstancia(idInstanciaApelada);
									for(int i=0;listaInstanciaMiembro.size()>i;i++){
							    		instanciaMiembro = listaInstanciaMiembro.remove(i);
										servicioInstanciaMiembro.eliminar(instanciaMiembro.getId());
									}
									mensajeAlUsuario.informacionEliminarCorrecto();
									binder.postCommand("limpiar", null);
									}
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
	 * @return Objeto Instanci aApelada seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void mostrarSeleccionada() {
		idInstanciaApelada = getInstanciaApeladaseleccionada().getIdInstanciaApelada();
		instanciaApelada = getInstanciaApeladaseleccionada().getInstanciaApelada();
		nombreRecursoApelacion = getInstanciaApeladaseleccionada().getNombreRecursoApelacion();
		descripcion = getInstanciaApeladaseleccionada().getDescripcion();
	}

	/**
	 * filtros
	 * 
	 * @param Ninguno
	 * @return instanciaFiltro, recursoFiltro
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada", "instanciaFiltro", "recursoFiltro" })
	public void filtros() {
		listaInstanciaApelada = servicioInstanciaApelada.buscarInstancia(instanciaFiltro,
				recursoFiltro);
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void limpiar(){
		idInstanciaApelada = null;
		instanciaApelada = null;
		nombreRecursoApelacion = null;
		descripcion = null;
		listadoInstancia();
	}

	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({"listaInstanciaApelada","idInstanciaApelada","instanciaApelada","nombreRecursoApelacion", "descripcion"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(instanciaApelada != null ||  nombreRecursoApelacion != null ||  descripcion != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}