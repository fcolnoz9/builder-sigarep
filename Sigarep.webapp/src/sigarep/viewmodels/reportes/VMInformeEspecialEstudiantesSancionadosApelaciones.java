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

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.reportes.ListaEspecialEstudiantesSancionadosApelaciones;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioInformeEspecialEstudiantesSancionadosApelaciones;

/**VM Informe Especial Estudiantes Sancionados y sus Apelaciones
 * UCLA DCYT Sistemas de Información.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeEspecialEstudiantesSancionadosApelaciones {
	
	@Wire("#winEstudianteSancionadoApelacion")//para conectarse a la ventana con el ID
	Window ventana;
	String ruta= "/WEB-INF/sigarepReportes/informes/especiales/RpInformeEspecialSancionadosApelaciones.jasper";
	
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioInformeEspecialEstudiantesSancionadosApelaciones servicioestudianteasignaturasancion;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	
	
	//***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listaPrograma;
	private List<SancionMaestro> listaSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<ListaEspecialEstudiantesSancionadosApelaciones> listaEAS = new LinkedList<ListaEspecialEstudiantesSancionadosApelaciones>();
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private SancionMaestro objSancion;
	private ProgramaAcademico  objprograma;
	private InstanciaApelada objinstanciaApelada;
	private String objVeredicto;
	private ProgramaAcademico programa;
	
	//*********************************Parametros para la Tira Sql***************************************
	private String parametroTipoSancion;
	private String parametroInstanciaApelada;
	private String parametroProgramaAcademico;
	private String parametroVeredicto;

	//*****************************************REPORTE******************************************
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	
	private ListModelList<String> cmbVeredicto;//Lista para llenar el combo Veredicto
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	private LapsoAcademico lapso;
	
	
	
	
	//**************METODOS SET Y GET NECESARIOS PARA GENERAR REPORTE*****************
	
	public SancionMaestro getObjSancion() {
		return objSancion;
	}
	public void setObjSancion(SancionMaestro objSancion) {
		this.objSancion = objSancion;
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
	
	public ProgramaAcademico getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaAcademico programa) {
		this.programa = programa;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
	
	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}
	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
	
	public List<ListaEspecialEstudiantesSancionadosApelaciones> getListaEAS() {
		return listaEAS;
	}
	public void setListaE(List<ListaEspecialEstudiantesSancionadosApelaciones> listaEAS) {
		this.listaEAS = listaEAS;
	}
	
	public String getParametroTipoSancion() {
		return parametroTipoSancion;
	}
	public void setParametroTipoSancion(String parametroTipoSancion) {
		this.parametroTipoSancion = parametroTipoSancion;
	}
	public String getParametroInstanciaApelada() {
		return parametroInstanciaApelada;
	}
	public void setParametroInstanciaApelada(String parametroInstanciaApelada) {
		this.parametroInstanciaApelada = parametroInstanciaApelada;
	}
	public String getParametroProgramaAcademico() {
		return parametroProgramaAcademico;
	}
	public void setParametroProgramaAcademico(String parametroProgramaAcademico) {
		this.parametroProgramaAcademico = parametroProgramaAcademico;
	}
	
	public String getParametroVeredicto() {
		return parametroVeredicto;
	}
	public void setParametroVeredicto(String parametroVeredicto) {
		this.parametroVeredicto = parametroVeredicto;
	}
	
	public String getObjVeredicto() {
		return objVeredicto;
	}
	public void setObjVeredicto(String objVeredicto) {
		this.objVeredicto = objVeredicto;
	}
	public LapsoAcademico getLapso() {
		return lapso;
	}
	public void setLapso(LapsoAcademico lapso) {
		this.lapso = lapso;
	}
	public ListModelList<String> getCmbVeredicto() {
		cmbVeredicto.add("Procedente");
		cmbVeredicto.add("No Procedente");
		cmbVeredicto.add("Todos");
		return cmbVeredicto;
	}
	
	public void setCmbVeredicto(ListModelList<String> cmbVeredicto) {
		this.cmbVeredicto = cmbVeredicto;
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
		public void init() {
		// initialization code
		buscarProgramaA();
		listadoSancion();
		listadoInstancia();
		cmbVeredicto= new ListModelList<String>();
	}
		
	//REPORTE
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
	
	
	/** buscar Sanción
	 * @param  
	 * @return lista de sanción
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro san = new  SancionMaestro(null,"Todos", null, "Todos");
		listaSancion.add(listaSancion.size(),san);
	}
	
	/** Objeto Combo Sanción.
 	* @param Ninguno
 	* @return Objeto Sanción
 	* @throws No dispara ninguna excepción.
 	*/
	
	@Command
	@NotifyChange({ "listaSancion" })
	public SancionMaestro objCmbSancion() {
		return objSancion;
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
		InstanciaApelada ins = new InstanciaApelada(null,"Todos", null, "Todos", null);
		listaInstanciaApelada.add(listaInstanciaApelada.size(),ins);
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
		
	
	/** Limpiar Combos.
	* @param Ninguno
	* @return Limpiar cada uno de los combos de la vista
	* @throws No dispara ninguna excepción.
	*/
	
	@Command
	@NotifyChange({ "objprograma", "objSancion","objinstanciaApelada","objLapso",
		"objVeredicto"})
	public void limpiarCombos() {
		objprograma = null;
		objSancion = null;
		objinstanciaApelada = null;
		objVeredicto= null;
	}
	
	/** Configurar Parámetro Sanción.
	* @param Ninguno
	* @return 
	* @throws No dispara ninguna excepción.
	*/
	
	@NotifyChange({ "parametroTipoSancion" })// parametro Tipo sancion
	@Command
	public String configurarParametroSancion() {
		if (objSancion.getNombreSancion() == "Todos") {
			parametroTipoSancion = "es.id_sancion";
		} else {
			parametroTipoSancion = "'" + objSancion.getIdSancion() + "'";
		}
		return parametroTipoSancion;
	}
	
	/** Configurar Parámetro Instancia Apelada.
	* @param Ninguno
	* @return 
	* @throws No dispara ninguna excepción.
	*/
	
	@NotifyChange({ "parametroInstanciaApelada" })// ParametroInstanciaApeldada
	@Command
	public String configurarParametroInstanciaApelada() {
		if (objinstanciaApelada.getDescripcion() == "Todos") {
			parametroInstanciaApelada = "sa.id_instancia_apelada";
		} else {
			parametroInstanciaApelada = "'"
					+ objinstanciaApelada.getIdInstanciaApelada() + "'";
		}
		return parametroInstanciaApelada;
	}

	/** Configurar Parámetro Programa Académico.
	* @param Ninguno
	* @return 
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
	
	/** Configurar Parámetro Veredicto
	* @param Ninguno
	* @return 
	* @throws No dispara ninguna excepcion.
	*/
	
	@NotifyChange({ "parametroVeredicto" })
	// Parametro Sexo
	@Command
	public String configurarParametroVeredicto() {
		if (objVeredicto.equals("Todos")) {
			parametroVeredicto = "sa.veredicto";
		} else {
			parametroVeredicto = "'"+objVeredicto+"'";
		}
		return parametroVeredicto;
	}
	
	/** Generar Reporte Estudiantes Sancionado Apelaciones Especial.
	* @param Ninguno
	* @return Reporte de Estudiantes Sancionados y sus Apelaciones generado en PDF u otro tipo de archivo
	* @throws Si la lista está vacía no genera el reporte.
	*/
	@Command("GenerarReporteEstudiantesSancionadoApelacionEspecial")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteEstudiantesSancionadoApelacionEspecial(){	
		if(objinstanciaApelada==null || objprograma==null || objSancion==null
				|| objVeredicto==null){
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		}
		else{
			configurarParametroSancion();
			configurarParametroInstanciaApelada();
			configurarParametroProgramaAcademico();
			configurarParametroVeredicto();
			listaEAS = servicioestudianteasignaturasancion.buscarEstudianteAsignaturasSancion(parametroTipoSancion , parametroInstanciaApelada, parametroProgramaAcademico, parametroVeredicto);
		
			if(listaEAS.size()>0){
				reportConfig =new ReportConfig(ruta);
				reportConfig.getParameters().put("ListaEstudianteAsignaturaSancionados", new JRBeanCollectionDataSource(listaEAS));
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(listaEAS));
			}
			else{
				mensajeAlUsuario.informacionNoHayCoincidencias();
			
			}
		}
	}
	
	//#####################MENSAJE PARA CERRAR##########################

	
	/**Cerrar Ventana
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepción.
	 */
		@Command
		public void cerrarVentanaReporteEspecialSancionadosApelaciones(@ContextParam(ContextType.BINDER) final Binder binder){
				
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
