package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * RegistrarReconsideracion 
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarReconsideracion {
	
	@Wire("#modalDialog")
	private Window window;
	
	private String sancion;
	private String lapso;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacion;
	
	private String caso;
	private Integer idRecaudo;
	
	private Documento doc = new Documento();
	private Media media;
	
	private String nombreDoc;
	private String nombreDocumento;
	private String tipoDocumento;
	
	private EstudianteSancionado estudianteSeleccionado;
	
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<RecaudoEntregado> listaRecaudos = new LinkedList<RecaudoEntregado>();
	private List<SolicitudApelacion> listaSolicitud = new LinkedList<SolicitudApelacion>();
	
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	
	// Para llamar a los diferentes mensajes de dialogo
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	MotivoPK motivoPK = new MotivoPK();
	Motivo motivos = new Motivo();
	EstadoApelacion estadoApelacion = new EstadoApelacion();
	
//Comienzo de los Metodos GET y SET
	public String getNombres() {
		return nombres;
	}

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public String getObservacion() {
		return observacion;
	}

	public List<RecaudoEntregado> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setAsignaturaLapsosConsecutivos(String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setListaRecaudos(List<RecaudoEntregado> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
// Fin Metodos GET y SET
	
//Comienzo Otros Metodos
	
	@Init
	public void init(
			@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado v1)

	// initialization code
	{
		Selectors.wireComponents(view, this, false);
		this.estudianteSeleccionado = v1;
		cedula = estudianteSeleccionado.getEstudiante().getCedulaEstudiante();
		lapso = estudianteSeleccionado.getLapsoAcademico().getCodigoLapso();
		sancion = estudianteSeleccionado.getSancionMaestro().getNombreSancion();
		lapsosConsecutivos = estudianteSeleccionado.getLapsosAcademicosRp();
		
		concatenacionNombres();
		concatenacionApellidos();
		buscarRecaudosEntregados(cedula);
		mostrarDatosDeSancion();
		buscarSolicitud(cedula);
		if(listaSolicitud.size() > 0)
			caso = listaSolicitud.get(0).getNumeroCaso();
		else
			registrarApelacionConMotivos();
	
		media = null;
		doc = new Documento();
	}

	/** concatenacionNombres
	 * @return devuelve primer y segundo nombre concatenados de un estudiante sancionado
	 */
	public void concatenacionNombres() {
		nombres = estudianteSeleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoNombre();
	}
	
	/** concatenacionApellidos
	 * @return devuelve primer y segundo apellido concatenados de un estudiante sancionado
	 */
	public void concatenacionApellidos() {
		apellidos = estudianteSeleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoApellido();
	}
	
	/** buscarRecaudosEntregados
	 * @param cedula 
	 * @return Lista de recaudos y motivos por estudiante
	 */
	@Command
	@NotifyChange({ "listaRecaudos" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudos = serviciorecaudoentregado
				.buscarRecaudosEntregadosReconsideracion(cedula);
	}
	
	/** mostrarDatosDeSancion
	 * @param  Ninguno
	 * @return Asignaturas y lapsos consecutivos dependiendo de la sanción
	 */
	private void mostrarDatosDeSancion() {
		if (sancion.equalsIgnoreCase("RR")) {
			asignaturas = servicioasignaturaestudiantesancionado
					.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i = 0; i < asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i)
							.getAsignatura().getNombreAsignatura()
							+ ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		} else {
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = estudianteSeleccionado
					.getLapsosAcademicosRp();
		}
	}

	/** registrarSolicitudApelacion
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones ocurren cuando se quiera registrar una reconsideracion y no se ha cargado la carta
	 */
	@NotifyChange({ "lista" , "observacion"})
	@Command
	public void registrarSolicitudApelacion(@BindingParam("window") Window winRegistrarReconsideracion) {
		Date fecha = new Date();
		Time hora = new Time(0);

			solicitudApelacionPK.setCedulaEstudiante(cedula);
			solicitudApelacionPK.setCodigoLapso(lapso);
			solicitudApelacionPK.setIdInstanciaApelada(2);
			solicitudApelacion.setId(solicitudApelacionPK);
			solicitudApelacion.setFechaSolicitud(fecha);
			solicitudApelacion.setEstatus(true);
			solicitudApelacion.setNumeroCaso(caso);
			solicitudApelacion.setObservacion(observacion);
			apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
			apelacionEstadoApelacionPK.setCodigoLapso(lapso);
			apelacionEstadoApelacionPK.setIdInstanciaApelada(2);
			apelacionEstadoApelacionPK.setIdEstadoApelacion(5);
			apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
			apelacionEstadoApelacion.setFechaEstado(new Date());
			motivoPK.setCedulaEstudiante(cedula);
			motivoPK.setCodigoLapso(lapso);
			motivoPK.setIdInstanciaApelada(2);
			motivoPK.setIdTipoMotivo(2);
			motivos.setId(motivoPK);
			motivos.setEstatus(true);
	
		try {
			serviciosolicitudapelacion.guardar(solicitudApelacion);
			servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
			serviciomotivo.guardarMotivo(motivos);
			mensajeAlUsuario.informacionRegistroCorrecto();
			winRegistrarReconsideracion.detach();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/** descargarDocumento
	 * @param 
	 * @return Documento descargado
	 */
	@Command
	public void descargarDocumento(
	@ContextParam(ContextType.COMPONENT) Component componente) {
		idRecaudo = listaRecaudos.get(0).getId().getIdRecaudo();
		int idRecaudo = Integer.parseInt(componente.getAttribute("idRecaudo")
				.toString());
		for (int j = 0; j < listaRecaudos.size(); j++) {
			if (listaRecaudos.get(j).getId().getIdRecaudo() == idRecaudo)
				Filedownload.save(listaRecaudos.get(j).getSoporte()
						.getDocumento().getContenidoDocumento(), listaRecaudos
						.get(j).getSoporte().getDocumento().getTipoDocumento(),
						listaRecaudos.get(j).getSoporte().getDocumento()
								.getNombreDocumento());
		}
	}
	
	/** cancelar
	 * @param Ninguno
	 * @return Ninguno
	 */
	@Command
	@NotifyChange({"observacion"})
	public void cancelar() {
		observacion = ""; 
	}
	
	/** buscarSolicitud
	 * @param String cedula
	 * @return Ninguno
	 */
	public void buscarSolicitud(String cedula){
		listaSolicitud = serviciosolicitudapelacion.buscarSolicitudEstudiante(cedula);	
	}
	
	/** registrarApelacionMotivo
	 * @param Ninguno
	 * @return Ninguno
	 */

	public void registrarApelacionConMotivos() {
		Integer instancia = 2;
		Integer idEstado = 5;
		Integer idMotivoGeneral = 2;
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("estudianteSeleccionado", estudianteSeleccionado);
		map.put("instancia", instancia);
		map.put("idEstado", idEstado);
		map.put("idMotivoGeneral", idMotivoGeneral);
		
		final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/RegistrarDatosInicialesApelacion.zul", null, map);
		window.setMaximizable(true);
		window.doModal();
	}
	
	@Command
	public void closeThis() {
		window.detach();
	}
}
