package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;

import java.sql.Time;
import java.util.Date;

/**
 * Cronograma de Actividades 
 * 
 * @author Equipo Builder 
 * @version 1.1
 * @since 10/02/14
 * @last 08/05/2014
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "cronograma")
public class Cronograma implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@EmbeddedId
	// Clave principal de la clase
	private CronogramaPK id;

	@Column(nullable = false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", nullable = false)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false)
	private Date fechaInicio;

	@Column(name = "hora_inicio", nullable = false)
	private Time horaInicio;

	@Column(length = 255)
	private String lugar;

	@Column(nullable = true, length = 255)
	private String observacion;
	
	// bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name = "id_actividad", nullable = false, insertable = false, updatable = false)
	private Actividad actividad;

	// bi-directional many-to-one association to LapsoAcademico
	@ManyToOne
	@JoinColumn(name = "codigo_lapso", nullable = false, insertable = false, updatable = false)
	private LapsoAcademico lapsoAcademico;

	// bi-directional many-to-one association to InstanciaApelada
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_instancia_apelada", nullable = true)
	private InstanciaApelada responsable;

	// constructor por defecto
	public Cronograma() {
	}
	
	/**
	 * Constructor Cronograma.
	 * 
	 * @param id,estatus,fechaFin,fechaInicio,horaInicio,lugar,observacion,
	 *        responsable
	 */
	public Cronograma(CronogramaPK id, Boolean estatus, Date fechaFin,
			Date fechaInicio, Time horaInicio, String lugar,
			String observacion, InstanciaApelada responsable) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.lugar = lugar;
		this.observacion = observacion;
		this.responsable = responsable;
	}

	//Métodos Set y Get
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

	public InstanciaApelada getResponsable() {
		return responsable;
	}

	public void setResponsable(InstanciaApelada responsable) {
		this.responsable = responsable;
	}
	//Fin Métodos Set y Get
}