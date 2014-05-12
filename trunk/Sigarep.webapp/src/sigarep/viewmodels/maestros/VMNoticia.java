package sigarep.viewmodels.maestros;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.servicio.maestros.ServicioNoticia;
import java.util.LinkedList;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.bind.Binder;

/** Clase VMNoticia : Clase ViewModels 
relacionada con el Maestro Noticia
 * @author Equipo Builder
 * @version 1.0
 * @since 22/01/14
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMNoticia  {
	//-----------------Servicios----------------------------
	@WireVariable ServicioNoticia servicionoticia;
	//-----------------Variables Noticia--------------------
	private Integer idNoticia; // clave principal de la tabla Noticia
	private String contenido; // contenido de la Noticia
	private String enlaceNoticia; // enlace de la Noticia
	private Boolean estatus; //Estatus de la Noticia
	private Date fechaRegistro; // fecha de registro de la Noticia
	private Archivo fotoNoticia = new Archivo();
	private Media mediaNoticia;
	private AImage imagenNoticia;
	private Date vencimiento; // fecha de vencimiento de la Noticia
	//-----------------Variables Filtro---------------------
	private String tituloFiltro="";
	private String titulo; // titulo de la Noticia
	//-----------------Variables Lista----------------------
	private List<Noticia> listaNoticia = new LinkedList<Noticia>(); //Lista de las Noticias
	//-----------------Variables Objeto---------------------
	private Noticia noticiaSeleccionada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();//Llama a los diferentes mensajes de dialogo
	Window win=null;
	int idcount=0;

	// Metodos GETS Y SETS
	public Integer getIdNoticia() {
		return idNoticia;
	}
	public String getContenido() {
		return contenido;
	}
	public String getEnlaceNoticia() {
		return enlaceNoticia;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public String getTitulo() {
		return titulo;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public List<Noticia> getListaNoticia() {
		return listaNoticia;
	}
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public Noticia getNoticiaSeleccionada() {
		return noticiaSeleccionada;
	}
	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public void setEnlaceNoticia(String enlaceNoticia) {
		this.enlaceNoticia = enlaceNoticia;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public void setListaNoticia(List<Noticia> listaNoticia) {
		this.listaNoticia = listaNoticia;
	}
	public void setNoticiaSeleccionada(Noticia noticiaSeleccionada) {
		this.noticiaSeleccionada = noticiaSeleccionada;
	}
	public AImage getImagenNoticia() {
		return imagenNoticia;
	}

	public void setImagenNoticia(AImage imagenNoticia) {
		this.imagenNoticia = imagenNoticia;
	}
	public Archivo getFotoNoticia() {
		return fotoNoticia;
	}
	public void setFotoNoticia(Archivo fotoNoticia) {
		this.fotoNoticia = fotoNoticia;
	}

	public Media getMediaNoticia() {
		return mediaNoticia;
	}
	public void setMediaNoticia(Media mediaNoticia) {
		this.mediaNoticia = mediaNoticia;
	}

	public String getTituloFiltro() {
		return tituloFiltro;
	}
	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
	}
	
	//  Fin de los métodos set y get

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno
	 * @return código de inicialización
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	
	public void init(){
	
		mediaNoticia = null;
		fotoNoticia = new Archivo();
		buscarNoticia();
	}

	/** Guardar Noticia : Guarda el registro completo, el command indica a las variables el
	 *  cambio que se hará en el objeto.
	 *  
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"idNoticia","contenido", "enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es idNoticia, contenido, enlaceNoticia, fechaRegistro, imagenNoticia, titulo, vencimiento
	public void guardarNoticia(){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia== null )
			mensajeAlUsuario.advertenciaLlenarCampos();
		else{
			Noticia noticia = new Noticia(idNoticia, contenido, enlaceNoticia, true, fechaRegistro, fotoNoticia,titulo, vencimiento);
			servicionoticia.guardar(noticia);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/** Elimina una Noticia 
	 * 
	 * @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void eliminarNoticia(@ContextParam(ContextType.BINDER) final Binder binder){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia==null || vencimiento==null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
					case YES:
						servicionoticia.eliminar(getNoticiaSeleccionada().getIdNoticia());
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
	 * mostrarSeleccionado : Muestra en el área de datos el registro
	 * seleccionado
	 * 
	 * @param Ninguno          .
	 * @return Objeto Noticia seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia","fotoNoticia"})
	public void mostrarSeleccionado(){
		idNoticia=getNoticiaSeleccionada().getIdNoticia();
		contenido=getNoticiaSeleccionada().getContenido();
		enlaceNoticia=getNoticiaSeleccionada().getEnlaceNoticia();
		fechaRegistro=getNoticiaSeleccionada().getFechaRegistro();
		titulo=getNoticiaSeleccionada().getTitulo();
		vencimiento=getNoticiaSeleccionada().getVencimiento();
		fotoNoticia=getNoticiaSeleccionada().getFotoNoticia();

		if (noticiaSeleccionada.getFotoNoticia().getTamano() > 0){
			try {
				imagenNoticia = new AImage(noticiaSeleccionada.getFotoNoticia().getNombreArchivo(), noticiaSeleccionada.getFotoNoticia().getContenidoArchivo());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		else{
			imagenNoticia = null;}

	}

	/** mostrarSeleccionado2. Permite tomar los datos del objeto noticiaseleccionada para pasarlo a la pantalla modal, que tambien se le hace llamado.
	 *
	 *  @param Ninguno.  
	 * @return Objeto Noticia seleccionada.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"noticiaSeleccionada"})
	public void mostrarSeleccionado2(){
		noticiaSeleccionada = getNoticiaSeleccionada();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("noticiaSeleccionada", this.noticiaSeleccionada);
		if(win!=null){
			win.detach();
			noticiaSeleccionada=null;
			win.setId(null);
		}
		win= (Window) Executions.createComponents("WEB-INF/sigarep/vistas/portal/externo/modales/DetalleNoticia.zul", null, map);
		win.setMaximizable(true);
		win.doModal();
		win.setId("doModal"+""+idcount+"");

	}

	/** Busca una Noticia
	 * 
	 *  @param Ninguno  
	 * @return Objeto Noticia.
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.listadoNoticia();
	}

	/**
	 * filtros :  Método que busca y filtra por el titulo de la noticia
	 * 
	 * @param Ninguno
	 * @return Objeto noticia
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"tituloFiltro","listaNoticia"})
	public void filtros(){
		listaNoticia = servicionoticia.filtrarNoticias(tituloFiltro);
	}

	/**
	 * limpiar : Metodo que limpia todos los campos.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		idNoticia=null;
		contenido=null;
		enlaceNoticia=null;
		fechaRegistro=null;
		titulo=null;
		vencimiento=null;
		mediaNoticia = null;
		imagenNoticia = null;
		fotoNoticia = new Archivo();
		buscarNoticia();
	}

	/** cargarImagenNoticia
	 * 
	 * @param @ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event
	 * @return Ningun.
	 * @throws la Excepcion es que la media noticia sea null
	 */
	@Command
	@NotifyChange({"imagenNoticia","fotoNoticia"})
	public void cargarImagenNoticia(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		mediaNoticia = event.getMedia();
		if (mediaNoticia != null) {
			if (mediaNoticia instanceof org.zkoss.image.Image) {
				fotoNoticia.setNombreArchivo(mediaNoticia.getName());
				fotoNoticia.setTipo(mediaNoticia.getContentType());
				fotoNoticia.setContenidoArchivo(mediaNoticia.getByteData());

				if(fotoNoticia.getTamano()>500000){
					mensajeAlUsuario.advertenciaTamannoImagen(500);

					fotoNoticia = new Archivo();
				}else{imagenNoticia = (AImage) mediaNoticia;}

			} else {
				Messagebox.show("El archivo: "+mediaNoticia+" no es una imagenNoticia valida", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} 
	}

	/** mostrarMensaje
	 * 
	 * @param ContextType.TRIGGER_EVENT) UploadEvent event.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	@Command
	public void mostrarMensaje(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		mediaNoticia = event.getMedia();
		Messagebox.show("Archivo" + mediaNoticia.getName(), "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}


	/** Validación de fechas
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "fechaRegistro", "fechaCierre" })
	public void validarFecha() {
		if (fechaRegistro != null && vencimiento != null) {
			if (fechaRegistro.compareTo(vencimiento) > 0) {
				mensajeAlUsuario.errorRangoFechas();
				vencimiento = null;
			}
		}
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(titulo!=null||contenido!=null|| fechaRegistro!=null|| enlaceNoticia!= null )
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}
