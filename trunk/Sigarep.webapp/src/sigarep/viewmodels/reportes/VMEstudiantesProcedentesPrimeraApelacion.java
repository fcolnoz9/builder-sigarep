package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaEstudiantesProcedentes;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioEstudiantesProcedentes;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * VM Estudiantes procedentes primera apelación.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 23/01/2014
 * @last 10/05/2014
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudiantesProcedentesPrimeraApelacion {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudiantesProcedentes servicioEstudiantesProcedentes;
	// --------------------------Variables de Control-------------------
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/RpListaEstudiantesProcedentes.jasper";
	@Wire("#winProcedentesPrimeraApelacion")
	Window ventanaPA;
	// --------------------------Variables lista------------------------
	private List<ProgramaAcademico> listadoProgramasAcademicos;
	private List<String> listaApelaciones;
	private List<ListaEstudiantesProcedentes> listaProcedentesComision = new LinkedList<ListaEstudiantesProcedentes>();
	// --------------------------Variables Objeto-----------------------
	private ProgramaAcademico objprogramasAcademicos;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	// --------------------------Variables tira sql---------------------
	private Integer idPrograma;

	// Métodos Set y Get
	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}

	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}

	public ServicioEstudiantesProcedentes getServicioEstudiantesProcedentes() {
		return servicioEstudiantesProcedentes;
	}

	public void setServicioEstudiantesProcedentes(
			ServicioEstudiantesProcedentes servicioEstudiantesProcedentes) {
		this.servicioEstudiantesProcedentes = servicioEstudiantesProcedentes;
	}

	public List<ProgramaAcademico> getListadoProgramasAcademicos() {
		return listadoProgramasAcademicos;
	}

	public void setListadoProgramasAcademicos(
			List<ProgramaAcademico> listadoProgramasAcademicos) {
		this.listadoProgramasAcademicos = listadoProgramasAcademicos;
	}

	public List<String> getListaApelaciones() {
		return listaApelaciones;
	}

	public void setListaApelaciones(List<String> listaApelaciones) {
		this.listaApelaciones = listaApelaciones;
	}

	public List<ListaEstudiantesProcedentes> getListaProcedentesComision() {
		return listaProcedentesComision;
	}

	public void setListaProcedentesComision(
			List<ListaEstudiantesProcedentes> listaProcedentesComision) {
		this.listaProcedentesComision = listaProcedentesComision;
	}

	public ProgramaAcademico getObjprogramasAcademicos() {
		return objprogramasAcademicos;
	}

	public void setObjprogramasAcademicos(
			ProgramaAcademico objprogramasAcademicos) {
		this.objprogramasAcademicos = objprogramasAcademicos;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public MensajesAlUsuario getMensajeAlUsuario() {
		return mensajeAlUsuario;
	}

	public void setMensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario) {
		this.mensajeAlUsuario = mensajeAlUsuario;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}

	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
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
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		buscarProgramasA();
		listaSesiones();
		if (listaApelaciones.size() == 0) {
			mensajeAlUsuario.cerrarVentanaSinVeredicto(ventanaPA, true);
		}
	}

	/**
	 * afterCompose. Conecta a los componentes de la vista. Es necesario para
	 * evitar null pointer.
	 * 
	 * @param @ContextParam(ContextType.VIEW) Component view
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	/**
	 * ListModelList. Muestra los tipos de formatos que puede mostrarse el
	 * reporte.
	 * 
	 * @param Ninguno
	 * @return Tipos de formatos para el reporte.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(new ReportType("PDF", "pdf"), new ReportType(
					"Word (RTF)", "rtf"), new ReportType("Reporte en Excel",
					"xls"), new ReportType("Excel (JXL)", "jxl"),
					new ReportType("CSV", "csv"), new ReportType(
							"OpenOffice (ODT)", "odt")));

	/**
	 * Buscar Programas Académicos
	 * 
	 * @param Ninguno
	 * @return lista de programa Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listadoProgramasAcademicos" })
	public void buscarProgramasA() {
		listadoProgramasAcademicos = servicioprogramaacademico
				.listadoProgramas();
	}

	/**
	 * Buscar sesiones
	 * 
	 * @param Ninguno
	 * @return lista de sesiones
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaApelaciones" })
	public void listaSesiones() {
		listaApelaciones = (serviciosolicitudapelacion.buscarSesion());
	}

	/**
	 * Limpiar Combo Programa Académico.
	 * 
	 * @param Ninguno
	 * @return Limpiar cada uno de los combos de la vista
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "objprogramasAcademicos", "reportType" })
	public void limpiarComboProgramaAcademico() {
		objprogramasAcademicos = null;
		reportType = null;
	}

	/**
	 * Generar Reporte Informe Especial Resultado Apelacion.
	 * 
	 * @param Ninguno
	 * @return Reporte Especial de Resultado Apelacion
	 * @throws Si
	 *             la lista está vacía no genera el reporte.
	 */
	@Command("GenerarReporteEstudiantesPrimeraApelacion")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporteEstudiantesPrimeraApelacion() {
		if (objprogramasAcademicos == null || reportType == null) {
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		} else {
			listaProcedentesComision.clear();
			idPrograma = objprogramasAcademicos.getIdPrograma();
			listaProcedentesComision = servicioEstudiantesProcedentes
					.buscarEstudiantesProcedentesPrimeraApelacion(idPrograma);
			if (listaProcedentesComision.size() > 0) {
				reportConfig = new ReportConfig(ruta);
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaProcedentesComision));
			} else {
				mensajeAlUsuario.informacionNoHayCoincidencias();

			}
		}
	}

	/**
	 * Objeto Combo Programa.
	 * 
	 * @param Ninguno
	 * @return Objeto Programa Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listadoProgramasAcademicos" })
	public ProgramaAcademico objprogramaA() {
		return objprogramasAcademicos;
	}
}
