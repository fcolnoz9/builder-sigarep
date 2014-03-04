package sigarep.viewmodels.reportes;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.BindingParam;
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

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**VM Informe Estructurado al Consejo Universitario
 * UCLA DCYT Sistemas de Información.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeCU {
	@Wire("#modalDialog")
	
	//***********************************DECLARACIÓN DE LAS VARIABLES GENERALES*************************
	private Window window;
	private String apellido;
	private String nombre;
	private String codigoLapso;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String programa;
	private Date fecha_comision;
	private String observacion_comision;
	private String sugerencia;
	private String codigo_sesion;
	private Date fecha_d;
	private String observacion_consejo_decanato;
	private String veredicto;
	private String sancion;
	private Date fecha_ingreso;
	private int semestre;
	private int unidades_cursadas;
	private int unidades_aprobadas;
	private int unidades_reprobadas;
	private float indice_grado;
	private String cedula;
	private EstudianteSancionado apelacionseleccionada;
	
	//***********************************DECLARACIÓN DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	
	//***********************************DECLARACIÓN DE LISTAS*************************
	private List<ApelacionEstadoApelacion> apelacionestudiante1 = new LinkedList<ApelacionEstadoApelacion>();
	private List<ApelacionEstadoApelacion> apelacionestudiante2 = new LinkedList<ApelacionEstadoApelacion>();
	private List<RecaudoEntregado> listaRecaudos1 = new LinkedList<RecaudoEntregado>();
	private List<RecaudoEntregado> listaRecaudos2 = new LinkedList<RecaudoEntregado>();
	private EstudianteSancionado sancionadoSeleccionado;
	
	//*********************************Mensajes***************************************
			MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
			
			
			// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL REPORTE***************************
			ReportType reportType = null;
			private ReportConfig reportConfig = null;
			String ruta="/WEB-INF/sigarepReportes/informes/RpInformeConsejoUniversitario.jasper";
			
//Reporte SET/GETS		
			
   public ReportConfig getReportConfig() {
		return reportConfig;
	}

   public ReportType getReportType() {
		return reportType;
	}

   public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}	

	public List<ApelacionEstadoApelacion> getApelacionestudiante1() {
		return apelacionestudiante1;
	}

	public void setApelacionestudiante1(
			List<ApelacionEstadoApelacion> apelacionestudiante1) {
		this.apelacionestudiante1 = apelacionestudiante1;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudiante2() {
		return apelacionestudiante2;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante2) {
		this.apelacionestudiante2 = apelacionestudiante2;
	}

	public EstudianteSancionado getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(EstudianteSancionado apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
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


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	// fin de metodos get y set

	/**
	 * concatenacionNombres
	 * 
	 * @return devuelve primer y segundo nombre concatenados
	 */
	public void concatenacionNombres() {

		String nombre1 = nombre;
		String nombre2 = segundoNombre;
		nombres = nombre1 + " " + nombre2;
	}

	/**
	 * concatenacionApellidos
	 * 
	 * @return devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {

		String apellido1 = apellido;
		String apellido2 = segundoApellido;
		apellidos = apellido1 + " " + apellido2;

	}
	//Método para Inicializar y Cargar los datos del estudiante sancionado seleccionado
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
	@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado sa)
	{
		Selectors.wireComponents(view, this, false);
		this.sancionadoSeleccionado = sa;
		cedula = sancionadoSeleccionado.getEstudiante().getCedulaEstudiante();
		codigoLapso = sancionadoSeleccionado.getId().getCodigoLapso();
		nombre = sancionadoSeleccionado.getEstudiante().getPrimerNombre();
		segundoNombre = sancionadoSeleccionado.getEstudiante().getSegundoNombre();
		apellido = sancionadoSeleccionado.getEstudiante().getPrimerApellido();
		segundoApellido = sancionadoSeleccionado.getEstudiante().getSegundoApellido();
		programa = sancionadoSeleccionado.getEstudiante().getProgramaAcademico().getNombrePrograma();
		sancion = sancionadoSeleccionado.getSancionMaestro().getNombreSancion();
		semestre = sancionadoSeleccionado.getSemestre();
		System.out.println("semestre"+semestre);
		unidades_cursadas = sancionadoSeleccionado.getUnidadesCursadas();
		unidades_aprobadas = sancionadoSeleccionado.getUnidadesAprobadas();
		unidades_reprobadas = (unidades_cursadas - unidades_aprobadas);
		indice_grado = sancionadoSeleccionado.getIndiceGrado();
		System.out.println("indice"+indice_grado);
		apelacionestudiante1 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, 1);
		System.out.println("ape1"+apelacionestudiante1);
		fecha_ingreso = sancionadoSeleccionado.getEstudiante().getAnioIngreso();
		
		for (int i = 0; i<apelacionestudiante1.size(); i++) {
			int estado = apelacionestudiante1.get(i).getEstadoApelacion().getIdEstadoApelacion();
			if (estado == 3) {
				fecha_comision = apelacionestudiante1.get(i).getFechaEstado();
				sugerencia = apelacionestudiante1.get(i).getSugerencia();
				observacion_comision = apelacionestudiante1.get(i).getObservacion();
			}
			
		}
		
		apelacionestudiante2 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, 2);
		System.out.println("ape2"+apelacionestudiante2);
		for (int i = 0; i<apelacionestudiante2.size(); i++) {
			int estado = apelacionestudiante2.get(i).getEstadoApelacion().getIdEstadoApelacion();
			System.out.println("estado"+estado);
			if (estado == 8) {
				System.out.println("entroooooooo");
				fecha_d = apelacionestudiante2.get(i).getFechaEstado();
				veredicto = apelacionestudiante2.get(i).getSolicitudApelacion().getVeredicto();
				observacion_consejo_decanato = apelacionestudiante2.get(i).getObservacion();
				codigo_sesion = apelacionestudiante2.get(i).getSolicitudApelacion().getNumeroSesion();
				System.out.println("lafecha"+fecha_d);
				System.out.println("vere"+veredicto);
				System.out.println("obs"+observacion_consejo_decanato);
				System.out.println("sesion"+codigo_sesion);
			}
			
		}
		
		
		concatenacionNombres();
		concatenacionApellidos();
		
		listaRecaudos1 = serviciorecaudoentregado.buscarRecaudosEntregadosVeredictoI(cedula, codigoLapso);
		listaRecaudos2 = serviciorecaudoentregado.buscarRecaudosEntregadosVeredictoII(cedula, codigoLapso);
		
	}

	//Reporte SET/GETS

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Date getFecha_comision() {
		return fecha_comision;
	}

	public void setFecha_comision(Date fecha_comision) {
		this.fecha_comision = fecha_comision;
	}

	public String getObservacion_comision() {
		return observacion_comision;
	}

	public void setObservacion_comision(String observacion_comision) {
		this.observacion_comision = observacion_comision;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	public String getCodigo_sesion() {
		return codigo_sesion;
	}

	public void setCodigo_sesion(String codigo_sesion) {
		this.codigo_sesion = codigo_sesion;
	}

	public Date getFecha_d() {
		return fecha_d;
	}

	public void setFecha_d(Date fecha_d) {
		this.fecha_d = fecha_d;
	}

	public String getObservacion_consejo_decanato() {
		return observacion_consejo_decanato;
	}

	public void setObservacion_consejo_decanato(
			String observacion_consejo_decanato) {
		this.observacion_consejo_decanato = observacion_consejo_decanato;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getUnidades_cursadas() {
		return unidades_cursadas;
	}

	public void setUnidades_cursadas(int unidades_cursadas) {
		this.unidades_cursadas = unidades_cursadas;
	}

	public int getUnidades_aprobadas() {
		return unidades_aprobadas;
	}

	public void setUnidades_aprobadas(int unidades_aprobadas) {
		this.unidades_aprobadas = unidades_aprobadas;
	}

	public int getUnidades_reprobadas() {
		return unidades_reprobadas;
	}

	public void setUnidades_reprobadas(int unidades_reprobadas) {
		this.unidades_reprobadas = unidades_reprobadas;
	}

	public float getIndice_grado() {
		return indice_grado;
	}

	public void setIndice_grado(float indice_grado) {
		this.indice_grado = indice_grado;
	}

	public List<RecaudoEntregado> getListaRecaudos1() {
		return listaRecaudos1;
	}

	public void setListaRecaudos1(List<RecaudoEntregado> listaRecaudos1) {
		this.listaRecaudos1 = listaRecaudos1;
	}

	public List<RecaudoEntregado> getListaRecaudos2() {
		return listaRecaudos2;
	}

	public void setListaRecaudos2(List<RecaudoEntregado> listaRecaudos2) {
		this.listaRecaudos2 = listaRecaudos2;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}
	//===============================FIN DE LOS METODOS SET Y GET==============================
	
	/** Generar Informe Estructurado al Consejo Universitario
	* @param Ninguno
	* @return  Informe Estructurado al Consejo Universitario generado en PDF u otro tipo de archivo
	* @throws Si la lista está vacía no genera el reporte.
	*/	//
	@Command("GenerarReporte")
	@NotifyChange({ "reportConfig" })
	public void generarReporte() {

		reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		reportConfig.getParameters().put("cedula_estudiante", cedula);
		reportConfig.getParameters().put("nombres", nombres);
		reportConfig.getParameters().put("apellidos", apellidos);
		reportConfig.getParameters().put("programa", programa);
		reportConfig.getParameters().put("sancion", sancion);
		reportConfig.getParameters().put("fecha_comision", fecha_comision);
		reportConfig.getParameters().put("observacion_comision", observacion_comision);
		reportConfig.getParameters().put("sugerencia", sugerencia);
		reportConfig.getParameters().put("nro_sesion", codigo_sesion);
		reportConfig.getParameters().put("fecha_d", fecha_d);
		reportConfig.getParameters().put("observacion_consejo_decanato", observacion_consejo_decanato);
		reportConfig.getParameters().put("veredicto", veredicto);
		reportConfig.getParameters().put("fecha_ingreso", fecha_ingreso);
		reportConfig.getParameters().put("unidades_cursadas", unidades_cursadas);
		reportConfig.getParameters().put("unidades_aprobadas", unidades_aprobadas);
		reportConfig.getParameters().put("unidades_reprobadas", unidades_reprobadas);
		reportConfig.getParameters().put("indice_grado", indice_grado);
		reportConfig.getParameters().put("semestre", semestre);
		reportConfig.getParameters().put("listaRecaudosComision", new JRBeanCollectionDataSource(listaRecaudos1));
		reportConfig.getParameters().put("listaRecaudosCD", new JRBeanCollectionDataSource(listaRecaudos2));
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE

	}
	

	@Command
	public void closeThis() {
		window.detach();
	}
	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana,condicion);		
	}

}
