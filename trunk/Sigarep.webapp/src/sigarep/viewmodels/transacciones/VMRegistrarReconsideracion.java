package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
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
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.*;
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
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

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
	private Integer caso;
	private Integer idTipoMotivo;
	private Integer idRecaudo;
	private Documento doc = new Documento();
	private Media media;
	private AImage imagen;
	private String nombreDoc;
	private String cedula;
	private String nombreDocumento;
	private String tipoDocumento;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacion;
	private SolicitudApelacion solicitudapelacion;
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();

	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioSoporte serviciosoporte;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	// Para llamar a los diferentes mensajes de dialogo
	MensajesAlUsuario mensajesusuario = new MensajesAlUsuario(); 
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
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<RecaudoEntregado> listaRecaudos = new LinkedList<RecaudoEntregado>();
	private List<SolicitudApelacion> listaSancionados = new LinkedList<SolicitudApelacion>();
	private SolicitudApelacion solicitudapelacionseleccionada;

	// Metodos Get y Set
	public SolicitudApelacion getSolicitudapelacionseleccionada() {
		return solicitudapelacionseleccionada;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setSolicitudapelacionseleccionada(
			SolicitudApelacion solicitudapelacionseleccionada) {
		this.solicitudapelacionseleccionada = solicitudapelacionseleccionada;
	}

	public List<SolicitudApelacion> getListaSancionados() {
		return listaSancionados;
	}

	public void setListaSancionados(List<SolicitudApelacion> listaSancionados) {
		this.listaSancionados = listaSancionados;
	}

	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public void setAsignaturaLapsosConsecutivos(
			String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public List<RecaudoEntregado> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setListaRecaudos(List<RecaudoEntregado> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}

	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}

	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}

	public Integer getIdRecaudo() {
		return idRecaudo;
	}

	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public AImage getImagen() {
		return imagen;
	}

	public void setImagen(AImage imagen) {
		this.imagen = imagen;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public SolicitudApelacion getSolicitudapelacion() {
		return solicitudapelacion;
	}

	public void setSolicitudapelacion(SolicitudApelacion solicitudapelacion) {
		this.solicitudapelacion = solicitudapelacion;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}
// FIN de metodos get y set
	
	/** concatenacionNombres
	 * @return devuelve primer y segundo nombre concatenados
	 */
	public void concatenacionNombres() {

		nombres = solicitudapelacionseleccionada.getEstudianteSancionado()
				.getEstudiante().getPrimerNombre()
				+ " "
				+ solicitudapelacionseleccionada.getEstudianteSancionado()
						.getEstudiante().getSegundoNombre();
	}
	/** concatenacionApellidos
	 * @return devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {

		apellidos = solicitudapelacionseleccionada.getEstudianteSancionado()
				.getEstudiante().getPrimerApellido()
				+ " "
				+ solicitudapelacionseleccionada.getEstudianteSancionado()
						.getEstudiante().getSegundoApellido();

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

	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion v1)

	// initialization code
	{
		Selectors.wireComponents(view, this, false);
		this.solicitudapelacionseleccionada = v1;
		cedula = solicitudapelacionseleccionada.getId().getCedulaEstudiante();
		concatenacionNombres();
		concatenacionApellidos();
		lapso = solicitudapelacionseleccionada.getEstudianteSancionado()
				.getLapsoAcademico().getCodigoLapso();
		sancion = solicitudapelacionseleccionada.getEstudianteSancionado()
				.getSancionMaestro().getNombreSancion();
		lapsosConsecutivos = solicitudapelacionseleccionada
				.getEstudianteSancionado().getLapsosAcademicosRp();
		caso = solicitudapelacionseleccionada.getNumeroCaso();

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

	@Command
	public void closeThis() {
		window.detach();
	}
	/** registrarSolicitudApelacion
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones ocurren cuando se quiera registrar una reconsideracion y no se ha cargado la carta
	 */
	@NotifyChange({ "lista" , "observacion"})
	@Command
	public void registrarSolicitudApelacion() {

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
			apelacionEstadoApelacion.setFechaEstado(hora);
			
		try {
			serviciosolicitudapelacion.guardar(solicitudApelacion);
			servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
			mensajesusuario.informacionRegistroCorrecto();
		
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
	
	@Command
	@NotifyChange({"observacion"})
	public void cancelar() {
		observacion = ""; 
	}

}
