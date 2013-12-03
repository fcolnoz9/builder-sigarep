package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.LapsoAcademico;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the cronograma database table.
 * 
 */
@Entity
@Table(name="cronograma")
public class Cronograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CronogramaPK id;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin", nullable=false)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio", nullable=false)
	private Date fechaInicio;

	@Column(name="hora_inicio", nullable=false)
	private Time horaInicio;

	@Column(length=255)
	private String lugar;

	@Column(nullable=false, length=255)
	private String observacion;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="id_actividad", nullable=false, insertable=false, updatable=false)
	private Actividad actividad;

	//bi-directional many-to-one association to LapsoAcademico
	@ManyToOne
	@JoinColumn(name="codigo_lapso", nullable=false, insertable=false, updatable=false)
	private LapsoAcademico lapsoAcademico;

	public Cronograma() {
	}

	public CronogramaPK getId() {
		return this.id;
	}

	public void setId(CronogramaPK id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public LapsoAcademico getLapsoAcademico() {
		return this.lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

}