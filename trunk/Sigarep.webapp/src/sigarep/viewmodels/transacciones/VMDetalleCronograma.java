package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import sigarep.modelos.data.transacciones.Cronograma;

/** DetalleCronograma
 * Contiene métodos necesarios para la presentacion de la información 
 * detallada de las actividades del cronograma.
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 22/01/14
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleCronograma {

	@Wire("#modalDialog")
	private Window window;
	private String nombreActividad;
	private String descripcionActividad;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Time hora_inicio;
	private String observacion;
	private String lugar;
	private Cronograma cronogramaSeleccionado;

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Cronograma getCronograma() {
		return cronogramaSeleccionado;
	}

	public void setCronograma(Cronograma cronograma) {
		cronogramaSeleccionado = cronograma;
	}

	/**
	 * init
	 * 
	 * @param recibe
	 *            la clase cronogrmaSeleccionado
	 * @return muestra el detalle de la actividad seleccionada en una lista.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cronogramaSeleccionado") Cronograma v1) {
		Selectors.wireComponents(view, this, false);
		this.cronogramaSeleccionado = v1;
		this.fecha_inicio = cronogramaSeleccionado.getFechaInicio();
		this.fecha_fin = cronogramaSeleccionado.getFechaFin();
		this.hora_inicio = cronogramaSeleccionado.getHoraInicio();
		this.nombreActividad = cronogramaSeleccionado.getActividad()
				.getNombre();
		this.descripcionActividad = cronogramaSeleccionado.getActividad()
				.getDescripcion();
		this.lugar = cronogramaSeleccionado.getLugar();
		this.observacion = cronogramaSeleccionado.getObservacion();
	}
}
