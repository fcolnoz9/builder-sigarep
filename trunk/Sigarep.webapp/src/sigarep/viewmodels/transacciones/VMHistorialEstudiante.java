package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import java.util.LinkedList;
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
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.HistorialEstudiante;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteVeredicto;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


/**
 * HistorialEstudiante 
 * Maneja la informacón de los estudiantes sancionados que 
 * han iniciado el proceso de apelación.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 23/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialEstudiante {
	@Wire("#winDetalleHistorialEstudiante")
	private Window window;
	private String apellido;
	private String nombre;
	private String codigoLapso;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String sancion;
	private String nombreAsignatura;
	private String cedula;
	private String fechaIngreso;
	private String fechaNacimiento;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private HistorialEstudiante apelacionseleccionada;
	private Estudiante estudiante;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioEstudiante servicioestudiante;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	private List<ListaHistorialEstudianteVeredicto> listaVeredicto = new LinkedList<ListaHistorialEstudianteVeredicto>();
	private List<ApelacionEstadoApelacion> apelacionestudiante = new LinkedList<ApelacionEstadoApelacion>();
	private List<EstudianteSancionado> apelacion = new LinkedList<EstudianteSancionado>();
	private List<TipoMotivo> listaTipoMotivo;
    private List<AsignaturaEstudianteSancionado> asignaturas;
	private EstudianteSancionado estudianteSeleccionado;
	
	private List<HistorialEstudiante> listaHistorial = new LinkedList<HistorialEstudiante>();
	
	// Para llamar a los diferentes mensajes de dialogo
				MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();	

				public List<HistorialEstudiante> getListaHistorial() {
					return listaHistorial;
				}

				public void setListaHistorial(List<HistorialEstudiante> listaHistorial) {
					this.listaHistorial = listaHistorial;
				}

	//Métodos  Get y Set
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

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

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
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

	

	public HistorialEstudiante getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(HistorialEstudiante apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

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

	//Fin Métodos  Get y Set
	
	/**
	 * inicialización
	 * 
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
	@ExecutionArgParam("estudiante") Estudiante v1)

	{
		Selectors.wireComponents(view, this, false);
		this.estudiante = v1;
		cedula = estudiante.getCedulaEstudiante();
	
		nombre = estudiante.getPrimerNombre();
		segundoNombre = estudiante.getSegundoNombre();
		apellido = estudiante.getPrimerApellido();
		segundoApellido = estudiante.getSegundoApellido();
		concatenacionNombres();
		concatenacionApellidos();
		buscarApelacion(cedula);
		buscarAsignaturas();
	
		String fecha = estudiante.getAnioIngreso().toString();
		String separador = " ";
	if( !fecha.equals("")){
        String[] palabraArray = fecha.split(separador);
		fechaIngreso = palabraArray[0];
	}
		String fecha1 = estudiante.getFechaNacimiento().toString();
		String separador1 = " ";
	if( !fecha1.equals("")){
		String[] palabraArray1 = fecha1.split(separador1);
		fechaNacimiento = palabraArray1[0];
	
}
	}
	

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
	 /**
	 * buscarApelacion
	 * 
	 * @param String cedula
	 * @return Busca la apelación del estudiante.
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "apelacion" })
	public void buscarApelacion(String cedula) {
		apelacion = servicioestudiantesancionado.buscarApelacion(cedula);
	}

	
	/**
	 * buscarAsignaturas
	 * 
	 * @param buscarAsignaturas()
	 * @return Devuelve las asignaturas por las que el estudiante ha sido sancionado.
	 * @throws No
	 * dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({ "listaHistorial" })
	public void buscarAsignaturas() {
		cedula = apelacion.get(0).getId().getCedulaEstudiante();
		
		
		
		for (int j = 0; j < apelacion.size(); j++){
			
			HistorialEstudiante estudianteH = new HistorialEstudiante();
			estudianteH.setLapsoSancion(apelacion.get(j).getId().getCodigoLapso());
			estudianteH.setTipoSancion(apelacion.get(j).getSancionMaestro().getNombreSancion());
			estudianteH.setIndiceGrado(apelacion.get(j).getIndiceGrado());
			estudianteH.setUnidadesCursadas(apelacion.get(j).getUnidadesCursadas().toString());
			estudianteH.setUnidadesAprobadas(apelacion.get(j).getUnidadesAprobadas().toString());
			estudianteH.setCedula(apelacion.get(j).getId().getCedulaEstudiante());
			sancion = apelacion.get(j).
					getSancionMaestro().getNombreSancion();
			codigoLapso = apelacion.get(j).getId().getCodigoLapso();
			if (apelacion.get(j).getSancionMaestro().getIdSancion() == 2) {
				asignaturas = servicioasignaturaestudiantesancionado
						.buscarAsignaturaDeSancion(cedula, codigoLapso);
				if (asignaturas != null){
					for (int i = 0; i < asignaturas.size(); i++){
						asignaturaLapsosConsecutivos += asignaturas.get(i)
								.getAsignatura().getNombreAsignatura();
						if(i+1 < asignaturas.size()){
							asignaturaLapsosConsecutivos +=", ";
							
							
						}
					
					}
					estudianteH.setAsignaturas(asignaturaLapsosConsecutivos);
					asignaturaLapsosConsecutivos = "";
					
				}
			}else{
				
				
			}
			listaHistorial.add(estudianteH);
		}
	}
	
	/**
	 * showModal()
	 * 
	 * @param showModal() 
	 * @return Conecta con la ventana modal DetalleHistorialEstudiante.zul
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Command
	public void showModal() {
		cedula = getApelacionseleccionada().getCedula();
		codigoLapso = getApelacionseleccionada().getLapsoSancion();
		sancion = getApelacionseleccionada().getTipoSancion(); 
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cedula", cedula);
		map.put("codigoLapso", codigoLapso);
		map.put("sancion", sancion);
		if (window != null){
			closeThis();
			window = null;
		}
		else{
		window = (Window) Executions
				.createComponents(
						"/WEB-INF/sigarep/vistas/transacciones/DetalleHistorialEstudiante.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();

	}
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
	public void closeThis() {
		window.detach();
	}

}//fin VMHistorialEstudiante
