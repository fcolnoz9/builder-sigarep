package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;

import java.util.List;


/**
 * The persistent class for the asignatura database table.
 * 
 */
@Entity
@Table(name="asignatura")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_asignatura", unique=true, nullable=false, length=8)
	private String codigoAsignatura;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_asignatura", nullable=false, length=60)
	private String nombreAsignatura;

	@Column(name="unidades_academicas", nullable=false)
	private Integer unidadesAcademicas;

	//bi-directional many-to-one association to ProgramaAcademico
	@ManyToOne
	@JoinColumn(name="id_programa", nullable=false)
	private ProgramaAcademico programaAcademico;

	//bi-directional many-to-one association to AsignaturaEstudianteSancionado
	@OneToMany(mappedBy="asignatura")
	private List<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados;

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

	public List<AsignaturaEstudianteSancionado> getAsignaturaEstudianteSancionados() {
		return this.asignaturaEstudianteSancionados;
	}

	public void setAsignaturaEstudianteSancionados(List<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados) {
		this.asignaturaEstudianteSancionados = asignaturaEstudianteSancionados;
	}

	public AsignaturaEstudianteSancionado addAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().add(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setAsignatura(this);

		return asignaturaEstudianteSancionado;
	}

	public AsignaturaEstudianteSancionado removeAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().remove(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setAsignatura(null);

		return asignaturaEstudianteSancionado;
	}

}