package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import sigarep.herramientas.MensajesAlUsuario;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;

/**
 * Cronograma UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDescargas {

	@Command
	@NotifyChange({})
	public void modalPreguntasFrecuentes() {
		final Window window = (Window) Executions.createComponents(
				"/Modal/preguntasFrecuentes.zul", null, null);
		window.setMaximizable(true);
		window.doModal();
	}
	
	@Command
	@NotifyChange({})
	public void modalReglamento() {
		final Window window = (Window) Executions.createComponents(
				"/Modal/reglamentos.zul", null, null);
		window.setMaximizable(true);
		window.doModal();
	}
}
