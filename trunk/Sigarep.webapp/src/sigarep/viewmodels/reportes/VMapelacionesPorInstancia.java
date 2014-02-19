package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.ApelacionesPorMotivo;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.reportes.ServicioApelacionesPorMotivo;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMapelacionesPorInstancia {

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
	
	@Wire("#winApelacionesPorInstancia")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta="/WEB-INF/sigarepReportes/RApelacionesInstancia-Veredicto.jasper";

	@Init
	public void init() {
		buscarPrograma();
		buscarTipoSancion();
		buscarLapso();
		// buscarApelacionesR();
		// prepare chart data
	}

	@Command
	@NotifyChange({ "apelacionesPrograma" })
	public void buscarApelacionesR() {
		System.out.println(objSancion.getNombreSancion());
		System.out.println(objLapso.getCodigoLapso());
		
				if (objSancion.getNombreSancion() == "Todos") {
					apelacionesPrograma = servicioapelacionespormotivo
							.buscarPorInstanciaResultado_Programa(
									objLapso.getCodigoLapso(),
									objPrograma.getIdPrograma());
				} else
					apelacionesPrograma = servicioapelacionespormotivo
							.buscarPorInstanciaResultado_ProgramaSancion(
									objLapso.getCodigoLapso(),
									objSancion.getIdSancion(),
									objPrograma.getIdPrograma());

	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarPrograma() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
		
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
		listaTipoSancion.add(/* listaTipoSancion.size() */0, sanc);
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



	// ###############METODO PARA IMPRIMIR REPORTE#################
	@Command("GenerarReporteApelacionesInstancia")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporte() {

		apelacionesPrograma.clear();


		ProgramaAcademico prog = objPrograma;
		LapsoAcademico lap = objLapso;
		
		
		if (objSancion.getNombreSancion() == "Todos") {
			apelacionesPrograma = servicioapelacionespormotivo
					.buscarPorInstanciaResultado_Programa(
							objLapso.getCodigoLapso(),
							objPrograma.getIdPrograma());
		} else
			apelacionesPrograma = servicioapelacionespormotivo
					.buscarPorInstanciaResultado_ProgramaSancion(
							objLapso.getCodigoLapso(),
							objSancion.getIdSancion(),
							objPrograma.getIdPrograma());

	
		
		
		reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		
		reportConfig.getParameters().put("Titulo", "REPORTE DE APELACIONES y VEREDICTO");
		reportConfig.getParameters().put("Lapso", lap.getCodigoLapso());
		reportConfig.getParameters().put("Programa", prog.getNombrePrograma().toUpperCase());
		
		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
											// IMPRESION DEL REPORTE
		reportConfig.setDataSource(new JRBeanCollectionDataSource(
				apelacionesPrograma)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
										// DATOS PARA DIBUJAR EL REPORTE

	}

	// #####################FIN DEL METODO##########################

	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "objLapso", "programa_academico", "nombre_sancion",
	 })
	public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
			
		if (objLapso != null)
		{
			Messagebox.show("¿Realemente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					}
				}
			});		
		}
		else{
		Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					
					}
				}
			});		
		}
	}
	
	

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
