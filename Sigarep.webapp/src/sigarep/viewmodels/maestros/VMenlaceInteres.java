package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.List;

import java.util.List;

import javax.swing.JOptionPane;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.servicio.maestros.ServicioEnlaceInteres;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMenlaceInteres {
	@WireVariable ServicioEnlaceInteres servicioenlacesinteres;
    private Integer idEnlace;
    private Integer idEnlaceFiltro;
	private String nombreEnlace;
	private String nombreEnlaceFiltro;
	private String direccionEnlace;
	private String direccionEnlaceFiltro;
	private String descripcion;
	private Boolean estatus;
	private Archivo imagen = new Archivo();
	private AImage imagenes;
	private Media media;
	private List<EnlaceInteres> listaEnlaces;
	private EnlaceInteres enlaceSeleccionado;
    @Wire Textbox txtnombre_enlace;
    @Wire Window ventana;
    

	public Integer getIdEnlace() {
		return idEnlace;
	}
	public void setIdEnlace(Integer idEnlace) {
		this.idEnlace = idEnlace;
	}
	public Integer getIdEnlaceFiltro() {
		return idEnlaceFiltro;
	}
	public void setIdEnlaceFiltro(Integer idEnlaceFiltro) {
		this.idEnlaceFiltro = idEnlaceFiltro;
	}
	public String getNombreEnlace() {
		return nombreEnlace;
	}
	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}
	public String getNombreEnlaceFiltro() {
		return nombreEnlaceFiltro;
	}
	public void setNombreEnlaceFiltro(String nombreEnlaceFiltro) {
		this.nombreEnlaceFiltro = nombreEnlaceFiltro;
	}
	public String getDireccionEnlace() {
		return direccionEnlace;
	}
	public void setDireccionEnlace(String direccionEnlace) {
		this.direccionEnlace = direccionEnlace;
	}
	public String getDireccionEnlaceFiltro() {
		return direccionEnlaceFiltro;
	}
	public void setDireccionEnlaceFiltro(String direccionEnlaceFiltro) {
		this.direccionEnlaceFiltro = direccionEnlaceFiltro;
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
	public Archivo getImagen() {
		return imagen;
	}
	public void setImagen(Archivo imagen) {
		this.imagen = imagen;
	}

	
	public AImage getImagenes() {
		return imagenes;
	}
	public void setImagenes(AImage imagenes) {
		this.imagenes = imagenes;
		
	}
	public List<EnlaceInteres> getListaEnlaces() {
		return listaEnlaces;
	}
	public void setListaEnlaces(List<EnlaceInteres> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}
	public EnlaceInteres getEnlaceSeleccionado() {
		return enlaceSeleccionado;
	}
	public void setEnlaceSeleccionado(EnlaceInteres enlaceSeleccionado) {
		this.enlaceSeleccionado = enlaceSeleccionado;
	}

	
    @Init
    public void init(){
    	imagen = new Archivo();
    	buscarEnlaceInteres();
    }
    
    @Command
    @NotifyChange({"idEnlace", "nombreEnlace", "direccionEnlace", "descripcion","estatus", "imagenes","listaEnlaces" })
    public void guardar(){
    	if (nombreEnlace.equals("") || direccionEnlace.equals("") || descripcion.equals(""))
    		Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
    	else{
    		EnlaceInteres enlace = new EnlaceInteres(idEnlace, nombreEnlace, direccionEnlace, descripcion, true, imagen);
    		servicioenlacesinteres.guardarEnlace(enlace);
    		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);    	}
    		limpiar();
    }
    
    @Command
	@NotifyChange("imagenes")
	public void cargarImagen(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		media = event.getMedia();
		if (media != null) {
			if (media instanceof org.zkoss.image.Image) {
				imagen.setNombreArchivo(media.getName());
				imagen.setTipo(media.getContentType());
				imagen.setContenidoArchivo(media.getByteData());
		
				imagenes = (AImage) media;
			} else {
				Messagebox.show("El archivo: "+media+" no es una imagen valida", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} 
	}
    
    @Command
    @NotifyChange({"idEnlace", "nombreEnlace", "direccionEnlace", "descripcion","estatus","imagenes" })
    public void limpiar(){
    	nombreEnlace= "";
    	direccionEnlace="";
        descripcion = "";
        idEnlace = null;
        media = null;
		imagenes = null;
		imagen = new Archivo();
        buscarEnlaceInteres();
    }
    
    @Command
    @NotifyChange({"listaEnlaces"})
	private void buscarEnlaceInteres() {
		listaEnlaces = servicioenlacesinteres.buscarEnlacesCodigo(idEnlace);
	}
	
    @Command
	@NotifyChange({"listaEnlaces"})
    public void buscarEnlaceFiltroId(){
    	listaEnlaces = servicioenlacesinteres.buscarEnlacesCodigo(idEnlaceFiltro);
    }
    
    @Command
   	@NotifyChange({"listaEnlaces"})
       public void buscarEnlaceFiltroNombreEnlace(){
       	listaEnlaces = servicioenlacesinteres.buscarEnlacesNombre(nombreEnlaceFiltro);
       }
    
    @Command
   	@NotifyChange({"listaEnlaces"})
       public void buscarEnlaceFiltroDireccionEnlace(){
       	listaEnlaces = servicioenlacesinteres.buscarEnlacesDireccion(direccionEnlaceFiltro);
       }
    
	@Command
	@NotifyChange({"listaEnlaces"})
	public void eliminarEnlaceSeleccionado(){
		servicioenlacesinteres.eliminar(idEnlace);
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	@Command
    @NotifyChange({"idEnlace", "nombreEnlace","direccionEnlace", "descripcion","estatus", "imagenes" })
	public void mostrarEnlace(){
		idEnlace= enlaceSeleccionado.getIdEnlace();
		nombreEnlace = enlaceSeleccionado.getNombreEnlace();
		direccionEnlace = enlaceSeleccionado.getDireccionEnlace();
		descripcion = enlaceSeleccionado.getDescripcion();
		if (enlaceSeleccionado.getImagen().getTamano() > 0){
			try {
				imagenes = new AImage(enlaceSeleccionado.getImagen().getNombreArchivo(), enlaceSeleccionado.getImagen().getContenidoArchivo());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		else
			imagenes = null;
	}    
}


