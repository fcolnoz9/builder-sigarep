package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.mensajes;
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
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

/**
 * VM Regisrar Datos Iniciales UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo: Builder-SIGAREP
 * @version 1.0
 * @since 20/12/13
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarDatosIniciales {
	@Wire("#modalDialog")
	private Window window;

	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioSoporte serviciosoporte;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;

	private List<EstudianteSancionado> listaSancionados = new LinkedList<EstudianteSancionado>();
	private EstudianteSancionado estudianteseleccionado;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private String cedula;
	private String primerNombre;
	private String segundoNombre;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String apellidos;
	private String sancion;
	private String lapso;
	private String asignatura;
	private String motivoCombo;
	private String lapsosConsecutivos;
	private Date fecha;
	private List<TipoMotivo> listamotivo;
	private String descripcion;
	private Motivo motivo;
	private List<Motivo> listamotivos;
	private TipoMotivo motivoseleccionado;
	private String listamotivoseleccionado;
	private String observacion;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<TipoMotivo> listaTipoMotivo;
	private List<Motivo> listaMotivoListBox = new LinkedList<Motivo>();
	MensajesAlUsuario mensajesusuario = new MensajesAlUsuario();
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();
	EstadoApelacion estadoApelacion = new EstadoApelacion();
	private Integer idTipoMotivo;

	private int caso;

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getMotivoCombo() {
		return motivoCombo;
	}

	public void setMotivoCombo(String motivoCombo) {
		this.motivoCombo = motivoCombo;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
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

	public List<AsignaturaEstudianteSancionado> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<AsignaturaEstudianteSancionado> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public List<EstudianteSancionado> getListaSancionados() {
		return listaSancionados;
	}

	public void setListaSancionados(List<EstudianteSancionado> listaSancionados) {
		this.listaSancionados = listaSancionados;
	}

	public EstudianteSancionado getEstudianteseleccionado() {
		return estudianteseleccionado;
	}

	public void setEstudianteseleccionado(
			EstudianteSancionado estudianteseleccionado) {
		this.estudianteseleccionado = estudianteseleccionado;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getListamotivoseleccionado() {
		return listamotivoseleccionado;
	}

	public void setListamotivoseleccionado(String listamotivoseleccionado) {
		this.listamotivoseleccionado = listamotivoseleccionado;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return solicitudApelacion;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

	public ServicioSolicitudApelacion getServiciosolicitudapelacion() {
		return serviciosolicitudapelacion;
	}

	public void setServiciosolicitudapelacion(
			ServicioSolicitudApelacion serviciosolicitudapelacion) {
		this.serviciosolicitudapelacion = serviciosolicitudapelacion;
	}

	public ServicioTipoMotivo getServiciotipomotivo() {
		return serviciotipomotivo;
	}

	public void setServiciotipomotivo(ServicioTipoMotivo serviciotipomotivo) {
		this.serviciotipomotivo = serviciotipomotivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<TipoMotivo> getListamotivo() {
		return listamotivo;
	}

	public void setListamotivo(List<TipoMotivo> listamotivo) {
		this.listamotivo = listamotivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public List<Motivo> getListamotivos() {
		return listamotivos;
	}

	public void setListamotivos(List<Motivo> listamotivos) {
		this.listamotivos = listamotivos;
	}

	public TipoMotivo getMotivoseleccionado() {
		return motivoseleccionado;
	}

	public List<Motivo> getListaMotivoListBox() {
		return listaMotivoListBox;
	}

	public void setListaMotivoListBox(List<Motivo> listaMotivoListBox) {
		this.listaMotivoListBox = listaMotivoListBox;
	}

	public void setMotivoseleccionado(TipoMotivo motivoseleccionado) {
		this.motivoseleccionado = motivoseleccionado;
	}

	/**
	 * concatenacionNombres
	 * 
	 * @return devuelve primer y segundo nombre concatenados
	 */
	public void concatenacionNombres() {

		nombres = estudianteseleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteseleccionado.getEstudiante().getSegundoNombre();
	}

	/**
	 * concatenacionApellidos
	 * 
	 * @return devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {

		apellidos = estudianteseleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteseleccionado.getEstudiante().getSegundoApellido();

	}

	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarMotivos() {
		listaTipoMotivo = serviciotipomotivo.buscarTodas();

	}

	@Command
	public void buscarCaso() {
		caso = serviciosolicitudapelacion.mayorNumeroCaso() + 1;
	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("estudianteseleccionado") EstudianteSancionado v1)

	{
		Selectors.wireComponents(view, this, false);
		this.estudianteseleccionado = v1;
		Date fecha = new Date();
		cedula = estudianteseleccionado.getId().getCedulaEstudiante();
		sancion = estudianteseleccionado.getSancionMaestro().getNombreSancion();
		lapso = estudianteseleccionado.getId().getCodigoLapso();

		concatenacionNombres();
		concatenacionApellidos();
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
			asignaturaLapsosConsecutivos = estudianteseleccionado
					.getLapsosAcademicosRp();
		}
		listamotivo = serviciotipomotivo.buscarTodas();
		buscarMotivos();
		buscarCaso();
	}

	@Command
	public void closeThis() {
		window.detach();
	}

	/**
	 * registrarSolicitudApelacion
	 * 
	 * @return No devuelve ningun valor.
	 * @throws las
	 *             Excepciones ocurren cuando se quiera registrar una apelacion
	 *             y no registrar motivos
	 */

	@Command
	@NotifyChange({ "listaSancionados", "listaTipoMotivoListBox" })
	public void registrarSolicitudApelacion(@BindingParam("window") Window winRegistrarDatosInicialesApelacion) {

		Date fecha = new Date();
		Time hora = new Time(0);

		solicitudApelacionPK.setCedulaEstudiante(cedula);
		solicitudApelacionPK.setCodigoLapso(lapso);
		solicitudApelacionPK.setIdInstanciaApelada(1);
		solicitudApelacion.setId(solicitudApelacionPK);
		solicitudApelacion.setFechaSolicitud(fecha);
		solicitudApelacion.setEstatus(true);
		solicitudApelacion.setAnalizado(false);
		solicitudApelacion.setVerificado(false);
		solicitudApelacion.setNumeroCaso(caso);
		solicitudApelacion.setObservacion(observacion);
		serviciosolicitudapelacion.guardar(solicitudApelacion);
		apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
		apelacionEstadoApelacionPK.setCodigoLapso(lapso);
		apelacionEstadoApelacionPK.setIdInstanciaApelada(1);
		apelacionEstadoApelacionPK.setIdEstadoApelacion(1);
		apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
		apelacionEstadoApelacion.setFechaEstado(hora);
		servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
		
		motivoPK.setCedulaEstudiante(cedula);
		motivoPK.setCodigoLapso(lapso);
		motivoPK.setIdInstanciaApelada(1);
		motivoPK.setIdTipoMotivo(1);
		motivos.setId(motivoPK);
		motivos.setEstatus(true);
		serviciomotivo.guardarMotivo(motivos);

		Motivo motivos = new Motivo();
		for (int j = 0; j < listaMotivoListBox.size(); j++) {

			idTipoMotivo = listaMotivoListBox.get(j).getTipoMotivo()
					.getIdTipoMotivo();
			motivoPK.setCedulaEstudiante(cedula);
			motivoPK.setCodigoLapso(lapso);
			motivoPK.setIdInstanciaApelada(1);
			motivoPK.setIdTipoMotivo(idTipoMotivo);
			motivos.setId(motivoPK);
			motivos.setEstatus(true);
			motivos.setDescripcion(descripcion);
			serviciomotivo.guardarMotivo(motivos);
		}

		try {
			mensajesusuario.informacionRegistroCorrecto();
			winRegistrarDatosInicialesApelacion.detach();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Command
	@NotifyChange({ "listaMotivo", "listaMotivoListBox", "motivoseleccionado",
			"descripcion" })
	public void agregarMotivo(
			@BindingParam("listBoxMotivo") Listbox listBoxMotivo) {

		Motivo motivoLista = new Motivo();
		motivoLista.setDescripcion(descripcion);
		motivoLista.setTipoMotivo(motivoseleccionado);
		listaMotivoListBox.add(motivoLista);
	}

	@Command
	@NotifyChange({"descripcion", "motivoseleccionado","listaMotivoListBox" })
	public void cancelar() { 
		descripcion = "";
		motivoseleccionado = null;
		listaMotivoListBox.clear();
	}
}
