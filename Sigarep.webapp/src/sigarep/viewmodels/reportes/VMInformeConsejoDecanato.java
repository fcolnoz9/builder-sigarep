package sigarep.viewmodels.reportes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.reportes.Sancionados;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.reportes.ServicioReportes;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeConsejoDecanato {

	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioReportes servicioreportes;

	@WireVariable
	private String codigoLapso;

	private List<LapsoAcademico> listaLapso;

	private List<Sancionados> listaInformatica = new LinkedList<Sancionados>();
	private List<Sancionados> listaAnalisis = new LinkedList<Sancionados>();
	private List<Sancionados> listaProduccion = new LinkedList<Sancionados>();
	private List<Sancionados> listaMatematicas= new LinkedList<Sancionados>();
	

	private LapsoAcademico objLapso;
	
	
	private String codigo_lapso;
	
	@Wire private String para ="";
	@Wire private String de="";
	@Wire private String contenido="";
	
	private int procedentesInf;
	private int procedentesPro;
	private int procedentesAna;
	private int procedentesMat;
	private int denegadosInf;
	private int denegadosPro;
	private int denegadosAna;
	private int denegadosMat;
	


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
	
	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;

	String ruta="/WEB-INF/sigarepReportes/informes/estructurados/RpInformeDeComisionPorPrograma.jasper";
	
	
	@Init
	public void init() {
		buscarLapso();
		//buscarSancionados();
		// prepare chart data
	}

//	@Command
//	@NotifyChange({ "sancionadosVeredicto","tituloinstancia","tituloprograma","titulosancion" })
//	public void buscarSancionados() {
//		System.out.println(objSancion.getNombreSancion());
//		System.out.println(objLapso.getCodigoLapso());
//		String radio = objRadio.getName();
//		System.out.println(radio);
//		System.out.println(tituloinstancia);
//		System.out.println(tituloprograma);
//		System.out.println(titulosancion);
//		sancionadosVeredicto = servicioapelacionespormotivo.buscarSancionadosPrueba(1/*tituloinstancia, tituloprograma, titulosancion*/);
//	
//		if (!selected.equals("")) {
//			if (getSelected().equals("resultado")) {
//				if (objSancion.getNombreSancion() == "Todos") {
//					apelacionesPrograma = servicioapelacionespormotivo
//							.buscarPorMotivoResultado_Programa(
//									objLapso.getCodigoLapso(),
//									objPrograma.getNombrePrograma());
//				} else
//					apelacionesPrograma = servicioapelacionespormotivo
//							.buscarPorMotivoResultado_ProgramaSancion(
//									objLapso.getCodigoLapso(),
//									objSancion.getNombreSancion(),
//									objPrograma.getNombrePrograma());
//				
//				 model = ChartDataTipoDeSexo.getModel(lista);
//			} else {
//				Messagebox.show("Debe Seleccionar una Opci�n", "Advertencia",
//						Messagebox.OK, Messagebox.EXCLAMATION);
//			}
//		} else {
//			Messagebox.show("Debe Seleccionar una Opci�n", "Advertencia",
//					Messagebox.OK, Messagebox.EXCLAMATION);
//		}
//	}

		
		
	
//	@Command
//	@NotifyChange({ "listaPrograma" })
//	public void buscarPrograma() {
//		listaPrograma = servicioprogramaacademico.listadoProgramas();
//		/*ProgramaAcademico prog = new ProgramaAcademico(null, "Todos", null);
//
//		listaPrograma.add(0, prog);*/
//	}
//
//	@Command
//	@NotifyChange({ "listaPrograma" })
//	public ProgramaAcademico objCmbPrograma() {
//		return objPrograma;
//
//	}
//
//	public ProgramaAcademico getObjPrograma() {
//		return objPrograma;
//	}
//
//	public void setObjPrograma(ProgramaAcademico objPrograma) {
//		this.objPrograma = objPrograma;
//	}

	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapso() {
		listaLapso = serviciolapsoacademico.buscarTodosLosLapsos();
		/*LapsoAcademico lap = new LapsoAcademico("asd", null, null, null);
		listaLapso.add(0, lap);*/
	}

	@Command
	@NotifyChange({ "listaLapso" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;

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

	// Lista que me permite llenar el combo para elegir el formato
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(new ReportType("PDF", "pdf"), new ReportType("HTML",
					"html"), new ReportType("Word (RTF)", "rtf"),
					new ReportType("Excel", "xls"), new ReportType(
							"Excel (JXL)", "jxl"),
					new ReportType("CSV", "csv"), new ReportType(
							"OpenOffice (ODT)", "odt")));



//	public List<InstanciaApelada> getListaInstancia() {
//		return listaInstancia;
//	}
//
//	public void setListaInstancia(List<InstanciaApelada> listaInstancia) {
//		this.listaInstancia = listaInstancia;
//	}



//	public List<SancionMaestro> getListaTipoSancion() {
//		return listaTipoSancion;
//	}
//
//	public void setListaTipoSancion(List<SancionMaestro> listaTipoSancion) {
//		this.listaTipoSancion = listaTipoSancion;
//	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> listaLapso) {
		this.listaLapso = listaLapso;
	}

//	public List<Sancionados> getsancionadosVeredicto() {
//		return sancionadosVeredicto;
//	}
//
//	public void setsancionadosVeredicto(
//			List<Sancionados> sancionadosVeredicto) {
//		this.sancionadosVeredicto = sancionadosVeredicto;
//	}

	public LapsoAcademico getObjLapso() {
		return objLapso;
	}

	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}

	/*public String getSelected() {
		return selected;
	}

	@NotifyChange
	public void setSelected(String selected) {
		this.selected = selected;
	}*/

	// ###############METODO PARA IMPRIMIR REPORTE#################
	@Command("GenerarReporteApelacionesMotivo")
	@NotifyChange({ "reportConfig","para","de","contenido","tituloinstancia","titulosancion","tituloprograma" })
	public void GenerarReporte() {

		listaInformatica.clear();
		listaAnalisis.clear();
		listaProduccion.clear();
		listaMatematicas.clear();
//		ProgramaAcademico prog = objPrograma;
		listaInformatica = servicioreportes.buscarEstudiantesComision(1);
		procedentesInf = listaInformatica.get(0).getProcedentes();
		denegadosInf = listaInformatica.get(0).getNoProcedentes();
		listaProduccion = servicioreportes.buscarEstudiantesComision(2);
		procedentesPro = listaProduccion.get(0).getProcedentes();
		denegadosPro = listaProduccion.get(0).getNoProcedentes();
		listaAnalisis = servicioreportes.buscarEstudiantesComision(3);
		procedentesAna = listaAnalisis.get(0).getProcedentes();
		denegadosAna = listaAnalisis.get(0).getNoProcedentes();
		listaMatematicas = servicioreportes.buscarEstudiantesComision(4);
		procedentesMat = listaMatematicas.get(0).getProcedentes();
		denegadosMat = listaMatematicas.get(0).getNoProcedentes();
		//sancionadosVeredicto = servicioapelacionespormotivo.buscarSancionados(objLapso.getCodigoLapso(), objPrograma.getNombrePrograma());
		//setSancionadosVeredicto2(servicioapelacionespormotivo.buscarSancionados(objLapso.getCodigoLapso(), "Licenciatura en Matematicas"));
		
		
//		System.out.println("Procedentes: " + procedentes);
//		System.out.println("Procedentes2: " + procedentes2);
//		System.out.println("Procedentes3: " + procedentes3);
//		System.out.println("Procedentes4: " + procedentes4);
//		System.out.println("No Procedentes: " + denegados);
//		System.out.println("No Procedentes2: " + denegados2);
//		System.out.println("No Procedentes3: " + denegados3);
//		System.out.println("No Procedentes4: " + denegados4);
		
//		nombre_sancion = objSancion.getNombreSancion(); // OBTENER EL VALOR DE LOS COMBOS
//		codigo_lapso = objLapso.getCodigoLapso();
//		sancionadosVeredicto = objInstancia.getInstanciaApelada();
		
//		System.out.println(nombre_sancion +"   "+ codigo_lapso + "   " + instancia_apelada);
//		
//		if (objSancion.getNombreSancion() == "Todos") {
//			sancionadosVeredicto = servicioapelacionespormotivo
//					.buscarPorMotivoResultado_Programa(
//							objLapso.getCodigoLapso(),
//							objPrograma.getNombrePrograma());
//		} else
//			sancionadosVeredicto = servicioapelacionespormotivo
//					.buscarPorMotivoResultado_ProgramaSancion(
//							objLapso.getCodigoLapso(),
//							objSancion.getNombreSancion(),
//							objPrograma.getNombrePrograma());
//		
//		
//		
//		
//
//		
//		  System.out.println(programaAcademico.getIdPrograma());
//		  System.out.println(lapsoAcademico.getCodigoLapso());
//		  System.out.println(lapsoAcademicoFinal.getCodigoLapso());
//		  System.out.println(reportType); System.out.println(listaAsigMayor);
		 
		
		reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		reportConfig.getParameters().put("De", de);
		//reportConfig.getParameters().put("SUBREPORT_DIR", "/WEB-INF/sigarepReportes/Lista de Resultado de Apelaciones_subreport2.jasper");
		reportConfig.getParameters().put("Para", para);
		reportConfig.getParameters().put("Contenido", contenido);
		reportConfig.getParameters().put("codigoLapso", objLapso.getCodigoLapso());
		reportConfig.getParameters().put("procedentesInf", procedentesInf);
		reportConfig.getParameters().put("denegadosInf", denegadosInf);
		reportConfig.getParameters().put("procedentesAna", procedentesAna);
		reportConfig.getParameters().put("denegadosAna", denegadosAna);
		reportConfig.getParameters().put("procedentesPro", procedentesPro);
		reportConfig.getParameters().put("denegadosPro", denegadosPro);
		reportConfig.getParameters().put("procedentesMat", procedentesMat);
		reportConfig.getParameters().put("denegadosMat", denegadosMat);
		reportConfig.getParameters().put("listaInformatica", (new JRBeanCollectionDataSource(listaInformatica)));
		reportConfig.getParameters().put("listaAnalisis", (new JRBeanCollectionDataSource(listaAnalisis)));
		reportConfig.getParameters().put("listaProduccion", (new JRBeanCollectionDataSource(listaProduccion)));
		reportConfig.getParameters().put("listaMatematicas", (new JRBeanCollectionDataSource(listaMatematicas)));
		
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
											// IMPRESION DEL REPORTE
		//reportConfig.setDataSource(new JRBeanCollectionDataSource(sancionadosVeredicto)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
										// DATOS PARA DIBUJAR EL REPORTE
		//String subruta = "/WEB-INF/sigarepReportes/Copy of Lista de Resultado de Apelaciones_subreport1.jasper";
		//"C:\\Users\\Nicolas\\workspace\\Sigarep.webapp\\WebContent\\WEB-INF\\sigarepReportes\\Copy Lista de Resultado de Apelaciones_subreport1.jasper"
		//subReportConfig = new ReportConfig(subruta);
		//subReportConfig.setType(reportType);
//			para ="";
//			de="";
//			contenido="";

	}

	// #####################FIN DEL METODO##########################

	public String getCodigo_lapso() {
		return codigo_lapso;
	}

	public void setCodigo_lapso(String codigo_lapso) {
		this.codigo_lapso = codigo_lapso;
	}

//	public List<ProgramaAcademico> getListaPrograma() {
//		return listaPrograma;
//	}
//
//	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
//		this.listaPrograma = listaPrograma;
//	}

//	public ReportConfig getSubReportConfig() {
//		return subReportConfig;
//	}
//
//	public void setSubReportConfig(ReportConfig subReportConfig) {
//		this.subReportConfig = subReportConfig;
//	}

}