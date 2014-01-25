package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.ListModelList;

import sigarep.modelos.data.reportes.ReportConfigApelaciones;
import sigarep.modelos.data.reportes.ReportTypeApelaciones;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.ApelacionesPorMotivo;
import sigarep.modelos.data.reportes.ChartDataApelacionesPorMotivo;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.reportes.ServicioApelacionesPorMotivo;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMapelacionesPorMotivo {

	CategoryModel model;
	String type;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioApelacionesPorMotivo servicioapelacionespormotivo;

	//@WireVariable
	//private String selected = "";

	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreSancion;
	@WireVariable
	private String codigoLapso;

	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<SancionMaestro> listaTipoSancion;
	private List<LapsoAcademico> listaLapso;

	private List<ApelacionesPorMotivo> apelacionesPrograma = new LinkedList<ApelacionesPorMotivo>();

	private SancionMaestro objSancion;

	private LapsoAcademico objLapso;

	private ProgramaAcademico objPrograma;

	private String nombre_sancion;
	private String codigo_lapso;
	private String programa_academico;

	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************
	ReportTypeApelaciones reportType = null;
	private ReportConfigApelaciones reportConfig = null;

	@Init
	public void init() {
		buscarPrograma();
		buscarTipoSancion();
		buscarLapso();
		// buscarApelacionesR();
		// prepare chart data
		type = "column";
		model = ChartDataApelacionesPorMotivo.getModel();
	}

	@Command
	@NotifyChange({ "apelacionesPrograma" })
	public void buscarApelacionesR() {
		System.out.println(objSancion.getNombreSancion());
		System.out.println(objLapso.getCodigoLapso());
		// String radio = objRadio.getName();
		// System.out.println(radio);

		//if (!selected.equals("")) {
			//if (getSelected().equals("resultado")) {
				if (objSancion.getNombreSancion() == "Todos") {
					apelacionesPrograma = servicioapelacionespormotivo
							.buscarPorMotivoResultado_Programa(
									objLapso.getCodigoLapso(),
									objPrograma.getNombrePrograma());
				} else
					apelacionesPrograma = servicioapelacionespormotivo
							.buscarPorMotivoResultado_ProgramaSancion(
									objLapso.getCodigoLapso(),
									objSancion.getNombreSancion(),
									objPrograma.getNombrePrograma());

				// model = ChartDataTipoDeSexo.getModel(lista);
			//} else {
			//	Messagebox.show("Debe Seleccionar una Opción", "Advertencia",
			//			Messagebox.OK, Messagebox.EXCLAMATION);
			//}
		//} else {
		//	Messagebox.show("Debe Seleccionar una Opción", "Advertencia",
		//			Messagebox.OK, Messagebox.EXCLAMATION);
		//}
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarPrograma() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
		/*ProgramaAcademico prog = new ProgramaAcademico(null, "Todos", null);

		listaPrograma.add(0, prog);*/
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public ProgramaAcademico objCmbPrograma() {
		return objPrograma;

	}

	@Command
	@NotifyChange({ "listaTipoSancion" })
	public void buscarTipoSancion() {
		listaTipoSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro sanc = new SancionMaestro(null, null, null, "Todos");
		listaTipoSancion.add(0, sanc);
	}

	@Command
	@NotifyChange({ "listaTipoSancion" })
	public SancionMaestro objCmbSancion() {
		return objSancion;

	}

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

	public CategoryModel getModel() {
		return model;
	}

	public String getType() {
		return type;
	}

	public ListModelList<ReportTypeApelaciones> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfigApelaciones getReportConfig() {
		return reportConfig;
	}

	public ReportTypeApelaciones getReportType() {
		return reportType;
	}

	public void setReportType(ReportTypeApelaciones reportType) {
		this.reportType = reportType;
	}

	// Lista que me permite llenar el combo para elegir el formato
	private ListModelList<ReportTypeApelaciones> reportTypesModel = new ListModelList<ReportTypeApelaciones>(
			Arrays.asList(new ReportTypeApelaciones("PDF", "pdf"), new ReportTypeApelaciones("HTML",
					"html"), new ReportTypeApelaciones("Word (RTF)", "rtf"),
					new ReportTypeApelaciones("Excel", "xls"), new ReportTypeApelaciones(
							"Excel (JXL)", "jxl"),
					new ReportTypeApelaciones("CSV", "csv"), new ReportTypeApelaciones(
							"OpenOffice (ODT)", "odt")));

	@GlobalCommand("configChanged")
	@NotifyChange("type")
	public void onConfigChanged(@BindingParam("type") String type) {
		this.type = type;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public List<SancionMaestro> getListaTipoSancion() {
		return listaTipoSancion;
	}

	public void setListaTipoSancion(List<SancionMaestro> listaTipoSancion) {
		this.listaTipoSancion = listaTipoSancion;
	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> listaLapso) {
		this.listaLapso = listaLapso;
	}

	public List<ApelacionesPorMotivo> getapelacionesPrograma() {
		return apelacionesPrograma;
	}

	public void setapelacionesPrograma(
			List<ApelacionesPorMotivo> apelacionesPrograma) {
		this.apelacionesPrograma = apelacionesPrograma;
	}

	public SancionMaestro getObjSancion() {
		return objSancion;
	}

	public void setObjSancion(SancionMaestro objSancion) {
		this.objSancion = objSancion;
	}

	public LapsoAcademico getObjLapso() {
		return objLapso;
	}

	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}

	public ProgramaAcademico getObjPrograma() {
		return objPrograma;
	}

	public void setObjPrograma(ProgramaAcademico objPrograma) {
		this.objPrograma = objPrograma;
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
	@NotifyChange({ "reportConfig" })
	public void GenerarReporte() {

		apelacionesPrograma.clear();
		nombre_sancion = objSancion.getNombreSancion(); // OBTENER EL VALOR DE LOS COMBOS
		codigo_lapso = objLapso.getCodigoLapso();
		programa_academico = objPrograma.getNombrePrograma();
		
		System.out.println(nombre_sancion +"   "+ codigo_lapso + "   " + programa_academico);
		
		if (objSancion.getNombreSancion() == "Todos") {
			apelacionesPrograma = servicioapelacionespormotivo
					.buscarPorMotivoResultado_Programa(
							objLapso.getCodigoLapso(),
							objPrograma.getNombrePrograma());
		} else
			apelacionesPrograma = servicioapelacionespormotivo
					.buscarPorMotivoResultado_ProgramaSancion(
							objLapso.getCodigoLapso(),
							objSancion.getNombreSancion(),
							objPrograma.getNombrePrograma());

		/*
		 * System.out.println(programaAcademico.getIdPrograma());
		 * System.out.println(lapsoAcademico.getCodigoLapso());
		 * System.out.println(lapsoAcademicoFinal.getCodigoLapso());
		 * System.out.println(reportType); System.out.println(listaAsigMayor);
		 */

		reportConfig = new ReportConfigApelaciones(); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
											// IMPRESION DEL REPORTE
		reportConfig.setDataSource(new JRBeanCollectionDataSource(
				apelacionesPrograma)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
										// DATOS PARA DIBUJAR EL REPORTE

	}

	// #####################FIN DEL METODO##########################


	public String getNombre_sancion() {
		return nombre_sancion;
	}

	public void setNombre_sancion(String nombre_sancion) {
		this.nombre_sancion = nombre_sancion;
	}

	public String getCodigo_lapso() {
		return codigo_lapso;
	}

	public void setCodigo_lapso(String codigo_lapso) {
		this.codigo_lapso = codigo_lapso;
	}

	public String getPrograma_academico() {
		return programa_academico;
	}

	public void setPrograma_academico(String programa_academico) {
		this.programa_academico = programa_academico;
	}

}
