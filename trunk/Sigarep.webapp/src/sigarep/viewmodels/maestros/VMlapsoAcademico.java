package sigarep.viewmodels.maestros;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.bind.Binder;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;

/** Clase Lapso Academico
 * REgistra y modifica un lapso academico 
 *  @author Equipo: Builder-SIGAREP
 * @version 1.0
 * @since 20/12/13
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMlapsoAcademico {
	@WireVariable
	ServicioLapsoAcademico serviciolapsoacademico;

	private String codigoLapso;// clave principal de la tabla lapso_academico
	private Date fechaInicio;// fecha de inicio del lapso academico
	private Date fechaCierre;//fecha de cierre del lapso academico
	private Boolean estatus;// estatus del codigolapso
	private List<LapsoAcademico> listaLapsoAcademico;// lista de los lapso academicos registrados
	private LapsoAcademico lapsoAcademicoseleccionado;
	
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@Wire
	Textbox txtcodigoLapso;

	@Wire("#winRegistrarLapso")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

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

	
	public LapsoAcademico getLapsoAcademicoseleccionado() {
		return lapsoAcademicoseleccionado;
	}

	public void setLapsoAcademicoseleccionado(
			LapsoAcademico lapsoAcademicoseleccionado) {
		this.lapsoAcademicoseleccionado = lapsoAcademicoseleccionado;
	}

	// Fin de los metodod gets y sets

	// ----------- OTROS METODOS

	@Init
	public void init() {
		// initialization code
		buscarActivoLapso();
		buscarLapso();
	}

	/**
	 * Guardar lapso academico si no hay un lapso activo ya registrado
	 * @return nada
	 * @parameters el objeto lapsoacademico
	 * @throws No  dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
			"listaLapsoAcademico" })
	// el notifychange le avisa a que parametros en la pantalla se van a
	// cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void guardarLapso() {
		if (codigoLapso == null || fechaInicio == null || fechaCierre == null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			if (getListaLapsoAcademico().size() != 0) {
				System.out.println("ESTATUS"
						+ getListaLapsoAcademico().get(0).getEstatus());
				mensajeAlUsuario.ErrorLapsoActivoExistente();
			} else {
				LapsoAcademico lapsoA = new LapsoAcademico(codigoLapso,
						fechaInicio, fechaCierre, true);
				serviciolapsoacademico.guardarLapso(lapsoA);
				mensajeAlUsuario.informacionRegistroCorrecto();
				limpiarlapso();
			}
		}
	}

	/**
	 * Metodo que valida la fecha de los calendarios que la fecha de inicoo de
	 * menos q la fecha de cierre
	 * @return nada
	 * @parameters fechaInicio, fechaCierre
	 * @throws No  dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "fechaInicio", "fechaCierre" })
	public void validarFecha() {
		if (fechaInicio != null && fechaCierre != null) {
			if (fechaInicio.compareTo(fechaCierre) > 0) {
				mensajeAlUsuario.ErrorRangoFechas();
				fechaCierre = null;
			}
		}
	}

	/**
	 * Metodo que limpia todos los campos
	 * @return nada
	 * @parameters codigoLapso, fechaInicio, fechaCierre y la
	 *             listaLapsoAcademico
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
			"listaLapsoAcademico" })
	public void limpiarlapso() {
		codigoLapso = null;
		Date fecha = null;
		codigoLapso = "";
		fechaInicio = fecha;
		fechaCierre = fecha;
		buscarActivoLapso();

	}

	/**
	 * Buscar un lapso academico activo
	 * @return el lapso academico buscado de la lista
	 * @parameters codigo_lapso,fechaInicio,fechaCierre y lista lapso academico
	 * @throws No dispara ninguna excepcion.
	 */
	
	//JESUS PALENCIA:
	//EL RESPONSABLE POR FAVOR REVISAR PARA QUE USA LOS SIGUIENTES METODOS
	//DEPENDIENDO PARA QUE LOS USA MODIFICAR CON LOS METODOS EXISTENTES EN EL SERVICIO
	//Y BORRAR ESTE COMENTARIO.
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public List<LapsoAcademico> buscarActivoLapso() {
		return listaLapsoAcademico = serviciolapsoacademico
				.buscarLapsoAcademico(codigoLapso);
	}

	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapso() {
		listaLapsoAcademico = serviciolapsoacademico
				.buscarLapsoAcademico(codigoLapso);
	}

	/**
	 * permite tomar los datos del objeto lapso academico seleccionado
	 * @return nada
	 * @parameters codigo_lapso,fechaInicio,fechaCierre y lista lapso academico
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
			"listaLapsoAcademico" })
	public void mostrarSeleccionadoLapso() {
		LapsoAcademico lapsoAA = getLapsoAcademicoseleccionado();
		codigoLapso = lapsoAA.getCodigoLapso();
		fechaInicio = lapsoAA.getFechaInicio();
		fechaCierre = lapsoAA.getFechaCierre();
	}

	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "codigoLapso", "fechaInicio", "fechaCierre",
	"listaLapsoAcademico" })
	public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
			
		if (codigoLapso != null)
		{
			Messagebox.show("¿Realemente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					}
				}
			});		
		}
		else{
		Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					
					
					}
				}
			});		
		}
	}
	
}