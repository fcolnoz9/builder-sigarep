package sigarep.viewmodels.transacciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
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

/**
 * VM Registrar Datos Iniciales.
 * Maneja los metodos necesarios para el registro de los datos iniciales 
 * requeridos del estudiante para iniciar el proceso de apelación.
 * @author Equipo Builder
 * @version 1.2
 * @since 20/12/2013
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarDatosIniciales {
	// --------------------------Servicios------------------------------
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
	// --------------------------Variables de Control-------------------
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
	private String descripcion;
	private Motivo motivo;
	private TipoMotivo motivoseleccionado;
	private String listamotivoseleccionado;
	private String observacion;
	private Integer idTipoMotivo;
	private String caso;
	private String numeroCaso;
	private Integer instancia;
	private Integer idEstado;
	private Integer idMotivoGeneral;
	// --------------------------Variables Lista------------------------
	private List<TipoMotivo> listamotivo;
	private List<EstudianteSancionado> listaSancionados = new LinkedList<EstudianteSancionado>();
	private List<Motivo> listamotivos;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<TipoMotivo> listaTipoMotivo;
	private List<Motivo> listaMotivoListBox = new LinkedList<Motivo>();
	// --------------------------Variables Objeto-----------------------
	private EstudianteSancionado estudianteSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();
	EstadoApelacion estadoApelacion = new EstadoApelacion();

	// Métodos Set y Get
	public String getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
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

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
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
		listamotivo = serviciotipomotivo.buscarTipoMotivoNoProtegido();
		buscarMotivos();
		buscarCaso();
	}

	/**
	 * afterCompose. Conecta a los componentes de la vista. Es necesario para
	 * evitar null pointer.
	 * 
	 * @param @ContextParam(ContextType.VIEW) Component view
	 * @return Ninguno.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	/**
	 * Actualizar Lista de Sancionados
	 * 
	 * @param Ninguno
	 * @return La lista de estudiantes en veredicto se actualiza cuando se
	 *         termina ese proceso.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@GlobalCommand
	public void actualizarListaSancionados() {
		BindUtils.postGlobalCommand(null, null, "buscarSancionados", null);
	}

	/**
	 * Concatenacion nombres.
	 * 
	 * @param Ninguno
	 * @return Devuelve primer y segundo nombre concatenados
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	public void concatenacionNombres() {
		nombres = estudianteSeleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoNombre();
	}

	/**
	 * Concatenacion apellidos.
	 * 
	 * @param Ninguno
	 * @return Devuelve primer y segundo apellido concatenados
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	public void concatenacionApellidos() {
		apellidos = estudianteSeleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoApellido();
	}

	/**
	 * Buscar motivos.
	 * 
	 * @param Ninguno
	 * @return Lista con tipos de motivos.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarMotivos() {
		listaTipoMotivo = serviciotipomotivo.buscarTipoMotivoNoProtegido();
	}

	/**
	 * Buscar caso.
	 * 
	 * @param Ninguno
	 * @return devuelve el último caso registrado y le incrementa en 1, en caso
	 *         de ser cero, lo inicializa en 1.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	public void buscarCaso() {
		caso = serviciosolicitudapelacion.mayorNumeroCaso();
		if (caso != null) {
			int entero = Integer.parseInt(caso);
			entero = entero + 1;
			Integer programa = estudianteSeleccionado.getEstudiante()
					.getProgramaAcademico().getIdPrograma();
			numeroCaso = lapso + "." + programa + "." + sancion + "." + entero;
		} else {
			int entero = 1;
			Integer programa = estudianteSeleccionado.getEstudiante()
					.getProgramaAcademico().getIdPrograma();
			numeroCaso = lapso + "." + programa + "." + sancion + "." + entero;
		}
	}

	/**
	 * Mostrar Datos de Sancion.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
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

	/**
	 * Registrar Solicitud Apelacion.
	 * 
	 * @param @BindingParam("window") Window winRegistrarDatosInicialesApelacion
	 * @return Ninguno
	 * @throws las
	 *             Excepciones ocurren cuando se quiera registrar una apelacion
	 *             y no registrar motivos
	 */
	@Command
	@NotifyChange({ "listaSancionados", "listaMotivoListBox" })
	public void registrarSolicitudApelacion(
			@BindingParam("window") Window winRegistrarDatosInicialesApelacion) {
		Date fecha = new Date();
		if (listaMotivoListBox.size() == 0) {
			mensajeAlUsuario.advertenciaAgregarMotivo();
		} else if (observacion == null) {
			mensajeAlUsuario.advertenciaAgregarObservacionGeneral();
		} else {
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
			if (instancia != null && idEstado != null
					|| idMotivoGeneral != null) {
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
				descripcion = listaMotivoListBox.get(j).getDescripcion();
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
				actualizarListaSancionados();// Actualiza la Lista Generica de
												// Estudiantes sancionados con
												// un comando Global

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Agregar motivo
	 * 
	 * @param binder
	 * @return agrega un motivo a la lista de motivos que se registran en la
	 *         apelación.
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaMotivo", "listaMotivoListBox", "motivoseleccionado",
			"descripcion", "listaTipoMotivo" })
	public void agregarMotivo(
			@BindingParam("listBoxMotivo") Listbox listBoxMotivo,
			@BindingParam("comboitem") Combobox comboItem) {
		if (motivoseleccionado == null || descripcion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			Motivo motivoLista = new Motivo();
			motivoLista.setDescripcion(descripcion);
			motivoLista.setTipoMotivo(motivoseleccionado);
			listaTipoMotivo.remove(comboItem.getSelectedItem().getIndex());
			comboItem.setValue("");
			listaMotivoListBox.add(motivoLista);
			descripcion = null;
		}
	}

	/**
	 * Cancelar
	 * 
	 * @param Ninguno
	 * @return devuelve las variables vacias y limpia el listbox de motivos.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({ "descripcion", "motivoseleccionado", "listaMotivoListBox",
			"listaTipoMotivo", "observacion" })
	public void cancelar() {
		descripcion = null;
		motivoseleccionado = null;
		listaMotivoListBox.clear();
		observacion = null;
		buscarMotivos();
	}

	/**
	 * cerrarVentana
	 * 
	 * @param binder
	 * @return nada
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "descripcion", "listaMotivoListBox", "motivoseleccionado" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = false;
		if (descripcion != null || listaMotivoListBox.size() != 0
				|| motivoseleccionado != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);
	}
}//fin VMRegistrarDatosIniciales
