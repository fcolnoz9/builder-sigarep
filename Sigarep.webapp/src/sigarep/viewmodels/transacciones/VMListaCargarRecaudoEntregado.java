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
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacionFiltros;
import sigarep.modelos.servicio.transacciones.ListaCargarRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ListaCargarRecaudoEntregadoFiltros;
import sigarep.modelos.servicio.transacciones.ListaEstudianteApelacion;
import sigarep.modelos.servicio.transacciones.ServicioCargarRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioListaEstudiate;

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
	private ServicioCargarRecaudoEntregado serviciocargarrecaudoentregado;
	
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ListaCargarRecaudoEntregado> lista = new LinkedList<ListaCargarRecaudoEntregado>();
	private ListaCargarRecaudoEntregadoFiltros filtros = new ListaCargarRecaudoEntregadoFiltros();
	private String sancion;
	private String programa;
	private String email;
	private String apellido;
	private String nombre;
	private String cedula;
	private String lapso;
	private Integer instancia;
	private String segundoNombre;
	private String segundoApellido;
	private String asignatura;
	private Integer caso;
	
	
	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

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

	public ServicioCargarRecaudoEntregado getServicioCargarRecaudoEntregado() {
		return serviciocargarrecaudoentregado;
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

	public String getEmail() {
		return email;
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

	public String getLapso() {
		return lapso;
	}

	public Integer getInstancia() {
		return instancia;
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

	public void setServicioCargarRecaudoEntregado(
			ServicioCargarRecaudoEntregado servicioCargarRecaudoEntregado) {
		this.serviciocargarrecaudoentregado = servicioCargarRecaudoEntregado;
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

	public void setEmail(String email) {
		this.email = email;
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

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	@NotifyChange({"filtros"})
	public ListaCargarRecaudoEntregadoFiltros getFiltros() {
		return filtros;
	}
	
	public void setFiltros(ListaCargarRecaudoEntregadoFiltros filtros) {
		this.filtros = filtros;
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
	  			lista = serviciocargarrecaudoentregado.buscarApelaciones();
	}
	
	@Command
	@NotifyChange({"cedula", "nombre", "apellido","email", "telefono", "programa", 
		"sancion", "lapso", "recaudo", "segundoNombre", "segundoApellido", "asignatura"})
	public void showModal (){
		cedula = listaEstudiantesCargarRecaudos.getCedulaEstudiante();
		nombre = listaEstudiantesCargarRecaudos.getPrimerNombre();
		apellido = listaEstudiantesCargarRecaudos.getPrimerApellido();
		email = listaEstudiantesCargarRecaudos.getEmail();
  		programa = listaEstudiantesCargarRecaudos.getPrograma();
  		sancion = listaEstudiantesCargarRecaudos.getNombreSancion();
  		lapso = listaEstudiantesCargarRecaudos.getLapso();
  		instancia = listaEstudiantesCargarRecaudos.getInstancia();
  		segundoNombre = listaEstudiantesCargarRecaudos.getSegundoNombre();
  		segundoApellido = listaEstudiantesCargarRecaudos.getSegundoApellido();
  		asignatura = listaEstudiantesCargarRecaudos.getAsignatura();
  		caso = listaEstudiantesCargarRecaudos.getCaso();
  		
  		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("cedula", this.cedula );
        map.put("nombre", this.nombre);
        map.put("apellido", this.apellido);
        map.put("email", this.email);
        map.put("programa", this.programa);
        map.put("sancion", this.sancion);
        map.put("lapso", this.lapso);
        map.put("instancia", this.instancia);
        map.put("segundoNombre", this.segundoNombre);
        map.put("segundoApellido", this.segundoApellido);
        map.put("asignatura", this.asignatura);
        map.put("caso", this.caso);
        
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/CargarRecaudoEntregado.zul", null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
	
	@Command
	@NotifyChange({"lista"})
	public void filtros(){
		lista = serviciocargarrecaudoentregado.buscarPorFiltros(filtros);
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
}

