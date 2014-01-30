package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

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


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMasignaturasMayorCantidadSancionados {
	
	//*************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	String ruta="/WEB-INF/sigarepReportes/RpAsignaturasSancionadosVsApelaciones.jasper";
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	//***********************************DECLARACION DE LISTAS*************************
	@WireVariable ListaAsignaturasMayorCantidadSancionados listaMayorSancionados;
	private  ListModelList<ListaAsignaturasMayorCantidadSancionados> listaAsigMayor;
	private List<ProgramaAcademico> listaComboPrograma;
	private List<LapsoAcademico> listaLapsoAcademico;
	private List<InstanciaApelada> listaComboInstancia;
	//***********************************DECLARACION DE LAS VARIABLES*************************
	@WireVariable private String nombreAsignatura;
	@WireVariable private Integer cantidadSancionados;
	private Integer idPrograma;
	private String nombrePrograma;
	private String codigoLapso;
	private Integer idInstanciaApelada;
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private LapsoAcademico lapsoAcademico;
	private ProgramaAcademico programaAcademico;
	private InstanciaApelada instanciaApelada;
	private Map<String, Object> parametros;
	
	//*******METODO DE INICIALIZACION*******
	@Init
	public void Init() {
		
		buscarProgramaAcademico();
		buscarLapso();
		buscarInstanciaApelada();
		listaAsigMayor = new ListModelList<ListaAsignaturasMayorCantidadSancionados>();
		
	}
	//*******FIN DEL METODO*******
	
	//**************METODOS SET Y GET NECESARIOS PARA GENERAR REPORTE*****************
	
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
	
	//*******************FIN DE LOS METODOS*****************************
	
	//==================================METODOS SET Y GET====================================
	
	
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

	public void setListaComboInstancia(List<InstanciaApelada> listaComboInstancia) {
		this.listaComboInstancia = listaComboInstancia;
	}

	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	public ListaAsignaturasMayorCantidadSancionados getListaMayorSancionados() {
		return listaMayorSancionados;
	}

	public void setListaMayorSancionados(ListaAsignaturasMayorCantidadSancionados listaMayorSancionados) {
		this.listaMayorSancionados = listaMayorSancionados;
	}

	public ListModelList<ListaAsignaturasMayorCantidadSancionados> getListaAsig() {
		return listaAsigMayor;
	}

	public void setListaAsig(ListModelList<ListaAsignaturasMayorCantidadSancionados> listaAsig) {
		this.listaAsigMayor = listaAsig;
	}
	//===============================FIN DE LOS METODOS SET Y GET==============================
	
 

	//Lista que me permite llenar el combo para elegir el formato 
  	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
  			Arrays.asList(
  					new ReportType("PDF", "pdf"),  
  					new ReportType("Word (RTF)", "rtf"), 
  					new ReportType("Excel", "xls"), 
  					new ReportType("Excel (JXL)", "jxl"), 
  					new ReportType("CSV", "csv"), 
  					new ReportType("OpenOffice (ODT)", "odt")));
  	
	
	
	//@@@@@@@@@@@@@@@@@METODO PARA CARGAR COMBOS TANTO DE PROGRAMA COMO LAPSO ACADEMICO@@@@@@@@@@@@@@@@@@@
	@Command
	@NotifyChange({ "listaComboPrograma" })
	public void buscarProgramaAcademico() {
		setListaComboPrograma(servicioprogramaacademico.listadoProgramas());
	}
	@Command
	 @NotifyChange({"listaComboPrograma"})
	public ProgramaAcademico objetoComboPrograma() {
		return programaAcademico;
		
	}
	
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapso() {
		setListaLapsoAcademico(serviciolapsoacademico.listadoLapsoAcademico());
	}
	@Command
	 @NotifyChange({"listaLapsoAcademico"})
	public LapsoAcademico objetoComboLapso() {
		return lapsoAcademico;
		
	}
	
	@Command
	@NotifyChange({ "listaComboInstancia" })
	public void buscarInstanciaApelada() {
		setListaComboInstancia(servicioInstanciaApelada.buscarTodas());
	}
	@Command
	 @NotifyChange({"listaComboInstancia"})
	public InstanciaApelada objetoComboInstancia() {
		return instanciaApelada;
		
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FIN DEL METODO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	// ###############METODO PARA IMPRIMIR REPORTE#################
	@Command("GenerarReporteAsigMayor")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteAsigMayor(){
		
		
				listaAsigMayor.clear();
				idPrograma = programaAcademico.getIdPrograma();// OBTENER EL VALOR DE LOS COMBOS
				codigoLapso = lapsoAcademico.getCodigoLapso();
				idInstanciaApelada= instanciaApelada.getIdInstanciaApelada();
		if(idPrograma!= null || codigoLapso!= null || idInstanciaApelada!= null){
				List<ListaAsignaturasMayorCantidadSancionados> lista= servicioListaAsignaturasMayor.buscarAsignaturasSancionados(idPrograma,codigoLapso,idInstanciaApelada);
				listaAsigMayor.addAll(lista);
				
			if(listaAsigMayor.size()> 0){	
				reportConfig= new ReportConfig(ruta); //INSTANCIANDO UNA NUEVA LLAMADA AL REPORTE
				reportConfig.getParameters().put("nombrePrograma", programaAcademico.getNombrePrograma());
				reportConfig.getParameters().put("instanciaApelada", instanciaApelada.getInstanciaApelada());
				reportConfig.getParameters().put("lapso", lapsoAcademico.getCodigoLapso());
				//reportConfig.getParameters().put("lista2", listaAsigMayor);
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE IMPRESION DEL REPORTE
				reportConfig.setDataSource(new JRBeanCollectionDataSource(listaAsigMayor)); //ASIGNANDO MEDIANTE EL DATA SOURCE LOS DATOS PARA DIBUJAR EL REPORTE   
			}
			else 
				Messagebox.show("No Hay Datos", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
			Messagebox.show("Seleccione los filtros de busqueda", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
}




