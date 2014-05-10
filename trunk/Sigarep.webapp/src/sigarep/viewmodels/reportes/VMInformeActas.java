package sigarep.viewmodels.reportes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;

/**
 * VM Informe actas.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 23/01/2014
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeActas {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	// --------------------------Variables de Control-------------------
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String codigoLapso;
	private String nombreEstudiante;
	private String nombreSancion;
	private String apellidoEstudiante;
	private Integer instancia;
	private String caso;
	private String programa;
	private Integer estado;
	private String sugerencias;
	private Date fecha;
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/informes/estructurados/RpInformeActas.jasper";
	// --------------------------Variables lista------------------------
	private List<ApelacionEstadoApelacion> sugerencia = new LinkedList<ApelacionEstadoApelacion>();
	// --------------------------Variables Objeto-----------------------
	private SolicitudApelacion sancionadoSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Métodos Set y Get
	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public String getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(String sugerencias) {
		this.sugerencias = sugerencias;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<ApelacionEstadoApelacion> getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(List<ApelacionEstadoApelacion> sugerencia) {
		this.sugerencia = sugerencia;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}

	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	// Fin Métodos Set y Get

	/**
	 * Inicialización
	 * 
	 * @param init
	 * @return Carga de Variables y métodos inicializados
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion v1) {
		Selectors.wireComponents(view, this, false);
		this.sancionadoSeleccionado = v1;
		cedula = sancionadoSeleccionado.getId().getCedulaEstudiante();
		String primerNombre = sancionadoSeleccionado.getEstudianteSancionado()
				.getEstudiante().getPrimerNombre();
		String segundoNombre = sancionadoSeleccionado.getEstudianteSancionado()
				.getEstudiante().getSegundoNombre();
		String primerApellido = sancionadoSeleccionado
				.getEstudianteSancionado().getEstudiante().getPrimerApellido();
		String segundoApellido = sancionadoSeleccionado
				.getEstudianteSancionado().getEstudiante().getSegundoApellido();
		nombreEstudiante = primerNombre + "  " + segundoNombre;
		apellidoEstudiante = primerApellido + "  " + segundoApellido;
		codigoLapso = sancionadoSeleccionado.getId().getCodigoLapso();
		instancia = sancionadoSeleccionado.getId().getIdInstanciaApelada();
		programa = sancionadoSeleccionado.getEstudianteSancionado()
				.getEstudiante().getProgramaAcademico().getNombrePrograma();
		estado = 3;
		sugerencia = servicioapelacionestadoapelacion.buscarSugerencia(cedula,
				codigoLapso, instancia, estado);
		sugerencias = sugerencia.get(0).getSugerencia();
		fecha = sugerencia.get(0).getFechaEstado();
	}

	/**
	 * Generar Reporte Informe actas.
	 * 
	 * @param Ninguno
	 * @return Reporte Informe actas.
	 * @throws Si
	 *             la lista está vacía no genera el reporte.
	 */
	@Command("GenerarReporteActas")
	@NotifyChange({ "reportConfig" })
	public void generarReporte() {
		reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA
												// AL
												// REPORTE
		reportConfig.getParameters().put("Titulo", "ACTA");
		reportConfig.getParameters().put("cedula", cedula);
		reportConfig.getParameters().put("nombreEstudiante", nombreEstudiante);
		reportConfig.getParameters().put("apellidoEstudiante",
				apellidoEstudiante);
		reportConfig.getParameters().put("programa", programa);
		reportConfig.getParameters().put("sugerencias", sugerencias);
		reportConfig.getParameters().put("fecha", fecha);
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
		// // IMPRESION DEL REPORTE
	}
}
