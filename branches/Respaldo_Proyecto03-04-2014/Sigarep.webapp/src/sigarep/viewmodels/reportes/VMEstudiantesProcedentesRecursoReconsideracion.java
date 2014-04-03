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
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaEstudiantesProcedentes;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioEstudiantesProcedentes;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudiantesProcedentesRecursoReconsideracion {

	String ruta= "/WEB-INF/sigarepReportes/RpListaEstudiantesProcedentes.jasper";	
	@Wire("#winProcedentesReconsideracion") // para conectarse a la ventana con el ID
	Window ventanaRR;
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudiantesProcedentes servicioEstudiantesProcedentes;
	
	//***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listadoProgramasA;
	private List<String> listaApelacionesReconsideracion;
	private List<ListaEstudiantesProcedentes> listaProcedentesReconsideracion = new LinkedList<ListaEstudiantesProcedentes>();
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private ProgramaAcademico  objprogramasAcademicosReconsideracion;
	
	//*********************************Parametros para la Tira Sql***************************************
	private Integer idPrograma;
	
	//*****************************************REPORTE******************************************
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	//**************METODOS SET Y GET NECESARIOS PARA GENERAR REPORTE*****************
	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}
	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}
	public ServicioEstudiantesProcedentes getServicioEstudiantesProcedentes() {
		return servicioEstudiantesProcedentes;
	}
	public void setServicioEstudiantesProcedentes(
			ServicioEstudiantesProcedentes servicioEstudiantesProcedentes) {
		this.servicioEstudiantesProcedentes = servicioEstudiantesProcedentes;
	}
	public List<ProgramaAcademico> getListadoProgramasA() {
		return listadoProgramasA;
	}
	public void setListadoProgramasA(
			List<ProgramaAcademico> listadoProgramasA) {
		this.listadoProgramasA = listadoProgramasA;
	}
	public List<String> getListaApelacionesReconsideracion() {
		return listaApelacionesReconsideracion;
	}
	public void setListaApelacionesReconsideracion(
			List<String> listaApelacionesReconsideracion) {
		this.listaApelacionesReconsideracion = listaApelacionesReconsideracion;
	}
	public List<ListaEstudiantesProcedentes> getListaProcedentesReconsideracion() {
		return listaProcedentesReconsideracion;
	}
	public void setListaProcedentesReconsideracion(
			List<ListaEstudiantesProcedentes> listaProcedentesReconsideracion) {
		this.listaProcedentesReconsideracion = listaProcedentesReconsideracion;
	}
	public ProgramaAcademico getObjprogramasAcademicosReconsideracion() {
		return objprogramasAcademicosReconsideracion;
	}
	public void setObjprogramasAcademicosReconsideracion(ProgramaAcademico objprogramasAcademicosReconsideracion) {
		this.objprogramasAcademicosReconsideracion = objprogramasAcademicosReconsideracion;
	}
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	public MensajesAlUsuario getMensajeAlUsuario() {
		return mensajeAlUsuario;
	}
	public void setMensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario) {
		this.mensajeAlUsuario = mensajeAlUsuario;
	}
	
	//Reporte SET/GETS
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
	//===============================FIN DE LOS METODOS SET Y GET==============================
	
	/**
	* afterCompose
	* Metodo que se usa para conectarse a los componentes de la vista
	* @return nada.
	* @throws no posee excepciones
	* 
	*/
	@AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	
	//Lista que me permite llenar el combo para elegir el formato 
		/** Muestra los tipos de formatos que puede mostrarse el reporte
		 * @param  
		 * @return modelos de la lista
		 * @throws No dispara ninguna excepción.
		*/
			
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
		  Arrays.asList(new ReportType("PDF", "pdf"), 
		  				new ReportType("Word (RTF)", "rtf"), 
		  				new ReportType("Reporte en Excel", "xls"), 
		  				new ReportType("Excel (JXL)", "jxl"), 
		  				new ReportType("CSV", "csv"), 
		  				new ReportType("OpenOffice (ODT)", "odt")));
	
	//@@@@@@@@@@@@@@@@@METODOS PARA CARGAR CADA UNO DE LOS COMBOS@@@@@@@@@@@@@@@@@@@
	/** buscar Programas Académicos
	 * @param  
	 * @return lista de programa Académico
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listadoProgramasA" })
	public void buscarProgramasARecursoR() {
		listadoProgramasA = servicioprogramaacademico.listadoProgramas();
	}
			
	/** Objeto Combo Programa.
	 * @param Ninguno
	 * @return Objeto Programa Académico
	 * @throws No dispara ninguna excepción.
	 */
			
	@Command
	@NotifyChange({ "listadoProgramasA" })
	public ProgramaAcademico objprogramaARecursoR() {
			return objprogramasAcademicosReconsideracion;
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FIN DEL METODO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	
	//******************************Método para buscar sesiones activas en solicitud de apelacion*****************************
	/** buscar sesiones
	 * @param  Ninguno
	 * @return lista de sesiones
	 * @throws No dispara ninguna excepción.
	*/
	@Command
	@NotifyChange({ "listaApelacionesReconsideracion" })
	public void listaSesionesReconsideracion() {
		listaApelacionesReconsideracion = (serviciosolicitudapelacion.buscarSesion());
	}
		
	
	//******************************METODO que Limpia Combos o Cajas de TextoS*****************************		
	/** Limpiar Combo Programa Académico.
	* @param Ninguno
	* @return Limpiar cada uno de los combos de la vista
	* @throws No dispara ninguna excepción.
	*/
	
	@Command
	@NotifyChange({ "objprogramasAcademicosReconsideracion","reportType"})
	public void limpiarComboProgramaAcademicoRR() {
		objprogramasAcademicosReconsideracion = null;
		reportType= null;
	}
	
	
	//******************************METODO DE INICIALIZACION*****************************

	/**Inicialización
	 * @param init
	 * @return Carga de Variables y métodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
  	
	@Init
		public void init(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		buscarProgramasARecursoR();
		listaSesionesReconsideracion();
		if(listaApelacionesReconsideracion.size()==0){
			mensajeAlUsuario.cerrarVentanaSinVeredicto(ventanaRR, true);
		}
	}
	
	
	// ###############METODO PARA IMPRIMIR REPORTE#################
	
	/** Generar Reporte Informe Especial Resultado Apelacion.
	 * @param Ninguno
	 * @return Reporte Especial de Resultado Apelacion
	 * @throws Si la lista está vacía no genera el reporte.
	*/
	@Command("GenerarReporteEstudiantesRecursoReconsideracion")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteEstudiantesRecursoReconsideracion(){
			if(objprogramasAcademicosReconsideracion==null || reportType == null){
				mensajeAlUsuario.advertenciaSeleccionarTodo();
			}
			else{
						
					listaProcedentesReconsideracion.clear();
					idPrograma= objprogramasAcademicosReconsideracion.getIdPrograma();
						
					listaProcedentesReconsideracion= servicioEstudiantesProcedentes.buscarEstudiantesProcedentesRecursoReconsideracion(idPrograma);
				
					if(listaProcedentesReconsideracion.size()>0){
						reportConfig =new ReportConfig(ruta);	
						reportConfig.setType(reportType);
						reportConfig.setDataSource(new JRBeanCollectionDataSource(listaProcedentesReconsideracion));
					}
					else{
						mensajeAlUsuario.informacionNoHayCoincidencias();
					
					}
				}
			}
}
