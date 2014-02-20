package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.ConfigurableApelaciones;
import sigarep.modelos.data.reportes.EstudianteSancionado;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioReporteConfigurableApelaciones;

/**VM Reporte Configurable Apelaciones
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConfigurableApelaciones {
	String ruta= "/WEB-INF/sigarepReportes/RpEstudiantesSancionadosConfigurable.jasper";
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
	private ServicioReporteConfigurableApelaciones servicioreporteconfigurableapelaciones;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
		//***********************************DECLARACION DE LISTAS*************************
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<LapsoAcademico> listaLapsoAcademico;
	private List<SancionMaestro> listaSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<EstadoApelacion> listaEdoApelacion;
	private List<Asignatura> listaAsignaturas;
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private LapsoAcademico objLapso;
	private SancionMaestro objSancion;
	private ProgramaAcademico  objprograma;
	private TipoMotivo objtipoMotivo;
	private InstanciaApelada objinstanciaApelada;
	private String objVeredicto;
	private EstadoApelacion objEdoApelacion;
	private Asignatura asignaturas;
	private ProgramaAcademico programa;
	private List<ConfigurableApelaciones> listaA = new LinkedList<ConfigurableApelaciones>();


	//*********************************Parametros para la Tira Sql***************************************
	private String parametroLapsoAcademico;
	private String parametroTipoSancion;
	private String parametroInstanciaApelada;
	private String parametroMotivo;
	private String parametroProgramaAcademico;
	private String parametroVeredicto;
	private String parametroEdoApelacion;
	private String parametroAsignatura;
	//*****************************************REPORTE******************************************
	ReportType reportType = null;
	ReportConfig reportConfig = null;
	
	private ListModelList<String> cmbVeredicto;//Lista para llenar el combo Veredicto
	private ListModelList<String> cmbEdoApelacion;//Lista para llenar el combo Edo Apelacion
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	
	//**************METODOS SET Y GET NECESARIOS PARA GENERAR REPORTE*****************
	
	public SancionMaestro getObjSancion() {
		return objSancion;
	}
	public ServicioAsignatura getServicioAsignatura() {
		return servicioAsignatura;
	}
	public void setServicioAsignatura(ServicioAsignatura servicioAsignatura) {
		this.servicioAsignatura = servicioAsignatura;
	}
	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}
	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}
	public Asignatura getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(Asignatura asignaturas) {
		this.asignaturas = asignaturas;
	}
	public ProgramaAcademico getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaAcademico programa) {
		this.programa = programa;
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
	public LapsoAcademico getObjLapso() {
		return objLapso;
	}
	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}
	public TipoMotivo getObjtipoMotivo() {
		return objtipoMotivo;
	}
	public void setObjtipoMotivo(TipoMotivo objtipoMotivo) {
		this.objtipoMotivo = objtipoMotivo;
	}
	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}
	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}
	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}
	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}
	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
		
	public List<ConfigurableApelaciones> getListaA() {
		return listaA;
	}
	public void setListaA(List<ConfigurableApelaciones> listaA) {
		this.listaA = listaA;
	}
	public List<EstadoApelacion> getListaEdoApelacion() {
		return listaEdoApelacion;
	}
	public void setListaEdoApelacion(List<EstadoApelacion> listaEdoApelacion) {
		this.listaEdoApelacion = listaEdoApelacion;
	}
	
	
	public String getParametroLapsoAcademico() {
		return parametroLapsoAcademico;
	}
	public void setParametroLapsoAcademico(String parametroLapsoAcademico) {
		this.parametroLapsoAcademico = parametroLapsoAcademico;
	}
	public String getParametroTipoSancion() {
		return parametroTipoSancion;
	}
	public void setParametroTipoSancion(String parametroTipoSancion) {
		this.parametroTipoSancion = parametroTipoSancion;
	}
	public String getParametroMotivo() {
		return parametroMotivo;
	}
	public void setParametroMotivo(String parametroMotivo) {
		this.parametroMotivo = parametroMotivo;
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

	public EstadoApelacion getObjEdoApelacion() {
		return objEdoApelacion;
	}
	public void setObjEdoApelacion(EstadoApelacion objEdoApelacion) {
		this.objEdoApelacion = objEdoApelacion;
	}
	public ListModelList<String> getCmbEdoApelacion()
	{      
		return cmbEdoApelacion;
	}
	public void setCmbEdoApelacion(ListModelList<String> cmbEdoApelacion) {
		this.cmbEdoApelacion = cmbEdoApelacion;
	}
	public void setCmbVeredicto(ListModelList<String> cmbVeredicto) {
		this.cmbVeredicto = cmbVeredicto;
	}
	
	public String getParametroEdoApelacion() {
		return parametroEdoApelacion;
	}
	public void setParametroEdoApelacion(String parametroEdoApelacion) {
		this.parametroEdoApelacion = parametroEdoApelacion;
	}
	public String getParametroAsignatura() {
		return parametroAsignatura;
	}
	public void setParametroAsignatura(String parametroAsignatura) {
		this.parametroAsignatura = parametroAsignatura;
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
	public ListModelList<String> getCmbVeredicto() {
		cmbVeredicto.add("Procedente");
		cmbVeredicto.add("No Procedente");
		cmbVeredicto.add("Todos");
		return cmbVeredicto;
	}
	

	//===============================FIN DE LOS METODOS SET Y GET==============================
		/**Inicialización
		 * @param init
		 * @return Carga de Variables y metodos inicializados
		 * @throws No dispara ninguna excepcion.
		 */
	@Init
	public void init() {
		// initialization code
		buscarTipoMotivo();
		buscarProgramaA();
		buscarActivoLapso();
		listadoSancion();
		listadoInstancia();
		buscarEdoApelacion();
		cmbVeredicto= new ListModelList<String>();
	}
	/** buscar Asignaturas
	 * @param  IdPrograma
	 * @return lista de de asignaturas, programas
	 */
	@Command
	@NotifyChange({ "listaAsignaturas","programa" })
	public void buscarAsignaturas() {
	 listaAsignaturas = servicioAsignatura.buscarAsignaturasPorPrograma(programa.getIdPrograma());
	}
	
	//REPORTE
	/** Muestra los tipo de modelos que puee mostrarse el reporte
	 * @param  
	 * @return modelos de la lista
	 */
	
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
  			Arrays.asList(
  					new ReportType("Word (RTF)", "rtf"), 
  					new ReportType("Reporte en Excel", "xls"), 
  					new ReportType("Excel (JXL)", "jxl"), 
  					new ReportType("CSV", "csv"), 
  					new ReportType("OpenOffice (ODT)", "odt")));
	
	/** buscar estado de Apelación
	 * @param  
	 * @return lista de estado de Apelación
	 */
	
	@Command
	@NotifyChange({ "listaEdoApelacion" })
	public void buscarEdoApelacion() {
		listaEdoApelacion = servicioestadoapelacion.listadoEstadoApelacionActivas();
		EstadoApelacion edo_ape = new EstadoApelacion(null, "Todos", "Todos", null);
		listaEdoApelacion.add(listaEdoApelacion.size()/*0*/, edo_ape);
	}
	
	/** Objeto Combo Estado  Apelación.
 	* @param Ninguno
 	* @return Objeto Estado  Apelación
 	* @throws No dispara ninguna excepción.
 	*/
	
	@Command
	@NotifyChange({ "listaEdoApelacion" })
	public EstadoApelacion objCmbEdoApelacion() {
		return objEdoApelacion;
	}
	
	/** buscar tipo motivo
	 * @param  
	 * @return lista de tipo motivo
	 */
	
	@Command
	@NotifyChange({ "lista" })
	public void buscarTipoMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
		TipoMotivo mot = new TipoMotivo(null,"Todos", null,"Todos",false);
		listaTipoMotivo.add(listaTipoMotivo.size()/*0*/, mot);
	}
	
	/** Objeto Combo Tipo Motivo.
 	* @param Ninguno
 	* @return Objeto Tipo Motivo
 	* @throws No dispara ninguna excepción.
 	*/
	
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public TipoMotivo objCmbtipoMotivo() {
		return objtipoMotivo;
	}
	
	/** buscar Programa academico
	 * @param  
	 * @return lista de programa Académico
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
	
	/** buscar Lapso Academico
	 * @param  
	 * @return lista de lapso Académico
	 */
	
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarActivoLapso() {
		listaLapsoAcademico = serviciolapsoacademico.listadoLapsoAcademico();
		LapsoAcademico lap = new LapsoAcademico("Todos", null, null, null);
		listaLapsoAcademico.add(listaLapsoAcademico.size(),lap);
	}
	
	/** Objeto Combo Lapso.
 	* @param Ninguno
 	* @return Objeto Lapso Académico
 	* @throws No dispara ninguna excepción.
 	*/
	
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;
	}
	
	/** buscar Sancionados
	 * @param  
	 * @return lista de sancion
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
	
	/** buscar Instancia
	 * @param  
	 * @return lista de instacias apeladas
	 */
	
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
		InstanciaApelada ins = new InstanciaApelada(null,"Todos", null, "Todos", null);
		listaInstanciaApelada.add(listaInstanciaApelada.size(),ins);
	}
	
	/** Objeto Combo Instancia.
 	* @param Ninguno
 	* @return Objeto Instancia Apelada
 	* @throws No dispara ninguna excepción.
 	*/
	
	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public InstanciaApelada objCmbinstanciaApelada() {
		return objinstanciaApelada;

	}

	@Command
	@NotifyChange({ "listaA" })
	public void buscarApelacion() {
		if(objinstanciaApelada==null|| objLapso==null || objprograma==null || objSancion==null || objtipoMotivo==null
				|| objVeredicto==null || objEdoApelacion==null){
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		}
		else{
			configurarParametro1();
			configurarParametroSancion();
			configurarParametroInstanciaApelada();
			configurarParametroMotivo();
			configurarParametroProgramaAcademico();
			configurarParametroVeredicto();
			configurarParametroEdoApelacion();
			listaA = servicioreporteconfigurableapelaciones.buscarTodasApelaciones(parametroLapsoAcademico,parametroTipoSancion,parametroInstanciaApelada,
					 parametroMotivo,parametroProgramaAcademico,parametroVeredicto,parametroEdoApelacion);
		}
		
	}
	/** Limpiar apelaciones 
	* @param Ninguno
	* @return Limpiar cada uno de los combos de la vista
	* @throws No dispara ninguna excepcion.
	*/
	
	@Command
	@NotifyChange({ "programa", "objSancion","objtipoMotivo", "objinstanciaApelada","objLapso",
		"objVeredicto", "objEdoApelacion","asignaturas"})
	public void limpiarCombos() {
		programa = null;
		objSancion = null;
		objtipoMotivo = null;
		objinstanciaApelada = null;
		objLapso =null ;
		objVeredicto= null;
		objEdoApelacion= null;
		asignaturas= null;
		
	}
	@NotifyChange({ "parametroLapsoAcademico" })
	@Command
	public String configurarParametro1() // parametro codigo Lapso
	{
		if (objLapso.getCodigoLapso() == "Todos") {
			parametroLapsoAcademico = "sap.codigo_lapso";
		} else {
			parametroLapsoAcademico = "'" + objLapso.getCodigoLapso() + "'";
		}
		return parametroLapsoAcademico;
	}
	@NotifyChange({ "parametroTipoSancion" })// parametro Tipo sancion
	@Command
	public String configurarParametroSancion() {
		if (objSancion.getNombreSancion() == "Todos") {
			parametroTipoSancion = "esa.id_sancion";
		} else {
			parametroTipoSancion = "'" + objSancion.getIdSancion() + "'";
		}
		return parametroTipoSancion;
	}

	@NotifyChange({ "parametroInstanciaApelada" })// ParametroInstanciaApeldada
	@Command
	public String configurarParametroInstanciaApelada() {
		if (objinstanciaApelada.getDescripcion() == "Todos") {
			parametroInstanciaApelada = "sap.id_instancia_apelada";
		} else {
			parametroInstanciaApelada = "'"
					+ objinstanciaApelada.getIdInstanciaApelada() + "'";
		}
		return parametroInstanciaApelada;
	}

	@NotifyChange({ "parametroMotivo" })// ParametroMotivo
	@Command
	public String configurarParametroMotivo() {
		if (objtipoMotivo.getDescripcion() == "Todos") {
			parametroMotivo = "mot.id_tipo_motivo";
		} else {
			parametroMotivo = "'" + objtipoMotivo.getIdTipoMotivo() + "'";
		}
		return parametroMotivo;
	}
	@NotifyChange({ "parametroProgramaAcademico" })// ParametroprogramaAcademico
	@Command
	public String configurarParametroProgramaAcademico() {
		if (objprograma.getNombrePrograma() == "Todos") {
			parametroProgramaAcademico = "es.id_programa";
		} else {
			parametroProgramaAcademico = "'" + objprograma.getIdPrograma()
					+ "'";
		}
		return parametroProgramaAcademico;
	}

	@NotifyChange({ "parametroVeredicto" })
	// Parametro Sexo
	@Command
	public String configurarParametroVeredicto() {
		if (objVeredicto.equals("Todos")) {
			parametroVeredicto = "sap.veredicto";
		} else {
			parametroVeredicto = "'"+objVeredicto.toUpperCase()+"'";
		}
		return parametroVeredicto;
	}
	@NotifyChange({ "parametroEdoApelacion" })
	// Parametro Sexo
	@Command
	public String configurarParametroEdoApelacion() {
		if (objEdoApelacion.getNombreEstado().equals("Todos")) {
			parametroEdoApelacion ="edo_ape.id_estado_apelacion";
		} else {
			parametroEdoApelacion = "'"+objEdoApelacion.getIdEstadoApelacion()+"'";
			System.out.println("ID EDO APELACION"+objEdoApelacion.getIdEstadoApelacion());
		}
		return parametroEdoApelacion;
	}
	
	@Command("GenerarReporteConfigurableApelaciones")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteConfigurableApelaciones(){	
			if(listaA.size()>0){
				reportConfig =new ReportConfig(ruta);
				reportConfig.getParameters().put("ListaSancionados", new JRBeanCollectionDataSource(listaA));
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(listaA));
			}
			else{
				mensajeAlUsuario.informacionNoHayCoincidencias();
			}
	}
			
}
