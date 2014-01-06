package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the estudiante database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="estudiante")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cedulaEstudiante;
	private Date anioIngreso;
	private String email;
	private Boolean estatus;
	private Date fechaNacimiento;
	private String primerApellido;
	private String primerNombre;
	private String segundoApellido;
	private String segundoNombre;
	private String sexo;
	private String telefono;
	
	public Estudiante(String cedulaEstudiante, Date anioIngreso, String email,
			Boolean estatus, Date fechaNacimiento, String primerApellido,
			String primerNombre, String segundoApellido, String segundoNombre,
			String sexo, String telefono, ProgramaAcademico programaAcademico,
			List<EstudianteSancionado> estudianteSancionados) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.anioIngreso = anioIngreso;
		this.email = email;
		this.estatus = estatus;
		this.fechaNacimiento = fechaNacimiento;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.programaAcademico = programaAcademico;
		this.estudianteSancionados = estudianteSancionados;
	}

	public Estudiante() {
	}
	//bi-directional many-to-one association to ProgramaAcademico
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_programa", nullable=false)
	private ProgramaAcademico programaAcademico;

	//bi-directional many-to-one association to EstudianteSancionado
	@OneToMany(mappedBy="estudiante",cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<EstudianteSancionado> estudianteSancionados = new LinkedList<EstudianteSancionado>();


     
	@Id
	@Column(name="cedula_estudiante", unique=true, nullable=false, length=8)
	public String getCedulaEstudiante() {
		return this.cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="anio_ingreso", nullable=false)
	public Date getAnioIngreso() {
		return this.anioIngreso;
	}

	public void setAnioIngreso(Date anioIngreso) {
		this.anioIngreso = anioIngreso;
	}
	@Column(name="email",length=50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="estatus",nullable=false)
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento", nullable=false)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@Column(name="primer_apellido", nullable=false, length=30)
	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	@Column(name="primer_nombre", nullable=false, length=30)
	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	@Column(name="segundo_apellido", length=30)
	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	@Column(name="segundo_nombre", length=30)
	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	@Column(name="sexo",nullable=false, length=1)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	@Column(name="telefono",length=11)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ProgramaAcademico getProgramaAcademico() {
		return this.programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
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

}