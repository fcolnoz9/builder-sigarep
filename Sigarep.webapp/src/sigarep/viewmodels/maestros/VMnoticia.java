package sigarep.viewmodels.maestros;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.data.maestros.NoticiaFiltro;
import sigarep.modelos.lista.ListaGenericaSancionados;
import sigarep.modelos.servicio.maestros.ServicioNoticia;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.BindingParam;


import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Timer;



import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.Component;

/**Noticia
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMnoticia extends SelectorComposer<Component>  {

	@WireVariable ServicioNoticia servicionoticia;
	private Integer idNoticia;
	private String contenido;
	private String enlaceNoticia;
	private Boolean estatus; //Estatus de la Noticia
	private Date fechaRegistro; 
	private Archivo fotoNoticia = new Archivo();
	private Media mediaNoticia;
	private AImage imagenNoticia;
	private String titulof="";
	private String titulo;
	private Date vencimiento;
	private List<Noticia> listaNoticia = new LinkedList<Noticia>(); //Lista de las Noticias
	private Noticia noticiaSeleccionada;
	MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();//Llama a los diferentes mensajes de dialogo

	private @Wire Listbox lbxNoticias;

	// Metodos GETS Y SETS
	public String getTitulof() {
		return titulof;
	}
	public void setTitulof(String titulof) {
		this.titulof = titulof;
	}
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

	// OTROS METODOS
	/** guardarNoticia
	 * @param idNoticia,contenido, enlaceNoticia, fechaRegistro, imagenNoticia, titulo, vencimiento, listaNoticia.
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones son que se quiera registrar una Noticia y haya campos en blanco.
	 */
	@Command
	@NotifyChange({"idNoticia","contenido", "enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarNoticia(){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia=="" || titulo=="" || contenido==""|| enlaceNoticia=="")
			mensajesAlUsuario.advertenciaLlenarCampos();
		else{
			Noticia noticia = new Noticia(idNoticia, contenido, enlaceNoticia, true, fechaRegistro, fotoNoticia,titulo, vencimiento);
			servicionoticia.guardar(noticia);
			mensajesAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	@Init
	public void init(){
		//initialization code
		mediaNoticia = null;
		fotoNoticia = new Archivo();
		buscarNoticia();
	}

	/** buscarNoticia
	 * @param listaNoticia cargada con  las noticias.
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.listadoNoticia();
	}

	/** limpiar
	 * @param idNoticia,contenido, enlaceNoticia, fechaRegistro, imagenNoticia, titulo, vencimiento, listaNoticia.
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		Date fecha = new Date();
		idNoticia=null;
		contenido="";
		enlaceNoticia="";
		fechaRegistro=null;
		titulo="";
		vencimiento=null;
		mediaNoticia = null;
		imagenNoticia = null;
		fotoNoticia = new Archivo();
		buscarNoticia();
	}

	/** eliminarNoticia
	 * @param idNoticia,contenido, enlaceNoticia, fechaRegistro, imagenNoticia, titulo, vencimiento, listaNoticia.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que quiera eliminar con los campos vacion, sin seleccionar ningun registro
	 */
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void eliminarNoticia(){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia==null || vencimiento==null)
			mensajesAlUsuario.advertenciaLlenarCampos();
		else{
			servicionoticia.eliminar(getNoticiaSeleccionada().getIdNoticia());
			limpiar();
			mensajesAlUsuario.informacionEliminarCorrecto();
		}
	}
	/** mostrarSeleccionado
	 * @param idNoticia,contenido, enlaceNoticia, fechaRegistro, imagenNoticia, titulo, vencimiento, listaNoticia.
	 * @return No devuelve ningun valor.
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

	/** cargarImagenNoticia
	 * @param imagenNoticia, UploadEvent event Zkoss UI.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que la media noticia sea null
	 */
	@Command
	@NotifyChange("imagenNoticia")
	public void cargarImagenNoticia(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		mediaNoticia = event.getMedia();
		if (mediaNoticia != null) {
			if (mediaNoticia instanceof org.zkoss.image.Image) {
				fotoNoticia.setNombreArchivo(mediaNoticia.getName());
				fotoNoticia.setTipo(mediaNoticia.getContentType());
				fotoNoticia.setContenidoArchivo(mediaNoticia.getByteData());
				imagenNoticia = (AImage) mediaNoticia;
			} else {
				Messagebox.show("El archivo: "+mediaNoticia+" no es una imagenNoticia valida", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} 
	}

	/** mostrarMensaje
	 * @param UploadEvent event Zkoss UI.
	 * @return No devuelve ningun valor.
	 */
	@Command
	public void mostrarMensaje(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		mediaNoticia = event.getMedia();
		Messagebox.show("Archivo" + mediaNoticia.getName(), "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}

	/** mostrarSeleccionado2. Permite tomar los datos del objeto noticiaseleccionada para pasarlo a la pantalla modal, que tambien se le hace llamado. José Galíndez
	 * @param contenido, enlaceNoticia, fechaRegistro, imagen, titulo, vencimiento, listaNoticia,fotoNoticia. 
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia","fotoNoticia"})
	public void mostrarSeleccionado2(){
		idNoticia=getNoticiaSeleccionada().getIdNoticia();
		contenido=getNoticiaSeleccionada().getContenido();
		enlaceNoticia=getNoticiaSeleccionada().getEnlaceNoticia();
		fechaRegistro=getNoticiaSeleccionada().getFechaRegistro();
		titulo=getNoticiaSeleccionada().getTitulo();
		vencimiento=getNoticiaSeleccionada().getVencimiento();
		fotoNoticia=getNoticiaSeleccionada().getFotoNoticia();

		noticiaSeleccionada = getNoticiaSeleccionada();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("noticiaSeleccionada", this.noticiaSeleccionada);
		final Window win = (Window) Executions.createComponents(
				"WEB-INF/sigarep/vistas/portal/externo/modales/DetalleNoticia.zul", null, map);
		win.setMaximizable(true);
		win.doModal();

	}

	/** reordenarLista.Metodo que reordena la lista
	 * @param listaNoticia cargada con las noticias. 
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"listaNoticia"})
	public void reordenarLista(List<Noticia> listaNoticia){		

		if(listaNoticia.size() > 2){
			Noticia nitic = listaNoticia.remove(0);
			listaNoticia.add(nitic);
			lbxNoticias.setModel(new ListModelList<Noticia>(listaNoticia));
		}//else{System.out.println("hay menos de 3 elementos en la lista");}

	}

	/** hacer. Maneja el timer de la  vista , se encarga de actualizar la lista cada 5 segundos
	 * @param onTimer. 
	 * @return No devuelve ningun valor.
	 */
	@Listen("onTimer = #tiempo")
	public void hacer(){
		reordenarLista(getListaNoticia());
	}

	/** doAfterCompose.
	 * @param comp. 
	 * @return No devuelve ningun valor.
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		buscarNoticia();		
	}
	/** filtros. Filtra por la variable titulo
	 * @param titulof,listaNoticia
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"titulof","listaNoticia"})
	public void filtros(){
		listaNoticia = servicionoticia.filtrarApelacionesCargarRecaudo(titulof);
	}
	
	@Command
	@NotifyChange({ "fechaRegistro", "fechaCierre" })
	public void validarFecha() {
		if (fechaRegistro != null && vencimiento != null) {
			if (fechaRegistro.compareTo(vencimiento) > 0) {
				mensajesAlUsuario.ErrorRangoFechas();
				vencimiento = null;
			}
		}
	}

}
