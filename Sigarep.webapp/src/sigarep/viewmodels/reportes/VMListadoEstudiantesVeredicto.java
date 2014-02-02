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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.reportes.ChartDataApelacionesPorMotivo;
import sigarep.modelos.data.reportes.ReportConfigApelaciones;
import sigarep.modelos.data.reportes.ReportConfigSancionados;
import sigarep.modelos.data.reportes.ReportTypeApelaciones;
import sigarep.modelos.data.reportes.Sancionados;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.reportes.ServicioApelacionesPorMotivo;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListadoEstudiantesVeredicto {

	CategoryModel model;
	String type;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioApelacionesPorMotivo servicioapelacionespormotivo;
	//@WireVariable
	//private String selected = "";

	@WireVariable
	private String nombreSancion;
	@WireVariable
	private String codigoLapso;

	private List<SancionMaestro> listaTipoSancion;
	private List<LapsoAcademico> listaLapso;
	private List<ProgramaAcademico> listaPrograma;

	private List<Sancionados> sancionadosVeredicto = new LinkedList<Sancionados>();
	private List<Sancionados> sancionadosVeredicto2 = new LinkedList<Sancionados>();
	
	private SancionMaestro objSancion;

	private LapsoAcademico objLapso;

	private ProgramaAcademico objPrograma;
	
	
	private String nombre_sancion;
	private String codigo_lapso;
	private String programa_academico;
	
	@Wire private String para ="";
	@Wire private String de="";
	@Wire private String contenido="";
	
	@Wire private String tituloinstancia="TODOS"; 
	@Wire private String tituloprograma="TODOS";
	@Wire private String titulosancion="TODOS";
	
	
	public String getTitulosancion() {
		return titulosancion;
	}

	public void setTitulosancion(String titulosancion) {
		this.titulosancion = titulosancion;
	}

	public String getTituloprograma() {
		return tituloprograma;
	}

	public void setTituloprograma(String tituloprograma) {
		this.tituloprograma = tituloprograma;
	}

	public String getTituloInstancia() {
		return tituloinstancia;
	}

	public void setTituloInstancia(String tituloinstancia) {
		this.tituloinstancia = tituloinstancia;
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

	

	
	
	@WireVariable
	private InstanciaApelada instanciaApelada;
	
	private List<InstanciaApelada> listaInstanciaApelada; 

	private  @Wire Combobox cmbInstanciaApelada;

	
	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************
	ReportTypeApelaciones reportType = null;
	private ReportConfigSancionados reportConfig = null;

	@Init
	public void init() {
		buscarInstanciaApelada();
		buscarTipoSancion();
		buscarLapso();
		buscarPrograma();
		//buscarSancionados();
		// prepare chart data
		type = "column";
		model = ChartDataApelacionesPorMotivo.getModel();
	}

	@Command
	@NotifyChange({ "sancionadosVeredicto","tituloinstancia","tituloprograma","titulosancion" })
	public void buscarSancionados() {
		//System.out.println(objSancion.getNombreSancion());
		//System.out.println(objLapso.getCodigoLapso());
		// String radio = objRadio.getName();
		// System.out.println(radio);
		System.out.println(tituloinstancia);
		System.out.println(tituloprograma);
		System.out.println(titulosancion);
		sancionadosVeredicto = servicioapelacionespormotivo.buscarSancionadosPrueba(tituloinstancia, tituloprograma, titulosancion);
	
		//if (!selected.equals("")) {
			//if (getSelected().equals("resultado")) {
				/*if (objSancion.getNombreSancion() == "Todos") {
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
				*/
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
	@NotifyChange({ "listaPrograma" })
	public void buscarPrograma() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
		/*ProgramaAcademico prog = new ProgramaAcademico(null, "Todos", null);

		listaPrograma.add(0, prog);*/
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public ProgramaAcademico objCmbPrograma() {
		return objPrograma;

	}

	public ProgramaAcademico getObjPrograma() {
		return objPrograma;
	}

	public void setObjPrograma(ProgramaAcademico objPrograma) {
		this.objPrograma = objPrograma;
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
	
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void buscarInstanciaApelada() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
		System.out.println("lista"+listaInstanciaApelada);
	}
	
	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}
	public void setInstanciaApelada(InstanciaApelada instanciaapelada) {
		this.instanciaApelada = instanciaapelada;
	}
	
	
	//Combo
		@Command
		@NotifyChange({"listaInstanciaApelada"})
		public InstanciaApelada objetoComboEstadoApelacion() {
			System.out.println(instanciaApelada.getInstanciaApelada());
			return instanciaApelada;
		}
	
	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	
	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
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

	public ReportConfigSancionados getReportConfig() {
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

	/*public List<InstanciaApelada> getListaInstancia() {
		return listaInstancia;
	}

	public void setListaInstancia(List<InstanciaApelada> listaInstancia) {
		this.listaInstancia = listaInstancia;
	}*/



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

	public List<Sancionados> getsancionadosVeredicto() {
		return sancionadosVeredicto;
	}

	public void setsancionadosVeredicto(
			List<Sancionados> sancionadosVeredicto) {
		this.sancionadosVeredicto = sancionadosVeredicto;
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

		sancionadosVeredicto.clear();
		ProgramaAcademico prog = objPrograma;
		LapsoAcademico lap = objLapso;
		sancionadosVeredicto = servicioapelacionespormotivo.buscarSancionados(objLapso.getCodigoLapso(), objPrograma.getNombrePrograma());
		setSancionadosVeredicto2(servicioapelacionespormotivo.buscarSancionados(objLapso.getCodigoLapso(), "Licenciatura en Matematicas"));
		
		/*nombre_sancion = objSancion.getNombreSancion(); // OBTENER EL VALOR DE LOS COMBOS
		codigo_lapso = objLapso.getCodigoLapso();
		sancionadosVeredicto = objInstancia.getInstanciaApelada();
		*/
		//System.out.println(nombre_sancion +"   "+ codigo_lapso + "   " + instancia_apelada);
		/*
		if (objSancion.getNombreSancion() == "Todos") {
			sancionadosVeredicto = servicioapelacionespormotivo
					.buscarPorMotivoResultado_Programa(
							objLapso.getCodigoLapso(),
							objPrograma.getNombrePrograma());
		} else
			sancionadosVeredicto = servicioapelacionespormotivo
					.buscarPorMotivoResultado_ProgramaSancion(
							objLapso.getCodigoLapso(),
							objSancion.getNombreSancion(),
							objPrograma.getNombrePrograma());
		*/
		
		
		

		/*
		 * System.out.println(programaAcademico.getIdPrograma());
		 * System.out.println(lapsoAcademico.getCodigoLapso());
		 * System.out.println(lapsoAcademicoFinal.getCodigoLapso());
		 * System.out.println(reportType); System.out.println(listaAsigMayor);
		 */

		reportConfig = new ReportConfigSancionados(prog, lap); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		reportConfig.getParameters().put("De", de);
		reportConfig.getParameters().put("Para", para);
		reportConfig.getParameters().put("Contenido", contenido);
		
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
											// IMPRESION DEL REPORTE
		reportConfig.setDataSource(new JRBeanCollectionDataSource(
				sancionadosVeredicto)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
										// DATOS PARA DIBUJAR EL REPORTE
		
//			para ="";
//			de="";
//			contenido="";

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

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<Sancionados> getSancionadosVeredicto2() {
		return sancionadosVeredicto2;
	}

	public void setSancionadosVeredicto2(List<Sancionados> sancionadosVeredicto2) {
		this.sancionadosVeredicto2 = sancionadosVeredicto2;
	}



	

	
}
