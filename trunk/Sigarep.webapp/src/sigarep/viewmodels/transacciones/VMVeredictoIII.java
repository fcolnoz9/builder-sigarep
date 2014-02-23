package sigarep.viewmodels.transacciones;

import org.zkoss.bind.BindUtils;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/** VMVeredictoIii
 * Contiene métodos necesarios  para el funcionamiento de VeredictoIII.zul, mostrado en el menu Gestion::Recurso Jerarquico::Evaluar Apelacion::Veredicto.
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 22/01/14
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMVeredictoIII {
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
	private String caso;
	private String fechaApelacion;
	private Integer peridoSancion;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacionGeneral;
	private String veredicto;
	
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion;
	
	private SolicitudApelacion solicitudApelacion;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
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
	
	// Getters and Setters
	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
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

	public String getNumeroSesion() {
		return numeroSesion;
	}

	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
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
	// Fin Getters and Setters

		/**
		 * concatenacionNombres
		 * 
		 * @return Devuelve primer y segundo nombre concatenados
		 */
	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}
	/**
	 * concatenacionApellidos
	 * 
	 * @return Devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}
	/**
	 * inicialización
	 * @param init
	 * @return Código de inicialización
	 * @throws No dispara ninguna excepcion.
	 */
	@Init
	public void init(
		@ContextParam(ContextType.VIEW) Component view,
		@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion sa,
		@ExecutionArgParam("numeroSesion") String numeroSesion,
		@ExecutionArgParam("tipoSesion") String tipoSesion,
		@ExecutionArgParam("fechaSesion") Date fechaSesion)
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
		
		this.numeroSesion = numeroSesion;
		this.tipoSesion = tipoSesion;
		this.fechaSesion = fechaSesion;
		
		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();

		buscarRecaudosEntregados(cedula);
	}
	/**
	 * Montrar Datos de Sancion
	 * @param mostrarDatosSancion
	 * @return asignaturas,asignaturaLapsosConsecutivos
	 * @throws No dispara ninguna excepcion.
	 */
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
	/**
	 * Buscar Recaudos Entregados
	 * @param buscarRecaudosEntregados, listaRecaudo
	 * @return listaRecaudos
	 * @throws No dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosVeredictoIII(cedula);

	}
	@Command
	public void closeThis() {
		window.detach();
	}
	
	/**
	 * Registrar Veredicto
	 * @param registrarVeredicto
	 * @return Guarda un veredicto y una observacion general
	 * @throws Las excepciones son que los datos a guardar esten vacios
	 */	

	@Command
	public void registrarVeredicto(@BindingParam("window") Window winVeredictoIII){
		if (veredicto == null){
			mensajeAlUsuario.advertenciaGuardarVeredicto();
		}else{
			solicitudApelacion.setObservacion(observacionGeneral);
			solicitudApelacion.setVeredicto(veredicto);
			solicitudApelacion.setNumeroSesion(numeroSesion);
			solicitudApelacion.setTipoSesion(tipoSesion);
			solicitudApelacion.setFechaSesion(fechaSesion);
			ApelacionEstadoApelacion apelacionEstado = new ApelacionEstadoApelacion();
			ApelacionEstadoApelacionPK apelacionEstadoPK = new ApelacionEstadoApelacionPK();
			apelacionEstadoPK.setCedulaEstudiante(cedula);
			apelacionEstadoPK.setCodigoLapso(lapso);
			apelacionEstadoPK.setIdEstadoApelacion(12);
			apelacionEstadoPK.setIdInstanciaApelada(instancia);
			apelacionEstado.setId(apelacionEstadoPK);
			apelacionEstado.setFechaEstado(new Date());
			apelacionEstado.setObservacion(observacionGeneral);
			solicitudApelacion.addApelacionEstadosApelacion(apelacionEstado);
			serviciosolicitudapelacion.guardar(solicitudApelacion);
			mensajeAlUsuario.informacionVeredictoRegistrado();
			winVeredictoIII.detach();
			actualizarListaSancionados();
		}
	}
	/**
	 * limpiar
	 * @param limpiar
	 * @return Metodo que limpia todos los campos de la pantalla
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "observacionGeneral" , "veredicto" })
	public void limpiar(){
		observacionGeneral = solicitudApelacion.getObservacion();
		veredicto = "";
	}
	@GlobalCommand
    public void actualizarListaSancionados(){
    	BindUtils.postGlobalCommand(null, null, "buscarSancionados", null);
    }
	
	/**
	 * Cerrar Ventana
	 * @param binder
	 * @return Cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"veredicto", "observacionGeneral"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(veredicto !=null|| observacionGeneral !=null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaTransacciones(ventana,condicion);		
	}
	
}
    

