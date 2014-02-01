package sigarep.viewmodels.lista;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaGenericaSancionados {


	private SolicitudApelacion sancionadoSeleccionado;
	private EstudianteSancionado estudianteSeleccionado;
	
	//Servicios para llenar los combos
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	
	//Servicios para buscar apelaciones segun su transaccion (AGREGA TU SERVICIO AQUI)
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	
	MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	
	//Lista que se llena segun la transaccion
	private List<SolicitudApelacion> lista = new LinkedList<SolicitudApelacion>();
	private List<EstudianteSancionado> listaEstudiantes = new LinkedList<EstudianteSancionado>();
	
	//Variables para el filtrado por combos o textbox
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private String nombrePrograma;
	private String nombreTipoMotivo;
	private String rutaModal="";
	private String sancion="";
	private String programa="";
	private String apellido="";
	private String nombre="";
	private String cedula="";
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion;

	
	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public List<EstudianteSancionado> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(List<EstudianteSancionado> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public String getSancion() {
		return sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public ServicioRecaudoEntregado getServiciorecaudoentregado() {
		return serviciorecaudoentregado;
	}

	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}

	public List<SolicitudApelacion> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudApelacion> lista) {
		this.lista = lista;
	}

	/**
	 * Otros Metodos
	 */
	
	@Init
    public void init(@ExecutionArgParam("rutaModal") String rutaModal,
    				 @ExecutionArgParam("numeroSesion") String numeroSesion,
    				 @ExecutionArgParam("tipoSesion") String tipoSesion,
    				 @ExecutionArgParam("fechaSesion") Date fechaSesion){
		this.rutaModal=rutaModal;
		
		if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul") || 
				rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul") || 
				rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul")){
			this.numeroSesion = numeroSesion;
			this.tipoSesion = tipoSesion;
			this.fechaSesion = fechaSesion;
		}

		buscarProgramaA ();
		buscarSancionados();
    }
	
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
	}
	
	//Metodo donde se decide cuales sancionados se deben buscar segun la transaccion
	@Command
	@NotifyChange({"lista","listaEstudiantes"})
	public void buscarSancionados(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			lista = serviciorecaudoentregado.buscarApelacionesCargarRecaudo();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosReconsideracion();
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoI(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoII(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoIII(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudos.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVerificarRecaudosI();
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosII.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosReconsideracionVerificar();	
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosIII.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosJerarquicoVerificar();
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezI();
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezII.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezII();
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezIII.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezIII();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarSancionados();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarSancionadosRecursoJerarquico();
		
}
	
	@Command
	public void showModal (){
  	
		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("sancionadoSeleccionado", sancionadoSeleccionado);
	 	map.put("estudianteSeleccionado", estudianteSeleccionado);
	 	map.put("numeroSesion", numeroSesion);
	 	map.put("tipoSesion", tipoSesion);
	 	map.put("fechaSesion", fechaSesion);
 
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/"+rutaModal, null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
	
	@Command
	@NotifyChange({"lista","programa","cedula","nombre","apellido","sancion"})
	public void filtros(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			lista = serviciorecaudoentregado.filtrarApelacionesCargarRecaudo(programa,cedula,nombre,apellido,sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesReconsideracion(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoI(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoII(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoIII(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			lista = null; //serviciodatosiniciales.filtrarEstudianteSancionado(cedula, nombre, apellido, nombrePrograma, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			listaEstudiantes = servicioestudiantesancionado.filtrarApelacionesRecursoJerarquico(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezI(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezII(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezIII(programa,cedula,nombre,apellido,sancion );	
	}
}

