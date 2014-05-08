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

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * VM Informe Especial de Estudiantes en Proceso de Apelación UCLA DCYT Sistemas
 * de Información.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudiantesEnProcesoApelacion {

	// ***********************************DECLARACION DE LAS VARIABLES
	// SERVICIOS*************************
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;

	// ***********************************PARÁMETROS PARA
	// REPORTES************************
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreSancion;
	@WireVariable
	private String codigoLapso;

	// ***********************************DECLARACION DE
	// LISTAS*************************
	private List<ProgramaAcademico> listaPrograma;
	private List<SancionMaestro> listaTipoSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	private List<EstadoApelacion> listaEstadoApelacion;
	private List<SolicitudApelacion> listaSA = new LinkedList<SolicitudApelacion>();
	private List<EstudianteSancionado> listaES = new LinkedList<EstudianteSancionado>();
	private List<EstudianteSancionado> lista1 = new LinkedList<EstudianteSancionado>();
	private List<SolicitudApelacion> lista2 = new LinkedList<SolicitudApelacion>();

	// ***********************************DECLARACION DE LAS VARIABLES TIPO
	// OBJETO*************************
	private ProgramaAcademico objPrograma;
	private EstadoApelacion objEstadoApelacion;
	private LapsoAcademico lapsoActivo;
	private InstanciaApelada objinstanciaApelada;

	// *********************************Mensajes***************************************
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************

	ReportType reportType = null;
	private ReportConfig reportConfig = null;

	String ruta1 = "/WEB-INF/sigarepReportes/informes/especiales/RpEstudiantesEnProcesoDeApelacion1.jasper";
	String ruta2 = "/WEB-INF/sigarepReportes/informes/especiales/RpEstudiantesEnProcesoDeApelacion2.jasper";

	// **************METODOS SET Y GET NECESARIOS PARA GENERAR
	// REPORTE*****************
	// Reporte SET/GETS
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

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<SancionMaestro> getListaTipoSancion() {
		return listaTipoSancion;
	}

	public void setListaTipoSancion(List<SancionMaestro> listaTipoSancion) {
		this.listaTipoSancion = listaTipoSancion;
	}

	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}

	public void setListaInstanciaApelada(
			List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}

	public List<EstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(
			List<EstadoApelacion> listaEstadoApelacion) {
		this.listaEstadoApelacion = listaEstadoApelacion;
	}

	public List<SolicitudApelacion> getListaSA() {
		return listaSA;
	}

	public void setListaSA(List<SolicitudApelacion> listaSA) {
		this.listaSA = listaSA;
	}

	public List<SolicitudApelacion> getLista2() {
		return lista2;
	}

	public void setLista2(List<SolicitudApelacion> lista2) {
		this.lista2 = lista2;
	}

	public List<EstudianteSancionado> getListaES() {
		return listaES;
	}

	public void setListaES(List<EstudianteSancionado> listaES) {
		this.listaES = listaES;
	}

	public List<EstudianteSancionado> getLista1() {
		return lista1;
	}

	public void setLista1(List<EstudianteSancionado> lista1) {
		this.lista1 = lista1;
	}

	public ProgramaAcademico getObjPrograma() {
		return objPrograma;
	}

	public void setObjPrograma(ProgramaAcademico objPrograma) {
		this.objPrograma = objPrograma;
	}

	public EstadoApelacion getObjEstadoApelacion() {
		return objEstadoApelacion;
	}

	public void setObjEstadoApelacion(EstadoApelacion objEstadoApelacion) {
		this.objEstadoApelacion = objEstadoApelacion;
	}

	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}

	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}

	// ===============================FIN DE LOS METODOS SET Y
	// GET==============================

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
			Arrays.asList(new ReportType("PDF", "pdf"), new ReportType(
					"Word (RTF)", "rtf"), new ReportType("Excel", "xls"),
					new ReportType("Excel (JXL)", "jxl"), new ReportType("CSV",
							"csv"), new ReportType("OpenOffice (ODT)", "odt")));

	// ******************************METODO DE
	// INICIALIZACION*****************************
	/**
	 * Inicialización
	 * 
	 * @param init
	 * @return Carga de Variables y métodos inicializados
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init() {
		buscarPrograma();
		// buscarEstadoApelacion();
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();
		listadoInstancia();
	}

	// @@@@@@@@@@@@@@@@@METODOS PARA CARGAR CADA UNO DE LOS
	// COMBOS@@@@@@@@@@@@@@@@@@@
	/**
	 * buscar Programa Académico
	 * 
	 * @param
	 * @return lista de programa Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarPrograma() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
		ProgramaAcademico prog = new ProgramaAcademico(0, "Todos", true);
		listaPrograma.add(0, prog);
	}

	/**
	 * Objeto Combo Programa.
	 * 
	 * @param Ninguno
	 * @return Objeto Programa Académico
	 * @throws No
	 *             dispara ninguna excepción.
	 */

	@Command
	@NotifyChange({ "listaPrograma" })
	public ProgramaAcademico objCmbPrograma() {
		return objPrograma;
	}

	/**
	 * buscar Instancia
	 * 
	 * @param
	 * @return lista de instacias apeladas
	 */

	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public void listadoInstancia() {
		listaInstanciaApelada = servicioInstanciaApelada
				.listadoInstanciaApelada();
	}

	/**
	 * Objeto Combo Instancia.
	 * 
	 * @param Ninguno
	 * @return Objeto Instancia Apelada
	 * @throws No
	 *             dispara ninguna excepción.
	 */

	@Command
	@NotifyChange({ "listaInstanciaApelada" })
	public InstanciaApelada objCmbinstanciaApelada() {
		return objinstanciaApelada;
	}

	@Command
	@NotifyChange({ "listaEstadoApelacion", "objinstanciaApelada" })
	// *********BUSCAR ESTADOS POR INSTANCIA
	public void buscarEstados() {
		listaEstadoApelacion = servicioestadoapelacion
				.buscarEstados(objinstanciaApelada.getIdInstanciaApelada());
	}

	/**
	 * Objeto Combo Estado Apelacion.
	 * 
	 * @param Ninguno
	 * @return Objeto Estado Apelacion
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaEstadoApelacion" })
	public EstadoApelacion objCmbEstadoApelacion() {
		return objEstadoApelacion;
	}

	// ###############METODO PARA IMPRIMIR REPORTE#################
	/**
	 * Generar Reporte Estadístico Comparativo de Apelaciones por Motivo y
	 * Veredicto.
	 * 
	 * @param Ninguno
	 * @return Reporte Estadístico Comparativo de Apelaciones por Motivo y
	 *         Veredicto generado en PDF u otro tipo de archivo
	 * @throws Si
	 *             la lista está vacía no genera el reporte.
	 */
	@Command("GenerarReporteEstudiantesEnProceso")
	@NotifyChange({ "reportConfig" })
	public void GenerarReporte() {
		listaES.clear();
		lista1.clear();
		listaSA.clear();
		lista2.clear();

		if (objEstadoApelacion == null || objPrograma == null
				|| reportType == null) {
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		} else {
			switch (objEstadoApelacion.getIdEstadoApelacion()) {
			case (1):
				// Registro de Apelacion Inicial - Estado = 1
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosI(lapsoActivo, 1);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2 = listaSA;
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma()))
							lista2.add(listaSA.get(i));
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (2):
				// Verificación de Recaudos I - Estado = 2
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezI(
						lapsoActivo, 1);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (3):
				// Analizar validez de Recaudos I - Estado = 3
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoI();
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (5):
				// Registro de Recurso de Reconsideración - Estado = 5
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosII(lapsoActivo, 2);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (6):
				// Verificación de Recuados II - Estado = 6
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezII(
						lapsoActivo, 2);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (7):
				// Analizar validez de Recaudos II - Estado = 7
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoII();
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (9):
				// Registro de Recurso Jerárquico - Estado = 9
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosIII(lapsoActivo, 3);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (10):
				// Verificación de Recaudos III - Estado = 10
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezIII(
						lapsoActivo, 3);
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (11):
				// Analizar validez de Recaudos III - Estado = 11
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoIII();
				if (objPrograma.getNombrePrograma() == "Todos") {
					lista2.addAll(listaSA);
				} else {
					for (int i = 0; i < listaSA.size(); i++) {
						if (listaSA.get(i).getEstudianteSancionado()
								.getEstudiante().getProgramaAcademico()
								.getIdPrograma()
								.equals(objPrograma.getIdPrograma())) {
							lista2.add(listaSA.get(i));
						}
					}
				}
				if (lista2.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("instancia",
							objinstanciaApelada.getInstanciaApelada());
					reportConfig.getParameters().put("programa",
							objPrograma.getNombrePrograma());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(lista2));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			default:
				break;
			}
		}

	}

	// *******************************METODO PARA LIMPIAR
	// COMBOS******************************
	@Command
	@NotifyChange({ "objPrograma", "objEstadoApelacion", "objinstanciaApelada",
			"reportType" })
	public void limpiar() {
		objPrograma = null;
		objEstadoApelacion = null;
		objinstanciaApelada = null;
		reportType = null;
	}

}
