package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;
import sigarep.modelos.data.maestros.EstadoApelacion;
import java.util.Date;

/**
 * Clase Apelacion Apelacion, trae los objetos de esta clase compuesta por
 * varias claves foraneas y atributos propios de la clase
 * 
 * @author BUILDER
 * @version 1
 * @since 03/01/2014
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "apelacion_estado_apelacion")
public class ApelacionEstadoApelacion implements Serializable {
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@EmbeddedId
	// Clave principal de la clase
	private ApelacionEstadoApelacionPK id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_estado", nullable = false)
	private Date fechaEstado;

	@Column(name = "observacion", nullable = true, length = 255)
	private String observacion;

	@Column(name = "sugerencia", nullable = true, length = 30)
	private String sugerencia;

	// bi-directional many-to-one association to EstadoApelacion
	@ManyToOne
	@JoinColumn(name = "id_estado_apelacion", nullable = false, insertable = false, updatable = false)
	private EstadoApelacion estadoApelacion;

	// bi-directional many-to-one association to SolicitudApelacion
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "cedula_estudiante", referencedColumnName = "cedula_estudiante", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "codigo_lapso", referencedColumnName = "codigo_lapso", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "id_instancia_apelada", referencedColumnName = "id_instancia_apelada", nullable = false, insertable = false, updatable = false) })
	private SolicitudApelacion solicitudApelacion;

	public ApelacionEstadoApelacion() {
	}

	// Metodos set y get
	public ApelacionEstadoApelacionPK getId() {
		return this.id;
	}

	public void setId(ApelacionEstadoApelacionPK id) {
		this.id = id;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return this.solicitudApelacion;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public EstadoApelacion getEstadoApelacion() {
		return estadoApelacion;
	}

	public void setEstadoApelacion(EstadoApelacion estadoApelacion) {
		this.estadoApelacion = estadoApelacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}
	// Fin Metodos set y get
}
