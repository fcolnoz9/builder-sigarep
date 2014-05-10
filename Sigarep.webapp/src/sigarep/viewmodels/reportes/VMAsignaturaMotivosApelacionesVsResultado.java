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
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaApelacionesMotivoPorAsignatura;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioApelacionesMotivosPorAsignatura;
import sigarep.modelos.servicio.reportes.ServicioListaAsignaturasMayorCantidadSancionados;

/**
 * VM Reporte Asignatura Motivos Apelaciones Vs Resultado Procedente.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 03/02/2014
 * @last 08/05/2014
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAsignaturaMotivosApelacionesVsResultado {
	// --------------------------Servicios------------------------------
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	ServicioAsignatura servicioAsignatura;
	@WireVariable
	ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable
	ServicioApelacionesMotivosPorAsignatura servicioApelacionesMotivos;
	// --------------------------Variables de Control-------------------
	private String contenido;
	private Integer idPrograma;
	private String codigoLapso;
	private Integer idInstanciaApelada;
	private String codigoAsignatura;
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/estadisticos/RpAsignaturaApelacionesMotivoVsResultado.jasper";
	// --------------------------Variables lista------------------------
	private ListModelList<ListaApelacionesMotivoPorAsignatura> listaApelacionMotivoAsignatura;
	private List<Asignatura> listaAsignatura;
	private List<ProgramaAcademico> listaPrograma;
	private List<LapsoAcademico> listaComboLapsoAcademico;
	private List<InstanciaApelada> listaComboInstancias;
	// --------------------------Variables Objeto-----------------------
	private ProgramaAcademico programaseleccionado;
	private LapsoAcademico lapsosAcademicos;
	private InstanciaApelada instanciasApeladas;
	private Asignatura asignatura;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Fin Métodos Set y Get
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public Integer getIdInstanciaApelada() {
		return idInstanciaApelada;
	}

	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
	}

	public List<Asignatura> getListaAsignatura() {
		return listaAsignatura;
	}

	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<LapsoAcademico> getListaComboLapsoAcademico() {
		return listaComboLapsoAcademico;
	}

	public void setListaComboLapsoAcademico(
			List<LapsoAcademico> listaComboLapsoAcademico) {
		this.listaComboLapsoAcademico = listaComboLapsoAcademico;
	}

	public ListModelList<ListaApelacionesMotivoPorAsignatura> getListaApelacionMotivo() {
		return listaApelacionMotivoAsignatura;
	}

	public void setListaApelacionMotivo(
			ListModelList<ListaApelacionesMotivoPorAsignatura> listaApelacionMotivo) {
		this.listaApelacionMotivoAsignatura = listaApelacionMotivo;
	}

	public List<InstanciaApelada> getListaComboInstancias() {
		return listaComboInstancias;
	}

	public void setListaComboInstancias(
			List<InstanciaApelada> listaComboInstancias) {
		this.listaComboInstancias = listaComboInstancias;
	}

	public ProgramaAcademico getProgramaseleccionado() {
		return programaseleccionado;
	}

	public void setProgramaseleccionado(ProgramaAcademico programaseleccionado) {
		this.programaseleccionado = programaseleccionado;
	}

	public LapsoAcademico getLapsosAcademicos() {
		return lapsosAcademicos;
	}

	public void setLapsosAcademicos(LapsoAcademico lapsosAcademicos) {
		this.lapsosAcademicos = lapsosAcademicos;
	}

	public InstanciaApelada getInstanciasApeladas() {
		return instanciasApeladas;
	}

	public void setInstanciasApeladas(InstanciaApelada instanciasApeladas) {
		this.instanciasApeladas = instanciasApeladas;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

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
	public void init() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
		listaAsignatura = servicioAsignatura.listaAsignaturas();
		buscarInstanciasApeladas();
		buscarLapsos();
		listaApelacionMotivoAsignatura = new ListModelList<ListaApelacionesMotivoPorAsignatura>();
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
	 * Limpiar Asignatura Motivos.
	 * 
	 * @param Ninguno
	 * @return Limpiar cada uno de los combos de la vista.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "programaseleccionado", "asignatura", "lapsosAcademicos",
			"instanciasApeladas", "reportType" })
	public void limpiarAsignaturaMotivos() {
		programaseleccionado = null;
		asignatura = null;
		lapsosAcademicos = null;
		instanciasApeladas = null;
		reportType = null;
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
	@NotifyChange({ "listaComboLapsoAcademico" })
	public void buscarLapsos() {
		setListaComboLapsoAcademico(serviciolapsoacademico
				.buscarTodosLosLapsos());
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
	@NotifyChange({ "listaComboInstancias" })
	public void buscarInstanciasApeladas() {
		setListaComboInstancias(servicioInstanciaApelada
				.listadoInstanciaApelada());
	}

	/**
	 * Programa Seleccionado.
	 * 
	 * @param Integer
	 *            IdPrograma
	 * @return Lista de Asignaturas dependiendo del Programa Seleccionado.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaAsignatura", "contenido" })
	public void onSelectPrograma() {
		listaAsignatura.clear();
		listaAsignatura = servicioAsignatura
				.buscarAsignaturasPorPrograma(programaseleccionado);
		contenido = "";
	}

	/**
	 * Generar Reporte Apelaciones Motivo Por Asignatura.
	 * 
	 * @param Ninguno
	 * @return Reporte de Apelaciones por Motivo y su Resultado Procedente por
	 *         Asignatura generado en PDF u otro tipo de archivo.
	 * @throws Si
	 *             la lista esta vacia no genera el reporte.
	 */
	@Command("GenerarReporteApelacionesMotivoPorAsignatura")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporteApelacionesMotivoPorAsignatura() {
		if (asignatura == null || lapsosAcademicos == null
				|| instanciasApeladas == null || reportType == null)
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		else {
			listaApelacionMotivoAsignatura.clear();
			codigoAsignatura = asignatura.getCodigoAsignatura();
			codigoLapso = lapsosAcademicos.getCodigoLapso();
			idInstanciaApelada = instanciasApeladas.getIdInstanciaApelada();
			List<ListaApelacionesMotivoPorAsignatura> lista2 = servicioApelacionesMotivos
					.buscarApelacionesMotivoPorAsignatura(codigoAsignatura,
							codigoLapso, idInstanciaApelada);
			listaApelacionMotivoAsignatura.addAll(lista2);
			if (listaApelacionMotivoAsignatura.getSize() > 0) {
				reportConfig = new ReportConfig(ruta);
				reportConfig.getParameters().put("nombrePrograma",
						programaseleccionado.getNombrePrograma());
				reportConfig.getParameters().put("instanciaApelada",
						instanciasApeladas.getInstanciaApelada());
				reportConfig.getParameters().put("lapso",
						lapsosAcademicos.getCodigoLapso());
				reportConfig.getParameters().put("asignatura",
						asignatura.getNombreAsignatura());
				reportConfig.getParameters().put(
						"ListaAsignaturaMotivos",
						new JRBeanCollectionDataSource(
								listaApelacionMotivoAsignatura));
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaApelacionMotivoAsignatura));
			} else
				mensajeAlUsuario.informacionNoHayCoincidencias();
		}
	}

	/**
	 * Objeto Combo Lapsos.
	 * 
	 * @param Ninguno
	 * @return Objeto Lapso Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaComboLapsoAcademico" })
	public LapsoAcademico objetoComboLapsos() {
		return lapsosAcademicos;
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
	@NotifyChange({ "listaComboInstancias" })
	public InstanciaApelada objetoComboInstancias() {
		return instanciasApeladas;
	}

}
