package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Clase estudiante sancionado, trae los objetos de esta clase compuesta por
 * varias claves principales y atributos propios
 * 
 * @author BUILDER
 * @version 1
 * @since 03/01/2014
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "estudiante_sancionado")
public class EstudianteSancionado implements Serializable {
	private static final long serialVersionUID = 1L;
	// declaracion por defecto de id, requerida para las clases serializables.
	// Atributos de la clase
	@EmbeddedId
	// Clave principal de la clase
	private EstudianteSancionadoPK id;

	@Column(name = "indice_grado", nullable = false)
	private float indiceGrado;

	@Column(name = "lapsos_academicos_rp", length = 15)
	private String lapsosAcademicosRp;

	@Column(nullable = false)
	private Integer semestre;

	@Column(name = "unidades_aprobadas", nullable = false)
	private Integer unidadesAprobadas;

	@Column(name = "unidades_cursadas", nullable = false)
	private Integer unidadesCursadas;

	@Column(name = "periodo_sacion", nullable = true)
	private Integer periodoSancion;

	@Column(name = "estatus", nullable = true)
	private boolean estatus;

	// bi-directional many-to-one association to AsignaturaEstudianteSancionado
	@OneToMany(fetch = FetchType.EAGER, mappedBy="estudianteSancionado", cascade={CascadeType.ALL})
	private Set<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados = new HashSet<AsignaturaEstudianteSancionado>();

	// bi-directional many-to-one association to Estudiante	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false)
	})
	private Estudiante estudiante = new Estudiante();

	
	
	// bi-directional many-to-one association to LapsoAcademico
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false)
	})
	private LapsoAcademico lapsoAcademico;

	// bi-directional many-to-one association to SancionMaestro
	@ManyToOne
	@JoinColumn(name = "id_sancion", nullable = false)
	private SancionMaestro sancionMaestro;

	// bi-directional many-to-one association to SolicitudApelacion
	@OneToMany(mappedBy = "estudianteSancionado")
	private List<SolicitudApelacion> solicitudApelacions = new LinkedList<SolicitudApelacion>();

	// constructor por defecto
	public EstudianteSancionado() {
	}

	/**
	 * Constructor EstudianteSancionado
	 * 
	 * @param id, indiceGrado, lapsosAcademicosRp, semestre,
	 *        unidadesAprobadas, unidadesCursadas, estudiante,
	 *        lapsoAcademico, sancionMaestro, estatus, periodoSancion
	 */
	public EstudianteSancionado(EstudianteSancionadoPK id, float indiceGrado,
			String lapsosAcademicosRp, Integer semestre,
			Integer unidadesAprobadas, Integer unidadesCursadas,
			Estudiante estudiante, LapsoAcademico lapsoAcademico,
			SancionMaestro sancionMaestro, boolean estatus,
			Integer periodoSancion) {
		super();
		this.id = id;
		this.indiceGrado = indiceGrado;
		this.lapsosAcademicosRp = lapsosAcademicosRp;
		this.semestre = semestre;
		this.unidadesAprobadas = unidadesAprobadas;
		this.unidadesCursadas = unidadesCursadas;
		this.estudiante = estudiante;
		this.lapsoAcademico = lapsoAcademico;
		this.sancionMaestro = sancionMaestro;
		this.estatus = estatus;
		this.periodoSancion = periodoSancion;
	}

	// metodos set y get
	public EstudianteSancionadoPK getId() {
		return this.id;
	}

	public void setId(EstudianteSancionadoPK id) {
		this.id = id;
	}

	public float getIndiceGrado() {
		return this.indiceGrado;
	}

	public void setIndiceGrado(float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}

	public String getLapsosAcademicosRp() {
		return this.lapsosAcademicosRp;
	}

	public void setLapsosAcademicosRp(String lapsosAcademicosRp) {
		this.lapsosAcademicosRp = lapsosAcademicosRp;
	}

	public Integer getSemestre() {
		return this.semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getUnidadesAprobadas() {
		return this.unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public Integer getUnidadesCursadas() {
		return this.unidadesCursadas;
	}

	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public Set<AsignaturaEstudianteSancionado> getAsignaturaEstudianteSancionados() {
		return this.asignaturaEstudianteSancionados;
	}

	public void setAsignaturaEstudianteSancionados(
			Set<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados) {
		this.asignaturaEstudianteSancionados = asignaturaEstudianteSancionados;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public LapsoAcademico getLapsoAcademico() {
		return this.lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public SancionMaestro getSancionMaestro() {
		return this.sancionMaestro;
	}

	public void setSancionMaestro(SancionMaestro sancionMaestro) {
		this.sancionMaestro = sancionMaestro;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public List<SolicitudApelacion> getSolicitudApelacions() {
		return this.solicitudApelacions;
	}

	public void setSolicitudApelacions(
			List<SolicitudApelacion> solicitudApelacions) {
		this.solicitudApelacions = solicitudApelacions;
	}

	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}
	// Fin metodos set y get
	
	/**
	 * Relación de la clase EstudianteSancionado con la clase AsignaturaEstudianteSancionado, Agregar AsignaturaEstudianteSancionado
	 * 
	 * @see AsignaturaEstudianteSancionado
	 * @param AsignaturaEstudianteSancionado
	 * @return AsignaturaEstudianteSancionado
	 */
	public AsignaturaEstudianteSancionado addAsignaturaEstudianteSancionado(
			AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados()
				.add(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setEstudianteSancionado(this);
		return asignaturaEstudianteSancionado;
	}
	/**
	 * Relación de la clase EstudianteSancionado con la clase AsignaturaEstudianteSancionado, Quitar AsignaturaEstudianteSancionado
	 * 
	 * @see AsignaturaEstudianteSancionado
	 * @param AsignaturaEstudianteSancionado
	 * @return AsignaturaEstudianteSancionado
	 */
	public AsignaturaEstudianteSancionado removeAsignaturaEstudianteSancionado(
			AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().remove(
				asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setEstudianteSancionado(null);
		return asignaturaEstudianteSancionado;
	}
	/**
	 * Relación de la clase EstudianteSancionado con la clase SolicitudApelacion, Agregar SolicitudApelacion
	 * 
	 * @see SolicitudApelacion
	 * @param SolicitudApelacion
	 * @return SolicitudApelacion
	 */
	public SolicitudApelacion addSolicitudApelacion(
			SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().add(solicitudApelacion);
		solicitudApelacion.setEstudianteSancionado(this);
		return solicitudApelacion;
	}
	/**
	 * Relación de la clase EstudianteSancionado con la clase SolicitudApelacion, Quitar SolicitudApelacion
	 * 
	 * @see SolicitudApelacion
	 * @param SolicitudApelacion
	 * @return SolicitudApelacion
	 */
	public SolicitudApelacion removeSolicitudApelacion(
			SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().remove(solicitudApelacion);
		solicitudApelacion.setEstudianteSancionado(null);
		return solicitudApelacion;
	}
}