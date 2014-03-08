package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import sigarep.herramientas.MensajesAlUsuario;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.viewmodels.seguridad.VMUtilidadesDeSeguridad;

/**Cronograma de Actividades - Planificar
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCronograma {

	@WireVariable ServicioCronograma serviciocronograma;
	private Date fechaInicio;
	private Date fechaFin;
	private Time horaInicio;
	private String lugar;
	private String observacion;
	private String codigoLapso;	
	private String lugarf="";
	private String actividadf="";
	private String responsablef="";
	private LapsoAcademico lapsoActivo;
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	private Actividad actividad = new Actividad();
	private InstanciaApelada responsable = new InstanciaApelada();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioActividad servicioactividad;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	private List<Cronograma> listaCronograma = new LinkedList<Cronograma>(); //Lista de Cronogramas
	private List<Actividad> listaActividad;
	private List<InstanciaApelada> listaResponsable;
	private Cronograma cronogramaSeleccionado;
	CronogramaPK cronogramaPK = new CronogramaPK();
	Cronograma cronograma = new Cronograma();
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();//Llama a los diferentes mensajes de dialogo
	Window win=null;
	int idcount=0;
	
	

	// Metodos GETS Y SETS
	public String getLugarf() {
		return lugarf;
	}
	public String getResponsablef() {
		return responsablef;
	}
	public String getActividadf() {
		return actividadf;
	}
	public void setLugarf(String lugarf) {
		this.lugarf = lugarf;
	}
	public void setResponsablef(String responsablef) {
		this.responsablef = responsablef;
	}
	public void setActividadf(String actividadf) {
		this.actividadf = actividadf;
	}
	public List<InstanciaApelada> getListaResponsable() {
		return listaResponsable;
	}
	public void setListaResponsable(List<InstanciaApelada> listaResponsable) {
		this.listaResponsable = listaResponsable;
	}
	public InstanciaApelada getResponsable() {
		return responsable;
	}
	public void setResponsable(InstanciaApelada responsable) {
		this.responsable = responsable;
	}
	public List<Actividad> getListaActividad() {
		return listaActividad;
	}
	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;
	}
	public ServicioCronograma getServiciocronograma() {
		return serviciocronograma;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public String getLugar() {
		return lugar;
	}
	public String getObservacion() {
		return observacion;
	}
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public ServicioLapsoAcademico getServiciolapsoacademico() {
		return serviciolapsoacademico;
	}
	public ServicioActividad getServicioactividad() {
		return servicioactividad;
	}
	public List<Cronograma> getListaCronograma() {
		return listaCronograma;
	}
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma"})
	public Cronograma getCronogramaSeleccionado() {
		return cronogramaSeleccionado;
	}
	public void setServiciocronograma(ServicioCronograma serviciocronograma) {
		this.serviciocronograma = serviciocronograma;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public void setServiciolapsoacademico(ServicioLapsoAcademico serviciolapsoacademico) {
		this.serviciolapsoacademico = serviciolapsoacademico;
	}
	public void setServicioactividad(ServicioActividad servicioactividad) {
		this.servicioactividad = servicioactividad;
	}
	public void setListaCronograma(List<Cronograma> listaCronograma) {
		this.listaCronograma = listaCronograma;
	}
	public void setCronogramaSeleccionado(Cronograma cronogramaSeleccionado) {
		this.cronogramaSeleccionado = cronogramaSeleccionado;
	}
	public String getCodigoLapso() {
		return codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	public LapsoAcademico getLapsoActivo() {
		return lapsoActivo;
	}
	public void setLapsoActivo(LapsoAcademico lapsoActivo) {
		this.lapsoActivo = lapsoActivo;
	}

	// OTROS METODOS
	@Wire("#winActualizarCronograma")//para conectarse a la ventana con el ID
	Window ventana;
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.BINDER) final Binder binder)
	{
		//initialization code
		Selectors.wireComponents(view, this, false);
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();
		if (ventana != null){
			if (ventana.getId().toString().equals("winActualizarCronograma")){
				if(lapsoActivo==null)
					mensajeAlUsuario.confirmacionCerrarVentanaLapsoAcademicoNoActivo(ventana);
				else{
					buscarCronograma();
					buscarActividad();
					buscarResponsable();
					codigoLapso = lapsoActivo.getCodigoLapso();				
				}
			}
		}
	}

	/** Guardar Cronograma
	 * @param fechaInicio, fechaFin, horaInicio, lugar, observacion, responsable, listaCronograma, actividad.
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones son que se quiera registrar un cronograma y no haya un lapso academico activo y
	 *         que quiera guardar con campos aun en blanco.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma", "actividad"})
	public void guardarCronograma() {
		if (codigoLapso == null)
			mensajeAlUsuario.errorLapsoActivoNoExistente();
		else{
			if(fechaInicio==null || fechaFin ==null || horaInicio ==null || lugar.equals(""))
				mensajeAlUsuario.advertenciaLlenarCampos();
			else {
				cronogramaPK.setIdActividad(actividad.getIdActividad());
				cronogramaPK.setCodigoLapso(codigoLapso);

				try {
					responsable=servicioInstanciaApelada.buscar(responsable.getIdInstanciaApelada());
					serviciocronograma.guardar(new Cronograma(cronogramaPK, true, fechaFin, fechaInicio, horaInicio, lugar, observacion, responsable));

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				mensajeAlUsuario.informacionRegistroCorrecto();
				buscarCronograma();
				limpiar();
			}
		}
	}

	/** Buscar un Detalle de Actividad seleccionada en el Combo. Al escoger una actividad en el combo, busca los datos asociados a ella(el detalle)
	 * @param fechaInicio, fechaFin, horaInicio, lugar, observacion, responsable, listaCronograma, actividad..
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que el Id de actividad no coincida con el Id de la actividad que se encuentra 
	 *         en la lista, es decir que no lo consiga en ella.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "actividad",  "listaCronograma" })
	public void buscarDetalleActividadCombo() {
		boolean check=false;
		for (int i = 0; i < listaCronograma.size(); i++) {
			if(actividad.getIdActividad() == listaCronograma.get(i).getActividad().getIdActividad()){
				fechaInicio=listaCronograma.get(i).getFechaInicio();
				fechaFin=listaCronograma.get(i).getFechaFin();
				lugar=listaCronograma.get(i).getLugar();
				horaInicio=listaCronograma.get(i).getHoraInicio();
				observacion=listaCronograma.get(i).getObservacion();
				responsable=listaCronograma.get(i).getResponsable();
				check=true;
			}

		}
		if(!check){
			fechaInicio=null;
			fechaFin=null;
			horaInicio=null;
			lugar="";
			observacion="";
			responsable = new InstanciaApelada();
		}
	}

	/** Se llena la lista de Cronogramas activos
	 * @param No recibe parametros
	 * @return La listaCronograma cargada con los cronogramas.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaCronograma"})
	public void buscarCronograma(){
		listaCronograma =serviciocronograma.listadoCronograma();
	}

	/**  Se llena la lista de Responsables activos
	 * @param No recibe parametros
	 * @return La listaResponsable cargada con los responsables (Instancia Apelada).
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaResponsable"})
	public void buscarResponsable(){
		listaResponsable =servicioInstanciaApelada.listadoInstanciaApelada(); 
	}

	/**  Se llena la lista de Actividades activas
	 * @param No recibe parametros
	 * @return La listaActividad cargada con las actividades.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaActividad"})
	public void buscarActividad(){
		listaActividad =servicioactividad.listadoActividad();
	}

	/** limpiar. Limpia todos los campos.
	 * @param No recibe parametros
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma", "actividad"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		fechaInicio=null;
		fechaFin=null;
		horaInicio=null;
		lugar="";
		observacion="";
		actividad= new Actividad();
		responsable = new InstanciaApelada();
	}

	/** eliminar.
	 * @param fechaInicio, fechaFin, horaInicio, lugar, observacion, responsable, listaCronograma, actividad.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que quiera eliminar con los campos vacios, sin seleccionar ningun registro
	 */
	@Command
	@NotifyChange({"id", "fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma", "actividad"})
	
	public void eliminarCronogramaSeleccionado(@ContextParam(ContextType.BINDER) final Binder binder){
		if (fechaInicio==null || fechaFin ==null || horaInicio ==null || lugar.equals("")) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
							//if you call super.delete here, since original zk event is not control by binder
							//the change of viewmodel will not update to the ui.
							//so, I post a delete to trigger to process it in binder controll.
							//binder.postCommand("limpiar", null);
							serviciocronograma.eliminarCronograma(getCronogramaSeleccionado().getId());
							mensajeAlUsuario.informacionEliminarCorrecto();
							binder.postCommand("limpiar", null);
							buscarCronograma();
						case NO:
					
							binder.postCommand("limpiar", null);
					}
				}
			});		
		}
	}

	/** mostrarSeleccionado. Muestra los datos del cronograma seleccionado previamente.
	 * @param fechaInicio, fechaFin, horaInicio, lugar, observacion, responsable, listaCronograma, actividad.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma", "actividad"})
	public void mostrarSeleccionado(){
		Cronograma cro = getCronogramaSeleccionado();
		fechaInicio = cro.getFechaInicio();
		fechaFin = cro.getFechaFin();
		horaInicio = cro.getHoraInicio();
		lugar = cro.getLugar();
		observacion = cro.getObservacion();
		actividad=cro.getActividad();
		responsable=cro.getResponsable();
	}

	/** Validar Fechas
	 * @param fechaInicio, fechaFin.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que quiera introducir en fecha fin una fecha menor o antes que la fecha de inicio.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin"})
	public void validarFecha(){
		if (fechaInicio != null && fechaFin != null){
			if (fechaInicio.compareTo(fechaFin) > 0){
				mensajeAlUsuario.errorRangoFechas();
				fechaFin=null;
			}
		}
	}

	/** filtroCronograma. Hace filtrado de los campos mencionados que entran como parametro.
	 * @param responsablef, lugarf, actividadf.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaCronograma", "responsablef", "lugarf", "actividadf"})
	public void filtroCronograma(){
		listaCronograma = serviciocronograma.filtrarCronograma(responsablef, lugarf, actividadf);
	}
	
	@Command
	@NotifyChange({ "actividad", "fechaInicio", "fechaFin",
			"horaInicio", "observacion", "lugar" })
	public void modalDetalleCronograma() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cronogramaSeleccionado", this.cronogramaSeleccionado);

		final Window window = (Window) Executions.createComponents(
				"WEB-INF/sigarep/vistas/portal/externo/modales/DetalleCronograma.zul", null, map);
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
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronograma", "actividad"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(fechaInicio!=null||fechaFin!=null|| horaInicio!=null|| lugar!= null || observacion!= null )
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}

