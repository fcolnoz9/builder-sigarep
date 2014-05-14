package sigarep.viewmodels.transacciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;

/**
 * VM Historicos Sigarep BD.
 * Maneja la generación de archivos históricos de los estudiantes
 * procesados en lapsos anteriores.
 * @author Equipo Builder
 * @version 1.2
 * @since 10/01/2014
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistoricosSigarepBD {
	// --------------------------Servicios------------------------------
	@WireVariable
	ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioCronograma serviciocronograma;
	// --------------------------Variables de Control-------------------
	private Radio radio;
	private boolean checkTodos;
	private Date fecha;
	private String selected = "";
	// --------------------------Variables Lista------------------------
	private List<LapsoAcademico> listaLapsoAcademico = new LinkedList<LapsoAcademico>();
	// --------------------------Variables Objeto-----------------------
	private LapsoAcademico lapso;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Métodos Set y Get
	public String getSelected() {
		return selected;
	}

	@NotifyChange
	public void setSelected(String selected) {
		this.selected = selected;
	}

	public boolean isCheckTodos() {
		return checkTodos;
	}

	public void setCheckTodos(boolean checkTodos) {
		this.checkTodos = checkTodos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Radio getRadio() {
		return radio;
	}

	public void setRadio(Radio radio) {
		this.radio = radio;
	}

	public LapsoAcademico getLapso() {
		return lapso;
	}

	public void setLapso(LapsoAcademico lapso) {
		this.lapso = lapso;
	}

	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	// Fin Métodos Set y Get

	/**
	 * inicialización
	 * 
	 * @param init
	 * @return Código de inicialización
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init() {
		buscarLapsoAcademicoAnteriores();
	}

	/**
	 * Buscar lapso académico anterios.
	 * 
	 * @param Ninguno
	 * @return Lista lapsos académicos.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapsoAcademicoAnteriores() {
		listaLapsoAcademico = serviciolapsoacademico.buscarTodosLosLapsos();
	}

	/**
	 * Generar historico.
	 * 
	 * @param Ninguno
	 * @return Genera el historico en la BD.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "fecha", "radio", "lapso" })
	public void generarHistorico() {
		if (lapso != null) {
			List<String> listaElementosAInsertar = new ArrayList<String>();
			List<String> listaAuxiliarElementos = new ArrayList<String>();
			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy-hh-mm-ss");
			String fechaString = sdf.format(new Date());
			String nombreHistorico = "historicoTodosSigarep-" + fechaString;
			String destinoHistorico = "todos/historicoTodosSigarep-"
					+ fechaString;
			if (!selected.equals("")) {
				if (getSelected().equals("todos")) {
					listaAuxiliarElementos = serviciosolicitudapelacion
							.historicoSolicitudApelacion(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					listaAuxiliarElementos = servicioestudiantesancionado
							.historicoEstudiantesSancionados(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					listaAuxiliarElementos = serviciomotivo
							.historicoMotivosApelacion(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					listaAuxiliarElementos = serviciocronograma
							.historicoCronogramaActividades(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
				}
				if (getSelected().equals("solicitud")) {
					listaAuxiliarElementos = serviciosolicitudapelacion
							.historicoSolicitudApelacion(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					nombreHistorico = "solicitudes-" + fechaString;
					destinoHistorico = "solicitudes/solicitudesApelacion-"
							+ fechaString;
				}
				if (getSelected().equals("sancionados")) {
					listaAuxiliarElementos = servicioestudiantesancionado
							.historicoEstudiantesSancionados(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					nombreHistorico = "sancionados-" + fechaString;
					destinoHistorico = "sancionados/estudiantesSancionados-"
							+ fechaString;
				}
				if (getSelected().equals("recaudosEntregados")) {
					listaAuxiliarElementos = serviciomotivo
							.historicoMotivosApelacion(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					nombreHistorico = "recaudosEntregados-" + fechaString;
					destinoHistorico = "recaudosEntregados/recaudosEntregados-"
							+ fechaString;
				}
				if (getSelected().equals("cronograma")) {
					listaAuxiliarElementos = serviciocronograma
							.historicoCronogramaActividades(lapso);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					nombreHistorico = "cronogramaActividades-" + fechaString;
					destinoHistorico = "cronogramaActividades/cronograma-"
							+ fechaString;
				}
				String ruta = UtilidadesSigarep.obtenerDirectorio();
				ruta = ruta
						+ "Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/historicos";
				if (listaElementosAInsertar.size() > 0) {
					File fichero = new File(ruta + "/" + destinoHistorico
							+ ".sql");
					try {
						BufferedWriter writer = new BufferedWriter(
								new FileWriter(fichero));
						for (int j = 0; j < listaElementosAInsertar.size(); j++) {
							String ln = System.getProperty("line.separator");
							writer.write(listaElementosAInsertar.get(j) + ln);
						}
						mensajeAlUsuario.informacionOperacionExitosa();
						mensajeAlUsuario
								.informacionCreacionHistorico(nombreHistorico);
						writer.close();
					} catch (Exception e) {
						System.err.println(e);
					}
				} else
					mensajeAlUsuario.errorNoHayResgistrosParaRespaldo();
			} else
				mensajeAlUsuario.advertenciaSeleccionarOpcion();
		} else
			mensajeAlUsuario.advertenciaSeleccionarLapso();
	}

	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana, condicion);
	}
}//fin VMHistoricosSigarepBD