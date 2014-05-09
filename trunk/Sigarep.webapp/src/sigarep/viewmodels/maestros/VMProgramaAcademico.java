package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

/** Clase VMProgramaAcademico.
 * @author Equipo Builder
 * @version 1.0
 * @since 02-/12/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMProgramaAcademico {
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;
	//-----------------Variables ProgramaAcademico ---------
	private Integer idPrograma;
	private Boolean estatus;
	private String nombrePrograma;
	//-----------------Variables Filtro---------------------
	private String nombreProgramaFiltro = "";
	//-----------------Variables Lista----------------------
	private List<ProgramaAcademico> listaPrograma;
	//-----------------Variables Objeto---------------------
	private ProgramaAcademico programaseleccionado;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Inicio Métodos Sets y Gets
	public Integer getIdProgramaAcademico() {
		return idPrograma;
	}

	public void setIdProgramaAcademico(Integer idProgramaAcademico) {
		this.idPrograma = idProgramaAcademico;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String programa) {
		this.nombrePrograma = programa;
	}

	public String getNombreProgramaFiltro() {
		return nombreProgramaFiltro;
	}

	public void setNombreProgramaFiltro(String programa) {
		this.nombreProgramaFiltro = programa;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public ProgramaAcademico getProgramaseleccionado() {
		return programaseleccionado;
	}

	public void setProgramaseleccionado(ProgramaAcademico programaseleccionado) {
		this.programaseleccionado = programaseleccionado;
	}// Fin Métodos Sets y Gets

	/**
	 * inicialización
	 *  Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		buscarProgramaA();
	}

	/** guardarPrograma
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus", "listaPrograma" })
	public void guardarPrograma() {
		if (nombrePrograma == null || nombrePrograma.equals("")) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			ProgramaAcademico proa = new ProgramaAcademico(idPrograma,
					nombrePrograma, true);
			servicioprogramaacademico.guardarPrograma(proa);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/** eliminarPrograma
	 * @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	@Command
	@NotifyChange({ "listaPrograma", "nombrePrograma" })
	public void eliminarPrograma(
			@ContextParam(ContextType.BINDER) final Binder binder) {
		if (nombrePrograma == null) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			Messagebox.show("¿Desea eliminar el registro realmente?",
					"Confirmar", new Messagebox.Button[] {
					Messagebox.Button.YES, Messagebox.Button.NO },
					Messagebox.QUESTION, new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
					case YES:
						// if you call super.delete here, since original
						// zk event is not control by binder
						// the change of viewmodel will not update to
						// the ui.
						// so, I post a delete to trigger to process it
						// in binder controll.
						// binder.postCommand("limpiar", null);
						servicioprogramaacademico
						.eliminarPrograma(getProgramaseleccionado()
								.getIdPrograma());
						mensajeAlUsuario.informacionEliminarCorrecto();
						binder.postCommand("limpiar", null);
					case NO:

						binder.postCommand("limpiar", null);
					}
				}
			});
		}
	}

	/**
	 * mostrarSeleccionada : Muestra en el área de datos el registro
	 * seleccionado
	 * @param Ninguno
	 * @return Objeto programa academico seleccionado
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "nombrePrograma" })
	public void mostrarSeleccionado() {
		idPrograma = getProgramaseleccionado().getIdPrograma();
		nombrePrograma = getProgramaseleccionado().getNombrePrograma();
	}

	/**
	 * buscarProgramaA
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
	}

	/**
	 * filtros   Método que busca y filtra por nombre de programa academico
	 * @param Ninguno
	 * @return Objeto programa academico.
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaPrograma", "nombreProgramaFiltro" })
	public void filtros() {
		listaPrograma = servicioprogramaacademico
				.buscarPrograma(nombreProgramaFiltro);
	}

	/**
	 * limpiar 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus", "listaPrograma",
	"nombreProgramaFiltro" })
	public void limpiar() {
		idPrograma = null;
		nombrePrograma = null;
		nombreProgramaFiltro = "";
		buscarProgramaA();
	}

	/**
	 * Cerrar Ventana : Cierra el .zul asociado al VM.  
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listaPrograma", "nombrePrograma" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(nombrePrograma != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
}
