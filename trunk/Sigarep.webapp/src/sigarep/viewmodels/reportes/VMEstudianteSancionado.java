package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.EstudianteSancionado;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioReporteEstudianteSancionado;

/**
 * VM Reporte Estudiante Sancionado.
 * 
 * @author Equipo Builder
 * @version 2.0.3
 * @since 23/01/2014
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudianteSancionado {
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
	private ServicioReporteEstudianteSancionado servicioreporteestudiantesancionado;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	// --------------------------Variables de Control-------------------
	@Wire("#cmbAsignaturas")
	Combobox cmbAsignatura;
	private String objsexo;
	private String objVeredicto;
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/configurable/RpEstudiantesSancionadosConfigurable.jasper";
	// --------------------------Variables lista------------------------
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<LapsoAcademico> listaLapsoAcademico;
	private List<SancionMaestro> listaSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<EstadoApelacion> listaEdoApelacion;
	private List<Asignatura> listaAsignaturas;
	private List<EstudianteSancionado> listaE = new LinkedList<EstudianteSancionado>();
	private ListModelList<String> cmbSexo;
	private ListModelList<String> cmbVeredicto;
	private ListModelList<String> cmbEdoApelacion;
	// --------------------------Variables Objeto-----------------------
	private LapsoAcademico objLapso;
	private SancionMaestro objSancion, objCmbSancion;
	private ProgramaAcademico objprograma;
	private TipoMotivo objtipoMotivo;
	private InstanciaApelada objinstanciaApelada;
	private EstadoApelacion objEdoApelacion;
	private Asignatura asignaturas, objAsignatura;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	// --------------------------Variables tira sql---------------------
	private String parametroLapsoAcademico;
	private String parametroTipoSancion;
	private String parametroInstanciaApelada;
	private String parametroMotivo;
	private String parametroProgramaAcademico;
	private String parametroSexo;
	private String parametroVeredicto;
	private String parametroEdoApelacion;
	private String parametroAsignatura;
	private String validarAsignatura;

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

	public LapsoAcademico getObjLapso() {
		return objLapso;
	}

	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}

	public TipoMotivo getObjtipoMotivo() {
		return objtipoMotivo;
	}

	public void setObjtipoMotivo(TipoMotivo objtipoMotivo) {
		this.objtipoMotivo = objtipoMotivo;
	}

	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}

	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}

	public Asignatura getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Asignatura asignaturas) {
		this.asignaturas = asignaturas;
	}

	public SancionMaestro getObjCmbSancion() {
		return objCmbSancion;
	}

	public void setObjCmbSancion(SancionMaestro objCmbSancion) {
		this.objCmbSancion = objCmbSancion;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(
			List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public String getValidarAsignatura() {
		return validarAsignatura;
	}

	public void setValidarAsignatura(String validarAsignatura) {
		this.validarAsignatura = validarAsignatura;
	}

	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}

	public List<EstadoApelacion> getListaEdoApelacion() {
		return listaEdoApelacion;
	}

	public void setListaEdoApelacion(List<EstadoApelacion> listaEdoApelacion) {
		this.listaEdoApelacion = listaEdoApelacion;
	}

	public ServicioAsignatura getServicioAsignatura() {
		return servicioAsignatura;
	}

	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}

	public void setServicioAsignatura(ServicioAsignatura servicioAsignatura) {
		this.servicioAsignatura = servicioAsignatura;
	}

	public List<EstudianteSancionado> getListaE() {
		return listaE;
	}

	public void setListaE(List<EstudianteSancionado> listaE) {
		this.listaE = listaE;
	}

	public ListModelList<String> getCmbSexo() {
		cmbSexo.add("F");
		cmbSexo.add("M");
		cmbSexo.add("Todos");
		return cmbSexo;
	}

	public void setCmbSexo(ListModelList<String> cmbSexo) {
		this.cmbSexo = cmbSexo;
	}

	public String getParametroLapsoAcademico() {
		return parametroLapsoAcademico;
	}

	public void setParametroLapsoAcademico(String parametroLapsoAcademico) {
		this.parametroLapsoAcademico = parametroLapsoAcademico;
	}

	public String getParametroTipoSancion() {
		return parametroTipoSancion;
	}

	public void setParametroTipoSancion(String parametroTipoSancion) {
		this.parametroTipoSancion = parametroTipoSancion;
	}

	public String getParametroMotivo() {
		return parametroMotivo;
	}

	public void setParametroMotivo(String parametroMotivo) {
		this.parametroMotivo = parametroMotivo;
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

	public String getParametroSexo() {
		return parametroSexo;
	}

	public void setParametroSexo(String parametroSexo) {
		this.parametroSexo = parametroSexo;
	}

	public String getObjsexo() {
		return objsexo;
	}

	public void setObjsexo(String objsexo) {
		this.objsexo = objsexo;
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

	public ListModelList<String> getCmbVeredicto() {
		cmbVeredicto.add("Procedente");
		cmbVeredicto.add("No Procedente");
		cmbVeredicto.add("Todos");
		return cmbVeredicto;
	}

	public EstadoApelacion getObjEdoApelacion() {
		return objEdoApelacion;
	}

	public void setObjEdoApelacion(EstadoApelacion objEdoApelacion) {
		this.objEdoApelacion = objEdoApelacion;
	}

	public ListModelList<String> getCmbEdoApelacion() {
		return cmbEdoApelacion;
	}

	public void setCmbEdoApelacion(ListModelList<String> cmbEdoApelacion) {
		this.cmbEdoApelacion = cmbEdoApelacion;
	}

	public void setCmbVeredicto(ListModelList<String> cmbVeredicto) {
		this.cmbVeredicto = cmbVeredicto;
	}

	public String getParametroEdoApelacion() {
		return parametroEdoApelacion;
	}

	public void setParametroEdoApelacion(String parametroEdoApelacion) {
		this.parametroEdoApelacion = parametroEdoApelacion;
	}

	public String getParametroAsignatura() {
		return parametroAsignatura;
	}

	public void setParametroAsignatura(String parametroAsignatura) {
		this.parametroAsignatura = parametroAsignatura;
	}

	public Asignatura getObjAsignatura() {
		return objAsignatura;
	}

	public void setObjAsignatura(Asignatura objAsignatura) {
		this.objAsignatura = objAsignatura;
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
		buscarTipoMotivo();
		buscarProgramaA();
		buscarActivoLapso();
		listadoSancion();
		listadoInstancia();
		buscarEdoApelacion();
		cmbSexo = new ListModelList<String>();
		cmbVeredicto = new ListModelList<String>();
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
		Selectors.wireComponents(cmbAsignatura, this, false);
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
			Arrays.asList(new ReportType("Reporte en Excel", "xls"),
					new ReportType("Word", "doc"), new ReportType("OpenOffice",
							"ODT"), new ReportType("CSV", "csv"),
					new ReportType("Excel (JXL)", "jxl")));

	/**
	 * Buscar asignaturas
	 * 
	 * @param IdPrograma
	 * @return lista de de asignaturas, programas.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaAsignaturas", "objprograma" })
	public void buscarAsignaturas() {
		if (objprograma.getNombrePrograma() == "Todos") {
			listaAsignaturas = servicioAsignatura.listaAsignaturas();
		} else {
			listaAsignaturas = servicioAsignatura
					.buscarAsignaturasPorPrograma(objprograma);
		}
	}

	/**
	 * Buscar estado de apelación
	 * 
	 * @param Ninguno
	 * @return Lista de estado de apelación
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaEdoApelacion" })
	public void buscarEdoApelacion() {
		listaEdoApelacion = servicioestadoapelacion
				.listadoEstadoApelacionActivas();
		EstadoApelacion edo_ape = new EstadoApelacion(null, "Todos", "Todos",
				null);
		listaEdoApelacion.add(listaEdoApelacion.size(), edo_ape);
	}

	/**
	 * Buscar tipo motivo
	 * 
	 * @param Ninguno
	 * @return Lista de tipo motivo.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTipoMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
		TipoMotivo mot = new TipoMotivo(null, "Todos", null, "Todos", false);
		listaTipoMotivo.add(listaTipoMotivo.size()/* 0 */, mot);
	}

	/**
	 * Buscar programa académico
	 * 
	 * @param Ninguno
	 * @return Lista de programa académico.
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
	 * Buscar lapso académico
	 * 
	 * @param Ninguno
	 * @return lista de lapso Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarActivoLapso() {
		listaLapsoAcademico = serviciolapsoacademico.buscarTodosLosLapsos();
		LapsoAcademico lap = new LapsoAcademico("Todos", null, null, null);
		listaLapsoAcademico.add(listaLapsoAcademico.size(), lap);
	}

	/**
	 * Buscar Sanción
	 * 
	 * @param Ninguno
	 * @return lista de sanción
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro san = new SancionMaestro(3, "Todos", null, "Todos");
		listaSancion.add(listaSancion.size(), san);
	}

	/**
	 * Buscar instancia
	 * 
	 * @param Ninguno
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
	 * Buscar estudiante sancionado
	 * 
	 * @param programa
	 *            , objSancion, objtipoMotivo,objinstanciaApelada, objLapso,
	 *            objVeredicto,objEdoApelacion, asignaturas, objsexo,reportType
	 * @return ListaE lista estudiantes Sancionados
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaE", "parametroAsignatura" })
	public void buscarEstudianteSancionado() {
		parametroAsignatura = "";
		if (objinstanciaApelada == null || objLapso == null
				|| objprograma == null || objSancion == null || objsexo == null
				|| objtipoMotivo == null || objVeredicto == null
				|| objEdoApelacion == null || reportType == null) {
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		} else {
			configurarLapsoAcademico();
			configurarParametroSancion();
			configurarParametroInstanciaApelada();
			configurarParametroMotivo();
			configurarParametroProgramaAcademico();
			configurarParametroSexo();
			configurarParametroVeredicto();
			configurarParametroEdoApelacion();
			if (objSancion.getIdSancion() == 2) {// idsancion= 2 es RR
				parametroAsignatura = "";
				configurarParametroAsignatura();
			}
			listaE = servicioreporteestudiantesancionado.buscarTodosSancionado(
					parametroLapsoAcademico, parametroTipoSancion,
					parametroInstanciaApelada, parametroMotivo,
					parametroProgramaAcademico, parametroSexo,
					parametroVeredicto, parametroEdoApelacion,
					parametroAsignatura);
		}
	}

	/**
	 * Limpiar Estudiante sancionado.
	 * 
	 * @param programa
	 *            , objSancion, objtipoMotivo,objinstanciaApelada, objLapso,
	 *            objVeredicto,objEdoApelacion, asignaturas, objsexo
	 * @return Deja los Campos en NULL en cada uno de los combos de la vista
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "objprograma", "objSancion", "objtipoMotivo",
			"objinstanciaApelada", "objLapso", "objVeredicto",
			"objEdoApelacion", "objAsignatura", "objsexo", "reportType" })
	public void limpiarCombos() {
		objprograma = null;
		objSancion = null;
		objtipoMotivo = null;
		objinstanciaApelada = null;
		objLapso = null;
		objVeredicto = null;
		objEdoApelacion = null;
		objAsignatura = null;
		objsexo = null;
		reportType = null;
	}

	/**
	 * Configurar lapso academico
	 * 
	 * @param parametroLapsoAcademico
	 * @return parametroLapsoAcademico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroLapsoAcademico" })
	@Command
	public String configurarLapsoAcademico() {
		if (objLapso.getCodigoLapso() == "Todos") {
			parametroLapsoAcademico = "sap.codigo_lapso";
		} else {
			parametroLapsoAcademico = "'" + objLapso.getCodigoLapso() + "'";
		}
		return parametroLapsoAcademico;
	}

	/**
	 * Configurar Parametro Sancion
	 * 
	 * @param parametroTipoSancion
	 * @return parametroTipoSancion
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroTipoSancion" })
	@Command
	public String configurarParametroSancion() {
		if (objSancion.getNombreSancion() == "Todos") {
			parametroTipoSancion = "esa.id_sancion";
		} else {
			parametroTipoSancion = "'" + objSancion.getIdSancion() + "'";
		}
		return parametroTipoSancion;
	}

	/**
	 * Configurar Parametro Instancia Apelada
	 * 
	 * @param parametroInstanciaApelada
	 * @return parametroInstanciaApelada
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroInstanciaApelada" })
	@Command
	public String configurarParametroInstanciaApelada() {
		if (objinstanciaApelada.getDescripcion() == "Todos") {
			parametroInstanciaApelada = "sap.id_instancia_apelada";
		} else {
			parametroInstanciaApelada = "'"
					+ objinstanciaApelada.getIdInstanciaApelada() + "'";
		}
		return parametroInstanciaApelada;
	}

	/**
	 * Configurar Parametro Motivo configurarParametroMotivo()
	 * 
	 * @param parametroMotivo
	 * @return parametroMotivo
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroMotivo" })
	@Command
	public String configurarParametroMotivo() {
		if (objtipoMotivo.getDescripcion() == "Todos") {
			parametroMotivo = "mot.id_tipo_motivo";
		} else {
			parametroMotivo = "'" + objtipoMotivo.getIdTipoMotivo() + "'";
		}
		return parametroMotivo;
	}

	/**
	 * Configurar Parametro de Programa Academico
	 * 
	 * @param parametroProgramaAcademico
	 * @return parametroProgramaAcademico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroProgramaAcademico" })
	@Command
	public String configurarParametroProgramaAcademico() {
		if (objprograma.getNombrePrograma() == "Todos") {
			parametroProgramaAcademico = "es.id_programa";
		} else {
			parametroProgramaAcademico = "'" + objprograma.getIdPrograma()
					+ "'";
		}
		return parametroProgramaAcademico;
	}

	/**
	 * Configurar Parametro Sexo
	 * 
	 * @param parametroSexo
	 * @return parametroSexo
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroSexo" })
	@Command
	public String configurarParametroSexo() {
		if (objsexo.equals("Todos")) {
			parametroSexo = "es.sexo";
		} else {
			parametroSexo = "'" + objsexo + "'";
		}
		return parametroSexo;
	}

	/**
	 * Configurar Parametro Veredicto
	 * 
	 * @param parametroVeredicto
	 * @return parametroVeredicto
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroVeredicto" })
	@Command
	public String configurarParametroVeredicto() {
		if (objVeredicto.equals("Todos")) {
			parametroVeredicto = "sap.veredicto";
		} else {
			parametroVeredicto = "'" + objVeredicto.toUpperCase() + "'";
		}
		return parametroVeredicto;
	}

	/**
	 * Configurar Parametro de Estado Apelacion
	 * 
	 * @param parametroEdoApelacion
	 * @return parametroEdoApelacion
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroEdoApelacion" })
	@Command
	public String configurarParametroEdoApelacion() {
		if (objEdoApelacion.getNombreEstado().equals("Todos")) {
			parametroEdoApelacion = "ape_edo_ape.id_estado_apelacion";
		} else {
			parametroEdoApelacion = "'"
					+ objEdoApelacion.getIdEstadoApelacion() + "'";
		}
		return parametroEdoApelacion;
	}

	/**
	 * Configurar Parametro Asignatura
	 * 
	 * @param parametroAsignatura
	 * @return parametroAsignatura
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@NotifyChange({ "parametroAsignatura" })
	@Command
	public String configurarParametroAsignatura() {
		if (objAsignatura.getNombreAsignatura() == "Todos") {
			parametroAsignatura = "asig.codigo_asignatura";
		} else {
			parametroAsignatura = "'" + objAsignatura.getCodigoAsignatura()
					+ "'";
		}
		return parametroAsignatura;
	}

	/**
	 * Configurar Combo Asignatura
	 * 
	 * @param cmbAsignatura
	 *            ,objAsignatura,parametroAsignatura
	 * @return cmbAsignatura.setDisabled(true) o
	 *         cmbAsignatura.setDisabled(false)
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "cmbAsignatura", "objAsignatura", "parametroAsignatura" })
	public void configurarComboAsignatura() {
		if (objSancion.getIdSancion() != 1
				&& objSancion.getDescripcion() == "Todos") {
			cmbAsignatura.setDisabled(false);
			objAsignatura = new Asignatura(null, true, "Todos", 3, null);
		} else {
			cmbAsignatura.setDisabled(true);
			parametroAsignatura = "val";
		}
	}

	/**
	 * Generar Reporte Estudiantes Sancionados Configurable
	 * 
	 * @category Reporte Ireport configuracion
	 * @param reportConfig
	 * @return reportConfig actualizado con los Datos de La Lista(listaE)
	 * @throws JRException
	 */
	@NotifyChange({ "reportConfig" })
	@Command("GenerarReporteEstudiantesSancionadosConfigurable")
	public void GenerarReporteEstudiantesSancionadosConfigurable()
			throws JRException {
		if (listaE.size() > 0) {
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("ListaSancionados",
					new JRBeanCollectionDataSource(listaE));
			reportConfig.getParameters().put("Report name", "Reporte");
			reportConfig.getParameters().put("jasperReport name", "Reporte");
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(listaE));
			JasperPrint jasperPrint = JasperFillManager.fillReport(Sessions
					.getCurrent().getWebApp().getRealPath(ruta), null,
					new JRBeanCollectionDataSource(listaE));
			// view the report using JasperViewer
			JasperViewer.viewReport(jasperPrint, false);
		} else {
			mensajeAlUsuario.informacionNoHayCoincidencias();
		}
	}

	/**
	 * Objeto Combo Tipo Motivo.
	 * 
	 * @param Ninguno
	 * @return Objeto Tipo Motivo
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public TipoMotivo objCmbtipoMotivo() {
		return objtipoMotivo;
	}

	/**
	 * Objeto Combo Estado Apelación.
	 * 
	 * @param Ninguno
	 * @return Objeto Estado Apelación
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaEdoApelacion" })
	public EstadoApelacion objCmbEdoApelacion() {
		return objEdoApelacion;
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

	/**
	 * Objeto Combo Instancia.
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
	 * Objeto Combo Lapso.
	 * 
	 * @param Ninguno
	 * @return Objeto Lapso Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;
	}
}