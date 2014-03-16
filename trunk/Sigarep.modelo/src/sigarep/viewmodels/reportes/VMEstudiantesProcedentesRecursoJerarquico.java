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
public class VMEstudiantesProcedentesRecursoJerarquico {


	String ruta= "/WEB-INF/sigarepReportes/RpListaEstudiantesProcedentes.jasper";	
	@Wire("#winProcedentesJerarquico") // para conectarse a la ventana con el ID
	Window ventanaRJ;
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudiantesProcedentes servicioEstudiantesProcedentes;
	
	//***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listadoProgramasAJerarquico;
	private List<String> listaApelacionesJerarquico;
	private List<ListaEstudiantesProcedentes> listaProcedentesJerarquico = new LinkedList<ListaEstudiantesProcedentes>();
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private ProgramaAcademico  objprogramasAJerarquico;
	
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
	public List<ProgramaAcademico> getListadoProgramasAJerarquico() {
		return listadoProgramasAJerarquico;
	}
	public void setListadoProgramasAJerarquico(
			List<ProgramaAcademico> listadoProgramasAJerarquico) {
		this.listadoProgramasAJerarquico = listadoProgramasAJerarquico;
	}
	public List<String> getListaApelacionesJerarquico() {
		return listaApelacionesJerarquico;
	}
	public void setListaApelacionesJerarquico(
			List<String> listaApelacionesJerarquico) {
		this.listaApelacionesJerarquico = listaApelacionesJerarquico;
	}
	public List<ListaEstudiantesProcedentes> getListaProcedentesJerarquico() {
		return listaProcedentesJerarquico;
	}
	public void setListaProcedentesJerarquico(
			List<ListaEstudiantesProcedentes> listaProcedentesJerarquico) {
		this.listaProcedentesJerarquico = listaProcedentesJerarquico;
	}
	public ProgramaAcademico getObjprogramasAJerarquico() {
		return objprogramasAJerarquico;
	}
	public void setObjprogramasAJerarquico(ProgramaAcademico objprogramasAJerarquico) {
		this.objprogramasAJerarquico = objprogramasAJerarquico;
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
	@NotifyChange({ "listadoProgramasAJerarquico" })
	public void buscarProgramasARecursoJ() {
		listadoProgramasAJerarquico = servicioprogramaacademico.listadoProgramas();
	}
			
	/** Objeto Combo Programa.
	 * @param Ninguno
	 * @return Objeto Programa Académico
	 * @throws No dispara ninguna excepción.
	 */
			
	@Command
	@NotifyChange({ "listadoProgramasAJerarquico" })
	public ProgramaAcademico objprogramaARecursoJ() {
			return objprogramasAJerarquico;
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
		public void listaSesionesJerarquico() {
			listaApelacionesJerarquico = (serviciosolicitudapelacion.buscarSesion());
		}
	
	
	//******************************METODO que Limpia Combos o Cajas de TextoS*****************************		
	/** Limpiar Combo Programa Académico.
	* @param Ninguno
	* @return Limpiar cada uno de los combos de la vista
	* @throws No dispara ninguna excepción.
	*/
	
	@Command
	@NotifyChange({ "objprogramasAJerarquico"})
	public void limpiarComboProgramaAcademicoRJ() {
		objprogramasAJerarquico = null;
	}
	
	
	//******************************METODO DE INICIALIZACION*****************************
	
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

	/**Inicialización
	 * @param init
	 * @return Carga de Variables y métodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
  	
	@Init
		public void init(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		buscarProgramasARecursoJ();
		listaSesionesJerarquico();
		if(listaApelacionesJerarquico.size()== 0){
			mensajeAlUsuario.cerrarVentanaSinVeredicto(ventanaRJ, true);
		}
	}
	
	
	// ###############METODO PARA IMPRIMIR REPORTE#################
	
	/** Generar Reporte Informe Especial Resultado Apelacion.
	 * @param Ninguno
	 * @return Reporte Especial de Resultado Apelacion
	 * @throws Si la lista está vacía no genera el reporte.
	*/
	@Command("GenerarReporteEstudiantesRecursoJerarquico")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteEstudiantesRecursoJerarquico(){
			if(objprogramasAJerarquico==null){
				mensajeAlUsuario.advertenciaSeleccionarTodo();
			}
			else{
						
					listaProcedentesJerarquico.clear();
					idPrograma= objprogramasAJerarquico.getIdPrograma();
						
					listaProcedentesJerarquico= servicioEstudiantesProcedentes.buscarEstudiantesProcedentesRecursoJerarquico(idPrograma);
				
					if(listaProcedentesJerarquico.size()>0){
						reportConfig =new ReportConfig(ruta);	
						reportConfig.setType(reportType);
						reportConfig.setDataSource(new JRBeanCollectionDataSource(listaProcedentesJerarquico));
					}
					else{
						mensajeAlUsuario.informacionNoHayCoincidencias();
					
					}
				}
			}
}

