package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.servicio.maestros.ServicioGrupo;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

public class VMGrupo {
	@WireVariable ServicioGrupo serviciogrupo;
	private Integer idgrupo;
	private String nombre;
	private String descripcion;
	private boolean estatus;
	mensajes msjs = new mensajes();
	
	private List<Grupo> listaGrupo = new LinkedList<Grupo>();
	private Grupo gruposeleccionado;
	
	//@wire //elementos de la vista
	public Integer getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public List<Grupo> getListaGrupo() {
		return listaGrupo;
	}
	public void setListaGrupo(List<Grupo> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}
	
	public Grupo getGruposeleccionado() {
		return gruposeleccionado;
	}
	public void setGruposeleccionado(Grupo gruposeleccionado) {
		this.gruposeleccionado = gruposeleccionado;
	}
	
	@Init
    public void init(){
    	 //initialization code
    	buscarGrupo();
    }
	
	@Command
  	@NotifyChange({"listaGrupo"})
  	public void buscarGrupo(){
		listaGrupo = serviciogrupo.listadoGrupo();
  	}
    
    //Metodos que perimite guardar los s de motivos
    @Command
	@NotifyChange({"nombre", "descripcion","listaGrupo"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarGrupo(){
		if (nombre.equals("")||descripcion.equals("")){
			msjs.advertenciaLlenarCampos();
		}
		else{
			Grupo grupo = new Grupo(descripcion,true,nombre);
			serviciogrupo.guardar(grupo);
			msjs.informacionRegistroCorrecto();
			limpiarGrupo();
		}
	}
  //Metodo que busca un  de motivo 
    @Command
	@NotifyChange({"nombre", "descripcion","listaGrupo","listaGrupo"})
	public void limpiarGrupo(){
    	idgrupo = 0;
    	nombre = "";
		descripcion="";
		buscarGrupo();
	}
 
  
  	@Command
  	@NotifyChange({"nombre", "descripcion", "listaGrupo"})
  	public void eliminarGrupo(){
  		serviciogrupo.eliminar(getGruposeleccionado().getIdGrupo());
  		limpiarGrupo();
  		msjs.informacionEliminarCorrecto();  		
  	}
  	
  //permite tomar los datos del objeto  motivo seleccionado
    @Command
	@NotifyChange({"nombre", "descripcion","listaGrupo"})
	public void mostrarGrupoSeleccionado(){
    	Grupo grupo = getGruposeleccionado();
		nombre= grupo.getNombre();
		descripcion= grupo.getDescripcion();
	}
}
