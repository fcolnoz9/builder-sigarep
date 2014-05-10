package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
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
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.reportes.ListaEspecialEstudiantesSancionadosApelaciones;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioInformeEspecialEstudiantesSancionadosApelaciones;

/**
 * VM Informe Especial Estudiantes Sancionados y sus Apelaciones.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 23/01/2014
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeEspecialEstudiantesSancionadosApelaciones {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioInformeEspecialEstudiantesSancionadosApelaciones servicioestudianteasignaturasancion;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	// --------------------------Variables de Control-------------------
	private String objVeredicto;
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/informes/especiales/RpInformeEspecialSancionadosApelaciones.jasper";
	// --------------------------Variables lista------------------------
	private List<ProgramaAcademico> listaPrograma;
	private List<SancionMaestro> listaSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<ListaEspecialEstudiantesSancionadosApelaciones> listaEAS = new LinkedList<ListaEspecialEstudiantesSancionadosApelaciones>();
	private ListModelList<String> cmbVeredicto;// Lista para llenar el combo
												// Veredicto
	// --------------------------Variables Objeto-----------------------
	private SancionMaestro objSancion;
	private ProgramaAcademico objprograma;
	private InstanciaApelada objinstanciaApelada;
	private ProgramaAcademico programa;
	private LapsoAcademico lapso;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	// --------------------------Variables tira sql---------------------
	private String parametroTipoSancion;
	private String parametroInstanciaApelada;
	private String parametroProgramaAcademico;
	private String parametroVeredicto;

	// Métodos Set y Get
	public SancionMaestro getObjSancion() {
		return objSancion;
	}

	public void setObjSancion(SancionMaestro objSancion) {
		this.objSancion = objSancion;
	}

	public ProgramaAcademico getObjprograma() {
		return objprograma;
	}

	public void setObjprograma(ProgramaAcademico objprograma) {
		this.objprograma = objprograma;
	}

	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}

	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}

	public ProgramaAcademico getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaAcademico programa) {
		this.programa = programa;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(
			List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}

	public List<ListaEspecialEstudiantesSancionadosApelaciones> getListaEAS() {
		return listaEAS;
	}

	public void setListaE(
			List<ListaEspecialEstudiantesSancionadosApelaciones> listaEAS) {
		this.listaEAS = listaEAS;
	}

	public String getParametroTipoSancion() {
		return parametroTipoSancion;
	}

	public void setParametroTipoSancion(String parametroTipoSancion) {
		this.parametroTipoSancion = parametroTipoSancion;
	}

	public String getParametroInstanciaApelada() {
		return parametroInstanciaApelada;
	}

	public void setParametroInstanciaApelada(String parametroInstanciaApelada) {
		this.parametroInstanciaApelada = parametroInstanciaApelada;
	}

	public String getParametroProgramaAcademico() {
		return parametroProgramaAcademico;
	}

	public void setParametroProgramaAcademico(String parametroProgramaAcademico) {
		this.parametroProgramaAcademico = parametroProgramaAcademico;
	}

	public String getParametroVeredicto() {
		return parametroVeredicto;
	}

	public void setParametroVeredicto(String parametroVeredicto) {
		this.parametroVeredicto = parametroVeredicto;
	}

	public String getObjVeredicto() {
		return objVeredicto;
	}

	public void setObjVeredicto(String objVeredicto) {
		this.objVeredicto = objVeredicto;
	}

	public LapsoAcademico getLapso() {
		return lapso;
	}

	public void setLapso(LapsoAcademico lapso) {
		this.lapso = lapso;
	}

	public ListModelList<String> getCmbVeredicto() {
		cmbVeredicto.add("PROCEDENTE");
		cmbVeredicto.add("NO PROCEDENTE");
		cmbVeredicto.add("Todos");
		return cmbVeredicto;
	}

	public void setCmbVeredicto(ListModelList<String> cmbVeredicto) {
		this.cmbVeredicto = cmbVeredicto;
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
	public void init() {
		buscarProgramaA();
		listadoSancion();
		listadoInstancia();
		cmbVeredicto = new ListModelList<String>();
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
	 * Buscar Programa Académico
	 * 
	 * @param Ninguno
	 * @return lista de programa Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
		ProgramaAcademico prog = new ProgramaAcademico(null, "Todos", null);
		listaPrograma.add(listaPrograma.size(), prog);
	}

	/**
	 * Buscar Sanción
	 * 
	 * @param
	 * @return lista de sanción
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro san = new SancionMaestro(null, "Todos", null, "Todos");
		listaSancion.add(listaSancion.size(), san);
	}

	/**
	 * Buscar Instancia Apelada
	 * 
	 * @param
	 * @return lista de instacias apeladas
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada
				.listadoInstanciaApelada();
		InstanciaApelada ins = new InstanciaApelada(null, "Todos", null,
				"Todos", null);
		listaInstanciaApelada.add(listaInstanciaApelada.size(), ins);
	}

	/**
	 * Limpiar Combos Reporte.
	 * 
	 * @param Ninguno
	 * @return Limpiar cada uno de los combos de la vista
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "objprograma", "objSancion", "objinstanciaApelada",
			"objLapso", "objVeredicto", "reportType" })
	public void limpiarCombosReporte() {
		objprograma = null;
		objSancion = null;
		objinstanciaApelada = null;
		objVeredicto = null;
		reportType = null;
	}

	/**
	 * Configurar Parámetro Sanción.
	 * 
	 * @param Ninguno
	 * @return
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroTipoSancion" })
	@Command
	public String configurarParametroSancion() {
		if (objSancion.getNombreSancion() == "Todos") {
			parametroTipoSancion = "es.id_sancion";
		} else {
			parametroTipoSancion = "'" + objSancion.getIdSancion() + "'";
		}
		return parametroTipoSancion;
	}

	/**
	 * Configurar Parámetro Instancia Apelada.
	 * 
	 * @param Ninguno
	 * @return
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroInstanciaApelada" })
	@Command
	public String configurarParametroInstanciaApelada() {
		if (objinstanciaApelada.getDescripcion() == "Todos") {
			parametroInstanciaApelada = "sa.id_instancia_apelada";
		} else {
			parametroInstanciaApelada = "'"
					+ objinstanciaApelada.getIdInstanciaApelada() + "'";
		}
		return parametroInstanciaApelada;
	}

	/**
	 * Configurar Parámetro Programa Académico.
	 * 
	 * @param Ninguno
	 * @return
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroProgramaAcademico" })
	@Command
	public String configurarParametroProgramaAcademico() {
		if (objprograma.getNombrePrograma() == "Todos") {
			parametroProgramaAcademico = "e.id_programa";
		} else {
			parametroProgramaAcademico = "'" + objprograma.getIdPrograma()
					+ "'";
		}
		return parametroProgramaAcademico;
	}

	/**
	 * Configurar Parámetro Veredicto
	 * 
	 * @param Ninguno
	 * @return
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@NotifyChange({ "parametroVeredicto" })
	@Command
	public String configurarParametroVeredicto() {
		if (objVeredicto.equals("Todos")) {
			parametroVeredicto = "sa.veredicto";
		} else {
			parametroVeredicto = "'" + objVeredicto + "'";
		}
		return parametroVeredicto;
	}

	/**
	 * Generar Reporte Estudiantes Sancionado Apelaciones Especial.
	 * 
	 * @param Ninguno
	 * @return Reporte de Estudiantes Sancionados y sus Apelaciones generado en
	 *         PDF u otro tipo de archivo
	 * @throws Si
	 *             la lista está vacía no genera el reporte.
	 */
	@Command("GenerarReporteEstudiantesSancionadoApelacionEspecial")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporteEstudiantesSancionadoApelacionEspecial() {
		if (objinstanciaApelada == null || objprograma == null
				|| objSancion == null || objVeredicto == null
				|| reportType == null) {
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		} else {
			listaEAS.clear();
			configurarParametroSancion();
			configurarParametroInstanciaApelada();
			configurarParametroProgramaAcademico();
			configurarParametroVeredicto();
			listaEAS = servicioestudianteasignaturasancion
					.buscarEstudianteAsignaturasSancion(parametroTipoSancion,
							parametroInstanciaApelada,
							parametroProgramaAcademico, parametroVeredicto);
			if (listaEAS.size() > 0) {
				reportConfig = new ReportConfig(ruta);
				reportConfig.getParameters().put(
						"ListaEstudianteAsignaturaSancionados",
						new JRBeanCollectionDataSource(listaEAS));
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaEAS));
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
	@NotifyChange({ "listaPrograma" })
	public ProgramaAcademico objCmbprograma() {
		return objprograma;
	}

	/**
	 * Objeto Combo Instancia Apelada.
	 * 
	 * @param Ninguno
	 * @return Objeto Instancia Apelada
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public InstanciaApelada objCmbinstanciaApelada() {
		return objinstanciaApelada;
	}

	/**
	 * Objeto Combo Sanción.
	 * 
	 * @param Ninguno
	 * @return Objeto Sanción
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaSancion" })
	public SancionMaestro objCmbSancion() {
		return objSancion;
	}
}
