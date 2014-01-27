package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;

import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.*;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacionFiltros;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudiante;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteFiltros;
import sigarep.modelos.servicio.transacciones.ListaRecaudosMotivoEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
import sigarep.modelos.servicio.transacciones.ServicioHistorialEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaHistorialEstudiante {

	@WireVariable
	private EstudianteSancionado estudiantesancionado;
	@WireVariable
	private ListaHistorialEstudiante listahistorialestudiante;
	@WireVariable
	private String nombrePrograma;

	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;

	@WireVariable
	private ServicioHistorialEstudiante serviciohistorial;

	private List<ProgramaAcademico> listaPrograma;
	private List<ListaHistorialEstudiante> lista = new LinkedList<ListaHistorialEstudiante>();

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
	private String sexo;
	private String asignatura;
	private String fechaNacimiento;
	private Integer caso;
	private Integer idMotivo;
	private String programaFiltro;
	private String anioIngreso;
	private Float indiceGrado;

	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
	private ListaHistorialEstudianteFiltros filtrosHistorial = new ListaHistorialEstudianteFiltros();

	public String getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(String anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public Float getIndiceGrado() {
		return indiceGrado;
	}

	public void setIndiceGrado(Float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}

	public Integer getUnidadesCursadas() {
		return unidadesCursadas;
	}

	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public Integer getIdMotivo() {
		return idMotivo;
	}

	public ListaHistorialEstudianteFiltros getFiltrosHistorial() {
		return filtrosHistorial;
	}

	public void setFiltrosHistorial(
			ListaHistorialEstudianteFiltros filtrosHistorial) {
		this.filtrosHistorial = filtrosHistorial;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	// no creo q vaya. private ListaApelacionEstadoApelacionFiltros filtros =
	// new ListaApelacionEstadoApelacionFiltros();

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProgramaFiltro() {
		return programaFiltro;
	}

	public void setProgramaFiltro(String programaFiltro) {
		this.programaFiltro = programaFiltro;
	}

	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<ListaHistorialEstudiante> getLista() {
		return lista;
	}

	public void setLista(List<ListaHistorialEstudiante> lista) {
		this.lista = lista;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public ListaHistorialEstudiante getListahistorialestudiante() {
		return listahistorialestudiante;
	}

	public void setListahistorialestudiante(
			ListaHistorialEstudiante listahistorialestudiante) {
		this.listahistorialestudiante = listahistorialestudiante;
	}

	@Init
	public void init() {
		// initialization code
		buscarProgramaA();
		buscarHistorialEstudiante();

	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
	}

	@Command
	@NotifyChange({ "lista" })
	public void buscarHistorialEstudiante() {
		lista = serviciohistorial.buscarEstudiante();
	}

	@Command
	@NotifyChange({ "cedula", "nombre", "apellido", "sexo", "fechaNacimiento",
			"telefono", "email", "segundoNombre", "segundoApellido",
			"programa", "anioIngreso", "indiceGrado", "unidadesCursadas",
			"unidadesAprobadas" })
	public void showModal() {
		cedula = getListahistorialestudiante().getCedulaEstudiante();
		nombre = getListahistorialestudiante().getPrimerNombre();
		apellido = getListahistorialestudiante().getPrimerApellido();
		sexo = getListahistorialestudiante().getSexo();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		fechaNacimiento = sdf.format(getListahistorialestudiante().getFechaNacimiento());
		telefono = getListahistorialestudiante().getTelefono();
		email = getListahistorialestudiante().getEmail();
		segundoNombre = getListahistorialestudiante().getSegundoNombre();
		segundoApellido = getListahistorialestudiante().getSegundoApellido();
		programa = getListahistorialestudiante().getPrograma();
		SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("dd/MM/yyyy");
		anioIngreso = sdf1.format(getListahistorialestudiante().getAnioIngreso());
		indiceGrado = getListahistorialestudiante().getIndiceGrado();
		unidadesCursadas = getListahistorialestudiante().getUnidadesCursadas();
		unidadesAprobadas = getListahistorialestudiante()
				.getUnidadesAprobadas();

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cedula", this.cedula);
		map.put("nombre", this.nombre);
		map.put("apellido", this.apellido);
		map.put("sexo", this.sexo);
		map.put("fechaNacimiento", this.fechaNacimiento);
		map.put("telefono", this.telefono);
		map.put("email", this.email);
		map.put("segundoNombre", this.segundoNombre);
		map.put("segundoApellido", this.segundoApellido);
		map.put("programa", this.programa);
		map.put("anioIngreso", this.anioIngreso);
		map.put("indiceGrado", this.indiceGrado);
		map.put("unidadesCursadas", this.unidadesCursadas);
		map.put("unidadesAprobadas", this.unidadesAprobadas);

		final Window window = (Window) Executions
				.createComponents(
						"/WEB-INF/sigarep/vistas/transacciones/HistorialEstudiante.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();
	}

	@Command
	@NotifyChange({ "lista" })
	public void filtros() {
		lista = serviciohistorial.buscarPorFiltros(filtrosHistorial);
	}

	@NotifyChange({ "filtroshistorial" })
	public ListaHistorialEstudianteFiltros getFiltros() {
		return filtrosHistorial;
	}

	public void setFiltros(ListaHistorialEstudianteFiltros filtrosHistorial) {
		this.filtrosHistorial = filtrosHistorial;

	}

}