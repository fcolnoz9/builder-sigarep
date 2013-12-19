package sigarep.viewmodels.maestros;

import java.util.List;

import java.util.List;

import javax.swing.JOptionPane;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
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
    @NotifyChange({"idEnlace", "nombreEnlace", "direccionEnlace", "descripcion","estatus" })
    public void limpiar(){
    	nombreEnlace= "";
    	direccionEnlace="";
        descripcion = "";
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
       	listaEnlaces = servicioenlacesinteres.buscarEnlacesNombre(direccionEnlaceFiltro);
       }
    
	@Command
	@NotifyChange({"listaEnlaces"})
	public void eliminarEnlaceSeleccionado(){
		servicioenlacesinteres.eliminar(idEnlace);
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	@Command
    @NotifyChange({"id_enlace", "nombre_enlace","direccion_enlace", "descripcion_enlace","estatus" })
	public void mostrarEnlace(){
		idEnlace= getEnlaceSeleccionado().getIdEnlace();
		nombreEnlace = getEnlaceSeleccionado().getNombreEnlace();
		direccionEnlace = getEnlaceSeleccionado().getDireccionEnlace();
		descripcion = getEnlaceSeleccionado().getDescripcion();
	}    
}


