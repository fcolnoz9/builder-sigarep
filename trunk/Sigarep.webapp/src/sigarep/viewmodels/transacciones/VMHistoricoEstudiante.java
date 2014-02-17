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
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistoricoEstudiante {

	private String cedulaEstudiante;
	private String programa;
	private String nombres;
	private String apellidos;
	private String lapsoAcademico;
	private String tipoSancion;
	private Integer periodoSancion;
	private String numeroCaso;
	private String fecha;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private EstudianteSancionado estudianteSan;
	private SolicitudApelacion solicitudEst;
	private Boolean boton1;
	private Boolean boton2;
	private Boolean boton3;
	private List<Motivo> listaMotivos;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionEstadoApelacion> listaEstadoApelacion;
	private List<SolicitudApelacion> listaSolicitudApelacion;

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

	public EstudianteSancionado getEstudianteSan() {
		return estudianteSan;
	}

	public void setEstudianteSan(EstudianteSancionado estudianteSan) {
		this.estudianteSan = estudianteSan;
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

	public List<Motivo> getListaMotivos() {
		return listaMotivos;
	}

	public void setListaMotivos(List<Motivo> listaMotivos) {
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

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String cedula) {
		Selectors.wireComponents(view, this, false);
		this.cedulaEstudiante = cedula;

		if (serviciolapsoacademico.encontrarLapsoActivo() == null)
			mensajeAlUsuario.ErrorLapsoActivoNoExistente();
		else {
			lapsoAcademico = serviciolapsoacademico.encontrarLapsoActivo()
					.getCodigoLapso();
			buscarEstadoEstudiante(cedula);
			buscarSolicitudEstudiante(cedula, lapsoAcademico);
			buscarListaTipoMotivo(cedula, lapsoAcademico);
			mostrarHistoricoEstudiante();
			buscarListaEstadoApelacion(3);
			buscarListaEstadoApelacion(2);
			buscarListaEstadoApelacion(1);
		}
	}

	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void buscarEstadoEstudiante(String cedula) {
		setEstudianteSan(serviciosolicitudapelacion
				.buscarEstudianteSancionadoxSolicitud(cedula));
	}

	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void buscarSolicitudEstudiante(String cedula, String lapso) {
		setListaSolicitudApelacion(serviciosolicitudapelacion
				.buscarSolicitudesEstudianteLapsoActual(cedula, lapso));
		for (int i = 0; i < listaSolicitudApelacion.size(); i++) {
			System.out.println(listaSolicitudApelacion.get(i).getNumeroCaso()
					+ " * ");
		}
	}

	@Command
	@NotifyChange({ "cedulaEstudiante", "programa", "nombres", "apellidos",
			"lapsoAcademico", "tipoSancion", "periodoSancion", "numeroCaso",
			"fecha" })
	public void mostrarHistoricoEstudiante() {
		cedulaEstudiante = getEstudianteSan().getId().getCedulaEstudiante();
		programa = getEstudianteSan().getEstudiante().getProgramaAcademico()
				.getNombrePrograma();
		nombres = getEstudianteSan().getEstudiante().getPrimerNombre() + " "
				+ getEstudianteSan().getEstudiante().getSegundoNombre();
		apellidos = getEstudianteSan().getEstudiante().getPrimerApellido()
				+ " " + getEstudianteSan().getEstudiante().getSegundoApellido();
		lapsoAcademico = getEstudianteSan().getLapsoAcademico()
				.getCodigoLapso();
		tipoSancion = getEstudianteSan().getSancionMaestro().getNombreSancion();
		periodoSancion = getEstudianteSan().getPeriodoSancion();
		numeroCaso = getListaSolicitudApelacion().get(
				getListaSolicitudApelacion().size() - 1).getNumeroCaso();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		fecha = formateador.format(getListaSolicitudApelacion().get(
				getListaSolicitudApelacion().size() - 1).getFechaSolicitud());
	}

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

	@Command
	@NotifyChange({ "listaMotivo" })
	public void buscarListaTipoMotivo(String cedula, String lapso) {
		System.out.println(cedula + " " + lapso);
		setListaMotivos(serviciomotivo.buscarMotivos(cedula, lapso));
		System.out.println(listaMotivos);
	}
}
