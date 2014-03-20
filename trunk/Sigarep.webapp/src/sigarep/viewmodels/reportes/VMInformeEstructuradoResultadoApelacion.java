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
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ListaInformeEstructuradoResultadosApelacion;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioInformeEstructuradoResultadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeEstructuradoResultadoApelacion {

	String ruta= "/WEB-INF/sigarepReportes/informes/estructurados/RpInformeEstructuradoResultadosApelacion.jasper";	
	
	@Wire("#winResultadoApelacion") // para conectarse a la ventana con el ID
	Window ventana2;
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioInformeEstructuradoResultadoApelacion servicioInformeEstructuradoResultadoApelacion;
	
	//***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listaPrograma;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<String> listaSolicitudApelacion;
	private List<ListaInformeEstructuradoResultadosApelacion> listaERA = new LinkedList<ListaInformeEstructuradoResultadosApelacion>();
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private ProgramaAcademico  objprograma;
	private InstanciaApelada objinstanciaApelada;
	
	//*********************************Parametros para la Tira Sql***************************************
	private String parametroProgramaAcademico;
	private String instancia;
	private String numeroSesion;
	//*********************************Declaración de variables***************************************
	private String sesiones;
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
	public ServicioInstanciaApelada getServicioInstanciaApelada() {
		return servicioInstanciaApelada;
	}
	public void setServicioInstanciaApelada(
		ServicioInstanciaApelada servicioInstanciaApelada) {
		this.servicioInstanciaApelada = servicioInstanciaApelada;
	}
	public ServicioSolicitudApelacion getServiciosolicitudapelacion() {
		return serviciosolicitudapelacion;
	}
	public void setServiciosolicitudapelacion(
		ServicioSolicitudApelacion serviciosolicitudapelacion) {
		this.serviciosolicitudapelacion = serviciosolicitudapelacion;
	}
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	public void setListaInstanciaApelada(
		List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	public List<String> getListaSolicitudApelacion() {
		return listaSolicitudApelacion;
	}
	public void setListaSolicitudApelacion(
		List<String> listaSolicitudApelacion) {
		this.listaSolicitudApelacion = listaSolicitudApelacion;
	}
	public List<ListaInformeEstructuradoResultadosApelacion> getListaERA() {
		return listaERA;
	}
	public void setListaERA(
		List<ListaInformeEstructuradoResultadosApelacion> listaERA) {
		this.listaERA = listaERA;
	}
	public ProgramaAcademico getObjprograma() {
		return objprograma;
	}
	public void setObjprograma(ProgramaAcademico objprograma) {
		this.objprograma = objprograma;
	}
	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}
	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}
	public String getInstancia() {
		return instancia;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	
	public String getNumeroSesion() {
		return numeroSesion;
	}
	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}
	public String getSesiones() {
		return sesiones;
	}
	public void setSesiones(String sesiones) {
		this.sesiones = sesiones;
	}
	public String getParametroProgramaAcademico() {
		return parametroProgramaAcademico;
	}
	public void setParametroProgramaAcademico(String parametroProgramaAcademico) {
		this.parametroProgramaAcademico = parametroProgramaAcademico;
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

	
	
	//******************************METODO DE INICIALIZACION*****************************
	/**Inicialización
	 * @param init
	 * @return Carga de Variables y métodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
  	
	@Init
		public void init(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		buscarProgramaA();
		listadoInstancia();
		listaSesion();
		if(listaSolicitudApelacion.size()== 0){
			mensajeAlUsuario.cerrarVentanaSinVeredicto(ventana2, true);
		}
	}
		
	//Lista que me permite llenar el combo para elegir el formato 
		/** Muestra los tipos de formatos que puede mostrarse el reporte
		 * @param  
		 * @return modelos de la lista
		 * @throws No dispara ninguna excepción.
		 */
		
		private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
	  			Arrays.asList(
	  					new ReportType("PDF", "pdf"), 
	  					new ReportType("Word (RTF)", "rtf"), 
	  					new ReportType("Reporte en Excel", "xls"), 
	  					new ReportType("Excel (JXL)", "jxl"), 
	  					new ReportType("CSV", "csv"), 
	  					new ReportType("OpenOffice (ODT)", "odt")));
		
		
		//@@@@@@@@@@@@@@@@@METODOS PARA CARGAR CADA UNO DE LOS COMBOS@@@@@@@@@@@@@@@@@@@
		/** buscar Programa Académico
		 * @param  
		 * @return lista de programa Académico
		 * @throws No dispara ninguna excepción.
		 */
		
		@Command
		@NotifyChange({ "listaPrograma" })
		public void buscarProgramaA() {
			listaPrograma = servicioprogramaacademico.listadoProgramas();
			ProgramaAcademico prog = new ProgramaAcademico(null, "Todos",null);
			listaPrograma.add(listaPrograma.size(),prog);
		}
		
		/** Objeto Combo Programa.
	 	* @param Ninguno
	 	* @return Objeto Programa Académico
	 	* @throws No dispara ninguna excepción.
	 	*/
		
		@Command
		@NotifyChange({ "listaPrograma" })
		public ProgramaAcademico objCmbprograma() {
			return objprograma;
		}
		
		/** buscar Instancia Apelada
		 * @param  
		 * @return lista de instacias apeladas
		 * @throws No dispara ninguna excepción.
		 */
		
		@Command
		@NotifyChange({ "listaInstanciaApelada" })
		public void listadoInstancia() {
			listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
		}
			
		/** Objeto Combo Instancia Apelada.
	 	* @param Ninguno
	 	* @return Objeto Instancia Apelada
	 	* @throws No dispara ninguna excepción.
	 	*/
		
		@Command
		@NotifyChange({ "listaInstanciaApelada" })
		public InstanciaApelada objCmbinstanciaApelada() {
			return objinstanciaApelada;
		}
		
		/** buscar sesiones
		 * @param  Ninguno
		 * @return lista de sesiones
		 * @throws No dispara ninguna excepción.
		 */
		@Command
		@NotifyChange({ "listaSolicitudApelacion" })
		public void listaSesion() {
			listaSolicitudApelacion = (serviciosolicitudapelacion.buscarSesion());
		}
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FIN DEL METODO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		
		
		//******************************METODO QUE LIMPIA CADA UNO DE LOS COMBOS*****************************
		/** Limpiar Combos Especial Resultados.
		* @param Ninguno
		* @return Limpiar cada uno de los combos de la vista
		* @throws No dispara ninguna excepción.
		*/
		
		@Command
		@NotifyChange({ "objprograma", "objinstanciaApelada","sesiones"})
		public void limpiarCombosEspecialResultados() {
			objprograma = null;
			objinstanciaApelada = null;
			sesiones = null;
		}
		
		
		// ########################Metodos de Configuracion de Parametros del Reporte########################
		
		/** Configurar Parámetro Programa Académico.
		* @param Ninguno
		* @return Programa Académico escogido en el combo 
		* @throws No dispara ninguna excepción.
		*/
		
		@NotifyChange({ "parametroProgramaAcademico" })// ParametroprogramaAcademico
		@Command
		public String configurarParametroProgramaAcademico() {
			if (objprograma.getNombrePrograma() == "Todos") {
				parametroProgramaAcademico = "e.id_programa";
			} else {
				parametroProgramaAcademico = "'" + objprograma.getIdPrograma()
						+ "'";
			}
			return parametroProgramaAcademico;
		}
		
		/** Configurar Parámetro Instancia Apelada.
		* @param Ninguno
		* @return Instancia Apelada escogida en el combo 
		* @throws No dispara ninguna excepción.
		*/
		@NotifyChange({ "instancia" })// ParametroInstanciaApeldada
		@Command
		public String configurarParametroInstanciaApelada() {
				instancia = "'"+ objinstanciaApelada.getIdInstanciaApelada() + "'";
			return instancia;
		}
		
		
		// ###############METODO PARA IMPRIMIR REPORTE#################
		
		/** Generar Reporte Informe Especial Resultado Apelacion.
		* @param Ninguno
		* @return Reporte Especial de Resultado Apelacion
		* @throws Si la lista está vacía no genera el reporte.
		*/
		@Command("GenerarReporteEspecialResultadoApelacion")
		@NotifyChange({"reportConfig"})
		public void GenerarReporteEspecialResultadoApelacion(){
			    if(objinstanciaApelada==null || objprograma==null || sesiones==null){
					mensajeAlUsuario.advertenciaSeleccionarTodo();
				}
				else{
					
					listaERA.clear();
					configurarParametroProgramaAcademico();
					configurarParametroInstanciaApelada();
					numeroSesion= sesiones;
					
					
					listaERA = servicioInformeEstructuradoResultadoApelacion.buscarEstudianteResultadoApelacion(instancia, parametroProgramaAcademico, numeroSesion);
			
					if(listaERA.size()>0){
						
						reportConfig =new ReportConfig(ruta);
						if(objinstanciaApelada.getIdInstanciaApelada()== 1 || objinstanciaApelada.getIdInstanciaApelada() == 2){
							reportConfig.getParameters().put("instancia", "Consejo de Decanato");
						} else{
							reportConfig.getParameters().put("instancia", "Consejo Universitario");
						}
						reportConfig.setType(reportType);
						reportConfig.setDataSource(new JRBeanCollectionDataSource(listaERA));
					}
					else{
						mensajeAlUsuario.informacionNoHayCoincidencias();
				
					}
				}
		}
		
		
}
