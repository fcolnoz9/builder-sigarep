package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioListaAsignaturasMayorCantidadSancionados;

/**
 * View Models de Reporte Asignaturas Mayor Cantidad Sancionados.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 20/01/2014
 * @last 08/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAsignaturasMayorCantidadSancionados {
	// --------------------------Servicios------------------------------
	@WireVariable
	ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable
	ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	// --------------------------Variables de Control-------------------
	private Integer idPrograma;
	private String nombrePrograma;
	private String codigoLapso;
	private Integer idInstanciaApelada;
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/estadisticos/RpAsignaturasSancionadosVsApelaciones.jasper";
	// --------------------------Variables lista------------------------
	private ListModelList<ListaAsignaturasMayorCantidadSancionados> listaAsigMayor;
	private List<ProgramaAcademico> listaComboPrograma;
	private List<LapsoAcademico> listaLapsoAcademico;
	private List<InstanciaApelada> listaComboInstancia;
	// --------------------------Variables Objeto-----------------------
	private LapsoAcademico lapsoAcademico;
	private ProgramaAcademico programaAcademico;
	private InstanciaApelada instanciaApelada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Métodos Set y Get
	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public Integer getIdInstanciaApelada() {
		return idInstanciaApelada;
	}

	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
	}

	public ProgramaAcademico getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public List<ProgramaAcademico> getListaComboPrograma() {
		return listaComboPrograma;
	}

	public void setListaComboPrograma(List<ProgramaAcademico> listaComboPrograma) {
		this.listaComboPrograma = listaComboPrograma;
	}

	public List<InstanciaApelada> getListaComboInstancia() {
		return listaComboInstancia;
	}

	public void setListaComboInstancia(
			List<InstanciaApelada> listaComboInstancia) {
		this.listaComboInstancia = listaComboInstancia;
	}

	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	public ListModelList<ListaAsignaturasMayorCantidadSancionados> getListaAsig() {
		return listaAsigMayor;
	}

	public void setListaAsig(
			ListModelList<ListaAsignaturasMayorCantidadSancionados> listaAsig) {
		this.listaAsigMayor = listaAsig;
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
	public void Init() {
		buscarProgramaAcademico();
		buscarLapso();
		buscarInstanciaApelada();
		listaAsigMayor = new ListModelList<ListaAsignaturasMayorCantidadSancionados>();
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
					"Word (RTF)", "rtf"), new ReportType("Excel", "xls"),
					new ReportType("Excel (JXL)", "jxl"), new ReportType("CSV",
							"csv"), new ReportType("OpenOffice (ODT)", "odt")));

	/**
	 * Limpiar Asignaturas Sancionados.
	 * 
	 * @param Ninguno
	 * @return Limpiar cada uno de los combos de la vista.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "programaAcademico", "lapsoAcademico", "instanciaApelada",
			"reportType" })
	public void limpiarAsignaturasSancionados() {
		programaAcademico = null;
		lapsoAcademico = null;
		instanciaApelada = null;
		reportType = null;
	}

	/**
	 * Buscar programa académico.
	 * 
	 * @param Ninguno
	 * @return Busca todos los registros.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaComboPrograma" })
	public void buscarProgramaAcademico() {
		setListaComboPrograma(servicioprogramaacademico.listadoProgramas());
	}

	/**
	 * Buscar lapsos académicos.
	 * 
	 * @param Ninguno
	 * @return Busca todos los registros.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapso() {
		setListaLapsoAcademico(serviciolapsoacademico.buscarTodosLosLapsos());
	}

	/**
	 * Buscar instancias apeladas.
	 * 
	 * @param Ninguno
	 * @return Listado de Instancias.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaComboInstancia" })
	public void buscarInstanciaApelada() {
		setListaComboInstancia(servicioInstanciaApelada
				.listadoInstanciaApelada());
	}

	/**
	 * Generar Reporte Asignaturas Mayor.
	 * 
	 * @param Ninguno
	 * @return Reporte de las 5 asignaturas con mas sancionados generado en PDF
	 *         u otro tipo de archivo.
	 * @throws Si
	 *             la lista esta vacia no genera el reporte.
	 */
	@Command("GenerarReporteAsigMayor")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporteAsigMayor() {
		if (programaAcademico == null || lapsoAcademico == null
				|| instanciaApelada == null || reportType == null)
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		else {
			listaAsigMayor.clear();
			idPrograma = programaAcademico.getIdPrograma();
			codigoLapso = lapsoAcademico.getCodigoLapso();
			idInstanciaApelada = instanciaApelada.getIdInstanciaApelada();
			List<ListaAsignaturasMayorCantidadSancionados> lista = servicioListaAsignaturasMayor
					.buscarAsignaturasSancionados(idPrograma, codigoLapso,
							idInstanciaApelada);
			listaAsigMayor.addAll(lista);
			if (listaAsigMayor.getSize() > 0) {
				reportConfig = new ReportConfig(ruta);
				reportConfig.getParameters().put("nombrePrograma",
						programaAcademico.getNombrePrograma());
				reportConfig.getParameters().put("instanciaApelada",
						instanciaApelada.getNombreRecursoApelacion());
				reportConfig.getParameters().put("lapso",
						lapsoAcademico.getCodigoLapso());
				reportConfig.getParameters().put("Lista",
						new JRBeanCollectionDataSource(listaAsigMayor));
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaAsigMayor));
			} else
				mensajeAlUsuario.informacionNoHayCoincidencias();
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
	@NotifyChange({ "listaComboPrograma" })
	public ProgramaAcademico objetoComboPrograma() {
		return programaAcademico;
	}

	/**
	 * Objeto Combo Lapso.
	 * 
	 * @param Ninguno
	 * @return Objeto Lapso Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public LapsoAcademico objetoComboLapso() {
		return lapsoAcademico;
	}

	/**
	 * Objeto Combo Instancia.
	 * 
	 * @param Ninguno
	 * @return Objeto Instancia Apelada
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaComboInstancia" })
	public InstanciaApelada objetoComboInstancia() {
		return instanciaApelada;

	}
}
