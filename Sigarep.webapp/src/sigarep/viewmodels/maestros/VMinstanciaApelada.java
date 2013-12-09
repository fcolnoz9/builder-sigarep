package sigarep.viewmodels.maestros;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMinstanciaApelada {
	@WireVariable ServicioInstanciaApelada spp;
	private Integer codigoInstancia;
	private String nombreInstancia;
	private String nombreRecurso;
	private String descripcion;
	private Boolean estatus;
	private List<InstanciaApelada> listaInstanciaApelada;
	private InstanciaApelada instanciaApeladaseleccionada;
    @Wire Textbox txtcodigoInstacia;
    @Wire Window ventana;
    
    public String getNombreInstancia(){
    	return nombreInstancia;
    }
    
    public void setNombreInstancia(String nombreInstancia){
    	this.nombreInstancia = nombreInstancia;
    }
    
    public Integer getCodigoInstancia() {
		return codigoInstancia;
	}
	
    public void setCodigoInstancia(Integer codigoInstancia) {
		this.codigoInstancia = codigoInstancia;
	}
	
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<InstanciaApelada> getInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	@Command
	@NotifyChange({"codigoInstancia", "nombreInstancia", "nombreRecurso", "descripcion", "estatus"})
	public InstanciaApelada getInstanciaApeladaseleccionada() {
		return instanciaApeladaseleccionada;
	}
	public void setInstanciaApeladaseleccionada(InstanciaApelada instanciaApeladaseleccionada) {
		this.instanciaApeladaseleccionada = instanciaApeladaseleccionada;
	}
	@Init
	public void init(){
        //initialization code
		buscarInstanciaApelada();
    }
	@Command
	@NotifyChange({"codigoInstancia", "nombreInstancia", "nombreRecurso", "descripcion"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void guardar(){
		if (codigoInstancia.equals("")||nombreInstancia.equals("")|| nombreRecurso.equals("")|| descripcion.equals(""))
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		InstanciaApelada pro = new InstanciaApelada(codigoInstancia,descripcion,estatus,nombreInstancia,nombreRecurso);
		spp.guardar(pro);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
	@Command
	@NotifyChange({"codigoIntancia", "nombreInstancia", "nombreRecurso","descripcion"})
	public void limpiar(){
		codigoInstancia = 0;//fechaInicio="";fechaCierre="";
		buscarInstanciaApelada();
	}
	@Command
	@NotifyChange({"listaInstanciaApelada"})
	public void buscarInstanciaApelada(){
		listaInstanciaApelada =spp.buscarP(codigoInstancia);
	}
	@Command
	@NotifyChange({"codigoInstancia", "nombreInstancia", "nombreRecurso","descripcion"})
	public void mostrarSeleccionado(){
		InstanciaApelada pro = getInstanciaApeladaseleccionada();
		codigoInstancia = pro.getIdInstanciaApelada();
		nombreInstancia = pro.getInstanciaApelada();
	    nombreRecurso = pro.getNombreRecursoApelacion();
	    descripcion = pro.getDescripcion();
	}	
}