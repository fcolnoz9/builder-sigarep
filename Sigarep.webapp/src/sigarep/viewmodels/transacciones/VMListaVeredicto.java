package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ListaVeredicto;
import sigarep.modelos.servicio.transacciones.ServicioVeredicto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaVeredicto {
	
	@WireVariable
	private ListaVeredicto listaApelacionVeredicto1;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioVeredicto servicioveredicto;
	
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ListaVeredicto> lista = new LinkedList<ListaVeredicto>();
	private String cedula="";
	private String primerNombre="";
	private String primerApellido="";
	private String programa="";
	private String sancion="";
	private String motivo="";

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public ServicioTipoMotivo getServiciotipomotivo() {
		return serviciotipomotivo;
	}

	public void setServiciotipomotivo(ServicioTipoMotivo serviciotipomotivo) {
		this.serviciotipomotivo = serviciotipomotivo;
	}

	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}

	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}

	public ServicioVeredicto getServicioveredicto() {
		return servicioveredicto;
	}

	public void setServicioveredicto(ServicioVeredicto servicioveredicto) {
		this.servicioveredicto = servicioveredicto;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}
	
	public ListaVeredicto getListaApelacionVeredicto1() {
		return listaApelacionVeredicto1;
	}

	public void setListaApelacionVeredicto1(
			ListaVeredicto listaApelacionVeredicto1) {
		this.listaApelacionVeredicto1 = listaApelacionVeredicto1;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public List<ListaVeredicto> getLista() {
		return lista;
	}

	public void setLista(List<ListaVeredicto> lista) {
		this.lista = lista;
	}

	
	@Init
    public void init(){
    	buscarTipoMotivo();
    	buscarProgramaA ();
    	buscarApelacionesVeredicto1();
    }
	
	@Command
	@NotifyChange({"cedula", "nombre", "apellido", "sancion"})
	public void limpiar(){
		cedula=""; primerNombre=""; primerApellido=""; sancion="";
	}
	
    //Metodo que busca un motivo partiendo por su titulo
  	@Command
  	@NotifyChange({"listaTipoMotivo"})
  	public void buscarTipoMotivo(){
  		listaTipoMotivo = serviciotipomotivo.buscarP(nombreTipoMotivo);
  	}
  	
  	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPr(programa);
	}
  	
  	@Command
	@NotifyChange({"lista"})
	public void buscarApelacionesVeredicto1(){
	  	lista = servicioveredicto.buscarApelacionesVeredicto1();
	}
  	
  	@Command
	public void showModal (){
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		map.put("cedula", listaApelacionVeredicto1.getCedulaEstudiante());
  		map.put("primerNombre", listaApelacionVeredicto1.getPrimerNombre());
  		map.put("primerApellido", listaApelacionVeredicto1.getPrimerApellido());
  		map.put("segundoNombre",  listaApelacionVeredicto1.getSegundoNombre());
  		map.put("segundoApellido", listaApelacionVeredicto1.getSegundoApellido());
  		map.put("programa", 	listaApelacionVeredicto1.getPrograma());
  		map.put("email", listaApelacionVeredicto1.getEmail());
  		map.put("indice", listaApelacionVeredicto1.getIndice());
  		map.put("lapso", listaApelacionVeredicto1.getCodigoLapso());
  		map.put("sancion", listaApelacionVeredicto1.getNombreSancion());
  		map.put("instancia", listaApelacionVeredicto1.getInstancia());
  		map.put("caso", listaApelacionVeredicto1.getNumeroCaso());
        	        
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/Veredicto.zul", null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
  	
  	@Command
	@NotifyChange({"lista"})
	public void filtros(){
		lista = servicioveredicto.filtrarApelacionesVeredicto1(cedula, primerNombre, primerApellido, programa, sancion);
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
