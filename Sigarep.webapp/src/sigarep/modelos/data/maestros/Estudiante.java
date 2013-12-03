package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estudiantes database table.
 * 
 */
@Entity
@Table(name="estudiantes")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cedula_estudiante", unique=true, nullable=false, length=8)
	private String cedulaEstudiante;

	@Temporal(TemporalType.DATE)
	@Column(name="anio_ingreso", nullable=false)
	private Date anioIngreso;

	@Column(length=50)
	private String email;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento", nullable=false)
	private Date fechaNacimiento;

	@Column(name="primer_apellido", nullable=false, length=30)
	private String primerApellido;

	@Column(name="primer_nombre", nullable=false, length=30)
	private String primerNombre;

	@Column(name="segundo_apellido", length=30)
	private String segundoApellido;

	@Column(name="segundo_nombre", length=30)
	private String segundoNombre;

	@Column(length=11)
	private String telefono;

	//bi-directional many-to-one association to EstudianteSancionado
	@OneToMany(mappedBy="estudiante")
	private List<EstudianteSancionado> estudianteSancionados;

	//bi-directional many-to-one association to ProgramaAcademico
	@ManyToOne
	@JoinColumn(name="id_programa", nullable=false)
	private ProgramaAcademico programaAcademico;

	public Estudiante() {
	}

	public String getCedulaEstudiante() {
		return this.cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public Date getAnioIngreso() {
		return this.anioIngreso;
	}

	public void setAnioIngreso(Date anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<EstudianteSancionado> getEstudianteSancionados() {
		return this.estudianteSancionados;
	}

	public void setEstudianteSancionados(List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}

	public EstudianteSancionado addEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setEstudiante(this);

		return estudianteSancionado;
	}

	public EstudianteSancionado removeEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setEstudiante(null);

		return estudianteSancionado;
	}

	public ProgramaAcademico getProgramaAcademico() {
		return this.programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
	}

}