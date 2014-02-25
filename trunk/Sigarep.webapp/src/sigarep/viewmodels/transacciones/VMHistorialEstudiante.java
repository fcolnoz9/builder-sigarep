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
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudiante;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteVeredicto;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioHistorialEstudiante;
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
	private String nombreSancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String codigoLapso;
	private String nombreTipoMotivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String nombreAsignatura;
	private String sexo;
	private String fechaNacimiento;
	private String anioIngreso;
	private Float indiceGrado;
	private Integer caso;
	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
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
	private ServicioHistorialEstudiante serviciohistorial;
	@WireVariable
	private List<ListaHistorialEstudiante> lista = new LinkedList<ListaHistorialEstudiante>();
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
	// Para llamar a los diferentes mensajes de dialogo
			MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	public String getMotivosEstudiante() {
		return motivosEstudiante;
	}

	public void setMotivosEstudiante(String motivosEstudiante) {
		this.motivosEstudiante = motivosEstudiante;
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

	public List<ListaHistorialEstudiante> getLista() {
		return lista;
	}

	public void setLista(List<ListaHistorialEstudiante> lista) {
		this.lista = lista;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
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

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
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

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Integer getUnidadesCursadas() {
		return unidadesCursadas;
	}

	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(String anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public Float getIndiceGrado() {
		return indiceGrado;
	}

	public void setIndiceGrado(Float indiceGrado) {
		this.indiceGrado = indiceGrado;
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
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("nombre") String v2,
			@ExecutionArgParam("apellido") String v3,
			@ExecutionArgParam("sexo") String v4,
			@ExecutionArgParam("fechaNacimiento") String v5,
			@ExecutionArgParam("telefono") String v6,
			@ExecutionArgParam("email") String v7,
			@ExecutionArgParam("segundoNombre") String v8,
			@ExecutionArgParam("segundoApellido") String v9,
			@ExecutionArgParam("unidadesCursadas") Integer v10,
			@ExecutionArgParam("unidadesAprobadas") Integer v11,
			@ExecutionArgParam("anioIngreso") String v12,
			@ExecutionArgParam("indiceGrado") Float v13,
			@ExecutionArgParam("programa") String v14,
			@ExecutionArgParam("codigoLapso") String v15)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.nombre = v2;
		this.apellido = v3;
		this.sexo = v4;
		this.fechaNacimiento = v5;
		this.telefono = v6;
		this.email = v7;
		this.segundoNombre = v8;
		this.segundoApellido = v9;
		this.unidadesCursadas = v10;
		this.unidadesAprobadas = v11;
		this.anioIngreso = v12;
		this.indiceGrado = v13;
		this.programa = v14;
		this.codigoLapso = v15;
		concatenacionNombres();
		concatenacionApellidos();
		buscarApelacion(cedula);
		buscarAsignaturas();
		buscarMotivos();
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
