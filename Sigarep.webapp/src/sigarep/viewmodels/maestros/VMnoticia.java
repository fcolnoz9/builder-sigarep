package sigarep.viewmodels.maestros;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sigarep.herramientas.Archivo;

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
import sigarep.modelos.servicio.maestros.ServicioNoticia;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMnoticia {
	@WireVariable ServicioNoticia servicionoticia;
	private Integer idNoticia,idNoticiaFiltro;
	private String contenido,contenidoFiltro;
	private String enlaceNoticia,enlaceNoticiaFiltro;
	private Boolean estatus;
	private Date fechaRegistro; 
	private Archivo fotoNoticia = new Archivo();
	private Media mediaNoticia;
	private AImage imagenNoticia;
	private String titulo,tituloFiltro;
	private Date vencimiento;
	private List<Noticia> listaNoticia;
	private Noticia noticiaSeleccionada;
	
	private NoticiaFiltro filtros = new NoticiaFiltro();
	
@NotifyChange({"filtros"})
public NoticiaFiltro getFiltros() {
		return filtros;
	}
@NotifyChange({"filtros"})
	public void setFiltros(NoticiaFiltro filtros) {
		this.filtros = filtros;
	}
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
	public String getContenidoFiltro() {
		return contenidoFiltro;
	}
	public String getEnlaceNoticiaFiltro() {
		return enlaceNoticiaFiltro;
	}
	public String getTituloFiltro() {
		return tituloFiltro;
	}
	public void setContenidoFiltro(String contenidoFiltro) {
		this.contenidoFiltro = contenidoFiltro;
	}
	public void setEnlaceNoticiaFiltro(String enlaceNoticiaFiltro) {
		this.enlaceNoticiaFiltro = enlaceNoticiaFiltro;
	}
	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
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

	public Integer getIdNoticiaFiltro() {
		return idNoticiaFiltro;
	}
	public void setIdNoticiaFiltro(Integer idNoticiaFiltro) {
		this.idNoticiaFiltro = idNoticiaFiltro;
	}
	
	public Media getMediaNoticia() {
		return mediaNoticia;
	}
	public void setMediaNoticia(Media mediaNoticia) {
		this.mediaNoticia = mediaNoticia;
	}
    //Fin de los metodod gets y sets
    // OTROS METODOS
	//Metodos que perimite guardar una noticia
	@Command
	@NotifyChange({"idNoticia","contenido", "enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarNoticia(){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia==null || vencimiento==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
			System.out.println(fotoNoticia.getTamano());
			System.out.println(contenido);
		Noticia noticia = new Noticia(idNoticia, contenido, enlaceNoticia, true, fechaRegistro, fotoNoticia,titulo, vencimiento);
		servicionoticia.guardar(noticia);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
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
	
	//Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.listadoNoticia();
		//listaNoticia =servicionoticia.buscarNoticia(titulo);
	}
	//Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({"contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		Date fecha = new Date();
		contenido="";
		enlaceNoticia="";
		fechaRegistro=fecha;
		titulo="";
		vencimiento=fecha;
		mediaNoticia = null;
		imagenNoticia = null;
		fotoNoticia = new Archivo();
		buscarNoticia();
	}
	
	//Metodo que elimina una noticia tomando en cuenta el idNoticia
	@Command
	@NotifyChange({"idNoticia","contenido","enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public void eliminarNoticia(){
		if (titulo==null||contenido==null|| fechaRegistro==null|| enlaceNoticia==null || vencimiento==null)
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		servicionoticia.eliminar(getNoticiaSeleccionada().getIdNoticia());
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	  }
	}
	//permite tomar los datos del objeto noticiaseleccionada
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
	
	//Este metodo busca la noticia por el filtro de titulo
		@Command
		@NotifyChange({"listaNoticia"})
		public void buscarNoticiaFiltroIdNoticia(){
			listaNoticia =servicionoticia.buscarn(idNoticiaFiltro);
		}
		@Command
		@NotifyChange({"listaNoticia"})
		public void buscarNoticiaFiltroTitulo(){
			listaNoticia =servicionoticia.buscarNoticia(tituloFiltro);
		}
		//Este metodo busca la noticia por el filtro de contenido
		@Command
		@NotifyChange({"listaNoticia"})
		public void buscarNoticiaFiltroContenido(){
			listaNoticia =servicionoticia.buscarNoticia(contenidoFiltro);
		}
		//Este metodo busca la noticia por el filtro de enlace
		@Command
		@NotifyChange({"listaNoticia"})
		public void buscarNoticiaFiltroEnlaceNoticia(){
			listaNoticia =servicionoticia.buscarNoticia(enlaceNoticiaFiltro);
		}
		
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
				//	Messagebox.show("Archivo: " + imagenNoticia.getHeight(), "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				} else {
					Messagebox.show("El archivo: "+mediaNoticia+" no es una imagenNoticia valida", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} 
		}
		
		@Command
		public void mostrarMensaje(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
			mediaNoticia = event.getMedia();
			Messagebox.show("Archivo" + mediaNoticia.getName(), "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		}
		
		@Command
		@NotifyChange({"listaNoticia"})
		public void filtroNoticia(){
			listaNoticia =servicionoticia.buscarNoticias(filtros);
		}
		
		//Permite tomar los datos del objeto noticiaseleccionada para pasarlo a la pantalla modal, que tambien se le hace llamado. Hecho por: José Galíndez
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
	        //map.put("contenido", this.contenido );
	        //map.put("enlaceNoticia", this.enlaceNoticia);
	        //map.put("titulo", this.titulo);
	        map.put("noticiaSeleccionada", this.noticiaSeleccionada);
	        final Window win = (Window) Executions.createComponents(
					"/Modal/ModalNoticia.zul", null, map);
			win.setMaximizable(true);
			win.doModal();

		}
		@Command
		@NotifyChange({"listaNoticia"})
		public void reordenarLista(){
			System.out.println("pase por reeordenar");
			Noticia nitic = listaNoticia.remove(0);
			System.out.println(nitic.getTitulo());
			System.out.println(listaNoticia.size());
			System.out.println(listaNoticia.size());
			listaNoticia.add(nitic);
			System.out.println(listaNoticia.size());
			nitic = listaNoticia.get((listaNoticia.size()-1));
			System.out.println(nitic.getTitulo());
			
		}

//Fin de los otros metodos.
}
