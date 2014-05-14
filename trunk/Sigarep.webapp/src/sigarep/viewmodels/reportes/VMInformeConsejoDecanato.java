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
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.reportes.ProcedentesComision;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.reportes.Sancionados;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioReportesEstructurados;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * VM Informe Estructurado al Consejo de Decanato.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 23/01/2014
 * @last 10/05/2014
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeConsejoDecanato {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioReportesEstructurados servicioreportesestructurados;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	// --------------------------Variables de Control-------------------
	@Wire
	private String para = "";
	@Wire
	private String de = "";
	@Wire
	private String contenido = "";
	@Wire
	private String nro = "";
	@Wire("#winApelacion")
	Window ventana; // para conectarse a la ventana con el ID
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/informes/estructurados/RpInformeConsejoDecanato.jasper";
	// --------------------------Variables lista------------------------
	private List<LapsoAcademico> listaLapso;
	private List<Sancionados> listaComision = new LinkedList<Sancionados>();
	private List<ProcedentesComision> procedentesComision = new LinkedList<ProcedentesComision>();
	// --------------------------Variables Objeto-----------------------
	private LapsoAcademico objLapso;
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

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getNro() {
		return nro;
	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> listaLapso) {
		this.listaLapso = listaLapso;
	}

	public LapsoAcademico getObjLapso() {
		return objLapso;
	}

	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}

	public List<ProcedentesComision> getProcedentesComision() {
		return procedentesComision;
	}

	public void setLista(List<ProcedentesComision> procedentesComision) {
		this.procedentesComision = procedentesComision;
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
		buscarLapso();
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
					"Word (RTF)", "rtf"), new ReportType("Excel", "xls"),
					new ReportType("Excel (JXL)", "jxl"), new ReportType("CSV",
							"csv"), new ReportType("OpenOffice (ODT)", "odt")));

	/**
	 * Buscar Lapso Académico
	 * 
	 * @param
	 * @return lista de Lapso Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapso() {
		listaLapso = serviciolapsoacademico.buscarTodosLosLapsos();
	}

	/**
	 * Objeto Combo Lapso Académico.
	 * 
	 * @param Ninguno
	 * @return Objeto Lapso Académico.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapso" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;
	}

	/**
	 * Generar Informe Estructurado al Consejo de Decanato
	 * 
	 * @param Ninguno
	 * @return Informe Estructurado al Consejo de Decanato generado en PDF u
	 *         otro tipo de archivo
	 * @throws Si
	 *             la lista está vacía no genera el reporte.
	 */
	@Command("GenerarReporteApelacionesMotivo")
	@NotifyChange({ "reportConfig", "para", "de", "contenido",
			"tituloinstancia", "titulosancion", "tituloprograma" })
	public void GenerarReporte() {
		listaComision.clear();

		if (objLapso == null || para.equals("") || nro.equals("")
				|| de.equals("") || contenido.equals(""))
			mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			listaComision = servicioreportesestructurados
					.buscarEstudiantesComision(objLapso.getCodigoLapso());
			procedentesComision = servicioreportesestructurados
					.buscarProcedentesComision(objLapso.getCodigoLapso());


			if (listaComision.size() > 0) {
				reportConfig = new ReportConfig(ruta);

				reportConfig.getParameters().put("De", de);
				reportConfig.getParameters().put("Para", para);
				reportConfig.getParameters().put("Contenido", contenido);
				reportConfig.getParameters().put("nro", nro);
				reportConfig.getParameters().put("codigoLapso",
						objLapso.getCodigoLapso());
				reportConfig.getParameters().put("instancia",
						"Consejo de Decanato");
				reportConfig.getParameters().put("listaResumen",
						(new JRBeanCollectionDataSource(procedentesComision)));
				reportConfig.setType(reportType);
				reportConfig
						.setDataSource(new JRBeanCollectionDataSource(listaComision));
			} else {
				mensajeAlUsuario.informacionNoHayCoincidencias();
			}
		}
	}

	/**
	 * Limpiar. Inicializa los combos.
	 * 
	 * @param Ninguno
	 * @return Ninguno.
	 * @throws No
	 *             dispara ninguna exepción.
	 */
	@Command
	@NotifyChange({ "para", "de", "contenido", "nro", "contenido", "objLapso" })
	public void limpiar() {
		para = "";
		de = "";
		nro = "";
		contenido = "";
		objLapso = null;
	}
}
