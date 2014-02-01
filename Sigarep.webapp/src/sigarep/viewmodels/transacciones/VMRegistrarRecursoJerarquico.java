package sigarep.viewmodels.transacciones;

/**VMRegistrarRecurso
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo: Builder-SIGAREP 
 * @version 1.0
 * @since 20/12/13 
 */

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.EstadoApelacion;
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
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarRecursoJerarquico {

	@Wire("#modalDialog")
	private Window window;

	private String sancion;
	private String lapso;
	private String nombres;
	private String apellidos;
	private Integer caso;
	private String cedula;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private String nombreDoc;
	private String observacion;
	

	private Integer idTipoMotivo;
	private Integer idRecaudo;

	private List<RecaudoEntregado> listaRecaudos = new LinkedList<RecaudoEntregado>();
	private List<AsignaturaEstudianteSancionado> asignaturas;

	private Documento doc = new Documento();
	private Media media;

	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioSoporte serviciosoporte;

	MensajesAlUsuario mensajesusuario = new MensajesAlUsuario();

	private EstudianteSancionado estudianteSeleccionado;

	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();

	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();

	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();

	Soporte soporte = new Soporte();

	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();

	EstadoApelacion estadoApelacion = new EstadoApelacion();

	Recaudo recaudos = new Recaudo();

	// METODOS GETS Y DETS

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public List<RecaudoEntregado> getListaRecaudos() {
		return listaRecaudos;
	}

	public List<AsignaturaEstudianteSancionado> getAsignaturas() {
		return asignaturas;
	}

	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return solicitudApelacion;
	}

	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}

	public void setListaRecaudos(List<RecaudoEntregado> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}

	public void setAsignaturas(List<AsignaturaEstudianteSancionado> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public void setAsignaturaLapsosConsecutivos(
			String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}

	// FIN DE LOS METODOS GETS Y SET
	
	// OTROS METODOS
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado v1)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.estudianteSeleccionado = v1;
		//cedula = sancionadoSeleccionado.getId().getCedulaEstudiante();
		cedula = estudianteSeleccionado.getEstudiante().getCedulaEstudiante();
		buscarSolicitud(cedula);
		concatenacionNombres();
		concatenacionApellidos();
//		lapso = sancionadoSeleccionado.getEstudianteSancionado()
//				.getLapsoAcademico().getCodigoLapso();
		lapso= estudianteSeleccionado.getLapsoAcademico().getCodigoLapso();
//		sancion = sancionadoSeleccionado.getEstudianteSancionado()
//				.getSancionMaestro().getNombreSancion();
		sancion=estudianteSeleccionado.getSancionMaestro().getNombreSancion();
//		lapsosConsecutivos = sancionadoSeleccionado.getEstudianteSancionado()
//				.getLapsosAcademicosRp();
		lapsosConsecutivos=estudianteSeleccionado.getLapsosAcademicosRp();
		//OJO: COMO HACER CON EL CASO
		//System.out.println("dddddddddd " + estudianteseleccionado.getSolicitudApelacions().size());

		buscarRecaudosEntregados(cedula);

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
			asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}
		media = null;
		doc = new Documento();
	}

	/**
	 * concatenacionNombres.
	 * 
	 * @param
	 * @return
	 * @throws No dispara ninguna excepcion.
	 */
	public void concatenacionNombres() {

		nombres = estudianteSeleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoNombre();
	}

	/**
	 * concatenacionApellidos.
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	public void concatenacionApellidos() {

		apellidos = estudianteSeleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoApellido();

	}

	/**
	 * buscarRecaudosEntregados.
	 * @param cedula, listaRecaudo
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaRecaudos" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudos = serviciorecaudoentregado
				.buscarRecaudosEntregadosRecurso(cedula);
		System.out.println(listaRecaudos);

	}

	/**
	 * closeThis.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	public void closeThis() {
		window.detach();
	}

	/**
	 * registrarSolicitudApelacion.
	 * 
	 * @param lista
	 * @return Ninguno
	 * @throws las Excepciones ocurren cuando se quiera registrar una Recurso Jerárquico
	 * 			y no se ha cargado la carta
	 */
	@NotifyChange({ "lista" , "observacion"})
	@Command
	public void registrarSolicitudApelacion() {
		
			Date fecha = new Date();
			Time hora = new Time(0);

			if (observacion==" " || observacion ==null) {
				mensajesusuario.advertenciaLlenarCampos();

			} else {
				solicitudApelacionPK.setCedulaEstudiante(cedula);
				solicitudApelacionPK.setCodigoLapso(lapso);
				solicitudApelacionPK.setIdInstanciaApelada(3);
				solicitudApelacion.setId(solicitudApelacionPK);
				solicitudApelacion.setFechaSolicitud(fecha);
				solicitudApelacion.setEstatus(true);
				solicitudApelacion.setNumeroCaso(caso);
				solicitudApelacion.setObservacion(observacion);

				apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
				apelacionEstadoApelacionPK.setCodigoLapso(lapso);
				apelacionEstadoApelacionPK.setIdInstanciaApelada(3);
				//OJO CON ESTE ID QUE ES CON EL ESTADO DE LA APELACION
				apelacionEstadoApelacionPK.setIdEstadoApelacion(9);
				apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
				apelacionEstadoApelacion.setFechaEstado(hora);

		
				motivoPK.setCedulaEstudiante(cedula);
				motivoPK.setCodigoLapso(lapso);
				motivoPK.setIdInstanciaApelada(3);
				motivoPK.setIdTipoMotivo(3);
				motivos.setId(motivoPK);
				motivos.setEstatus(true);
				
			}
		try {

			serviciosolicitudapelacion.guardar(solicitudApelacion);
			servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
			serviciomotivo.guardarMotivo(motivos);
			mensajesusuario.informacionRegistroCorrecto();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * descargarDocumento.
	 *  @param nombreDoc    .
	 * @return Ninguno
	 * @throws Ninguna.
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

	public void buscarSolicitud(String cedula){
		serviciosolicitudapelacion.buscarSolicitudRecursoJerarquico(cedula);	
	}
	// FIN OTROS METODOS
}
