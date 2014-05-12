package sigarep.viewmodels.maestros;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;

/**Clase VMLapsoAcademico : Clase ViewModels 
relacionada con el Maestro LapsoAcademico.
 
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/13
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMLapsoAcademico {
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioLapsoAcademico serviciolapsoacademico;
	//-----------------Variables LapsoAcadémico-------------
	private String codigoLapso;// clave principal de la tabla lapso_academico
	private Date fechaInicio;// fecha de inicio del lapso académico
	private Date fechaCierre;// fecha de cierre del lapso académico
	private Boolean estatus;// estatus del codigolapso
	//-----------------Variables Lista----------------------
	private List<LapsoAcademico> listaLapsoAcademico;// lista de los lapso
	// académico registrados
	//-----------------Variables Objeto---------------------
	private LapsoAcademico lapsoAcademicoSeleccionado;
	private LapsoAcademico lapsoAcademico;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@Wire
	Textbox txtcodigoLapso;

	// Metodos set y get
	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	public LapsoAcademico getLapsoAcademicoSeleccionado() {
		return lapsoAcademicoSeleccionado;
	}

	public void setLapsoAcademicoSeleccionado(
			LapsoAcademico lapsoAcademicoseleccionado) {
		this.lapsoAcademicoSeleccionado = lapsoAcademicoseleccionado;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	// Fin de los metodos gets y sets

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno.
	 * @return Objetos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
	@Init
	public void init() {

		buscarLapsoAcademico();
		buscarTodosLapsoAcademicos();
	}

	/**
	 * Guardar un lapso Académico : si no hay un lapso activo ya registrado y
	 * modifica el lapso actual
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
		"listaLapsoAcademico", "lapsoAcademico" })
	public void guardarLapso() {
		if (codigoLapso == null || fechaInicio == null || fechaCierre == null
				|| codigoLapso.equals(""))
			mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			if (serviciolapsoacademico.buscarUnLapsoAcademico(codigoLapso) == null
					&& serviciolapsoacademico.buscarLapsoActivo() == null) {
				LapsoAcademico lapsoA = new LapsoAcademico(codigoLapso,
						fechaInicio, fechaCierre, true);
				serviciolapsoacademico.guardarLapso(lapsoA);
				mensajeAlUsuario.informacionRegistroCorrecto();
				limpiarlapso();
				buscarLapsoAcademico();

			} else if (serviciolapsoacademico
					.buscarUnLapsoAcademico(codigoLapso) == null
					&& serviciolapsoacademico.buscarLapsoActivo() != null) {
				mensajeAlUsuario.errorLapsoActivoExistente();
			} else if (serviciolapsoacademico
					.buscarUnLapsoAcademico(codigoLapso) != null
					&& serviciolapsoacademico.buscarUnLapsoAcademico(
							codigoLapso).getEstatus() == false) {
				mensajeAlUsuario.errorLapsoFinalizadoNoModificable();
			} else {
				if (serviciolapsoacademico.buscarUnLapsoAcademico(codigoLapso)
						.getEstatus() == true) {
					LapsoAcademico lapsoA = new LapsoAcademico(codigoLapso,
							fechaInicio, fechaCierre, true);
					serviciolapsoacademico.guardarLapso(lapsoA);
					mensajeAlUsuario.informacionRegistroCorrecto();
					limpiarlapso();
					buscarLapsoAcademico();
				}
			}

		}
		buscarLapsoAcademico();
	}

	/**
	 * Valida la Fecha : Metodo que valida la fecha de los calendarios, que la fecha de inico debe
	 * ser menor que la fecha de cierre.
	 * @param Ninguno.
	 * @return Ninguno.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "fechaInicio", "fechaCierre" })
	public void validarFecha() {
		if (fechaInicio != null && fechaCierre != null) {
			if (fechaInicio.compareTo(fechaCierre) > 0) {
				mensajeAlUsuario.errorRangoFechas();
				fechaCierre = null;
			}
		}
	}

	/**
	 * mostrarSeleccionada 
	 * @param Ningun.            .
	 * @return Objeto LapsoAcademico seleccionado
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre" })
	public void mostrarSeleccionadoLapso() {
		codigoLapso = lapsoAcademicoSeleccionado.getCodigoLapso();
		fechaInicio = lapsoAcademicoSeleccionado.getFechaInicio();
		fechaCierre = lapsoAcademicoSeleccionado.getFechaCierre();
	}

	/**
	 * Buscar Todos Lapso Academicos : Permite mostrar la lista de todos los lapso académico
	 * @param Ningun. 
	 * @return Objeto LapsoAcademico
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public List<LapsoAcademico> buscarTodosLapsoAcademicos() {
		return listaLapsoAcademico = serviciolapsoacademico
				.buscarTodosLosLapsos();
	}

	/**
	 * buscarLapsoAcademico
	 * 
	 * @param Ningun.
	 * @return Objeto LapsoAcademico.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "lapsoAcademico" })
	public void buscarLapsoAcademico() {
		lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo();
	}

	/**
	 * limpiar lapso : Metodo que limpia todos los campos
	 * @param Ningun.
	 * @return Ningun
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
		"listaLapsoAcademico", "estatus" })
	public void limpiarlapso() {
		codigoLapso = "";
		fechaInicio = null;
		fechaCierre = null;
		lapsoAcademicoSeleccionado = new LapsoAcademico();
		buscarTodosLapsoAcademicos();
	}

	/**
	 * Cerrar Ventana : cierra el .zul asociado al VM
	 * @param  Window ventana
	 * @return Ningun
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
	"listaLapsoAcademico" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = false;
		if (codigoLapso != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);
	}
}