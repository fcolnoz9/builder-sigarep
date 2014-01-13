package sigarep.viewmodels.transacciones;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.mensajes;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialCronograma {

	@WireVariable ServicioCronograma serviciocronograma;
	private Date fechaInicio;
	private Date fechaFin;
	private Time horaInicio;
	private String lugar;
	private String observacion;
	@WireVariable
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	@WireVariable
	private Actividad actividad = new Actividad();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private List<Cronograma> listaCronogramas;
	@WireVariable
	private List<LapsoAcademico> listaLapsoAcademico;
	@WireVariable
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo

	// Metodos GETS Y SETS

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
    //Fin de los metodod gets y sets
	
    // OTROS METODOS
	
	@Init
	public void init(){
        //initialization code
		buscarLapsoAcademico();
    }

	@Command
	@NotifyChange({"listaLapsoAcademico"})
	public void buscarLapsoAcademico(){
		listaLapsoAcademico =serviciolapsoacademico.buscarTodosLosLapsos();
	}
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "listaCronogramas", "listaLapsoAcademico", "lapsoAcademico"})
	public void buscarDetalleLapsoAcademicoCombo() {
		listaCronogramas = serviciocronograma.buscarTodosCronogramas(lapsoAcademico.getCodigoLapso());
	}
//Fin de los otros metodos.
}
