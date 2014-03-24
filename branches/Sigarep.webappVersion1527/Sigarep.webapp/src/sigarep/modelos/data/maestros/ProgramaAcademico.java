package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase Programa academico Registra y Modifica un programa academico
 * 
 * @author BUILDER
 * @version 1
 * @since 15/12/2013
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "programa_academico")
public class ProgramaAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_programa", unique = true, nullable = false)
	private Integer idPrograma;

	@Column(name = "estatus_programa", nullable = false)
	private Boolean estatusPrograma;

	@Column(name = "nombre_programa", nullable = false, length = 60)
	private String nombrePrograma;

	// bi-directional many-to-one association to Asignatura
	@OneToMany(mappedBy = "programaAcademico", cascade = { CascadeType.ALL })
	private List<Asignatura> asignaturas = new LinkedList<Asignatura>();

	// bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy = "programaAcademico")
	private List<Estudiante> estudiantes = new LinkedList<Estudiante>();

	// constructor por defecto
	public ProgramaAcademico() {
	}

	/**
	 * Constructor ProgramaAcademico
	 * 
	 * @param idPrograma
	 *            , nombrePrograma, estatus
	 * @return Constructor lleno
	 */
	public ProgramaAcademico(Integer idPrograma, String nombrePrograma,
			Boolean estatus) {
		super();
		this.idPrograma = idPrograma;
		this.nombrePrograma = nombrePrograma;
		this.estatusPrograma = estatus;
	}

	// metodos set y get
	public Integer getIdPrograma() {
		return this.idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Boolean getEstatusPrograma() {
		return this.estatusPrograma;
	}

	public void setEstatusPrograma(Boolean estatusPrograma) {
		this.estatusPrograma = estatusPrograma;
	}

	public String getNombrePrograma() {
		return this.nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Asignatura addAsignatura(Asignatura asignatura) {
		getAsignaturas().add(asignatura);
		asignatura.setProgramaAcademico(this);

		return asignatura;
	}

	public Asignatura removeAsignatura(Asignatura asignatura) {
		getAsignaturas().remove(asignatura);
		asignatura.setProgramaAcademico(null);

		return asignatura;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setProgramaAcademico(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setProgramaAcademico(null);

		return estudiante;
	}

}