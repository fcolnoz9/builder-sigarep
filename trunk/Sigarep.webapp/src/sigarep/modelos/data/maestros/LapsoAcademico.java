package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.Date;
import java.util.List;

/**as
 * The persistent class for the lapso_academico database table.
 * 
 */
@Entity
@Table(name = "lapso_academico")
public class LapsoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoLapso;
	private Date fechaInicio;
	private Date fechaCierre;
	private Boolean estatus;

	public LapsoAcademico(String codigoLapso, Date fechaCierre, Date fechaInicio,Boolean estatus) {
		super();
		this.codigoLapso = codigoLapso;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaCierre;
		this.estatus = estatus;

	}

	public LapsoAcademico() {
	}

	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "codigolapso", unique = true, nullable = false, length = 6)
	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

		@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cierre", nullable = false)
	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@Column(nullable = false)
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	
	// bi-directional many-to-one association to Cronograma
	@OneToMany(mappedBy = "lapsoAcademico")
	private List<Cronograma> cronogramas;

	// bi-directional many-to-one association to EstudianteSancionado
	@OneToMany(mappedBy = "lapsoAcademico")
	private List<EstudianteSancionado> estudianteSancionados;

		

	public List<Cronograma> getCronogramas() {
		return this.cronogramas;
	}

	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}

	public Cronograma addCronograma(Cronograma cronograma) {
		getCronogramas().add(cronograma);
		cronograma.setLapsoAcademico(this);

		return cronograma;
	}

	public Cronograma removeCronograma(Cronograma cronograma) {
		getCronogramas().remove(cronograma);
		cronograma.setLapsoAcademico(null);

		return cronograma;
	}

	public List<EstudianteSancionado> getEstudianteSancionados() {
		return this.estudianteSancionados;
	}

	public void setEstudianteSancionados(
			List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}

	public EstudianteSancionado addEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setLapsoAcademico(this);

		return estudianteSancionado;
	}

	public EstudianteSancionado removeEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setLapsoAcademico(null);

		return estudianteSancionado;
	}

}