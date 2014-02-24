package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import sigarep.herramientas.EnviarCorreo;
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
	private List<Reglamento> listaReglamento = new LinkedList<Reglamento>();
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private String nombre;
	private String correo;
	private String telefono;
	private String consulta;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Init
	public void init() {
	}

	/**
	 * modalEstadoEstudiante.
	 * 
	 * @param Ninguno
	 * @return Muestra la ventana con el historial del estudiante sancionado.
	 * @throws Debe
	 *             agregar una cédula y esta debe estar en la lista de
	 *             estudiantes sancionados.
	 * 
	 */
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
	 *             dispara ninguna excepción.
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

	/**
	 * descargarGuia.
	 * 
	 * @param Ninguno
	 * @return Descarga la Guía paso a paso.
	 * @throws Debe
	 *             haber un documento cargado.
	 * 
	 */
	@Command
	public void descargarGuia() {
		listaReglamento = servicioreglamento.buscarGuia();
		if (listaReglamento.size() > 0) {
			Reglamento guia = servicioreglamento.buscarGuia().get(0);
			if (guia != null) {
				Filedownload.save(guia.getDocumento().getContenidoDocumento(),
						guia.getDocumento().getTipoDocumento(), guia
								.getDocumento().getNombreDocumento());
			} else {
				mensajeAlUsuario.advertenciaCargarDocumento();
			}
		} else {
			mensajeAlUsuario.advertenciaCargarDocumento();
		}
	}

	/**
	 * modalContactanos.
	 * 
	 * @param Ninguno
	 * @return Muestra la ventana contáctanos.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Command
	public void modalContactanos() {
		if (win != null) {
			win.detach();
		}
		win = (Window) Executions
				.createComponents(
						"WEB-INF/sigarep/vistas/portal/externo/modales/Contactanos.zul",
						null, null);
		win.setMaximizable(true);
		win.doModal();
	}

	/**
	 * limpiar.
	 * 
	 * @param Ninguno
	 * @return Limpiar todos los campos de la ventana.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({ "correo", "nombre", "telefono", "consulta" })
	public void limpiar() {
		nombre = "";
		telefono = "";
		correo = "";
		consulta = "";
	}

	/**
	 * enviarCorreoContactanos.
	 * 
	 * @param Ninguno
	 * @return envía el correo con el mensaje/consulta al sistema.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({ "correo", "nombre", "telefono", "consulta" })
	public void enviarCorreoContactanos() {
		EnviarCorreo enviar = new EnviarCorreo();
		enviar.sendEmailContactanos(correo, nombre, telefono, consulta);
		mensajeAlUsuario.informacionCorreoEnviado();
		limpiar();
	}

}
