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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacionFiltros;
import sigarep.modelos.servicio.transacciones.ListaEstudianteApelacion;
import sigarep.modelos.servicio.transacciones.ServicioListaEstudiate;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelListastudianteApelacion {

	@WireVariable
	private Estudiante estudiante = new Estudiante();
	@WireVariable
	private EstudianteSancionado estudiantesancionado;
	@WireVariable
	private ListaEstudianteApelacion listaestudianteapelacion;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private EstadoApelacion estadoapelacion;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private ApelacionEstadoApelacion apelacionestadoapelacion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioListaEstudiate serviciolistaestudiate;
	@WireVariable
	private List<EstudianteSancionado> listaSancionados =  new LinkedList<EstudianteSancionado>();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionEstadoApelacion> listadoApelaciones;
	private List<ListaEstudianteApelacion> lista = new LinkedList<ListaEstudianteApelacion>();
	private ListaApelacionEstadoApelacionFiltros filtros = new ListaApelacionEstadoApelacionFiltros();
	private String programaFiltro;
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String cedula;
	private String lapso;
	private Integer instancia;
	private String motivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String asignatura;
	private Integer caso;
	private Textbox txtAsignatura;
	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public EstudianteSancionado getEstudiantesancionado() {
		return estudiantesancionado;
	}
	

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public ListaEstudianteApelacion getListaestudianteapelacion() {
		return listaestudianteapelacion;
	}

	public SolicitudApelacion getSolicitudapelacion() {
		return solicitudapelacion;
	}

	public EstadoApelacion getEstadoapelacion() {
		return estadoapelacion;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public ApelacionEstadoApelacion getApelacionEstadoapelacion() {
		return apelacionestadoapelacion;
	}

	public ServicioTipoMotivo getServiciotipomotivo() {
		return serviciotipomotivo;
	}

	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}

	public ServicioListaEstudiate getServiciolistaestudiate() {
		return serviciolistaestudiate;
	}

	public List<EstudianteSancionado> getListaSancionados() {
		return listaSancionados;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public List<ApelacionEstadoApelacion> getListadoApelaciones() {
		return listadoApelaciones;
	}

	public List<ListaEstudianteApelacion> getLista() {
		return lista;
	}

	public String getSancion() {
		return sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public String getTelefono() {
		return telefono;
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

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public void setEstudiantesancionado(EstudianteSancionado estudiantesancionado) {
		this.estudiantesancionado = estudiantesancionado;
	}

	public void setListaestudianteapelacion(
			ListaEstudianteApelacion listaestudianteapelacion) {
		this.listaestudianteapelacion = listaestudianteapelacion;
	}

	public void setSolicitudapelacion(SolicitudApelacion solicitudapelacion) {
		this.solicitudapelacion = solicitudapelacion;
	}

	public void setEstadoapelacion(EstadoApelacion estadoapelacion) {
		this.estadoapelacion = estadoapelacion;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public void setApelacionestadoapelacion(ApelacionEstadoApelacion apelacionestadoapelacion) {
		this.apelacionestadoapelacion = apelacionestadoapelacion;
	}

	public void setServiciotipomotivo(ServicioTipoMotivo serviciotipomotivo) {
		this.serviciotipomotivo = serviciotipomotivo;
	}

	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}

	public void setServiciolistaestudiate(
			ServicioListaEstudiate serviciolistaestudiate) {
		this.serviciolistaestudiate = serviciolistaestudiate;
	}

	public void setListaSancionados(List<EstudianteSancionado> listaSancionados) {
		this.listaSancionados = listaSancionados;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public void setListadoApelaciones(List<ApelacionEstadoApelacion> listadoApelaciones) {
		this.listadoApelaciones = listadoApelaciones;
	}

	public void setLista(List<ListaEstudianteApelacion> lista) {
		this.lista = lista;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getMotivo() {
		return motivo;
	}

	public String getRecaudo() {
		return recaudo;
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

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
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
	public ListaApelacionEstadoApelacionFiltros getFiltros() {
		return filtros;
	}

	public String getProgramaFiltro() {
		return programaFiltro;
	}

	public void setFiltros(ListaApelacionEstadoApelacionFiltros filtros) {
		this.filtros = filtros;
	}

	public void setProgramaFiltro(String programaFiltro) {
		this.programaFiltro = programaFiltro;
	}

	/**
	 * Otros Metodos
	 */
	
	@Init
    public void init(){
		buscarTipoMotivo();
		buscarProgramaA ();
		buscarApelacionesR();
    	 //initialization code
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
	public void buscarApelacionesR(){
	  			lista = serviciolistaestudiate.buscarEstudianteApelacions();
	}
	
	@Command
	@NotifyChange({"cedula", "nombre", "apellido","email", "telefono", "programa", 
		"sancion", "lapso", "recaudo", "segundoNombre", "segundoApellido", "asignatura"})
	public void showModal (){
		cedula = getListaestudianteapelacion().getCedulaEstudiante();
		nombre = getListaestudianteapelacion().getPrimerNombre();
		apellido = getListaestudianteapelacion().getPrimerApellido();
		email = getListaestudianteapelacion().getEmail();
  		telefono = getListaestudianteapelacion().getTelefono();
  		programa = getListaestudianteapelacion().getPrograma();
  		sancion = getListaestudianteapelacion().getNombreSancion();
  		lapso = getListaestudianteapelacion().getLapso();
  		instancia = getListaestudianteapelacion().getInstancia();
  		motivo = getListaestudianteapelacion().getMotivo();
  		recaudo = getListaestudianteapelacion().getRecaudo();
  		segundoNombre = getListaestudianteapelacion().getSegundoNombre();
  		segundoApellido = getListaestudianteapelacion().getSegundoApellido();
  		asignatura = getListaestudianteapelacion().getAsignatura();
  		caso = getListaestudianteapelacion().getCaso();
  		
  		final HashMap<String, Object> map = new HashMap<String, Object>();
  		 map.put("cedula", this.cedula );
	        map.put("nombre", this.nombre);
	        map.put("apellido", this.apellido);
	        map.put("email", this.email);
	        map.put("telefono", this.telefono);
	        map.put("programa", this.programa);
	        map.put("sancion", this.sancion);
	        map.put("lapso", this.lapso);
	        map.put("instancia", this.instancia);
	        map.put("motivo", this.motivo);
	        map.put("recaudo", this.recaudo);
	        map.put("segundoNombre", this.segundoNombre);
	        map.put("segundoApellido", this.segundoApellido);
	        map.put("asignatura", this.asignatura);
	        map.put("caso", this.caso);
	       
        
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/RegistrarRecursoJerarquico.zul", null, map);
		window.setMaximizable(true);
		window.doModal();	
  	}
	
	@Command
	@NotifyChange({"lista"})
	public void filtros(){
		lista =serviciolistaestudiate.buscarPorFiltros(filtros);
	}
	

	

}

