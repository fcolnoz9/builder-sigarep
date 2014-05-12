package sigarep.viewmodels.maestros;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

/** Clase VMReglamento : Clase ViewModels 
relacionada con el Maestro Reglamento.

 * @author Equipo Builder
 * @version 2.5.2
 * @since 22-/01/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReglamento {
	//-----------------Servicios----------------------------
	@WireVariable ServicioReglamento servicioreglamento;
	//-----------------Variables Reglamento-----------------
	private Integer IdDocumento; 
	private String titulo;
	private String descripcion;
	private Boolean estatus;
	private Date fechaSubida;
	private String categoria;
	private Media media;
	private String nombreDoc;
	//-----------------Variables Filtro---------------------
	private String tituloF ="";
	private String categoriaF ="";
	//-----------------Variables Lista----------------------
	private List<Reglamento> listaReglamento = new LinkedList<Reglamento>();
	//-----------------Variables Objeto---------------------
	private Reglamento reglamentoSeleccionado;
	private Documento documento = new Documento();
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	//Métodos Sets y Gets
	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public Integer getIdDocumento() {
		return IdDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		IdDocumento = idDocumento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Date getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public List<Reglamento> getListaReglamento() {
		return listaReglamento;
	}

	public void setListaReglamento(List<Reglamento> listaReglamento) {
		this.listaReglamento = listaReglamento;
	}

	@Command
	@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria", "fechaSubida", "nombreDoc"})
	public Reglamento getReglamentoSeleccionado() {
		return reglamentoSeleccionado;
	}

	public void setReglamentoSeleccionado(Reglamento reglamentoSeleccionado) {
		this.reglamentoSeleccionado = reglamentoSeleccionado;
	}

	public String getTituloF() {
		return tituloF;
	}

	public void setTituloF(String tituloF) {
		this.tituloF = tituloF;
	}

	public String getCategoriaF() {
		return categoriaF;
	}

	public void setCategoriaF(String categoriaF) {
		this.categoriaF = categoriaF;
	}
	
	//Fin Métodos Sets y Gets

	public VMReglamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init 
	public void init(){

		fechaSubida = new Date();
		media = null;
		documento = new Documento();
		tituloF ="";
		categoriaF ="";
		buscarReglamento();	
	}

	/** guardarReglamento : Guarda el registro completo, el command indica a las variables el
	 *  cambio que se hará en el objeto.
	 * 
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command 
	@NotifyChange({"IdDocumento","titulo", "descripcion","categoria","fechaSubida", "listaReglamento","nombreDoc", "documento"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es los atributos de la pantalla se va a colocar en blanco al guardar!!
	public void guardarReglamento(){
		if (titulo == null || descripcion ==null|| categoria==null )
			mensajeAlUsuario.advertenciaLlenarCampos();	

		else if (documento.getTamanoDocumento() < 1)
			mensajeAlUsuario.advertenciaCargarDocumento();
		else{
			Reglamento reg = new Reglamento(IdDocumento,documento ,titulo, descripcion,true, fechaSubida,categoria);
			try{
				servicioreglamento.guardarReglamento(reg);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			mensajeAlUsuario.informacionArchivoCargado();
			limpiar();

		}
	}

	/**eliminarReglamento
	 * 
	* @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc","documento"})
	public void eliminarReglamento(@ContextParam(ContextType.BINDER) final Binder binder){
		if (titulo == null || descripcion ==null || categoria ==null|| documento ==null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
					case YES:
						servicioreglamento.eliminar(getReglamentoSeleccionado().getIdDocumento());
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
	 * mostrarSeleccionada : Muestra en el área de datos el registro
	 * seleccionado
	 * 
	 * @param Ninguno         .
	 * @return Objeto reglamento seleccionada
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida","listaReglamento","nombreDoc","documento"})
	public void mostrarSeleccionado(){
		Reglamento reg = getReglamentoSeleccionado();
		IdDocumento = reg.getIdDocumento();
		titulo = reg.getTitulo(); 
		descripcion = reg.getDescripcion();
		categoria = reg.getCategoria();
		fechaSubida = reg.getFechaSubida();
		documento = reg.getDocumento();
		if (reg.getDocumento().getNombreDocumento()!=null){
			nombreDoc=reg.getDocumento().getNombreDocumento();
		}
	}

	/**Busca un Reglamento
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"listaReglamento"})
	public void buscarReglamento(){
		listaReglamento =servicioreglamento.listaReglamento();
	}

	/**filtros. Filtra por las variables tituloF,categoriaF 
	* @param Ninguno
	 * @return Objeto reglamento.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaReglamento","tituloF","categoriaF" })
	public void filtros() {
		listaReglamento = servicioreglamento.filtrarReglamento(tituloF,categoriaF);
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(titulo != null || descripcion !=null || categoria !=null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc", "documento"})
	public void limpiar(){
		IdDocumento = null;
		titulo = null;
		descripcion = null;
		categoria=null;
		fechaSubida= new Date();
		media = null;
		documento = new Documento();
		nombreDoc=null;
		tituloF ="";
		categoriaF ="";
		buscarReglamento();
	}

	/**cargarDocumento : Muestra Documento Seleccionado
	 * 
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({"nombreDoc","documento"})
	public void cargarDocumento(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		media = event.getMedia();
		if (media != null) {
			if (media.getContentType().equals("image/jpeg") ||
					media.getContentType().equals("application/pdf") ||
					media.getContentType().equals("application/msword") ||
					media.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
					media.getContentType().equals("application/vnd.oasis.opendocument.text") ||
					media.getContentType().equals("application/x-vnd.oasis.opendocument.text"))
			{
				documento.setNombreDocumento(media.getName());
				documento.setTipoDocumento(media.getContentType());
				documento.setContenidoDocumento(media.getByteData());

				if(documento.getTamanoDocumento()>20971520){
					mensajeAlUsuario.advertenciaTamannoArchivo(20480);

					documento=new Documento();
				}else{nombreDoc=documento.getNombreDocumento();}


			} else {
				mensajeAlUsuario.advertenciaFormatoNoSoportado();
			}
		} 
	}

	/**descargarDocumento
	 * 
	 * @param Ninguno.
	 * @return Ninguno.
     * @throws No dispara ninguna excepción
	 */
	@Command
	public void descargarDocumento(){
		Filedownload.save(documento.getContenidoDocumento(), documento.getTipoDocumento(), documento.getNombreDocumento());
	}
}

