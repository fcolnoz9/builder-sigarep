package sigarep.viewmodels.transacciones;

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
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;

/**
 * VM Historial observación analizar recaudos.
 * 
 * @author Equipo Builder
 * @version 1.2
 * @since 20/12/2013
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialObservacionAnalizarRecaudos {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	// --------------------------Variables de Control-------------------
	private String cedula;
	// --------------------------Variables Lista------------------------
	private List<RecaudoEntregado> listaRecaudo;
	private List<SolicitudApelacion> lista = new LinkedList<SolicitudApelacion>();
	// --------------------------Variables Objeto-----------------------
	private SolicitudApelacion sancionadoSeleccionado;
	private EstudianteSancionado estudianteseleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Métodos Set y Get
	public List<SolicitudApelacion> getLista() {
		return lista;
	}

	public void setLista(List<SolicitudApelacion> lista) {
		this.lista = lista;
	}

	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}

	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}

	public EstudianteSancionado getEstudianteseleccionado() {
		return estudianteseleccionado;
	}

	public void setEstudianteseleccionado(
			EstudianteSancionado estudianteseleccionado) {
		this.estudianteseleccionado = estudianteseleccionado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	// Fin Métodos Set y Get

	/**
	 * inicialización
	 * 
	 * @param init
	 * @return Código de inicialización
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String cedula) {
		this.cedula = cedula;
		buscarRecaudosEntregados(cedula);
	}

	/**
	 * Buscar Recaudos Entregados.
	 * 
	 * @param String
	 *            cedula
	 * @return Lista de recaudos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado
				.buscarRecaudosEntregadosObservacionesAnalizar(cedula);
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
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana, condicion);
	}

}
