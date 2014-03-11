package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase Estudiante
 * 
 * @author BUILDER
 * @version 1
 * @since 10/12/2013
 */
@Entity
@Access(AccessType.FIELD)
// anotación indica que el JavaBean es una entidad persistente
@Table(name = "estudiante")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "cedula_estudiante", unique = true, nullable = false, length = 8)
	private String cedulaEstudiante;

	@Temporal(TemporalType.DATE)
	@Column(name = "anio_ingreso", nullable = false)
	private Date anioIngreso;

	@Column(length = 50)
	private String email;

	@Column(nullable = false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;

	@Column(name = "primer_apellido", nullable = false, length = 30)
	private String primerApellido;

	@Column(name = "primer_nombre", nullable = false, length = 30)
	private String primerNombre;

	@Column(name = "segundo_apellido", length = 30)
	private String segundoApellido;

	@Column(name = "segundo_nombre", length = 30)
	private String segundoNombre;

	@Column(nullable = false, length = 1)
	private String sexo;

	@Column(length = 11)
	private String telefono;

	// Relación bidireccional de muchos a uno, asociada a la clase Usuario
	@ManyToOne
	@JoinColumn(name = "nombreusuario", nullable = true)
	private Usuario nombreUsuario;

	// Relación bidireccional de muchos a uno, asociada a la
	// claseProgramaAcademico
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_programa", nullable = false)
	private ProgramaAcademico programaAcademico;

	// Relación bidireccional de muchos a uno, asociada a la clase
	// EstudianteSancionado
	@OneToMany(mappedBy = "estudiante", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<EstudianteSancionado> estudianteSancionados = new LinkedList<EstudianteSancionado>();

	// constructorpor defecto
	public Estudiante() {
		super();
	}

	/**
	 * Constructor Estudiante
	 * 
	 * @param cedulaEstudiante
	 *            , anioIngreso, email, estatus, fechaNacimiento,
	 *            primerApellido, primerNombre, segundoApellido, segundoNombre,
	 *            sexo, telefono, programaAcademico
	 * @return Constructor lleno
	 */
	public Estudiante(String cedulaEstudiante, Date anioIngreso, String email,
			Boolean estatus, Date fechaNacimiento, String primerApellido,
			String primerNombre, String segundoApellido, String segundoNombre,
			String sexo, String telefono, ProgramaAcademico programaAcademico) {
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
	}

	// metodos set y get
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

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

	public void setEstudianteSancionados(
			List<EstudianteSancionado> estudianteSancionados) {
		this.estudianteSancionados = estudianteSancionados;
	}

	/**
	 * Relación de la clase Estudiante con la clase EstudianteSancionado,
	 * Agregar EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param estudianteSancionado
	 * @return estudianteSancionado
	 */
	public EstudianteSancionado addEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().add(estudianteSancionado);
		estudianteSancionado.setEstudiante(this);

		return estudianteSancionado;
	}

	/**
	 * Relación de la clase Estudiante con la clase EstudianteSancionado, Quitar
	 * EstudianteSancionado
	 * 
	 * @see EstudianteSancionado
	 * @param estudianteSancionado
	 * @return estudianteSancionado
	 */
	public EstudianteSancionado removeEstudianteSancionado(
			EstudianteSancionado estudianteSancionado) {
		getEstudianteSancionados().remove(estudianteSancionado);
		estudianteSancionado.setEstudiante(null);

		return estudianteSancionado;
	}

	public Usuario getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(Usuario nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}