package sigarep.viewmodels.transacciones;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
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
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;

/**
 * DetalleHistorialEstudiante 
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleHistorialEstudiante {
	@Wire("#modalDialog")
	private Window window;
	private String codigoLapso;
	private String cedula;
	private String lapso;
	private String nombreEstudiante;
	private String nombreSancion;
	private String apellidoEstudiante;
	private Integer instancia;
	private String motivosEstudiante = "";
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	private List<ApelacionEstadoApelacion> apelacionestudiante  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia2  = new LinkedList<ApelacionEstadoApelacion>(); 
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia3  = new LinkedList<ApelacionEstadoApelacion>(); 
	private SolicitudApelacion apelacionseleccionada;
	private List<EstudianteSancionado> estudiante = new LinkedList<EstudianteSancionado>();
	private List<SolicitudApelacion> apelacion = new LinkedList<SolicitudApelacion>();
	private List<String> motivos;
	// Para llamar a los diferentes mensajes de dialogo
		MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	
	// Metodos get y set
		
	

	public Integer getInstancia() {
		return instancia;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public String getMotivosEstudiante() {
		return motivosEstudiante;
	}

	public void setMotivosEstudiante(String motivosEstudiante) {
		this.motivosEstudiante = motivosEstudiante;
	}

	public List<EstudianteSancionado> getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(List<EstudianteSancionado> estudiante) {
		this.estudiante = estudiante;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia2() {
		return apelacionestudianteinstancia2;
	}

	public void setApelacionestudianteinstancia2(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia2) {
		this.apelacionestudianteinstancia2 = apelacionestudianteinstancia2;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia3() {
		return apelacionestudianteinstancia3;
	}

	public void setApelacionestudianteinstancia3(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia3) {
		this.apelacionestudianteinstancia3 = apelacionestudianteinstancia3;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudiante() {
		return apelacionestudiante;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante) {
		this.apelacionestudiante = apelacionestudiante;
	}

	public SolicitudApelacion getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(SolicitudApelacion apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	public List<SolicitudApelacion> getApelacion() {
		return apelacion;
	}

	public void setApelacion(List<SolicitudApelacion> apelacion) {
		this.apelacion = apelacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}
			
 // fin de metodos get y set
	

	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}

	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	@Command
	@NotifyChange({ "apelacionestudiante" })
	public void buscarSolicitud(String cedula, String codigoLapso, Integer instancia) {
		instancia = 1;
		apelacionestudiante = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia2" })
	public void buscarSolicitudInstancia2(String cedula, String codigoLapso, Integer instancia) {
		instancia = 2;
		apelacionestudianteinstancia2 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}
	@Command
	@NotifyChange({ "apelacionestudianteinstancia3" })
	public void buscarSolicitudInstancia3(String cedula, String codigoLapso, Integer instancia) {
		instancia = 3;
		apelacionestudianteinstancia3 = servicioapelacionestadoapelacion.buscarApelacionHistorial(cedula, codigoLapso, instancia);	
	}
	
	@Command
	@NotifyChange({ "estudiante" })
	public void buscarEstudiante(String cedula) {
		estudiante = servicioestudiantesancionado.buscarApelacion(cedula);
	}
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("codigoLapso") String v2)

	// initialization code
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.codigoLapso = v2;
		buscarSolicitud(cedula, codigoLapso, instancia);
		buscarSolicitudInstancia2(cedula, codigoLapso, instancia);
		buscarSolicitudInstancia3(cedula, codigoLapso, instancia);
		buscarEstudiante(cedula);
		buscarMotivos();
		
		if (apelacionestudiante.size() != 0) {
		lapso = apelacionestudiante.get(0).getSolicitudApelacion().getId().getCodigoLapso();
		cedula = apelacionestudiante.get(0).getSolicitudApelacion().getId().getCedulaEstudiante();
		nombreEstudiante = apelacionestudiante.get(0).getSolicitudApelacion().getEstudianteSancionado().getEstudiante().getPrimerNombre();
		apellidoEstudiante = apelacionestudiante.get(0).getSolicitudApelacion().getEstudianteSancionado().getEstudiante().getPrimerApellido();
		nombreSancion = apelacionestudiante.get(0).getSolicitudApelacion().getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		nombreEstudiante = nombreEstudiante + " "+ apellidoEstudiante;
	}
		else {
		cedula = estudiante.get(0).getId().getCedulaEstudiante();
		lapso = estudiante.get(0).getId().getCodigoLapso();
		nombreSancion = estudiante.get(0).getSancionMaestro().getNombreSancion();
		nombreEstudiante = estudiante.get(0).getEstudiante().getPrimerNombre();
		apellidoEstudiante = estudiante.get(0).getEstudiante().getPrimerApellido();
		nombreEstudiante = nombreEstudiante + " "+ apellidoEstudiante;
		}
	}
	@Command
	public void closeThis() {
		window.detach();
	}
	
	@Command
	public void showModal() {
		apelacionestudiante = getApelacionestudiante ();
		apelacionestudianteinstancia2 = getApelacionestudianteinstancia2 ();
		apelacionestudianteinstancia3 = getApelacionestudianteinstancia3 ();
		estudiante = getEstudiante ();
		apelacion = getApelacion ();
		apelacionseleccionada = getApelacionseleccionada();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("apelacionestudiante", this.apelacionestudiante);
		map.put("apelacionestudianteinstancia2", this.apelacionestudianteinstancia2);
		map.put("apelacionestudianteinstancia3", this.apelacionestudianteinstancia3);
		map.put("apelacion", this.apelacion);
		map.put("estudiante", this.estudiante);
		map.put("cedula", this.cedula);
		map.put("codigoLapso", this.codigoLapso);
		final Window window = (Window) Executions
				.createComponents(
						"/WEB-INF/sigarep/vistas/reportes/Informes/Historialestudiante.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana,condicion);		
	}

	@Command
	public void buscarMotivos() {
			motivos = serviciomotivo.buscarMotivosApelacion(cedula, codigoLapso);
			if (motivos != null)
				for (int i = 0; i < motivos.size(); i++)
					motivosEstudiante += motivos.get(i)
							+ ", ";
		}
	

}
