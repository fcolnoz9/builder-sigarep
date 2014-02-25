package sigarep.viewmodels.reportes;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
/**
 * DetalleHistorialEstudiante 
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleHistorialEstudiante {
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String codigoLapso;
	private String nombreEstudiante;
	private String nombreSancion;
	private String apellidoEstudiante;
	private Integer instancia;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	private List<ApelacionEstadoApelacion> apelacionestudiante  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia2  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia3  = new LinkedList<ApelacionEstadoApelacion>(); 
	private SolicitudApelacion apelacionseleccionada;
	private List<EstudianteSancionado> estudiante = new LinkedList<EstudianteSancionado>();
	private List<SolicitudApelacion> apelacion = new LinkedList<SolicitudApelacion>();
	
	// Para llamar a los diferentes mensajes de dialogo
		MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

		ReportType reportType = null;
		private ReportConfig reportConfig = null;
		
		String ruta="/WEB-INF/sigarepReportes/informes/RHistorialEstudiante.jasper";

	// Metodos get y set
		

		public ReportConfig getReportConfig() {
			return reportConfig;
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

	
	public List<EstudianteSancionado> getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(List<EstudianteSancionado> estudiante) {
		this.estudiante = estudiante;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia2() {
		return apelacionestudianteinstancia2;
	}

	public void setApelacionestudianteinstancia2(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia2) {
		this.apelacionestudianteinstancia2 = apelacionestudianteinstancia2;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia3() {
		return apelacionestudianteinstancia3;
	}

	public void setApelacionestudianteinstancia3(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia3) {
		this.apelacionestudianteinstancia3 = apelacionestudianteinstancia3;
	}
	
	
	
	public List<ApelacionEstadoApelacion> getApelacionestudiante() {
		return apelacionestudiante;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante) {
		this.apelacionestudiante = apelacionestudiante;
	}

	public SolicitudApelacion getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(SolicitudApelacion apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	public List<SolicitudApelacion> getApelacion() {
		return apelacion;
	}

	public void setApelacion(List<SolicitudApelacion> apelacion) {
		this.apelacion = apelacion;
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
			
 // fin de metodos get y set
	

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

	@Command
	@NotifyChange({ "apelacionestudiante" })
	public void buscarSolicitud(String cedula, String codigoLapso, Integer instancia) {
		instancia = 1;
		apelacionestudiante = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia2" })
	public void buscarSolicitudInstancia2(String cedula, String codigoLapso, Integer instancia) {
		instancia = 2;
		apelacionestudianteinstancia2 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia3" })
	public void buscarSolicitudInstancia3(String cedula, String codigoLapso, Integer instancia) {
		instancia = 3;
		apelacionestudianteinstancia3 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	
	@Command
	@NotifyChange({ "estudiante" })
	public void buscarEstudiante(String cedula) {
		estudiante = servicioestudiantesancionado.buscarApelacion(cedula);
	}
		
		@Command("GenerarReporteHistorial")
		@NotifyChange({ "reportConfig" })
		public void generarReporte() {

			reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
												// REPORTE
			reportConfig.getParameters().put("Titulo", "Historial de Estudiante");
			reportConfig.getParameters().put("codigoLapso", codigoLapso);
			reportConfig.getParameters().put("cedula", cedula);
			reportConfig.getParameters().put("nombre", estudiante.get(0).getEstudiante().getPrimerNombre());
			reportConfig.getParameters().put("apellido", estudiante.get(0).getEstudiante().getPrimerApellido());
			reportConfig.getParameters().put("sugerencia1", apelacionestudiante.get(0).getSugerencia());
			reportConfig.getParameters().put("sugerencia2", apelacionestudianteinstancia2.get(0).getSugerencia());
			reportConfig.getParameters().put("sugerencia3", apelacionestudianteinstancia3.get(0).getSugerencia());
			reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
					apelacionestudiante));
			reportConfig.getParameters().put("ListaInstancia2", new JRBeanCollectionDataSource(
					apelacionestudianteinstancia2));
			reportConfig.getParameters().put("ListaInstancia3", new JRBeanCollectionDataSource(
					apelacionestudianteinstancia3));
			reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
//											// IMPRESION DEL REPORTE
//			reportConfig.setDataSource(new JRBeanCollectionDataSource(
//					apelacionestudiante));				// DATOS PARA DIBUJAR EL REPORTE
//			reportConfig.setDataSource(new JRBeanCollectionDataSource(
//					apelacionestudianteinstancia2));
//			reportConfig.setDataSource(new JRBeanCollectionDataSource(
//					apelacionestudianteinstancia3));
		}
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("apelacionestudiante") List<ApelacionEstadoApelacion> v3,
			@ExecutionArgParam("apelacionestudianteinstancia2") List<ApelacionEstadoApelacion> v4,
			@ExecutionArgParam("apelacionestudianteinstancia3") List<ApelacionEstadoApelacion> v5,
			@ExecutionArgParam("apelacion") List<SolicitudApelacion> v1,
			@ExecutionArgParam("estudiante") List<EstudianteSancionado> v2,
			@ExecutionArgParam("cedula") String v6,
			@ExecutionArgParam("codigoLapso") String v7
			)

	// initialization code
	{
	
		Selectors.wireComponents(view, this, false);

		this.apelacionestudiante = v3;
		this.apelacionestudianteinstancia2 = v4;
		this.apelacionestudianteinstancia3 = v5;
		this.apelacion = v1;
		this.estudiante = v2;
		this.cedula = v6;
		this.codigoLapso = v7;
		}
	}


