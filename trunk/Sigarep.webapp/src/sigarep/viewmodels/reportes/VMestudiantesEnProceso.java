package sigarep.viewmodels.reportes;

import java.util.Arrays;
import java.util.Date;
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
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.reportes.ApelacionesComparativos;
import sigarep.modelos.data.reportes.EstudiantesEnProceso;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.reportes.ServicioReportes;
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
public class VMestudiantesEnProceso {
	// ***********************************DECLARACION DE LAS VARIABLES
	// SERVICIOS*************************
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioReportes servicioreportes;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;

	// ***********************************PARÁMETROS PARA
	// SERVICIOS************************
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
	// private List<LapsoAcademico> listaLapso;
	private List<EstudiantesEnProceso> estudiantesEnProceso = new LinkedList<EstudiantesEnProceso>();
	private List<SolicitudApelacion> listaSA = new LinkedList<SolicitudApelacion>();
	private List<EstudianteSancionado> listaES = new LinkedList<EstudianteSancionado>();

	// ***********************************DECLARACION DE LAS VARIABLES TIPO
	// OBJETO*************************
	private SancionMaestro objSancion;
	private ProgramaAcademico objPrograma;
	private EstadoApelacion objEstadoApelacion;
	private LapsoAcademico lapsoActivo;

	private Date desde;
	private Date hasta;
	// *********************************Mensajes***************************************
	@Wire("#winApelacionesPorMotivo")
	// para conectarse a la ventana con el ID
	Window ventana;

	@AfterCompose
	// para poder conectarse con los componentes en la vista, es necesario si no
	// da null Pointer
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL
	// REPORTE***************************

	ReportType reportType = null;
	private ReportConfig reportConfig = null;

	String ruta1 = "/WEB-INF/sigarepReportes/informes/especiales/RpEstudiantesEnProcesoDeApelacion1.jasper";
	String ruta2 = "/WEB-INF/sigarepReportes/informes/especiales/RpEstudiantesEnProcesoDeApelacion2.jasper";

	@Init
	public void init() {
		buscarPrograma();
		buscarTipoSancion();
		buscarEstadoApelacion();
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();

	}

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
		/*
		 * ProgramaAcademico prog = new ProgramaAcademico(null, "Todos", null);
		 * 
		 * listaPrograma.add(0, prog);
		 */
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
	 * buscar Sanción
	 * 
	 * @param
	 * @return lista de sanción
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaTipoSancion" })
	public void buscarTipoSancion() {
		listaTipoSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro sanc = new SancionMaestro(null, null, null, "Todos");
		listaTipoSancion.add(0, sanc);
	}

