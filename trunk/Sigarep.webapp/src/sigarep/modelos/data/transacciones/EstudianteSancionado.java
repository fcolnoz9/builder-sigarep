package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;


/**
 * The persistent class for the estudiante_sancionado database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="estudiante_sancionado")
public class EstudianteSancionado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstudianteSancionadoPK id;

	@Column(name="indice_grado", nullable=false)
	private float indiceGrado;

	@Column(name="lapsos_academicos_rp", length=15)
	private String lapsosAcademicosRp;

	@Column(nullable=false)
	private Integer semestre;

	@Column(name="unidades_aprobadas", nullable=false)
	private Integer unidadesAprobadas;

	@Column(name="unidades_cursadas", nullable=false)
	private Integer unidadesCursadas;
	
	@Column(name="estatus", nullable=true)
	private boolean estatus;

	//bi-directional many-to-one association to AsignaturaEstudianteSancionado
	@OneToMany(mappedBy="estudianteSancionado")
	private List<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados = new LinkedList<AsignaturaEstudianteSancionado>();

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="cedula_estudiante", nullable=false, insertable=false, updatable=false)
	private Estudiante estudiante = new Estudiante();

	//bi-directional many-to-one association to LapsoAcademico
	@ManyToOne
	@JoinColumn(name="codigo_lapso", nullable=false, insertable=false, updatable=false)
	private LapsoAcademico lapsoAcademico;

	//bi-directional many-to-one association to SancionMaestro
	@ManyToOne
	@JoinColumn(name="id_sancion", nullable=false)
	private SancionMaestro sancionMaestro;

	//bi-directional many-to-one association to SolicitudApelacion
	@OneToMany(mappedBy="estudianteSancionado")
	private List<SolicitudApelacion> solicitudApelacions = new LinkedList<SolicitudApelacion>();
		
	public EstudianteSancionado() {
	}
		
	public EstudianteSancionado(EstudianteSancionadoPK id, float indiceGrado,
			String lapsosAcademicosRp, Integer semestre,
			Integer unidadesAprobadas, Integer unidadesCursadas,
			Estudiante estudiante, LapsoAcademico lapsoAcademico,
			SancionMaestro sancionMaestro,boolean estatus) {
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
		this.estatus=estatus;
	}

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

	public List<AsignaturaEstudianteSancionado> getAsignaturaEstudianteSancionados() {
		return this.asignaturaEstudianteSancionados;
	}

	public void setAsignaturaEstudianteSancionados(List<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados) {
		this.asignaturaEstudianteSancionados = asignaturaEstudianteSancionados;
	}

	public AsignaturaEstudianteSancionado addAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().add(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setEstudianteSancionado(this);

		return asignaturaEstudianteSancionado;
	}

	public AsignaturaEstudianteSancionado removeAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado) {
		getAsignaturaEstudianteSancionados().remove(asignaturaEstudianteSancionado);
		asignaturaEstudianteSancionado.setEstudianteSancionado(null);

		return asignaturaEstudianteSancionado;
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

	public void setSolicitudApelacions(List<SolicitudApelacion> solicitudApelacions) {
		this.solicitudApelacions = solicitudApelacions;
	}

	public SolicitudApelacion addSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().add(solicitudApelacion);
		solicitudApelacion.setEstudianteSancionado(this);
		return solicitudApelacion;
	}

	public SolicitudApelacion removeSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		getSolicitudApelacions().remove(solicitudApelacion);
		solicitudApelacion.setEstudianteSancionado(null);

		return solicitudApelacion;
	}
}