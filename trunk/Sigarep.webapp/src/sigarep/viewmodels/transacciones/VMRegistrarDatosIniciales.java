package sigarep.viewmodels.transacciones;

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
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

/**Clase VMRegistrarDatosIniciales
* ViewModel para la interfaz RegistrarDatosIniciales.zul
* @author Builder
* @version 1.0
* @since 20/12/13
*/
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarDatosIniciales {

	@Wire("#modalDialog")
	private Window window;

	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion; //Servicio para La Solicitud de Apelacion
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo; //Servicio para el tipo de motivo
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion; //Servicio para la Apelacion y Estado Apelacion
	@WireVariable
	private ServicioMotivo serviciomotivo; //Servicio Motivo
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado; //Servicio Recaudo Entregado
	@WireVariable
	private ServicioSoporte serviciosoporte; //Servicio soporte
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado; //Servicio Estudiante sancionado

	private List<EstudianteSancionado> listaSancionados = new LinkedList<EstudianteSancionado>(); //Listado de Estudiante sancionados
	private EstudianteSancionado estudianteSeleccionado; //Estudiante Seleccionado
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
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();
	EstadoApelacion estadoApelacion = new EstadoApelacion();
	private Integer idTipoMotivo;

	private Integer caso;
	private String numeroCaso;
	private Integer instancia;
	private Integer idEstado;
	private Integer idMotivoGeneral;

	//Metodos GET Y SET
	public String getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

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

	//Finalizacion de los Metodos SET y GET
	
	/**
	 * concatenacionNombres
	 * 
	 * @return devuelve primer y segundo nombre concatenados
	 */
	public void concatenacionNombres() {

		nombres = estudianteSeleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoNombre();
	}

	/**
	 * concatenacionApellidos
	 * 
	 * @return devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {

		apellidos = estudianteSeleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoApellido();

	}

	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarMotivos() {
		listaTipoMotivo = serviciotipomotivo.buscarTodas();

	}

	@Command
	public void buscarCaso() {
		caso = serviciosolicitudapelacion.mayorNumeroCaso() + 1;
		Integer programa = estudianteSeleccionado.getEstudiante().getProgramaAcademico().getIdPrograma();
		numeroCaso = lapso+ "."+programa+ "."+sancion+"."+caso;
		
	}

	@Init //Metodo que inicializa el VM
	public void init(
			@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado v1,
			@ExecutionArgParam("instancia") Integer instancia,
			@ExecutionArgParam("idMotivoGeneral") Integer idMotivoGeneral,
			@ExecutionArgParam("idEstado") Integer idEstado) {
		
		Selectors.wireComponents(view, this, false);
		this.estudianteSeleccionado = v1;
		this.instancia = instancia;
		this.idEstado = idEstado;
		this.idMotivoGeneral = idMotivoGeneral;


		cedula = estudianteSeleccionado.getId().getCedulaEstudiante();
		sancion = estudianteSeleccionado.getSancionMaestro().getNombreSancion();
		lapso = estudianteSeleccionado.getId().getCodigoLapso();

		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();
		listamotivo = serviciotipomotivo.buscarTodas();
		buscarMotivos();
		buscarCaso();
		System.out.println("caso:" +numeroCaso);
	}

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
	@NotifyChange({ "listaSancionados", "listaMotivoListBox" })
	public void registrarSolicitudApelacion(
			@BindingParam("window") Window winRegistrarDatosInicialesApelacion) {

		Date fecha = new Date();
		
		if ( listaMotivoListBox.size() == 0) {
		mensajeAlUsuario.advertenciaAgregarMotivo();
		}
		else if (observacion == null){
			mensajeAlUsuario.advertenciaAgregarObservacionGeneral();
		}
		else {
		solicitudApelacionPK.setCedulaEstudiante(cedula);
		solicitudApelacionPK.setCodigoLapso(lapso);
		solicitudApelacion.setFechaSolicitud(fecha);
		solicitudApelacion.setEstatus(true);
		solicitudApelacion.setAnalizado(false);
		solicitudApelacion.setVerificado(false);
		solicitudApelacion.setNumeroCaso(numeroCaso);
		solicitudApelacion.setObservacion(observacion);
		apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
		apelacionEstadoApelacionPK.setCodigoLapso(lapso);
		if (instancia != null && idEstado != null || idMotivoGeneral != null) {
			apelacionEstadoApelacionPK.setIdInstanciaApelada(instancia);
			apelacionEstadoApelacionPK.setIdEstadoApelacion(idEstado);
			solicitudApelacionPK.setIdInstanciaApelada(instancia);
			motivoPK.setIdInstanciaApelada(instancia);
			motivoPK.setIdTipoMotivo(idMotivoGeneral);
		} else {
			solicitudApelacionPK.setIdInstanciaApelada(1);
			apelacionEstadoApelacionPK.setIdInstanciaApelada(1);
			apelacionEstadoApelacionPK.setIdEstadoApelacion(1);
			motivoPK.setIdInstanciaApelada(1);
			motivoPK.setIdTipoMotivo(1);
		}
		solicitudApelacion.setId(solicitudApelacionPK);
		serviciosolicitudapelacion.guardar(solicitudApelacion);
		apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
		apelacionEstadoApelacion.setFechaEstado(new Date());
		apelacionEstadoApelacion.setObservacion(observacion);
		servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);

		motivoPK.setCedulaEstudiante(cedula);
		motivoPK.setCodigoLapso(lapso);
		motivos.setId(motivoPK);
		motivos.setEstatus(true);
		serviciomotivo.guardarMotivo(motivos);

		Motivo motivos = new Motivo();
		for (int j = 0; j < listaMotivoListBox.size(); j++) {

			idTipoMotivo = listaMotivoListBox.get(j).getTipoMotivo()
					.getIdTipoMotivo();
			motivoPK.setCedulaEstudiante(cedula);
			motivoPK.setCodigoLapso(lapso);
			if (instancia != null && idEstado != null
					|| idMotivoGeneral != null)
				motivoPK.setIdInstanciaApelada(instancia);
			else
				motivoPK.setIdInstanciaApelada(1);
			motivoPK.setIdTipoMotivo(idTipoMotivo);
			motivos.setId(motivoPK);
			motivos.setEstatus(true);
			motivos.setDescripcion(descripcion);
			serviciomotivo.guardarMotivo(motivos);
		}
		
		try {
			mensajeAlUsuario.informacionRegistroCorrecto();
			winRegistrarDatosInicialesApelacion.detach();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	}
	@Command
	@NotifyChange({ "listaMotivo", "listaMotivoListBox", "motivoseleccionado",
			"descripcion", "listaTipoMotivo" })
	public void agregarMotivo(
			@BindingParam("listBoxMotivo") Listbox listBoxMotivo, @BindingParam("comboitem") Combobox comboItem) {

			
		if (motivoseleccionado == null || descripcion == null ||
				motivoseleccionado.equals("") || descripcion.equals("")){
			mensajeAlUsuario.advertenciaLlenarCampos();
		}
		else {
			
			Motivo motivoLista = new Motivo();
			motivoLista.setDescripcion(descripcion);
			motivoLista.setTipoMotivo(motivoseleccionado);
			listaTipoMotivo.remove(comboItem.getSelectedItem().getIndex());
			comboItem.setValue("");
			listaMotivoListBox.add(motivoLista);
			descripcion= ""; 
		}
	}
	@Command
	@NotifyChange({ "descripcion", "motivoseleccionado", "listaMotivoListBox", "listaTipoMotivo" })
	public void cancelar() {
		descripcion = "";
		motivoseleccionado = null;
		listaMotivoListBox.clear();
		buscarMotivos();
		
	}

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}
}
