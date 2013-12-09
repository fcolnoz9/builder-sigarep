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



import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;


@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMTipoMotivo {
	@WireVariable ServicioTipoMotivo serviciotipomotivo;
	private Integer idTipoMotivo;private String nombreTipoMotivo;private String descripcion;private Boolean estatus;
	private List<TipoMotivo> listaTipoMotivo;
	private TipoMotivo tiposeleccionado;
    @Wire Textbox txtnombreTipoMotivo;
    @Wire Window ventana;
	
    public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
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
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	public TipoMotivo getTiposeleccionado() {
		return tiposeleccionado;
	}
	public void setTiposeleccionado(TipoMotivo tiposeleccionado) {
		this.tiposeleccionado = tiposeleccionado;
	}
	
    @Init
    public void init(){
    	
    }
    
    @Command
	@NotifyChange({"nombreTipoMotivo", "descripcion","listaTipoMotivo"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardar(){
		if (nombreTipoMotivo.equals("")||descripcion.equals("")){
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else{
		TipoMotivo motivo = new TipoMotivo(idTipoMotivo,nombreTipoMotivo, descripcion,true);
		serviciotipomotivo.guardar(motivo);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
    
    @Command
	@NotifyChange({"nombreTipoMotivo", "descripcion"})
	public void limpiar(){
		nombreTipoMotivo = "";descripcion="";
	}

    @Command
	@NotifyChange({"listaTipoMotivo"})
	public void buscarTipoMotivo(){
    	listaTipoMotivo =serviciotipomotivo.buscarP(nombreTipoMotivo);
	}
    
    @Command
	@NotifyChange({"idTipoMotivo","nombreTipoMotivo", "Descripcion"})
	public void mostrarSeleccionado(){
    	idTipoMotivo= getTiposeleccionado().getIdTipoMotivo();
		nombreTipoMotivo= getTiposeleccionado().getNombreTipoMotivo();
		descripcion= getTiposeleccionado().getDescripcion();
	}
    
    
}
