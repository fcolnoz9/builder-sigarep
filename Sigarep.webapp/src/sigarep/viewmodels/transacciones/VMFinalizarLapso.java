package sigarep.viewmodels.transacciones;

import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/** FinalizarLapso
 * Contiene métodos necesarios para la finalización de un lapso académico.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 22/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMFinalizarLapso {

	public VMFinalizarLapso() {
		// TODO Auto-generated constructor stub
	}
	
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioCronograma serviciocronograma;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	
	private LapsoAcademico lapsoAcademico;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	
	@Wire("#winFinalizarLapso")
	Window ventana;
	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
		if (serviciolapsoacademico.buscarLapsoActivo() == null)
			mensajeAlUsuario.advertenciaLapsoAcademicoNoActivo(ventana);
		else
			lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo();
    }
	
	@Command
	public void finalizarLapso(){	
		long apelacionesSinVeredicto = 0;
		long apelacionesSinSesion = 0;
		Date ultimaFechaCronograma = serviciocronograma.buscarUltimaFechaDelCronogramaActual();
		Date fechaActual = new Date();
		
		apelacionesSinVeredicto = serviciosolicitudapelacion.contarApelacionesSinVeredicto();
		apelacionesSinSesion = serviciosolicitudapelacion.contarApelacionesSinSesion();
		if (apelacionesSinVeredicto > 0)
			mensajeAlUsuario.errorFinalizarLapsoVeredicto();
		else if (apelacionesSinSesion > 0)
			mensajeAlUsuario.errorFinalizarLapsoSesion();
		else if (fechaActual.compareTo(ultimaFechaCronograma) < 0)
			mensajeAlUsuario.errorFinalizarLapsoCronograma();
		else{
			lapsoAcademico.setEstatus(false);
			serviciolapsoacademico.guardarLapso(lapsoAcademico);
			mensajeAlUsuario.informacionFinalizarLapsoExitoso();
		}
	}
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}
	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
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
		boolean condicion = false;
		if(lapsoAcademico != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
	

}//fin VMFinalizarLapso
