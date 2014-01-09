package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.InstanciaApelada;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the solicitud_apelacion database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="solicitud_apelacion")
public class SolicitudApelacion implements Comparable<SolicitudApelacion>,Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudApelacionPK id;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_solicitud", nullable=false)
	private Date fechaSolicitud;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_veredicto")
	private Date fechaVeredicto;

	@Column(name="numero_sesion", length=15)
	private String numeroSesion;

	@Column(length=255)
	private String observacion;
	
	@Column(name="numero_caso", nullable=true)
	private Integer numeroCaso;

	//bi-directional many-to-one association to ApelacionEstadoApelacion
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="solicitudApelacion")
	private Set<ApelacionEstadoApelacion> apelacionEstadosApelacion = new HashSet<ApelacionEstadoApelacion>();

	//bi-directional many-to-one association to Motivo
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,mappedBy="solicitudApelacion")
	private Set<Motivo> motivos = new HashSet<Motivo>();
	
	@Column(length=60)
	private String veredicto;

	//bi-directional many-to-one association to EstudianteSancionado
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false)
		})
	private EstudianteSancionado estudianteSancionado;

	//bi-directional many-to-one association to InstanciaApelada
	@ManyToOne
	@JoinColumn(name="id_instancia_apelada", nullable=false, insertable=false, updatable=false)
	private InstanciaApelada instanciaApelada;

	public SolicitudApelacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolicitudApelacionPK getId() {
		return this.id;
	}

	public void setId(SolicitudApelacionPK id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaVeredicto() {
		return this.fechaVeredicto;
	}

	public void setFechaVeredicto(Date fechaVeredicto) {
		this.fechaVeredicto = fechaVeredicto;
	}

	public String getNumeroSesion() {
		return this.numeroSesion;
	}

	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getVeredicto() {
		return this.veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public Set<ApelacionEstadoApelacion> getApelacionEstadosApelacion() {
		return this.apelacionEstadosApelacion;
	}

	public void setApelacionEstadosApelacion(Set<ApelacionEstadoApelacion> apelacionEstadosApelacion) {
		this.apelacionEstadosApelacion = apelacionEstadosApelacion;
	}

	public ApelacionEstadoApelacion addApelacionEstadosApelacion(ApelacionEstadoApelacion apelacionEstadoApelacion) {
		getApelacionEstadosApelacion().add(apelacionEstadoApelacion);
		apelacionEstadoApelacion.setSolicitudApelacion(this);

		return apelacionEstadoApelacion;
	}

	public ApelacionEstadoApelacion removeApelacionEstadosApelacion(ApelacionEstadoApelacion apelacionEstadoApelacion) {
		getApelacionEstadosApelacion().remove(apelacionEstadoApelacion);
		apelacionEstadoApelacion.setSolicitudApelacion(null);

		return apelacionEstadoApelacion;
	}

	public Set<Motivo> getMotivos() {
		return this.motivos;
	}

	public void setMotivos(Set<Motivo> motivos) {
		this.motivos = motivos;
	}

	public Motivo addMotivo(Motivo motivo) {
		getMotivos().add(motivo);
		motivo.setSolicitudApelacion(this);

		return motivo;
	}

	public Motivo removeMotivo(Motivo motivo) {
		getMotivos().remove(motivo);
		motivo.setSolicitudApelacion(null);

		return motivo;
	}

	public EstudianteSancionado getEstudianteSancionado() {
		return this.estudianteSancionado;
	}

	public void setEstudianteSancionado(EstudianteSancionado estudianteSancionado) {
		this.estudianteSancionado = estudianteSancionado;
	}

	public InstanciaApelada getInstanciaApelada() {
		return this.instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	@Override
	public int compareTo(SolicitudApelacion arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Integer getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(Integer numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

}