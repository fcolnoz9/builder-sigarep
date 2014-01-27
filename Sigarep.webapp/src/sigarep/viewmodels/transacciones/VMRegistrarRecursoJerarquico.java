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
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
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

	private SolicitudApelacion sancionadoSeleccionado;

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

	
	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
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

	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
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
			@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion v1)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.sancionadoSeleccionado = v1;
		cedula = sancionadoSeleccionado.getId().getCedulaEstudiante();
		concatenacionNombres();
		concatenacionApellidos();
		lapso = sancionadoSeleccionado.getEstudianteSancionado()
				.getLapsoAcademico().getCodigoLapso();
		sancion = sancionadoSeleccionado.getEstudianteSancionado()
				.getSancionMaestro().getNombreSancion();
		lapsosConsecutivos = sancionadoSeleccionado.getEstudianteSancionado()
				.getLapsosAcademicosRp();
		caso = sancionadoSeleccionado.getNumeroCaso();

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
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public void concatenacionNombres() {

		nombres = sancionadoSeleccionado.getEstudianteSancionado()
				.getEstudiante().getPrimerNombre()
				+ " "
				+ sancionadoSeleccionado.getEstudianteSancionado()
						.getEstudiante().getSegundoNombre();
	}

	/**
	 * concatenacionApellidos.
	 * 
	 * @param Ninguno
	 *            .
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public void concatenacionApellidos() {

		apellidos = sancionadoSeleccionado.getEstudianteSancionado()
				.getEstudiante().getPrimerApellido()
				+ " "
				+ sancionadoSeleccionado.getEstudianteSancionado()
						.getEstudiante().getSegundoApellido();

	}

	/**
	 * buscarRecaudosEntregados.
	 * 
	 * @param cedula
	 *            , listaRecaudo
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
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
	 * @throws No
	 *             dispara ninguna excepcion.
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
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@NotifyChange({ "lista" , "observacion"})
	@Command
	public void registrarSolicitudApelacion() {
		
			Date fecha = new Date();
			Time hora = new Time(0);

			if (nombreDoc == null || observacion.equals("")) {
				mensajesusuario.advertenciaLlenarCampos();

			} else {
				solicitudApelacionPK.setCedulaEstudiante(cedula);
				solicitudApelacionPK.setCodigoLapso(lapso);
				solicitudApelacionPK.setIdInstanciaApelada(2);
				solicitudApelacion.setId(solicitudApelacionPK);
				solicitudApelacion.setFechaSolicitud(fecha);
				solicitudApelacion.setEstatus(true);
				solicitudApelacion.setNumeroCaso(caso);

				apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
				apelacionEstadoApelacionPK.setCodigoLapso(lapso);
				apelacionEstadoApelacionPK.setIdInstanciaApelada(2);
				//OJO CON ESTE ID
				apelacionEstadoApelacionPK.setIdEstadoApelacion(1);
				apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
				apelacionEstadoApelacion.setFechaEstado(hora);
				apelacionEstadoApelacion.setObservacion(observacion);

				idTipoMotivo = listaRecaudos.get(0).getMotivo().getId()
						.getIdTipoMotivo();
				motivoPK.setCedulaEstudiante(cedula);
				motivoPK.setCodigoLapso(lapso);
				motivoPK.setIdInstanciaApelada(2);
				motivoPK.setIdTipoMotivo(idTipoMotivo);
				motivos.setId(motivoPK);
				motivos.setEstatus(true);

				recaudoEntregadoPK.setCedulaEstudiante(cedula);
				recaudoEntregadoPK.setCodigoLapso(lapso);
				recaudoEntregadoPK.setIdInstanciaApelada(2);
				recaudoEntregadoPK.setIdRecaudo(2);
				recaudoEntregadoPK.setIdTipoMotivo(idTipoMotivo);
				recaudoEntregado.setId(recaudoEntregadoPK);
				recaudoEntregado.setEstatus(true);

				soporte.setRecaudoEntregado(recaudoEntregado);
				soporte.setDocumento(doc);
				soporte.setEstatus(true);
				soporte.setFechaSubida(fecha);
				soporte.setRecaudoEntregado(recaudoEntregado);
				
			}
		
		try {

			serviciosolicitudapelacion.guardar(solicitudApelacion);
			servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
			serviciomotivo.guardarMotivo(motivos);
			serviciorecaudoentregado.guardar(recaudoEntregado);
			serviciosoporte.guardar(soporte);
			mensajesusuario.informacionRegistroCorrecto();
		} catch (Exception e) {
			System.out.println(e.getMessage());

			//serviciolista.buscarApelaciones(); POR FAVOR REVISAR AQUI
		}

	}

	/**
	 * cargarCartaReconsideracion.
	 * 
	 * @param nombreDoc
	 *            .
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange("nombreDoc")
	public void cargarRecursoJerarquico(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		if (media != null) {
			if (media.getContentType().equals("image/jpeg")
					|| media.getContentType().equals("application/pdf")
					|| media.getContentType().equals("application/msword")
					|| media.getContentType()
							.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
					|| media.getContentType().equals(
							"application/vnd.oasis.opendocument.text")
					|| media.getContentType().equals(
							"application/x-vnd.oasis.opendocument.text")) {
				doc.setNombreDocumento(media.getName());
				doc.setTipoDocumento(media.getContentType());
				doc.setContenidoDocumento(media.getByteData());
				nombreDoc = doc.getNombreDocumento();
			} else {
				Messagebox.show(media.getName()
						+ " No es un tipo de archivo valido!", "Error",
						Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

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

	// FIN OTROS METODOS
}
