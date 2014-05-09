package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase ProgramaAcadémico
 * 
 * @author Equipo Builder 
 * @version 1
 * @since 15/12/2013
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name = "programa_academico")
public class ProgramaAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "id_programa", unique = true, nullable = false)
	private Integer idPrograma;

	@Column(name = "estatus_programa", nullable = false)
	private Boolean estatusPrograma;

	@Column(name = "nombre_programa", nullable = false, length = 60)
	private String nombrePrograma;

	// Relación bidireccional de muchos a uno, asociada a la clase Asignatura
	@OneToMany(mappedBy = "programaAcademico", cascade = { CascadeType.ALL })
	private List<Asignatura> asignaturas = new LinkedList<Asignatura>();

	// Relación bidireccional de muchos a uno, asociada a la clase Estudiante
	@OneToMany(mappedBy = "programaAcademico")
	private List<Estudiante> estudiantes = new LinkedList<Estudiante>();

	// constructor por defecto
	public ProgramaAcademico() {
	}

	/**
	 * Constructor ProgramaAcademico
	 * 
	 * @param idPrograma , nombrePrograma, estatus
	 */
	public ProgramaAcademico(Integer idPrograma, String nombrePrograma,
			Boolean estatus) {
		super();
		this.idPrograma = idPrograma;
		this.nombrePrograma = nombrePrograma;
		this.estatusPrograma = estatus;
	}

	//Métodos set y get
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

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	// Fin Métodos set y get
	/**
	 * Relación de la clase ProgramaAcademico con la clase Asignatura, Agregar
	 * Asignatura
	 * 
	 * @see Asignatura
	 * @param asignatura
	 * @return asignatura
	 */
	public Asignatura addAsignatura(Asignatura asignatura) {
		getAsignaturas().add(asignatura);
		asignatura.setProgramaAcademico(this);
		return asignatura;
	}

	/**
	 * Relación de la clase ProgramaAcademico con la clase Asignatura, Quitar
	 * Asignatura
	 * 
	 * @see Asignatura
	 * @param asignatura
	 * @return asignatura
	 */
	public Asignatura removeAsignatura(Asignatura asignatura) {
		getAsignaturas().remove(asignatura);
		asignatura.setProgramaAcademico(null);
		return asignatura;
	}

	/**
	 * Relación de la clase ProgramaAcademico con la clase Estudiante, Agregar
	 * Estudiante
	 * 
	 * @see Estudiante
	 * @param estudiante
	 * @return estudiante
	 */
	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setProgramaAcademico(this);
		return estudiante;
	}
	/**
	 * Relación de la clase ProgramaAcademico con la clase Estudiante, Quitar
	 * Estudiante
	 * 
	 * @see Estudiante
	 * @param estudiante
	 * @return estudiante
	 */
	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setProgramaAcademico(null);
		return estudiante;
	}

}