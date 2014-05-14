package sigarep.viewmodels.transacciones;
import java.util.LinkedList;
import java.util.List;
import sigarep.herramientas.MensajesAlUsuario;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;

/**Historial de Cronograma
 * Muestra los cronogramas de los diferentes lapsos académicos
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialCronograma {

	@WireVariable 
	private ServicioCronograma serviciocronograma;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	private String lugarf=""; // Inicializacion de variable lugarf para filtrar
	private String responsablef="";// Inicializacion de variable responsablef para filtrar
	private String actividadf="";// Inicializacion de variable actividadf para filtrar
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	private Actividad actividad = new Actividad();
	private InstanciaApelada  responsable = new InstanciaApelada();
	private List<Cronograma> listaCronogramas = new LinkedList<Cronograma>();//Lista de Cronogramas
	private List<LapsoAcademico> listaLapsoAcademico;//Lista de Lapso Academico
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
	public InstanciaApelada getResponsable() {
		return responsable;
	}
	public void setResponsable(InstanciaApelada responsable) {
		this.responsable = responsable;
	}
	public List<Cronograma> getListaCronogramas() {
		return listaCronogramas;
	}
	public void setListaCronogramas(List<Cronograma> listaCronogramas) {
		this.listaCronogramas = listaCronogramas;
	}
	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}
	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}
	public ServicioCronograma getServiciocronograma() {
		return serviciocronograma;
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
	public void setServiciocronograma(ServicioCronograma serviciocronograma) {
		this.serviciocronograma = serviciocronograma;
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

	// OTROS METODOS
	@Init
	public void init(){
		//initialization code
		buscarLapsoAcademico();
	}

	/**  Se llena la lista de lapsos Académicos
	 * @param No recibe parametros
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaLapsoAcademico"})
	public void buscarLapsoAcademico(){
		listaLapsoAcademico =serviciolapsoacademico.buscarTodosLosLapsos();
	}

	/** buscarDetalleLapsoAcademicoCombo. Al escoger una lapso en el combo, busca los datos asociados a el(el detalle)
	 * @param fechaInicio, fechaFin, horaInicio, lugar, observacion, responsable, listaCronograma, listaLapsoAcademico, lapsoAcademico, actividad.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "responsable", "listaCronogramas", "listaLapsoAcademico", "lapsoAcademico", "actividad"})
	public void buscarDetalleLapsoAcademicoCombo() {
		listaCronogramas = serviciocronograma.buscarTodosCronogramas(lapsoAcademico.getCodigoLapso());
	}

	/** filtroCronograma. Hace filtrado de los campos mencionados que entran como parametro.
	 * @param listaConogramas, responsablef, lugarf, actividadf.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({"listaCronogramas", "responsablef", "lugarf", "actividadf"})
	public void filtroCronograma(){
		listaCronogramas = serviciocronograma.filtrarCronograma(responsablef, lugarf, actividadf);
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
	
}//fin VMHistorialCronograma
