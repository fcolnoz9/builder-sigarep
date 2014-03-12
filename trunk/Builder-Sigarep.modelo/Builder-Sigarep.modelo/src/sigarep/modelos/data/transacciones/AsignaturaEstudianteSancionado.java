package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Asignatura;

/**
 * Clase Asignatura Estudiante Sancionado, trae los objetos de esta clase
 * compuesta por varias claves principales y los atributos propios de la clase
 * 
 * @author BUILDER
 * @version 1
 * @since 03/01/2014
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "asignatura_estudiante_sancionado")
public class AsignaturaEstudianteSancionado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsignaturaEstudianteSancionadoPK id;

	@Column(name = "condicion_asignatura", nullable = false)
	private Integer condicionAsignatura;

	// bi-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name = "codigo_asignatura", nullable = false, insertable = false, updatable = false)
	private Asignatura asignatura;

	// bi-directional many-to-one association to EstudianteSancionado
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "cedula_estudiante", referencedColumnName = "cedula_estudiante", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "codigo_lapso", referencedColumnName = "codigo_lapso", nullable = false, insertable = false, updatable = false) })
	private EstudianteSancionado estudianteSancionado;

	// constructor por defecto
	public AsignaturaEstudianteSancionado() {
	}

	// Constructor con parametros
	public AsignaturaEstudianteSancionado(AsignaturaEstudianteSancionadoPK id,
			Integer condicionAsignatura, Asignatura asignatura,
			EstudianteSancionado estudianteSancionado) {
		super();
		this.id = id;
		this.condicionAsignatura = condicionAsignatura;
		this.asignatura = asignatura;
		this.estudianteSancionado = estudianteSancionado;
	}

	//metodos set y get
	public AsignaturaEstudianteSancionadoPK getId() {
		return this.id;
	}

	public void setId(AsignaturaEstudianteSancionadoPK id) {
		this.id = id;
	}

	public Integer getCondicionAsignatura() {
		return this.condicionAsignatura;
	}

	public void setCondicionAsignatura(Integer condicionAsignatura) {
		this.condicionAsignatura = condicionAsignatura;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public EstudianteSancionado getEstudianteSancionado() {
		return this.estudianteSancionado;
	}

	public void setEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		this.estudianteSancionado = estudianteSancionado;
	}

}