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
import org.zkoss.zul.Messagebox;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaApelacionesMotivoPorAsignatura;
import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioApelacionesMotivosPorAsignatura;
import sigarep.modelos.servicio.reportes.ServicioListaAsignaturasMayorCantidadSancionados;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAsignaturaMotivosApelacionesVsResultado {
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	ServicioAsignatura servicioAsignatura;
	@WireVariable ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable ServicioApelacionesMotivosPorAsignatura servicioApelacionesMotivos;
	
	
	private String contenido;
	private String contenidoProgramaA;
	private String contenidoLapsos;
	private String contenidoInstancias;
	private Integer idPrograma;
	private String codigoLapso;
	private Integer idInstanciaApelada;
	private String codigoAsignatura;

	private  ListModelList<ListaApelacionesMotivoPorAsignatura> listaApelacionMotivoAsignatura;
	private List<Asignatura> listaAsignatura;
	private List<ProgramaAcademico> listaPrograma;
	private List<LapsoAcademico> listaComboLapsoAcademico;
	private List<InstanciaApelada> listaComboInstancias;
	
	private ProgramaAcademico programaseleccionado;
	private LapsoAcademico lapsosAcademicos;
	private InstanciaApelada instanciasApeladas;
	private Asignatura asignatura;
	
	
	String ruta= "/WEB-INF/sigarepReportes/RpAsignaturaApelacionesMotivoVsResultado.jasper";
	
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getContenidoProgramaA() {
		return contenidoProgramaA;
	}
	public void setContenidoProgramaA(String contenidoProgramaA) {
		this.contenidoProgramaA = contenidoProgramaA;
	}
	public String getContenidoLapsos() {
		return contenidoLapsos;
	}
	public void setContenidoLapsos(String contenidoLapsos) {
		this.contenidoLapsos = contenidoLapsos;
	}
	public String getContenidoInstancias() {
		return contenidoInstancias;
	}
	public void setContenidoInstancias(String contenidoInstancias) {
		this.contenidoInstancias = contenidoInstancias;
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
	public void setListaComboLapsoAcademico(List<LapsoAcademico> listaComboLapsoAcademico) {
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
	public void setListaComboInstancias(List<InstanciaApelada> listaComboInstancias) {
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
	public Asignatura getAsignatura(){
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura){
		this.asignatura= asignatura;
	}
	
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
	
		//Lista que me permite llenar el combo para elegir el formato 
	  	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
	  			Arrays.asList(
	  					new ReportType("PDF", "pdf"),  
	  					new ReportType("Word (RTF)", "rtf"), 
	  					new ReportType("Excel", "xls"), 
	  					new ReportType("Excel (JXL)", "jxl"), 
	  					new ReportType("CSV", "csv"), 
	  					new ReportType("OpenOffice (ODT)", "odt")));
	  	
	
	
	
	@Init
    public void init() {
        // Cargar todos los departamentos
		contenido= "Seleccione una Opcion...";
		contenidoProgramaA= "Seleccione una Opcion...";
		contenidoLapsos= "Seleccione una Opcion...";
		contenidoInstancias= "Seleccione una Opcion...";
    	listaPrograma = servicioprogramaacademico.listadoProgramas();
    	listaAsignatura= servicioAsignatura.listaAsignaturas();
    	buscarInstanciasApeladas();
    	buscarLapsos();
    	listaApelacionMotivoAsignatura = new ListModelList<ListaApelacionesMotivoPorAsignatura>();
    	
    	 
    }
    
	@Command
	@NotifyChange({"contenido","contenidoProgramaA","contenidoLapsos","contenidoInstancias"})
	public void limpiarAsignaturaMotivos(){
		contenido= "Seleccione una Opcion...";
		contenidoProgramaA= "Seleccione una Opcion...";
		contenidoLapsos= "Seleccione una Opcion...";
		contenidoInstancias= "Seleccione una Opcion...";
	}
	
    @Command
	@NotifyChange({ "listaComboLapsoAcademico" })
	public void buscarLapsos() {
		setListaComboLapsoAcademico(serviciolapsoacademico.listadoLapsoAcademico());
	}
	@Command
	 @NotifyChange({"listaComboLapsoAcademico"})
	public LapsoAcademico objetoComboLapsos() {
		return lapsosAcademicos;
		
	}
	
	@Command
	@NotifyChange({ "listaComboInstancias" })
	public void buscarInstanciasApeladas() {
		setListaComboInstancias(servicioInstanciaApelada.buscarTodas());
	}
	@Command
	 @NotifyChange({"listaComboInstancias"})
	public InstanciaApelada objetoComboInstancias() {
		return instanciasApeladas;
		
	}
	
	@Command
    @NotifyChange({"listaAsignatura","contenido"})
    public void onSelectPrograma()
    {
		
		listaAsignatura.clear();
		listaAsignatura = servicioAsignatura.buscarAsignaturasPorPrograma(programaseleccionado.getIdPrograma());
		contenido="Seleccione una Opcion...";
    }
	
	@Command("GenerarReporteApelacionesMotivoPorAsignatura")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteApelacionesMotivoPorAsignatura(){
				
				
				if(contenido== "Seleccione una Opcion..." || contenidoLapsos== "Seleccione una Opcion..." || contenidoInstancias== "Seleccione una Opcion...")
					Messagebox.show("Debe seleccionar todos los campos", "Informacion", Messagebox.OK,Messagebox.INFORMATION);
				else{
					
					listaApelacionMotivoAsignatura.clear();
					codigoAsignatura= asignatura.getCodigoAsignatura();
					codigoLapso = lapsosAcademicos.getCodigoLapso();
					idInstanciaApelada= instanciasApeladas.getIdInstanciaApelada();
					List<ListaApelacionesMotivoPorAsignatura> lista2= servicioApelacionesMotivos.buscarApelacionesMotivoPorAsignatura(codigoAsignatura, codigoLapso, idInstanciaApelada);
					
					listaApelacionMotivoAsignatura.addAll(lista2);
					
					if(listaApelacionMotivoAsignatura.getSize()> 0){
						reportConfig= new ReportConfig(ruta); //INSTANCIANDO UNA NUEVA LLAMADA AL REPORTE
						reportConfig.getParameters().put("nombrePrograma", programaseleccionado.getNombrePrograma());
						reportConfig.getParameters().put("instanciaApelada", instanciasApeladas.getInstanciaApelada());
						reportConfig.getParameters().put("lapso", lapsosAcademicos.getCodigoLapso());
						reportConfig.getParameters().put("asignatura", asignatura.getNombreAsignatura());
						reportConfig.getParameters().put("ListaAsignaturaMotivos", new JRBeanCollectionDataSource(listaApelacionMotivoAsignatura));
						reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE IMPRESION DEL REPORTE
						reportConfig.setDataSource(new JRBeanCollectionDataSource(listaApelacionMotivoAsignatura)); //ASIGNANDO MEDIANTE EL DATA SOURCE LOS DATOS PARA DIBUJAR EL REPORTE   
					}
					else
						Messagebox.show("No existen datos para la seleccion", "Informacion", Messagebox.OK,Messagebox.INFORMATION);
	
				}
	}
	
}
