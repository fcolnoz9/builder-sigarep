package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase LapsoAcadémico
 * 
 * @author BUILDER
 * @version 1.0
 * @since 20/12/13
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
// Anotación indica que el JavaBean es una entidad persistente
@Table(name = "lapso_academico")
public class LapsoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "codigo_lapso", unique = true, nullable = false, length = 6)
	private String codigoLapso;

	@Column(nullable = false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cierre", nullable = false)
	private Date fechaCierre;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false)
	private Date fechaInicio;

	// Relación bidireccional de muchos a uno, asociada a la clase Cronograma
	@OneToMany(mappedBy = "lapsoAcademico")
	private List<Cronograma> cronogramas = new LinkedList<Cronograma>();

	// Relación bidireccional de muchos a uno, asociada a la clase
	// EstudianteSancionado
	@OneToMany(mappedBy = "lapsoAcademico")
	private List<EstudianteSancionado> estudianteSancionados = new LinkedList<EstudianteSancionado>();

	// Constructor por defecto
	public LapsoAcademico() {
	}

	/**
	 * Constructor LapsoAcademico
	 * 
	 * @param codigoLapso
	 *            , fechaInicio, fechaCierre, estatus
	 * @return Constructor lleno
	 */
	public LapsoAcademico(String codigoLapso, Date fechaInicio,
			Date fechaCierre, Boolean estatus) {
		super();
		this.codigoLapso = codigoLapso;
		this.estatus = estatus;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaCierre;

	}

	// Métodos Set y Get
	public String getCodigoLapso() {
		return this.codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<Cronograma> getCronogramas() {
		return this.cronogramas;
	}

	public void setCronogramas(List<Cronograma> cronogramas) {
		this.cronogramas = cronogramas;
	}

	public List<EstudianteSancionado> getEstudianteSancionados() {
		return this.estudianteSancionados;
	}
	
	public void setEstudianteSancionados(
			List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}
	// Fin Métodos Set y Get
	
	/**
	 * Relación de la clase LapsoAcademico con la clase Cronograma, Agregar
	 * Cronograma
	 * 
	 * @see Cronograma
	 * @param cronograma
	 * @return cronograma
	 */
	public Cronograma addCronograma(Cronograma cronograma) {
		getCronogramas().add(cronograma);
		cronograma.setLapsoAcademico(this);
		return cronograma;
	}

	/**
	 * Relación de la clase LapsoAcademico con la clase Cronograma, Quitar
	 * Cronograma
	 * 
	 * @see Cronograma
	 * @param cronograma
	 * @return cronograma
	 */
	public Cronograma removeCronograma(Cronograma cronograma) {
		getCronogramas().remove(cronograma);
		cronograma.setLapsoAcademico(null);
		return cronograma;
	}
	
	/**
	 * Relación de la clase LapsoAcademico con la clase EstudianteSancionado,
	 * Agregar EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param estudianteSancionado
	 * @return estudianteSancionado
	 */
	public EstudianteSancionado addEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setLapsoAcademico(this);

		return estudianteSancionado;
	}

	/**
	 * Relación de la clase LapsoAcademico con la clase EstudianteSancionado,
	 * Quitar EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param estudianteSancionado
	 * @return estudianteSancionado
	 */
	public EstudianteSancionado removeEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setLapsoAcademico(null);
		return estudianteSancionado;
	}
}//Fin Clase LapsoAcademico