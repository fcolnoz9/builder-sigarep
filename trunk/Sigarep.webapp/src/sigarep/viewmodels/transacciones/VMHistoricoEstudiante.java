package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * VM Historico estudiante. Consulta del Portal principal.
 * 
 * @author Equipo Builder
 * @version 1.2
 * @since 15/01/2014
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistoricoEstudiante {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	// --------------------------Variables de Control-------------------
	private String Motivos;
	private String cedulaEstudiante;
	private String programa;
	private String nombres;
	private String apellidos;
	private String lapsoAcademico;
	private String tipoSancion;
	private Integer periodoSancion;
	private String numeroCaso;
	private String fecha;
	private Boolean boton1;
	private Boolean boton2;
	private Boolean boton3;
	// --------------------------Variables Lista------------------------
	private List<EstudianteSancionado> listaEstudianteSancionado;
	private List<String> listaMotivos;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionEstadoApelacion> listaEstadoApelacion;
	private List<SolicitudApelacion> listaSolicitudApelacion;
	// --------------------------Variables Objeto-----------------------
	private EstudianteSancionado estudianteSancionado;
	private SolicitudApelacion solicitudEst;

	// Métodos Set y Get
	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
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

	public String getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(String lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public String getTipoSancion() {
		return tipoSancion;
	}

	public void setTipoSancion(String tipoSancion) {
		this.tipoSancion = tipoSancion;
	}

	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}

	public String getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public EstudianteSancionado getEstudianteSancionado() {
		return estudianteSancionado;
	}

	public void setEstudianteSan(EstudianteSancionado estudianteSancionado) {
		this.estudianteSancionado = estudianteSancionado;
	}

	public SolicitudApelacion getSolicitudEst() {
		return solicitudEst;
	}

	public void setSolicitudEst(SolicitudApelacion solicitudEst) {
		this.solicitudEst = solicitudEst;
	}

	public List<SolicitudApelacion> getListaSolicitudApelacion() {
		return listaSolicitudApelacion;
	}

	public void setListaSolicitudApelacion(
			List<SolicitudApelacion> listaSolicitudApelacion) {
		this.listaSolicitudApelacion = listaSolicitudApelacion;
	}

	public List<ApelacionEstadoApelacion> getListaEstadoApelacion() {
		return listaEstadoApelacion;
	}

	public void setListaEstadoApelacion(
			List<ApelacionEstadoApelacion> listaEstadoApelacion) {
		this.listaEstadoApelacion = listaEstadoApelacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public List<String> getListaMotivos() {
		return listaMotivos;
	}

	public void setListaMotivos(List<String> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	public Boolean getBoton1() {
		return boton1;
	}

	public void setBoton1(Boolean boton1) {
		this.boton1 = boton1;
	}

	public Boolean getBoton2() {
		return boton2;
	}

	public void setBoton2(Boolean boton2) {
		this.boton2 = boton2;
	}

	public Boolean getBoton3() {
		return boton3;
	}

	public void setBoton3(Boolean boton3) {
		this.boton3 = boton3;
	}

	public String getMotivos() {
		return Motivos;
	}

	public void setMotivos(String motivos) {
		Motivos = motivos;
	}

	public List<EstudianteSancionado> getListaEstudianteSancionado() {
		return listaEstudianteSancionado;
	}

	public void setListaEstudianteSancionado(
			List<EstudianteSancionado> listaEstudianteSancionado) {
		this.listaEstudianteSancionado = listaEstudianteSancionado;
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
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String cedula) {
		Selectors.wireComponents(view, this, false);
		this.cedulaEstudiante = cedula;
		if (serviciolapsoacademico.buscarLapsoActivo() != null)
			lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo()
					.getCodigoLapso();
		buscarEstadoEstudiante(cedula);
		mostrarHistoricoEstudiante(cedula);
		buscarListaTipoMotivo(cedula, lapsoAcademico);
		buscarListaEstadoApelacion(3);
		buscarListaEstadoApelacion(2);
		buscarListaEstadoApelacion(1);
	}

	/**
	 * Buscar Estado Estudiante.
	 * 
	 * @param String
	 *            cedula
	 * @return busca si el estudiante realizo alguna apelación.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void buscarEstadoEstudiante(String cedula) {
		setEstudianteSan(servicioestudiantesancionado
				.buscarEstudianteSancionadoLapsoActual(cedula));
	}

	/**
	 * Buscar Solicitud Estudiante.
	 * 
	 * @param String
	 *            cedula, String lapso
	 * @return devuelve los datos de apelacion del estudiante.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void buscarSolicitudEstudiante(String cedula, String lapso) {
		setListaSolicitudApelacion(serviciosolicitudapelacion
				.buscarSolicitudApelacionLapsoActual(cedula, lapso));
	}

	/**
	 * Mostrar Historico Estudiante.
	 * 
	 * @param String
	 *            cedula
	 * @return devuelve todos los datos referente a los basicos, sancion y
	 *         apelacion en el lapso actual.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void mostrarHistoricoEstudiante(String cedula) {
		estudianteSancionado = servicioestudiantesancionado
				.buscarEstudianteSancionadoLapsoActual(cedula);
		cedulaEstudiante = estudianteSancionado.getId().getCedulaEstudiante();
		programa = estudianteSancionado.getEstudiante().getProgramaAcademico()
				.getNombrePrograma();
		nombres = estudianteSancionado.getEstudiante().getPrimerNombre() + " "
				+ estudianteSancionado.getEstudiante().getSegundoNombre();
		apellidos = estudianteSancionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSancionado.getEstudiante().getSegundoApellido();
		lapsoAcademico = estudianteSancionado.getLapsoAcademico()
				.getCodigoLapso();
		tipoSancion = estudianteSancionado.getSancionMaestro()
				.getNombreSancion();
		periodoSancion = estudianteSancionado.getPeriodoSancion();
		listaSolicitudApelacion = serviciosolicitudapelacion
				.buscarSolicitudApelacionLapsoActual(cedula, lapsoAcademico);
		if (listaSolicitudApelacion.size() > 0) {
			numeroCaso = listaSolicitudApelacion.get(0).getNumeroCaso();
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			fecha = formateador.format(listaSolicitudApelacion.get(0)
					.getFechaSolicitud());
		}
	}

	/**
	 * Buscar ListaEstado Apelacion
	 * 
	 * @param @BindingParam("id") Integer id
	 * @return devuelve cada uno de los procesos por las que pasa el estudiante
	 *         en cada instancia seleccionada, esta dependera del id de la
	 *         instncia que se consulte en el lapso actual.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaEstadoApelacion" })
	public void buscarListaEstadoApelacion(@BindingParam("id") Integer id) {
		setListaEstadoApelacion(servicioapelacionestadoapelacion
				.buscarApelacionHistorial(cedulaEstudiante, lapsoAcademico, id));
		if (listaEstadoApelacion.isEmpty() && id == 1) {
			setBoton1(true);
		}
		if (listaEstadoApelacion.isEmpty() && id == 2) {
			setBoton2(true);
		}
		if (listaEstadoApelacion.isEmpty() && id == 3) {
			setBoton3(true);
		}
	}

	/**
	 * Buscar Lista Tipo Motivo.
	 * 
	 * @param String
	 *            cedula, String lapso
	 * @return devuelve los motivos por las cuales apelo el estudiante en el
	 *         lapso actual.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaMotivos", "Motivos" })
	public void buscarListaTipoMotivo(String cedula, String lapso) {
		listaMotivos = (serviciomotivo.buscarMotivosApelacion(cedula, lapso));
		setMotivos("");
		for (int i = 0; i < listaMotivos.size(); i++) {
			setMotivos(getMotivos() + (listaMotivos.get(i) + ", "));
		}
	}
}
