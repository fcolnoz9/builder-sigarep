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
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteVeredicto;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;

import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * HistorialEstudiante UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialEstudiante {
	@Wire("#modalDialog")
	private Window window;
	private String apellido;
	private String nombre;
	private String codigoLapso;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String nombreAsignatura;
	private String asignaturaLapsosConsecutivos = "";
	private String motivosEstudiante = "";
	private String labelAsignaturaLapsosConsecutivos;
	private List<TipoMotivo> listaTipoMotivo;
	private String cedula;
	private EstudianteSancionado apelacionseleccionada;
	private String sancion;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	private List<ListaHistorialEstudianteVeredicto> listaVeredicto = new LinkedList<ListaHistorialEstudianteVeredicto>();
	private List<ApelacionEstadoApelacion> apelacionestudiante = new LinkedList<ApelacionEstadoApelacion>();
	private List<EstudianteSancionado> apelacion = new LinkedList<EstudianteSancionado>();
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<String> motivos;
	private EstudianteSancionado estudianteSeleccionado;
	// Para llamar a los diferentes mensajes de dialogo
			MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private String fechaIngreso;
	private String fechaNacimiento;
			

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getMotivosEstudiante() {
		return motivosEstudiante;
	}

	public void setMotivosEstudiante(String motivosEstudiante) {
		this.motivosEstudiante = motivosEstudiante;
	}


	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public List<String> getMotivos() {
		return motivos;
	}

	public void setMotivos(List<String> motivos) {
		this.motivos = motivos;
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


	public List<EstudianteSancionado> getApelacion() {
		return apelacion;
	}

	public void setApelacion(List<EstudianteSancionado> apelacion) {
		this.apelacion = apelacion;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudiante() {
		return apelacionestudiante;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante) {
		this.apelacionestudiante = apelacionestudiante;
	}

	

	public EstudianteSancionado getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(EstudianteSancionado apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	// Metodos get y set
	public List<ListaHistorialEstudianteVeredicto> getListaVeredicto() {
		return listaVeredicto;
	}

	public void setListaVeredicto(
			List<ListaHistorialEstudianteVeredicto> listaVeredicto) {
		this.listaVeredicto = listaVeredicto;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
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


	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// fin de metodos get y set

	/**
	 * concatenacionNombres
	 * 
	 * @return devuelve primer y segundo nombre concatenados
	 */
	public void concatenacionNombres() {

		String nombre1 = nombre;
		String nombre2 = segundoNombre;
		nombres = nombre1 + " " + nombre2;
	}

	/**
	 * concatenacionApellidos
	 * 
	 * @return devuelve primer y segundo apellido concatenados
	 */
	public void concatenacionApellidos() {

		String apellido1 = apellido;
		String apellido2 = segundoApellido;
		apellidos = apellido1 + " " + apellido2;

	}

	@Command
	@NotifyChange({ "apelacion" })
	public void buscarApelacion(String cedula) {
		apelacion = servicioestudiantesancionado.buscarApelacion(cedula);
	}

	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
	@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado v1)

	{
		Selectors.wireComponents(view, this, false);
		this.estudianteSeleccionado = v1;
		cedula = estudianteSeleccionado.getId().getCedulaEstudiante();
		codigoLapso = estudianteSeleccionado.getId().getCodigoLapso();
		nombre = estudianteSeleccionado.getEstudiante().getPrimerNombre();
		segundoNombre = estudianteSeleccionado.getEstudiante().getSegundoNombre();
		apellido = estudianteSeleccionado.getEstudiante().getPrimerApellido();
		concatenacionNombres();
		concatenacionApellidos();
		buscarApelacion(cedula);
		buscarAsignaturas();
		buscarMotivos();
		
		String fecha = estudianteSeleccionado.getEstudiante().getAnioIngreso().toString();
		String separador = " ";
	if( !fecha.equals("")){
        String[] palabraArray = fecha.split(separador);
		fechaIngreso = palabraArray[0];
	}
		String fecha1 = estudianteSeleccionado.getEstudiante().getFechaNacimiento().toString();
		String separador1 = " ";
	if( !fecha1.equals("")){
		String[] palabraArray1 = fecha1.split(separador1);
		fechaNacimiento = palabraArray1[0];
	
}
	}

	@Command
	public void closeThis() {
		window.detach();
	}

	@Command
	public void buscarAsignaturas() {
		sancion = apelacion.get(0).
				getSancionMaestro().getNombreSancion();
		cedula = apelacion.get(0).getId().getCedulaEstudiante();
		codigoLapso = apelacion.get(0).getId().getCodigoLapso();
		if (sancion.equalsIgnoreCase("RR")) {
			asignaturas = servicioasignaturaestudiantesancionado
					.buscarAsignaturaDeSancion(cedula, codigoLapso);
			if (asignaturas != null)
				for (int i = 0; i < asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i)
							.getAsignatura().getNombreAsignatura()
							+ ", ";
		}
	}
	
	@Command
	public void buscarMotivos() {
			motivos = serviciomotivo.buscarMotivosApelacion(cedula, codigoLapso);
			if (motivos != null)
				for (int i = 0; i < motivos.size(); i++)
					motivosEstudiante += motivos.get(i)
							+ ", ";
		}
	

	@Command
	public void showModal() {
		cedula = apelacionseleccionada.getId().getCedulaEstudiante();
		codigoLapso = apelacionseleccionada.getId().getCodigoLapso();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cedula", cedula);
		map.put("codigoLapso", codigoLapso);
		final Window window = (Window) Executions
				.createComponents(
						"/WEB-INF/sigarep/vistas/transacciones/DetalleHistorialEstudiante.zul",
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

}
