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
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.servicio.maestros.ServicioNoticia;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMnoticia {

	@WireVariable ServicioNoticia servicionoticia;
	private Integer idNoticia;
	private String contenido,contenidoFiltro;
	private String enlaceNoticia,enlaceNoticiaFiltro;
	private Boolean estatus;
	private Date fechaRegistro;
	private byte[] imagen;
	private String titulo,tituloFiltro;
	private Date vencimiento;
	private List<Noticia> listaNoticia;
	private Noticia noticiaSeleccionada;
   
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
	public byte[] getImagen() {
		return imagen;
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
	public Noticia getNoticiaseleccionada() {
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
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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
	public void setNoticiaseleccionada(Noticia noticiaSeleccionada) {
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
	
    //Fin de los metodod gets y sets
    // OTROS METODOS
	//Metodos que perimite guardar una noticia
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarNoticia(){
		if (titulo.equals("")||contenido.equals("")|| fechaRegistro.equals("")|| enlaceNoticia.equals("") || vencimiento.equals(""))
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		//Noticia noticia = new Noticia(idNoticia, contenido, enlaceNoticia, true, fechaRegistro, imagen,titulo, vencimiento);
		//servicionoticia.guardar(noticia);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
	
	@Init
	public void init(){
        //initialization code
		buscarNoticia();
    }
	
	//Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.buscarNoticia(titulo);
	}
	//Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		Date fecha = new Date();
		contenido="";enlaceNoticia="";fechaRegistro=fecha;imagen=null;titulo="";vencimiento=fecha;
		buscarNoticia();
	}
	
	//Metodo que elimina una noticia tomando en cuenta el idNoticia
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento","listaNoticia"})
	public void eliminarNoticia(){
		servicionoticia.eliminar(getNoticiaseleccionada().getIdNoticia());
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	//permite tomar los datos del objeto noticiaseleccionada
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})
	public void mostrarSeleccionado(){
		contenido=getNoticiaseleccionada().getContenido();
		enlaceNoticia=getNoticiaseleccionada().getEnlaceNoticia();
		fechaRegistro=getNoticiaseleccionada().getFechaRegistro();
		//imagen=getNoticiaseleccionada().getImagen();
		titulo=getNoticiaseleccionada().getTitulo();
		vencimiento=getNoticiaseleccionada().getVencimiento();

	}
	
	//Este metodo busca la noticia por el filtro de titulo
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
//Fin de los otros metodos.
}
