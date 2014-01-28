package sigarep.viewmodels.lista;

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

import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.viewmodels.transacciones.VMVeredictoI;
import sigarep.viewmodels.transacciones.VMVeredictoIII;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaGenericaSancionados {


	private SolicitudApelacion sancionadoSeleccionado;
	private EstudianteSancionado estudianteseleccionado;
	
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
	
	//Variables de tipo VM para llamar a metodos segun la funcionalidad "Finalizar"
	private VMVeredictoI vmVeredictoI = new VMVeredictoI();
	private VMVeredictoIII vmVeredictoIII = new VMVeredictoIII();
	
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

	
	public EstudianteSancionado getEstudianteseleccionado() {
		return estudianteseleccionado;
	}

	public void setEstudianteseleccionado(
			EstudianteSancionado estudianteseleccionado) {
		this.estudianteseleccionado = estudianteseleccionado;
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
    public void init(@ExecutionArgParam("rutaModal") String rutaModal){
		this.rutaModal=rutaModal;
		if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul")){
		listaEstudiantes = servicioestudiantesancionado.buscarSancionados();
		}
		buscarTipoMotivo();
		buscarProgramaA ();
		buscarSancionados();
    }
	
	@Command
  	@NotifyChange({"listaTipoMotivo"})
  	public void buscarTipoMotivo(){
  		listaTipoMotivo = serviciotipomotivo.buscarP(nombreTipoMotivo);
  	}
	
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
	}
	
	//Metodo donde se decide cuales sancionados se deben buscar segun la transaccion
	@Command
	@NotifyChange({"lista"})
	public void buscarSancionados(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			lista = serviciorecaudoentregado.buscarApelacionesCargarRecaudo();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosReconsideracion();
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoI(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoIII(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			lista = null;
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosRecursoJerarquico();
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudos.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVerificarRecaudosI();
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosII.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosReconsideracionVerificar();	
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosIII.zul"))
			lista = serviciosolicitudapelacion.buscarSancionadosJerarquicoVerificar();
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidez1();
	}
	
	@Command
	public void showModal (){
  		
  		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("sancionadoSeleccionado", sancionadoSeleccionado);
	 	map.put("estudianteseleccionado", estudianteseleccionado);
 
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
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredicto1(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredicto3(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			lista = null; //serviciodatosiniciales.filtrarEstudianteSancionado(cedula, nombre, apellido, nombrePrograma, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesRecursoJerarquico(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesRecursoJerarquico(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezI(programa,cedula,nombre,apellido,sancion );
	}

	@Command
	public void finalizar(){
		if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul")){
			buscarSancionados();
			vmVeredictoI.finalizarVeredictoI(lista);
		}
		
	}
//	@Command
//	public void finalizarVeredicto3(){
//		if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul")){
//			buscarSancionados();
//			vmVeredictoIII.finalizarVeredictoIII(lista);
//	}
//		
//	}
	
}
