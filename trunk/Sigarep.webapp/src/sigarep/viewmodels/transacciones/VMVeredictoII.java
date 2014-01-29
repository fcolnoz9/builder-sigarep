package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMVeredictoII {
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String sancion;
	private String programa;
	private String email;
	private String primerApellido;
	private String primerNombre;
	private String lapso;
	private Integer instancia;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private Integer caso;
	private String fechaApelacion;
	private Integer peridoSancion;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacionGeneral;
	private String veredicto;
	
	private SolicitudApelacion solicitudApelacion;
	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();


	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;

	private List<RecaudoEntregado> listaRecaudo; 

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

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}
	
	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
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

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}

	@Init
	public void init(
		@ContextParam(ContextType.VIEW) Component view,
		@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion sa)
	{
		Selectors.wireComponents(view, this, false);
		this.solicitudApelacion = sa;
		this.cedula = sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante();
		this.primerNombre = sa.getEstudianteSancionado().getEstudiante().getPrimerNombre();
		this.primerApellido = sa.getEstudianteSancionado().getEstudiante().getPrimerApellido();
		this.email = sa.getEstudianteSancionado().getEstudiante().getEmail();
		this.programa = sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma();
		this.sancion = sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		this.lapso = sa.getEstudianteSancionado().getLapsoAcademico().getCodigoLapso();
		this.instancia = sa.getInstanciaApelada().getIdInstanciaApelada();
		this.segundoNombre = sa.getEstudianteSancionado().getEstudiante().getSegundoNombre();
		this.segundoApellido = sa.getEstudianteSancionado().getEstudiante().getSegundoApellido();
		this.caso = sa.getNumeroCaso();
		this.lapsosConsecutivos = sa.getEstudianteSancionado().getLapsosAcademicosRp();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		this.fechaApelacion = sdf.format(sa.getFechaSolicitud());
		this.peridoSancion = sa.getEstudianteSancionado().getPeriodoSancion();
		this.observacionGeneral = sa.getObservacion();
		
		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();

		buscarRecaudosEntregados(cedula);
	}
	
	private void mostrarDatosDeSancion() {
		if (sancion.equalsIgnoreCase("RR")){
			asignaturas = servicioasignaturaestudiantesancionado.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i=0; i<asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i).getAsignatura().getNombreAsignatura() + ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		}
		else{
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}
	}

	
	@Command
	@NotifyChange({"listaRecaudo"})
	public void buscarRecaudosEntregados(String cedula){
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosVeredictoII(cedula);
	}
	
	
	@Command
	public void closeThis() {
		window.detach();
	}
	
	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public void setAsignaturaLapsosConsecutivos(String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public String getFechaApelacion() {
		return fechaApelacion;
	}

	public void setFechaApelacion(String fechaApelacion) {
		this.fechaApelacion = fechaApelacion;
	}

	public Integer getPeridoSancion() {
		return peridoSancion;
	}

	public void setPeridoSancion(Integer peridoSancion) {
		this.peridoSancion = peridoSancion;
	}

	public String getObservacionGeneral() {
		return observacionGeneral;
	}

	public void setObservacionGeneral(String observacionGeneral) {
		this.observacionGeneral = observacionGeneral;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	@Command
	public void registrarVeredicto(){
		if (veredicto == null){
			mensajesAlUsuario.advertenciaGuardarVeredicto();
			
		}else{
		
		solicitudApelacion.setObservacion(observacionGeneral);
		solicitudApelacion.setVeredicto(veredicto);
		ApelacionEstadoApelacion apelacionEstado = new ApelacionEstadoApelacion();
		ApelacionEstadoApelacionPK apelacionEstadoPK = new ApelacionEstadoApelacionPK();
		apelacionEstadoPK.setCedulaEstudiante(cedula);
		apelacionEstadoPK.setCodigoLapso(lapso);
		apelacionEstadoPK.setIdEstadoApelacion(4);
		apelacionEstadoPK.setIdInstanciaApelada(instancia);
		apelacionEstado.setId(apelacionEstadoPK);
		apelacionEstado.setFechaEstado(new Date());
		apelacionEstado.setObservacion(observacionGeneral);
		solicitudApelacion.addApelacionEstadosApelacion(apelacionEstado);
		serviciosolicitudapelacion.guardar(solicitudApelacion);
		mensajesAlUsuario.informacionVeredictoRegistrado();
		
	}
}
	
	@Command
	@NotifyChange({ "observacionGeneral" , "veredicto" })
	public void limpiar(){
		observacionGeneral = solicitudApelacion.getObservacion();
		veredicto = "";
	}

	
  	
	
	
	
}
