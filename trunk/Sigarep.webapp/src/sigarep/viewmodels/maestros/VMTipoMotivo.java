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



import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.maestros.TipoMotivoFiltros;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMTipoMotivo {
	@WireVariable ServicioTipoMotivo serviciotipomotivo;
//	private Integer idTipoMotivo;
	private String nombreTipoMotivo,nombreTipoMotivofiltro;
	private String descripcion,descripcionfiltro;
	private Integer idTipoMotivo;
	  private mensajes mensajeAlUsuario = new mensajes();
	private Boolean estatus;
	private List<TipoMotivo> listaTipoMotivo;
	private TipoMotivo tiposeleccionado;
	private TipoMotivoFiltros filtros = new TipoMotivoFiltros();
	@Wire Textbox txtnombreTipoMotivo;
    @Wire Window winTipoMotivo;
  
	
    //Metodos set y get

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
	@NotifyChange({"idTipoMotivo","nombreTipoMotivo", "descripcion","listaTipoMotivo"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardarTipoMotivo(){
    	if (nombreTipoMotivo == null || nombreTipoMotivo.equals("") || descripcion.equals("") || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			TipoMotivo tipo = new TipoMotivo(idTipoMotivo, descripcion, true, nombreTipoMotivo, false);
			serviciotipomotivo.guardarTipoMotivo(tipo);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}
    	
 
    public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	// Método que trae todos los registros en una lista de tipo de motivos
 	@Command
 	@NotifyChange({ "listaTipoMotivo" })
 	public void listadoTipoMotivo() {
 		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
 	}	

  //Metodo que limpia  todos los campos 
    @Command
	@NotifyChange({"listaTipoMotivo","idTipoMotivo","nombreTipoMotivo", "descripcion"})
	public void limpiar(){
		nombreTipoMotivo = "";
		descripcion="";
		listadoTipoMotivo();
	}
   
  //Metodo que elimina un tipo de motivo tomando en cuenta el idTipoMotivo
  	@Command
  	@NotifyChange({"listaTipoMotivo","nombreTipoMotivo", "descripcion"})
  	public void eliminarTipoMotivo(){
  		if (nombreTipoMotivo==null || nombreTipoMotivo.equals("") || descripcion==null || descripcion.equals("") ){
  			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		}
		else{
  		serviciotipomotivo.eliminarTipoMotivo(getTiposeleccionado().getIdTipoMotivo());
  		mensajeAlUsuario.informacionEliminarCorrecto();
  		limpiar();
  	}
  	}
  	
  	
  
  	
  	
 
  	
  //permite tomar los datos del objeto tipo motivo seleccionado
    @Command
	@NotifyChange({"listaTipoMotivo","nombreTipoMotivo", "descripcion"})
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



	
//idTipoMotivo = 0;
//if (nombreTipoMotivo==null|| descripcion==null ){
//	mensajeAlUsuario.advertenciaLlenarCampos();
//}
//else{
//	listaTipoMotivo= serviciotipomotivo.buscarTodas();
//	if (listaTipoMotivo == null){
//		idTipoMotivo = 1;
//	} else {
//		System.out.print(listaTipoMotivo.size());
//		System.out.print(listaTipoMotivo.size()+1);
//		idTipoMotivo = listaTipoMotivo.size()+1;
//	}
//	TipoMotivo tipo = new TipoMotivo(idTipoMotivo, descripcion, true, nombreTipoMotivo);
//	serviciotipomotivo.guardarTipoMotivo(tipo);
//	limpiar();
//	mensajeAlUsuario.informacionRegistroCorrecto();
//}
//}