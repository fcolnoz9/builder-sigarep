package sigarep.viewmodels.transacciones;

import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

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
	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	
	@Init
    public void init(){
		if (serviciolapsoacademico.encontrarLapsoActivo() == null)
			mensajesAlUsuario.ErrorLapsoActivoNoExistente();
		else
			lapsoAcademico = serviciolapsoacademico.encontrarLapsoActivo();
		
    }
	
	@Command
	public void finalizarLapso(){
		if (lapsoAcademico == null)
			mensajesAlUsuario.ErrorLapsoActivoNoExistente();
		else{	
			long apelacionesSinVeredicto = 0;
			long apelacionesSinSesion = 0;
			Date ultimaFechaCronograma = serviciocronograma.buscarUltimaFechaDelCronogramaActual();
			Date fechaActual = new Date();
			
			apelacionesSinVeredicto = serviciosolicitudapelacion.contarApelacionesSinVeredicto();
			apelacionesSinSesion = serviciosolicitudapelacion.contarApelacionesSinSesion();
			if (apelacionesSinVeredicto > 0)
				mensajesAlUsuario.ErrorFinalizarLapsoVeredicto();
			else if (apelacionesSinSesion > 0)
				mensajesAlUsuario.ErrorFinalizarLapsoSesion();
			else if (fechaActual.compareTo(ultimaFechaCronograma) < 0)
				mensajesAlUsuario.ErrorFinalizarLapsoCronograma();
			else{
				lapsoAcademico.setEstatus(false);
				serviciolapsoacademico.guardarLapso(lapsoAcademico);
				mensajesAlUsuario.informacionFinalizarLapsoExitoso();
			}
		}
	}
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}
	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

}
