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
import sigarep.modelos.servicio.transacciones.ListaCargarRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaCargarRecaudoEntregado {

	@WireVariable
	private ListaCargarRecaudoEntregado listaEstudiantesCargarRecaudos;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ListaCargarRecaudoEntregado> lista = new LinkedList<ListaCargarRecaudoEntregado>();
	private String sancion="";
	private String programa="";
	private String apellido="";
	private String nombre="";
	private String cedula="";

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public ServicioTipoMotivo getServiciotipomotivo() {
		return serviciotipomotivo;
	}

	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}

	public List<ListaCargarRecaudoEntregado> getLista() {
		return lista;
	}

	public String getSancion() {
		return sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public void setServiciotipomotivo(ServicioTipoMotivo serviciotipomotivo) {
		this.serviciotipomotivo = serviciotipomotivo;
	}

	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}

	public void setLista(List<ListaCargarRecaudoEntregado> lista) {
		this.lista = lista;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Otros Metodos
	 */
	
	@Init
    public void init(){
		buscarTipoMotivo();
		buscarProgramaA ();
		buscarApelacionesRecaudoEntregado();
    }
	
	@Command
  	@NotifyChange({"listaTipoMotivo"})
  	public void buscarTipoMotivo(){
  		listaTipoMotivo = serviciotipomotivo.buscarP(nombreTipoMotivo);
  	}
	
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPr(nombrePrograma);
	}
	
	@Command
	@NotifyChange({"lista"})
	public void buscarApelacionesRecaudoEntregado(){
	  			lista = serviciorecaudoentregado.buscarApelacionesCargarRecaudo();
	}
	
	@Command
	public void showModal (){
  		
  		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("cedula", listaEstudiantesCargarRecaudos.getCedulaEstudiante());
        map.put("nombre", listaEstudiantesCargarRecaudos.getPrimerNombre());
        map.put("apellido", listaEstudiantesCargarRecaudos.getPrimerApellido());
        map.put("email", listaEstudiantesCargarRecaudos.getEmail());
        map.put("programa", listaEstudiantesCargarRecaudos.getPrograma());
        map.put("sancion", listaEstudiantesCargarRecaudos.getNombreSancion());
        map.put("lapso", listaEstudiantesCargarRecaudos.getLapso());
        map.put("instancia", listaEstudiantesCargarRecaudos.getInstancia());
        map.put("segundoNombre", listaEstudiantesCargarRecaudos.getSegundoNombre());
        map.put("segundoApellido", listaEstudiantesCargarRecaudos.getSegundoApellido());
        map.put("caso", listaEstudiantesCargarRecaudos.getCaso());
        map.put("lapsosConsecutivos", listaEstudiantesCargarRecaudos.getLapsosConsecutivos());
        
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/CargarRecaudoEntregado.zul", null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
	
	@Command
	@NotifyChange({"lista","programa","cedula","nombre","apellido","sancion"})
	public void filtros(){
		lista = serviciorecaudoentregado.filtrarApelacionesCargarRecaudo(programa,cedula,nombre,apellido,sancion);
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

	public ListaCargarRecaudoEntregado getListaEstudiantesCargarRecaudos() {
		return listaEstudiantesCargarRecaudos;
	}

	public void setListaEstudiantesCargarRecaudos(
			ListaCargarRecaudoEntregado listaEstudiantesCargarRecaudos) {
		this.listaEstudiantesCargarRecaudos = listaEstudiantesCargarRecaudos;
	}

	public ServicioRecaudoEntregado getServiciorecaudoentregado() {
		return serviciorecaudoentregado;
	}

	public void setServiciorecaudoentregado(
			ServicioRecaudoEntregado serviciorecaudoentregado) {
		this.serviciorecaudoentregado = serviciorecaudoentregado;
	}
}

