package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;

import java.util.Set;

/**
 * Clase Asignatura
 * 
 * @author BUILDER
 * @version 1.0
 * @since 18/12/2013
 */

@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name = "asignatura")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "codigo_asignatura", unique = true, nullable = false, length = 8)
	private String codigoAsignatura;

	@Column(nullable = false)
	private Boolean estatus;

	@Column(name = "nombre_asignatura", nullable = false, length = 60)
	private String nombreAsignatura;

	@Column(name = "unidades_academicas", nullable = false)
	private Integer unidadesAcademicas;

	// Relación bidireccional de muchos a uno, asociada a la clase
	// ProgramaAcademico
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_programa", nullable = false)
	private ProgramaAcademico programaAcademico;

	// Relación bidireccional de muchos a uno, asociada a la clase
	// AsignaturaEstudianteSancionado
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "asignatura", cascade={CascadeType.ALL})
	private Set<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados;

	/**
	 * Constructor Asignatura
	 * 
	 * @param codigoAsignatura
	 *            , estatus, nombreAsignatura, unidadesAcademicas,
	 *            programaAcademico
	 * @return Constructor lleno
	 */
	public Asignatura(String codigoAsignatura, Boolean estatus,
			String nombreAsignatura, Integer unidadesAcademicas,
			ProgramaAcademico programaAcademico) {
		super();
		this.codigoAsignatura = codigoAsignatura;
		this.estatus = estatus;
		this.nombreAsignatura = nombreAsignatura;
		this.unidadesAcademicas = unidadesAcademicas;
		this.programaAcademico = programaAcademico;
	}

	// Métodos Set y Get
	public Asignatura() {
	}

	public String getCodigoAsignatura() {
		return this.codigoAsignatura;
	}

	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreAsignatura() {
		return this.nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public Integer getUnidadesAcademicas() {
		return this.unidadesAcademicas;
	}

	public void setUnidadesAcademicas(Integer unidadesAcademicas) {
		this.unidadesAcademicas = unidadesAcademicas;
	}

	public ProgramaAcademico getProgramaAcademico() {
		return this.programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
	}

	public Set<AsignaturaEstudianteSancionado> getAsignaturaEstudianteSancionados() {
		return this.asignaturaEstudianteSancionados;
	}

	public void setAsignaturaEstudianteSancionados(
			Set<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados) {
		this.asignaturaEstudianteSancionados = asignaturaEstudianteSancionados;
	}
	// Fin Métodos Set y Get

	/**
	 * Relación de la clase Asignatura con la clase
	 * asignaturaEstudianteSancionado, Agregar asignaturaEstudianteSancionado
	 * 
	 * @see AsignaturaEstudianteSancionado
	 * @param asignaturaEstudianteSancionado
	 * @return asignaturaEstudianteSancionado
	 */
	public AsignaturaEstudianteSancionado addAsignaturaEstudianteSancionado(
			AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados()
				.add(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setAsignatura(this);

		return asignaturaEstudianteSancionado;
	}

	/**
	 * Relación de la clase Asignatura con la clase
	 * asignaturaEstudianteSancionado, Quitar asignaturaEstudianteSancionado
	 * 
	 * @see AsignaturaEstudianteSancionado
	 * @param asignaturaEstudianteSancionado
	 * @return asignaturaEstudianteSancionado
	 */
	public AsignaturaEstudianteSancionado removeAsignaturaEstudianteSancionado(
			AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().remove(
				asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setAsignatura(null);

		return asignaturaEstudianteSancionado;
	}
	
}//Fin Clase Asignatura