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
import sigarep.modelos.data.maestros.TipoMotivoFiltros;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;


@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMTipoMotivo {
	@WireVariable ServicioTipoMotivo serviciotipomotivo;
//	private Integer idTipoMotivo;
	private String nombreTipoMotivo,nombreTipoMotivofiltro;
	private String descripcion,descripcionfiltro;
	private Boolean estatus;
	private List<TipoMotivo> listaTipoMotivo;
	private TipoMotivo tiposeleccionado;
	private TipoMotivoFiltros filtros = new TipoMotivoFiltros();
	@Wire Textbox txtnombreTipoMotivo;
    @Wire Window winTipoMotivo;
	
    //Metodos set y get
//    public Integer getIdTipoMotivo() {
//		return idTipoMotivo;
//	}
    public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
    public String getDescripcion() {
		return descripcion;
	}
    public Boolean getEstatus() {
		return estatus;
	}
    public String getNombreTipoMotivofiltro() {
		return nombreTipoMotivofiltro;
	}
    public String getDescripcionfiltro() {
		return descripcionfiltro;
	}
    public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
    public TipoMotivo getTiposeleccionado() {
		return tiposeleccionado;
	}
	public void setNombreTipoMotivofiltro(String nombreTipoMotivofiltro) {
		this.nombreTipoMotivofiltro = nombreTipoMotivofiltro;
	}
	
	public void setDescripcionfiltro(String descripcionfiltro) {
		this.descripcionfiltro = descripcionfiltro;
	}
	public TipoMotivoFiltros getFiltros() {
		return filtros;
	}

	public void setFiltros(TipoMotivoFiltros filtros) {
		this.filtros = filtros;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	public void setTiposeleccionado(TipoMotivo tiposeleccionado) {
		this.tiposeleccionado = tiposeleccionado;
	}
	//Fin de los metodod gets y sets
   
	//----------- OTROS METODOS
    @Init
    public void init(){
      	listadoTipoMotivo();
      	
    }
    
    //Metodos que Permite guardar los tipos de motivos
    @Command
	@NotifyChange({"nombreTipoMotivo", "descripcion","listaTipoMotivo"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarTipoMotivo(){
		if (nombreTipoMotivo== null|| descripcion == null ){
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else{
		TipoMotivo motivo = new TipoMotivo(nombreTipoMotivo, descripcion,true);
		serviciotipomotivo.guardarTipoMotivo(motivo);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
  //Metodo que limpia  todos los campos 
    @Command
	@NotifyChange({"nombreTipoMotivo", "descripcion"})
	public void limpiar(){
		nombreTipoMotivo = "";
		descripcion="";
		listadoTipoMotivo();
	}
    // Método que trae todos los registros en una lista de tipo de motivos
 	@Command
 	@NotifyChange({ "listaTipoMotivo" })
 	public void listadoTipoMotivo() {
 		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
 	}
  //Metodo que elimina un tipo de motivo tomando en cuenta el idTipoMotivo
  	@Command
  	@NotifyChange({"nombreTipoMotivo", "descripcion", "listaTipoMotivo"})
  	public void eliminarTipoMotivo(){
  		if (nombreTipoMotivo== null|| descripcion == null ){
			Messagebox.show("Debes Seleccionar un motivo", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else{
  		serviciotipomotivo.eliminarTipoMotivo(getTiposeleccionado().getIdTipoMotivo());
  		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
  		limpiar();
  	}
  	}	
  //permite tomar los datos del objeto tipo motivo seleccionado
    @Command
	@NotifyChange({"nombreTipoMotivo", "descripcion"})
	public void mostrarSeleccionado(){
		nombreTipoMotivo= getTiposeleccionado().getNombreTipoMotivo();
		descripcion=getTiposeleccionado().getDescripcion();	
	}
    // Método que busca y filtra las sanciones
 	@Command
 	@NotifyChange({ "listaTipoMotivo" })
 	public void filtros() {
 		listaTipoMotivo = serviciotipomotivo.buscarTipoMotivo(filtros);
 	}
}
