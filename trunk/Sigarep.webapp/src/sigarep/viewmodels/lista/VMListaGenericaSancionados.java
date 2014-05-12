package sigarep.viewmodels.lista;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
* Clase VMListaGenericaSancionados : Clase ViewModels relacionada con la clase ListaGenericaSancionados
*
* @author Equipo Builder
* @version 1.0
* @since 04/12/13
* @last 10/05/2014
*/

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaGenericaSancionados{
	//-----------------Servicios----------------------------
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioEstudiante servicioestudiante;
	//-----------------Variables ListaGenericaSancionados ------------------
	private String nombrePrograma;
	private String nombreTipoMotivo;
	private String rutaModal="";
	private String sancion="";
	private String programa="";
	private String apellido="";
	private String nombre="";
	private String cedula="";
	private String veredicto="";
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion;
	//-----------------Variables Lista----------------------
	private List<SolicitudApelacion> lista = new LinkedList<SolicitudApelacion>();
	private List<EstudianteSancionado> listaEstudiantes = new LinkedList<EstudianteSancionado>();
	private List<Estudiante> listaMaestroEstudiantes = new LinkedList<Estudiante>();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private ListModelList<String> listaVeredicto;//Lista para llenar el combo Veredicto
	//-----------------Variables Objeto---------------------
	private SolicitudApelacion sancionadoSeleccionado;
	private EstudianteSancionado estudianteSeleccionado;
	private Estudiante estudiante;
	private LapsoAcademico lapsoActivo;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	// Métodos Set y Get
	public List<Estudiante> getListaMaestroEstudiantes() {
		return listaMaestroEstudiantes;
	}

	public void setListaMaestroEstudiantes(List<Estudiante> listaMaestroEstudiantes) {
		this.listaMaestroEstudiantes = listaMaestroEstudiantes;
	}

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

	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}
	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(
			Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<SolicitudApelacion> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudApelacion> lista) {
		this.lista = lista;
	}
	
	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}
	
	public ListModelList<String> getCmbVeredicto() {
		listaVeredicto.add("PROCEDENTE");
		listaVeredicto.add("NO PROCEDENTE");
		listaVeredicto.add("TODOS");
		listaVeredicto.add("SIN VEREDICTO");
		return listaVeredicto;		
	}

	public void setCmbVeredicto(ListModelList<String> cmbVeredicto) {
		this.listaVeredicto = cmbVeredicto;
	}
	//Fin Métodos Set y Get

	/**
	 * Init. Código de inicialización.
	 * 
	 * @param view, binder, rutaModal, numeroSesion, tipoSesion, fechaSesion.
	 * @return Carga las lista de sancionados, el lapso y programas academicos.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Wire("#winListaGenericaSancionados")//para conectarse a la ventana con el ID
	Window ventana;
	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
    				@ContextParam(ContextType.BINDER) final Binder binder,
    				@ExecutionArgParam("rutaModal") String rutaModal,
    				 @ExecutionArgParam("numeroSesion") String numeroSesion,
    				 @ExecutionArgParam("tipoSesion") String tipoSesion,
    				 @ExecutionArgParam("fechaSesion") Date fechaSesion){
		
		Selectors.wireComponents(view, this, false);
		this.rutaModal=rutaModal;
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();
		if (!rutaModal.equalsIgnoreCase("transacciones/HistorialEstudiante.zul"))
			if(lapsoActivo == null)
				mensajeAlUsuario.advertenciaLapsoAcademicoNoActivo(ventana);
	
		//Se valida que si se puede registrar o no la apelacion para
		//la instancia seleccionada
		if (validarLogicaApelaciones()){
			if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul") || 
					rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul") || 
					rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul")){
				this.numeroSesion = numeroSesion;
				this.tipoSesion = tipoSesion;
				this.fechaSesion = fechaSesion;
			}
			buscarProgramaA ();
			buscarSancionados();
			listaVeredicto= new ListModelList<String>();				
		}
    }
	
	/**Busca la lista de los programas acádemicos registrados. 
	 * @param ninguno.
	 * @return Llena la lista de programas, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
	}
	
	/**Metodo que busca una lista de estudiantes o solicitudes de apelación
	 * de los mismos según la transacción seleccionada del menú del sistema
	 * decidiendo cuales sancionados se deben buscar segun la transacción. 
	 * @param ninguno.
	 * @return Llena la lista de estudiantes o de solicitudes de apelación, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */	

	@Command
	@GlobalCommand
	@NotifyChange({"lista","listaEstudiantes", "listaMaestroEstudiantes"})
	public void buscarSancionados(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarEstudiantesCargarRecaudoEntregado();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarSancionadosReconsideracion();
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoI(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoII(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVeredictoIII(); 
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosI.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVerificarRecaudosI(lapsoActivo,1);
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVerificarRecaudosII(lapsoActivo,2);	
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosIII.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesVerificarRecaudosIII(lapsoActivo,3);	
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezI(lapsoActivo,1);
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezII.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezII(lapsoActivo,2);
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezIII.zul"))
			lista = serviciosolicitudapelacion.buscarAnalizarValidezIII(lapsoActivo,3);
		else if (rutaModal.equalsIgnoreCase("reportes/informes/InformeCU.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesRecursoJerarquico(lapsoActivo,3);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarSancionados();
		else if (rutaModal.equalsIgnoreCase("transacciones/HistorialEstudiante.zul"))
			listaMaestroEstudiantes = servicioestudiante.buscarEstudiante();
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			listaEstudiantes = servicioestudiantesancionado.buscarSancionadosRecursoJerarquico();
		else if (rutaModal.equalsIgnoreCase("reportes/informes/InformeActas.zul"))
			lista = serviciosolicitudapelacion.buscarApelacionesActa (lapsoActivo, 1);
	}	
	
	/**Metodo que valida si se puede registrar apelaciones en una Instancia 
	 * seleccionada considerando si se ha comenzado o finalizado el proceso 
	 * de apelaciones en otra Instancia. 
	 * @param ninguno.
	 * @return false si existen apelaciones por procesar en una instancia anterior a
	 * la seleccionada o true en caso contrario.
	 * @throws No dispara ninguna excepción.
	 */	
	
	public boolean validarLogicaApelaciones(){
		boolean resultado=true;
		if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul")){
			if (serviciosolicitudapelacion.existenApelacionesIniciadas(2) ||
					serviciosolicitudapelacion.existenApelacionesIniciadas(3)){
				mensajeAlUsuario.advertenciaNoPuedeRegistrarApelacionInicial();
				return resultado = false;
			}
		}
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul")){
			if (!serviciosolicitudapelacion.estanFinalizadasLasApelaciones(1)){
				mensajeAlUsuario.advertenciaNoPuedeRegistrarRecursoReconsideracion();
				resultado = false;
			}
			else if (serviciosolicitudapelacion.existenApelacionesIniciadas(3)){
				mensajeAlUsuario.advertenciaNoPuedeRegistrarRecursoReconsideracion2();
				resultado = false;
			}
			return resultado;
		}
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul")){
			if (!serviciosolicitudapelacion.estanFinalizadasLasApelaciones(2) ||
				!serviciosolicitudapelacion.estanFinalizadasLasApelaciones(1)){
				mensajeAlUsuario.advertenciaNoPuedeRegistrarRecursoJerarquico();
				return resultado = false;
			}
		}
		return resultado;
	}
	
	/**Metodo que valida que aparezca el combo de veredicto cuando una transacción
	 * de veredicto es seleccionada o que no aparezca en caso contrario. 
	 * @param ninguno.
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	public void validarComboVeredicto(@BindingParam("combo") Combobox cmbVeredicto, 
										@BindingParam("label") Label lblVeredicto) {
		if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul") || 
				rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul") || 
				rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul")){
			lblVeredicto.setVisible(true);
			cmbVeredicto.setVisible(true);
		}
		else{
			lblVeredicto.setVisible(false);
			cmbVeredicto.setVisible(false);
		}
	}
	
	/**Metodo que muestra en pantalla la ventana modal con la información
	 * de los estudiantes sancionados que tienen apelación en esa transacción
	 * seleccionada. 
	 * @param listboxSancionados.
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto
	 * @throws No dispara ninguna excepción.
	 */	
	
	@Command
	public void showModal(@BindingParam("listboxSancionados") Listbox listboxSancionados){
		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("sancionadoSeleccionado", sancionadoSeleccionado);
	 	map.put("estudianteSeleccionado", estudianteSeleccionado);
	 	map.put("estudiante", estudiante);
	 	map.put("numeroSesion", numeroSesion);
	 	map.put("tipoSesion", tipoSesion);
	 	map.put("fechaSesion", fechaSesion);
	 	listboxSancionados.clearSelection();
        final Window window = (Window) Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/"+rutaModal, null, map);
		window.setMaximizable(true);
		window.doModal();
  	}
	
	/**
	* Filtros. Método que busca y filtra los estudiantes sancionados
	* en una transacción seleccionada en el menú del sistema.
	*
	* @param Ninguno.
	* @return Objeto Estudiante Sancionado filtrado.
	* @throws No dispara ninguna excepción.
	*
	*/
	
	@Command
	@NotifyChange({"lista","listaEstudiantes","programa","cedula","nombre","apellido","sancion", "listaMaestroEstudiantes"})
	public void filtros(){
		if (rutaModal.equalsIgnoreCase("transacciones/CargarRecaudoEntregado.zul"))
			listaEstudiantes = servicioestudiantesancionado.filtrarApelacionesCargarRecaudoEntregado(programa,cedula,nombre,apellido,sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarReconsideracion.zul"))
			listaEstudiantes = servicioestudiantesancionado.filtrarApelacionesReconsideracion(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoI(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoII(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVeredictoIII(cedula, nombre, apellido, programa, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarDatosInicialesApelacion.zul"))
			listaEstudiantes = servicioestudiantesancionado.filtrarDatosIniciales(programa, cedula, nombre, apellido, sancion);
		else if (rutaModal.equalsIgnoreCase("transacciones/RegistrarRecursoJerarquico.zul"))
			listaEstudiantes = servicioestudiantesancionado.filtrarApelacionesRecursoJerarquico(programa,cedula,nombre,apellido,sancion );
		else if (rutaModal.equalsIgnoreCase("transacciones/HistorialEstudiante.zul"))
			listaMaestroEstudiantes = servicioestudiante.filtrarEstudiantesHistorial(programa,cedula,nombre,apellido);
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezI(programa,cedula,nombre,apellido,sancion,lapsoActivo,1);
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezII(programa,cedula,nombre,apellido,sancion,lapsoActivo,2);
		else if (rutaModal.equalsIgnoreCase("transacciones/AnalizarValidezIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesAnalizarValidezIII(programa,cedula,nombre,apellido,sancion,lapsoActivo,3);	
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosI.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVerificarRecaudosI(programa,cedula,nombre,apellido,sancion,lapsoActivo,1);
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVerificarRecaudosII(programa,cedula,nombre,apellido,sancion,lapsoActivo,2);
		else if (rutaModal.equalsIgnoreCase("transacciones/VerificarRecaudosIII.zul"))
			lista = serviciosolicitudapelacion.filtrarApelacionesVerificarRecaudosIII(programa,cedula,nombre,apellido,sancion,lapsoActivo,3);
		else if (rutaModal.equalsIgnoreCase("reportes/informes/InformeActas.zul"))
			lista = serviciosolicitudapelacion.filtrarActa(programa,cedula,nombre,apellido,sancion,lapsoActivo,3);
		
		filtrarVeredicto(lista);
	}
	
	/**
	* Filtrar Veredicto. Método que busca y filtra los estudiantes sancionados 
	* por un resultado de veredicto seleccionado.
	*
	* @param listaFiltrarVeredicto.
	* @return Objeto Estudiante Sancionado filtrado.
	* @throws No dispara ninguna excepción.
	*/	
		
	@Command
	@NotifyChange({"lista","veredicto"})
	public void filtrarVeredicto(List<SolicitudApelacion> listaFiltrarVeredicto){
		if(!veredicto.equalsIgnoreCase("TODOS") && !veredicto.equals(""))
			lista = serviciosolicitudapelacion.filtrarComboVeredictoListaGenerica(listaFiltrarVeredicto,veredicto);
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
} //fin VMListaGenericaSancionados