	/**
	 * Objeto Combo Sanción.
	 * 
	 * @param Ninguno
	 * @return Objeto Sanción
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaTipoSancion" })
	public SancionMaestro objCmbSancion() {
		return objSancion;

	}

	/**
	 * buscar Estado Apelacion
	 * 
	 * @param
	 * @return lista de estado apelacion
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaEstadoApelacion" })
	public void buscarEstadoApelacion() {
		listaEstadoApelacion = servicioestadoapelacion.listadoEstadoApelacionActivas();


		for (int i = 0; i<listaEstadoApelacion.size(); i++) {
			int instancia = listaEstadoApelacion.get(i).getInstanciaApelada().getIdInstanciaApelada();
			switch (instancia) {
			case 1:
				listaEstadoApelacion.get(i).setNombreEstado(listaEstadoApelacion.get(i).getNombreEstado() + " I");
				break;
			case 2:
				listaEstadoApelacion.get(i).setNombreEstado(listaEstadoApelacion.get(i).getNombreEstado() + " II");
				break;
			case 3:
				listaEstadoApelacion.get(i).setNombreEstado(listaEstadoApelacion.get(i).getNombreEstado() + " III");
				break;
			default:
				break;
			}
			
		}
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

	public List<EstudiantesEnProceso> getestudiantesEnProceso() {
		return estudiantesEnProceso;
	}

	public void setestudiantesEnProceso(
			List<EstudiantesEnProceso> estudiantesEnProceso) {
		this.estudiantesEnProceso = estudiantesEnProceso;
	}

	public SancionMaestro getObjSancion() {
		return objSancion;
	}

	public void setObjSancion(SancionMaestro objSancion) {
		this.objSancion = objSancion;
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

	// ===============================FIN DE LOS METODOS SET Y
	// GET==============================
	// REPORTE
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

		estudiantesEnProceso.clear();
		ProgramaAcademico prog = objPrograma;
		// LapsoAcademico lap = objLapso;

		if (objEstadoApelacion == null) {
			mensajeAlUsuario.advertenciaSeleccionarTodo();
		} else {

			// listaES.get(0).getEstudiante().getCedulaEstudiante();
			// listaES.get(0).getEstudiante().getPrimerNombre();
			// listaES.get(0).getEstudiante().getPrimerApellido();
			// listaES.get(0).getSancionMaestro().getNombreSancion();
			// listaES.get(0).getEstudiante().getProgramaAcademico().getNombrePrograma();
			//
			//
			// listaSA.get(0).getEstudianteSancionado().getEstudiante().getCedulaEstudiante();
			// listaSA.get(0).getEstudianteSancionado().getEstudiante().getPrimerNombre();
			// listaSA.get(0).getEstudianteSancionado().getEstudiante().getPrimerApellido();
			// listaSA.get(0).getEstudianteSancionado().getSancionMaestro().getNombreSancion();
			// listaSA.get(0).getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma();

			switch (objEstadoApelacion.getIdEstadoApelacion()) {
			case (1):
				// Registro de Apelacion Inicial - Estado = 1
				listaES = servicioestudiantesancionado.buscarSancionados();

				if (listaES.size() > 0) {
					reportConfig = new ReportConfig(ruta1); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaES));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (2):
				// Verificación de Recaudos I - Estado = 2
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosI(lapsoActivo, 1);

				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (3):
				// Analizar validez de Recaudos I - Estado = 3
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezI(
						lapsoActivo, 1);
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (4):
				// Veredicto del Caso I - Estado = 4
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoI();
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (5):
				// Registro de Recurso de Reconsideración - Estado = 5
				listaES = servicioestudiantesancionado
						.buscarSancionadosReconsideracion();
				if (listaES.size() > 0) {
					reportConfig = new ReportConfig(ruta1); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaES));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (6):
				// Verificación de Recuados II - Estado = 6
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosII(lapsoActivo, 2);
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (7):
				// Analizar validez de Recaudos II - Estado = 7
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezII(
						lapsoActivo, 2);
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (8):
				// Veredicto del Caso II - Estado = 8
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoII();
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (9):
				// Registro de Recurso Jerárquico - Estado = 9
				listaES = servicioestudiantesancionado
						.buscarSancionadosRecursoJerarquico();
				if (listaES.size() > 0) {
					reportConfig = new ReportConfig(ruta1); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaES));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (10):
				// Verificación de Recaudos III - Estado = 10
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVerificarRecaudosIII(lapsoActivo, 3);
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (11):
				// Analizar validez de Recaudos III - Estado = 11
				listaSA = serviciosolicitudapelacion.buscarAnalizarValidezIII(
						lapsoActivo, 3);
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
					reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE
														// FORMATO DE IMPRESION
														// DEL REPORTE
				} else {
					mensajeAlUsuario.informacionNoHayCoincidencias();
				}
				break;
			case (12):
				// Veredicto del caso III - Estado = 12
				listaSA = serviciosolicitudapelacion
						.buscarApelacionesVeredictoIII();
				if (listaSA.size() > 0) {
					reportConfig = new ReportConfig(ruta2); // INSTANCIANDO UNA
															// NUEVA LLAMADA AL
															// REPORTE
					reportConfig.getParameters().put("estado",
							objEstadoApelacion.getNombreEstado());
					reportConfig.getParameters().put("Lista",
							new JRBeanCollectionDataSource(listaSA));
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

	@Command
	@NotifyChange({ "objInstanciaApelada", "objSancion", "objPrograma",
			"objEstadoApelacion" })
	public void limpiar() {
		// objLapso= null;
		objSancion = null;
		objPrograma = null;
		objEstadoApelacion = null;

	}

	// #####################MENSAJE PARA CERRAR##########################
	@Command
	@NotifyChange({})
	public void cerrarVentana(
			@ContextParam(ContextType.BINDER) final Binder binder) {

		Messagebox.show("¿Realmente desea cerrar la ventana?", "Confirmar",
				new Messagebox.Button[] { Messagebox.Button.YES,
						Messagebox.Button.NO }, Messagebox.QUESTION,
				new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
						case YES:
							ventana.detach();

						}
					}
				});
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public List<SolicitudApelacion> getListaSA() {
		return listaSA;
	}

	public void setListaSA(List<SolicitudApelacion> listaSA) {
		this.listaSA = listaSA;
	}

	public List<EstudianteSancionado> getListaES() {
		return listaES;
	}

	public void setListaES(List<EstudianteSancionado> listaES) {
		this.listaES = listaES;
	}

}
