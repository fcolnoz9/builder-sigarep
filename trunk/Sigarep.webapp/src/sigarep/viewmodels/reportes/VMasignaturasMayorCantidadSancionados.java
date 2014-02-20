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

/** View Models de Reporte Asignaturas Mayor Cantidad Sancionados.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 * @since 20/01/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMasignaturasMayorCantidadSancionados {
	
	//*************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	//*************************RUTA DEL REPORTE***************************
	String ruta= "/WEB-INF/sigarepReportes/estadisticos/RpAsignaturasSancionadosVsApelaciones.jasper";
	

	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable ServicioInstanciaApelada servicioInstanciaApelada;
	
	//***********************************DECLARACION DE LISTAS*************************
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
  	
	
	
  //*******METODO DE INICIALIZACION*******
  	
	  	/**Inicialización
		 * @param init
		 * @return Carga de Variables y metodos inicializados
		 * @throws No dispara ninguna excepcion.
		 */
	  	
	  	@Init
	  	public void Init() {
	  		buscarProgramaAcademico();
	  		buscarLapso();
	  		buscarInstanciaApelada();
	  		listaAsigMayor = new ListModelList<ListaAsignaturasMayorCantidadSancionados>();
	  		
	  	}
  	//*******FIN DEL METODO*******
  	
  	
	  	/** Limpiar Asignaturas Sancionados.
		* @param Ninguno
		* @return Limpiar cada uno de los combos de la vista
		* @throws No dispara ninguna excepcion.
		*/

		@Command
		@NotifyChange({"programaAcademico","lapsoAcademico","instanciaApelada"})
		public void limpiarAsignaturasSancionados(){
			programaAcademico= null;
			lapsoAcademico= null;
			instanciaApelada= null;
		}

  	
	//@@@@@@@@@@@@@@@@@METODOS PARA CARGAR CADA UNO DE LOS COMBOS@@@@@@@@@@@@@@@@@@@
	
  	/** Buscar Programas Academicos.
 	* @param Ninguno
 	* @return Listado de Programas Academicos
 	* @throws No dispara ninguna excepcion.
 	*/
  	
  	@Command
	@NotifyChange({ "listaComboPrograma" })
	public void buscarProgramaAcademico() {
		setListaComboPrograma(servicioprogramaacademico.listadoProgramas());
	}
  	
  	/** Objeto Combo Programa.
 	* @param Ninguno
 	* @return Objeto Programa Academico
 	* @throws No dispara ninguna excepcion.
 	*/
  	
	@Command
	 @NotifyChange({"listaComboPrograma"})
	public ProgramaAcademico objetoComboPrograma() {
		return programaAcademico;
		
	}
	
	/** Buscar Lapsos.
 	* @param Ninguno
 	* @return Listado de Lapsos Academicos
 	* @throws No dispara ninguna excepcion.
 	*/
	
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapso() {
		setListaLapsoAcademico(serviciolapsoacademico.listadoLapsoAcademico());
	}
	
	/** Objeto Combo Lapso.
 	* @param Ninguno
 	* @return Objeto Lapso Academico
 	* @throws No dispara ninguna excepcion.
 	*/
	
	@Command
	 @NotifyChange({"listaLapsoAcademico"})
	public LapsoAcademico objetoComboLapso() {
		return lapsoAcademico;
		
	}
	
	/** Buscar Instancias Apeladas.
 	* @param Ninguno
 	* @return Listado de Instancias
 	* @throws No dispara ninguna excepcion.
 	*/
	
	@Command
	@NotifyChange({ "listaComboInstancia" })
	public void buscarInstanciaApelada() {
		setListaComboInstancia(servicioInstanciaApelada.buscarTodas());
	}
	
	/** Objeto Combo Instancia.
 	* @param Ninguno
 	* @return Objeto Instancia Apelada
 	* @throws No dispara ninguna excepcion.
 	*/
	
	@Command
	 @NotifyChange({"listaComboInstancia"})
	public InstanciaApelada objetoComboInstancia() {
		return instanciaApelada;
		
	}
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FIN DEL METODO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	
	// ###############METODO PARA IMPRIMIR REPORTE#################
	
	/** Generar Reporte Asignaturas Mayor.
 	* @param Ninguno
 	* @return Reporte de las 5 asignaturas con mas sancionados generado en PDF u otro tipo de archivo
 	* @throws Si la lista esta vacia no genera el reporte
 	*/
	
	@Command("GenerarReporteAsigMayor")
	@NotifyChange({"reportConfig"})
	public void GenerarReporteAsigMayor(){
		
				
			if(programaAcademico==null || lapsoAcademico== null || instanciaApelada== null)
				Messagebox.show("Debe seleccionar todos los campos", "Informacion", Messagebox.OK,Messagebox.INFORMATION);
			else{
				
				listaAsigMayor.clear();
				idPrograma = programaAcademico.getIdPrograma();// OBTENER EL VALOR DE LOS COMBOS
				codigoLapso = lapsoAcademico.getCodigoLapso();
				idInstanciaApelada= instanciaApelada.getIdInstanciaApelada();
		
				List<ListaAsignaturasMayorCantidadSancionados> lista= servicioListaAsignaturasMayor.buscarAsignaturasSancionados(idPrograma,codigoLapso,idInstanciaApelada);
				listaAsigMayor.addAll(lista);
				
				if(listaAsigMayor.getSize()> 0){
					reportConfig= new ReportConfig(ruta); //INSTANCIANDO UNA NUEVA LLAMADA AL REPORTE
					reportConfig.getParameters().put("nombrePrograma", programaAcademico.getNombrePrograma());
					reportConfig.getParameters().put("instanciaApelada", instanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("lapso", lapsoAcademico.getCodigoLapso());
					reportConfig.getParameters().put("Lista", new JRBeanCollectionDataSource(listaAsigMayor));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE IMPRESION DEL REPORTE
					reportConfig.setDataSource(new JRBeanCollectionDataSource(listaAsigMayor)); //ASIGNANDO MEDIANTE EL DATA SOURCE LOS DATOS PARA DIBUJAR EL REPORTE   
				}
				else
					Messagebox.show("No existen datos para la seleccion", "Informacion", Messagebox.OK,Messagebox.INFORMATION);

			}
				
	}
}




