package sigarep.viewmodels.reportes;
import java.util.Arrays;

import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.EstudianteSancionado;


import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMlistaMaestros {

	// ***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioActividad servicioactividad;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioRecaudo serviciorecaudo;
	@WireVariable
	private ServicioCronograma serviciocronograma;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	
	
	// ***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<SancionMaestro> listaTipoSancion;
	private List<LapsoAcademico> listaLapso;
	private List<Actividad> listaActividades;
	private List<EstadoApelacion> listaEstadoApelacion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<Recaudo> listaRecaudo;
	private List<EstudianteSancionado> listaEstudiantesSancionados;
	private List<Cronograma> listaCronograma;
	private List<Asignatura> listaAsignatura;
	

	private String maestro;
	

	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	private MensajesAlUsuario message;
	
	//*****************************METODOS SET Y GET DE LA CLASE*****************************
	public String getMaestro() {
		return maestro;
	}

	public void setMaestro(String maestro) {
		this.maestro = maestro;
	}

	
	public MensajesAlUsuario getMessage() {
		return message;
	}

	public void setMessage(MensajesAlUsuario message) {
		this.message = message;
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

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> listaLapso) {
		this.listaLapso = listaLapso;
	}
	
	public List<Actividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(List<Actividad> listaActividades) {
		this.listaActividades = listaActividades;
	}

	
	public List<EstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(List<EstadoApelacion> listaEstadoApelacion) {
		this.listaEstadoApelacion = listaEstadoApelacion;
	}
	
	
	
	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	
	public List<Recaudo> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<Recaudo> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public List<SancionMaestro> getListaTipoSancion() {
		return listaTipoSancion;
	}

	public void setListaEstudiantesSancionados(List<EstudianteSancionado> listaEstudiantesSancionados) {
		this.listaEstudiantesSancionados = listaEstudiantesSancionados;
	}


	public List<EstudianteSancionado> getListaEstudiantesSancionados() {
		return listaEstudiantesSancionados;
	}
	
	
	public void setListaCronograma(List<Cronograma> listaCronograma) {
		this.listaCronograma = listaCronograma;
	}


	public List<Cronograma> getListaCronograma() {
		return listaCronograma;
	}
	
	public List<Asignatura> getAsignatura() {
		return listaAsignatura;
	}

	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}
	//***********************************FIN DE LOS METODOS SET Y GET*************************************
	
	// Lista que me permite llenar el combo para elegir el formato
	/**
	 * Muestra los tipos de formatos que puede mostrarse el reporte
	 * 
	 * @param
	 * @return modelos de la lista
	 * @throws No
	 *             dispara ninguna excepción.
	 */
		private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
				Arrays.asList(new ReportType("PDF", "pdf"), new ReportType("HTML",
						"html"), new ReportType("Word (RTF)", "rtf"),
						new ReportType("Excel", "xls"), new ReportType(
								"Excel (JXL)", "jxl"),
						new ReportType("CSV", "csv"), new ReportType(
								"OpenOffice (ODT)", "odt")));

		
	//********************METODO PARA LIMPIAR COMBOS******************************
	
	@Command
	@NotifyChange({"maestro","reportType"})
	public void limpiarMaestros(){
		maestro= null;
		reportType= null;
	}
		
		
		
	// ###############METODO PARA IMPRIMIR REPORTE#################
	@Command("GenerarReporteMaestro")
	@NotifyChange({ "reportConfig", "maestro" })
	public void GenerarReporte() {

	
		
			switch(maestro) {
			 case "lapsoacademico": 
				 listaLapso =serviciolapsoacademico.buscarTodosLosLapsos();
			        String ruta="/WEB-INF/sigarepReportes/maestros/RMaestroLapsoAcadémico.jasper";
					reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
														// REPORTE
					reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
							listaLapso));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
													// IMPRESION DEL REPORTE
					
					reportConfig.setDataSource(new JRBeanCollectionDataSource(
							listaLapso)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
													// DATOS PARA DIBUJAR EL REPORTE
			     break;
	         case "actividad": 
	        	 
	        	 listaActividades =servicioactividad.listadoActividad();
			        ruta="/WEB-INF/sigarepReportes/maestros/RMaestroActividades.jasper";
					reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
														// REPORTE
					reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
							listaActividades));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
													// IMPRESION DEL REPORTE
					
					reportConfig.setDataSource(new JRBeanCollectionDataSource(
							listaActividades)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
													// DATOS PARA DIBUJAR EL REPORTE
			     
			     break;
			
			 case "estadoApelacion": 
				 listaEstadoApelacion =servicioestadoapelacion.listadoEstadoApelacionActivas();
			         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroEstadoapelacion.jasper";
					reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
														// REPORTE
					reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
							listaEstadoApelacion));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
													// IMPRESION DEL REPORTE
					
					reportConfig.setDataSource(new JRBeanCollectionDataSource(
							listaEstadoApelacion)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
													// DATOS PARA DIBUJAR EL REPORTE
				 
			     
			     break;
			 case "instanciaApelada": 
				 listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroInstanciaApelada.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						listaInstanciaApelada));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaInstanciaApelada)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE
				 break;
				 
			 case "motivos": 
				 listaTipoMotivo =serviciotipomotivo.listadoTipoMotivo();
			         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroMotivoApelacion.jasper";
					reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
														// REPORTE
					reportConfig.getParameters().put("Titulo", "Reporte Comparativo de Apelaciones por Motivo y Veredicto");
					reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
							listaTipoMotivo));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
													// IMPRESION DEL REPORTE
					
					reportConfig.setDataSource(new JRBeanCollectionDataSource(
							listaTipoMotivo)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
													// DATOS PARA DIBUJAR EL REPORTE
			     break;
				 
			 case "recaudos": 
				 
				 listaRecaudo =serviciorecaudo.listadoRecaudosActivos();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroRecaudos.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Comparativo de Apelaciones por Motivo y Veredicto");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						listaRecaudo));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaRecaudo)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE		    
			     break;
			 case "programaacademico": 
				 
				 listaPrograma =servicioprogramaacademico.listadoProgramas();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroProgramaAcademico.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Lista de Programas Académicos");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						 listaPrograma));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						 listaPrograma)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE	
				    
			     break;
	         case "asignatura": 
				 
	        	 listaAsignatura =servicioAsignatura.listaAsignaturas();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroAsignatura.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Lista de Programas Académicos");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						 listaAsignatura));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaAsignatura)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE	
				    
			     break;
			 case "tipossancion": 
				    
				 
				 listaTipoSancion =serviciosancionmaestro.listaTipoSanciones();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroTipoSancion.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Lista de Tipos de Sanciones");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						listaTipoSancion));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaTipoSancion)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE	
				 
				 
			     break;
			     
			 case "sancionados": 
				 listaEstudiantesSancionados =servicioestudiantesancionado.buscarSancionados();
		         ruta="/WEB-INF/sigarepReportes/maestros/RMaestroSancionados.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Lista de Estudiantes Sancionados");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						listaEstudiantesSancionados));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaEstudiantesSancionados)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE	
				    
			     break;
			 case "cronograma": 
				 listaCronograma =serviciocronograma.listadoCronograma();
		         ruta="/WEB-INF/sigarepReportes/maestros/RCronograma.jasper";
				reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
													// REPORTE
				reportConfig.getParameters().put("Titulo", "Reporte Lista de Estudiantes Sancionados");
				reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(
						listaCronograma));
				reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE
												// IMPRESION DEL REPORTE
				
				reportConfig.setDataSource(new JRBeanCollectionDataSource(
						listaCronograma)); // ASIGNANDO MEDIANTE EL DATA SOURCE LOS
												// DATOS PARA DIBUJAR EL REPORTE
				 
				 
				    
			     break;
			 default: 
			     
			     break;
			 }
	}

	// #####################FIN DEL METODO##########################
	

	

}
