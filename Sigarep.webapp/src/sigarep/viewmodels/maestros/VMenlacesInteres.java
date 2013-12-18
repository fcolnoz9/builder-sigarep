package sigarep.viewmodels.maestros;

import java.util.List;

import java.util.List;

import javax.swing.JOptionPane;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.EnlacesInteres;
import sigarep.modelos.servicio.maestros.ServicioEnlacesInteres;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMenlacesInteres {
	@WireVariable ServicioEnlacesInteres servicioenlacesinteres;
    private Integer id_enlace;
    private Integer id_enlaceFiltro;
	private String nombre_enlace;
	private String nombre_enlaceFiltro;
	private String direccion_enlace;
	private String direccion_enlaceFiltro;
	//private imagen;
	private String descripcion_enlace;
	private Boolean estatus;
	private List<EnlacesInteres> listaEnlaces;
	private EnlacesInteres enlaceSeleccionado;
    @Wire Textbox txtnombre_enlace;
    @Wire Window ventana;
    

	public Integer getId_enlace() {
		return id_enlace;
	}
	public void setId_enlace(Integer id_enlace) {
		this.id_enlace = id_enlace;
	}
	public String getNombre_enlace() {
		return nombre_enlace;
	}
	public void setNombre_enlace(String nombre_enlace) {
		this.nombre_enlace = nombre_enlace;
	}
	public String getDireccion_enlace() {
		return direccion_enlace;
	}
	public void setDireccion_enlace(String direccion_enlace) {
		this.direccion_enlace = direccion_enlace;
	}
	public String getDescripcion_enlace() {
		return descripcion_enlace;
	}
	public void setDescripcion_enlace(String descripcion_enlace) {
		this.descripcion_enlace = descripcion_enlace;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	//Getters y Setters filtros
	public Integer getId_enlaceFiltro() {
		return id_enlaceFiltro;
	}
	public void setId_enlaceFiltro(Integer id_enlaceFiltro) {
		this.id_enlaceFiltro = id_enlaceFiltro;
	}
	public String getNombre_enlaceFiltro() {
		return nombre_enlaceFiltro;
	}
	public void setNombre_enlaceFiltro(String nombre_enlaceFiltro) {
		this.nombre_enlaceFiltro = nombre_enlaceFiltro;
	}
	public String getDireccion_enlaceFiltro() {
		return direccion_enlaceFiltro;
	}
	public void setDireccion_enlaceFiltro(String direccion_enlaceFiltro) {
		this.direccion_enlaceFiltro = direccion_enlaceFiltro;
	}
	//Getters y Setters filtros fin
	
	public List<EnlacesInteres> getListaEnlaces() {
		return listaEnlaces;
	}
	public void setListaEnlaces(List<EnlacesInteres> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}
	public EnlacesInteres getEnlaceSeleccionado() {
		return enlaceSeleccionado;
	}
	public void setEnlaceSeleccionado(EnlacesInteres enlaceSeleccionado) {
		this.enlaceSeleccionado = enlaceSeleccionado;
	}

	
    @Init
    public void init(){
    	buscarEnlaceInteres();
    }
    
    @Command
    @NotifyChange({"id_enlace","nombre_enlace","direccion_enlace", "descripcion_enlace","estatus","listaEnlaces" })
    public void guardar(){
    	if (nombre_enlace.equals("") || direccion_enlace.equals("") || descripcion_enlace.equals(""))
    		Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
    	else{
    		EnlacesInteres enlace = new EnlacesInteres(id_enlace,nombre_enlace,direccion_enlace,descripcion_enlace,true);
    		servicioenlacesinteres.guardarEnlace(enlace);
    		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);    	}
    		limpiar();
    }
    
    @Command
    @NotifyChange({"id_enlace", "nombre_enlace","direccion_enlace", "descripcion_enlace","estatus" })
    public void limpiar(){
    	nombre_enlace= "";
    	direccion_enlace="";
        descripcion_enlace = "";
        buscarEnlaceInteres();
    }
    
    @Command
    @NotifyChange({"listaEnlaces"})
	private void buscarEnlaceInteres() {
		listaEnlaces = servicioenlacesinteres.buscarEnlacesCodigo(id_enlace);
	}
	
    @Command
	@NotifyChange({"listaEnlaces"})
    public void buscarEnlaceFiltroId(){
    	listaEnlaces = servicioenlacesinteres.buscarEnlacesCodigo(id_enlaceFiltro);
    }
    
    @Command
   	@NotifyChange({"listaEnlaces"})
       public void buscarEnlaceFiltroNombreEnlace(){
       	listaEnlaces = servicioenlacesinteres.buscarEnlacesNombre(nombre_enlaceFiltro);
       }
    
    @Command
   	@NotifyChange({"listaEnlaces"})
       public void buscarEnlaceFiltroDireccionEnlace(){
       	listaEnlaces = servicioenlacesinteres.buscarEnlacesNombre(direccion_enlaceFiltro);
       }
    
	@Command
	@NotifyChange({"listaEnlaces"})
	public void eliminarEnlaceSeleccionado(){
		servicioenlacesinteres.eliminar(id_enlace);
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	@Command
    @NotifyChange({"id_enlace", "nombre_enlace","direccion_enlace", "descripcion_enlace","estatus" })
	public void mostrarEnlace(){
		id_enlace= getEnlaceSeleccionado().getId_enlace();
		nombre_enlace = getEnlaceSeleccionado().getNombre_enlace();
		direccion_enlace = getEnlaceSeleccionado().getDireccion_enlace();
		descripcion_enlace = getEnlaceSeleccionado().getDescripcion_enlace();
	}    
}


