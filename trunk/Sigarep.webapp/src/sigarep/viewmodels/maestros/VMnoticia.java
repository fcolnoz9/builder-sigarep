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
	private Integer idNoticia;private String contenido;private String enlaceNoticia;private Boolean estatus;private Date fechaRegistro;private byte[] imagen;private String titulo;private Date vencimiento;
	private List<Noticia> listaNoticia;
	private Noticia noticiaseleccionada;
   
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
		return noticiaseleccionada;
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

	public void setNoticiaseleccionada(Noticia noticiaseleccionada) {
		this.noticiaseleccionada = noticiaseleccionada;
	}
    
    // METODOS
	
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarNoticia(){
		if (titulo.equals("")||contenido.equals("")|| fechaRegistro.equals("")|| enlaceNoticia.equals("") || vencimiento.equals(""))
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		Noticia noticia = new Noticia(idNoticia, contenido, enlaceNoticia, true, fechaRegistro, imagen,titulo, vencimiento);
		servicionoticia.guardar(noticia);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
	
	@Init
	public void init(){
        //initialization code
		buscarNoticia();
    }
	
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.buscarNoticia(titulo);
	}
  

	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})
	public void limpiar(){
		Date fecha = new Date();
		contenido="";enlaceNoticia="";fechaRegistro=fecha;imagen=null;titulo="";vencimiento=fecha;
		buscarNoticia();
	}

	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento","listaNoticia"})
	public void eliminarNoticia(){
		servicionoticia.eliminar(getNoticiaseleccionada().getIdNoticia());
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagen", "titulo", "vencimiento", "listaNoticia"})
	public void mostrarSeleccionado(){
		contenido=getNoticiaseleccionada().getContenido();

		enlaceNoticia=getNoticiaseleccionada().getEnlaceNoticia();

		fechaRegistro=getNoticiaseleccionada().getFechaRegistro();

		imagen=getNoticiaseleccionada().getImagen();

		titulo=getNoticiaseleccionada().getTitulo();

		vencimiento=getNoticiaseleccionada().getVencimiento();

	}
	
}
