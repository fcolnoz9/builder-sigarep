package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioReglamento;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPortalPrincipal {

	@WireVariable
	private ServicioReglamento servicioreglamento;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioCronograma serviciocronograma;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	private String cedula;
	private String nombreActividad;
	private String descripcionActividad;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Time hora_inicio;
	private String observacionCronograma;
	private String lugarActividad;
	private Cronograma cronograma;
	private List<Cronograma> listaCronograma = new LinkedList<Cronograma>();
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	Window win = null;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getObservacionCronograma() {
		return observacionCronograma;
	}

	public void setObservacionCronograma(String observacionCronograma) {
		this.observacionCronograma = observacionCronograma;
	}

	public String getLugarActividad() {
		return lugarActividad;
	}

	public void setLugarActividad(String lugarActividad) {
		this.lugarActividad = lugarActividad;
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public List<Cronograma> getListaCronograma() {
		return listaCronograma;
	}

	public void setListaCronograma(List<Cronograma> listaCronograma) {
		this.listaCronograma = listaCronograma;
	}

	@Init
	public void init() {
	}

	@Command
	public void modalEstadoEstudiante() {
		if (cedula == "" || cedula == null) {
			mensajeAlUsuario.advertenciaIngresarCedula();
		} else {
			if (servicioestudiantesancionado
					.buscarEstudianteSancionadoLapsoActual(cedula) == null) {
				mensajeAlUsuario.advertenciaNoExisteEstudianteSancionado();
			} else {
				if (serviciosolicitudapelacion
						.buscarSolicitudEstudiante(cedula).isEmpty()) {
					mensajeAlUsuario
							.informacionEstudianteSinSolicitudApelacion();
				}
				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("cedula", this.cedula);
				Executions
						.createComponents(
								"WEB-INF/sigarep/vistas/portal/externo/modales/HistorialEstudiante.zul",
								null, map);
			}
		}
	}

	/**
	 * modalPreguntasFrecuentes.
	 * 
	 * @param Ninguno
	 * @return Muestra la ventana con las preguntas frecuentes
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	public void modalPreguntasFrecuentes() {
		if (win != null) {
			win.detach();
		}
		win = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/Preguntas_Frecuentes.zul",
						null, null);
		win.setMaximizable(true);
		win.doModal();
	}

	@Command
	public void descargarGuia() {
		Reglamento guia = servicioreglamento.buscarGuia();
		if (guia != null) {
			Filedownload.save(guia.getDocumento().getContenidoDocumento(), guia
					.getDocumento().getTipoDocumento(), guia.getDocumento()
					.getNombreDocumento());
		} else {
			mensajeAlUsuario.advertenciaCargarDocumento();
		}
	}
}
