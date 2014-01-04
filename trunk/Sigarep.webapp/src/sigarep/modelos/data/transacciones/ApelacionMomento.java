package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Momento;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the apelacion_momento database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="apelacion_momento")
@AssociationOverrides({
	@AssociationOverride(name = "id.momento", 
		joinColumns = @JoinColumn(name = "id_momento")),
	@AssociationOverride(name = "id.solicitud_apelacion", 
		joinColumns = @JoinColumn(name = "codigo_lapso")),
	@AssociationOverride(name = "id.solicitud_apelacion", 
		joinColumns = @JoinColumn(name = "cedula_estudiante")),
	@AssociationOverride(name = "id.solicitud_apelacion", 
		joinColumns = @JoinColumn(name = "id_instancia_apelada"))})
public class ApelacionMomento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApelacionMomentoPK id;

	@Column(name="fecha_momento", nullable=false)
	private Date fechaMomento;

	//bi-directional many-to-one association to Momento
	@ManyToOne
	@JoinColumn(name="id_momento", nullable=false, insertable=false, updatable=false)
	private Momento momento;

	//bi-directional many-to-one association to SolicitudApelacion
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_instancia_apelada", referencedColumnName="id_instancia_apelada", nullable=false, insertable=false, updatable=false)
		})
	private SolicitudApelacion solicitudApelacion;

	public ApelacionMomento() {
	}

	public ApelacionMomentoPK getId() {
		return this.id;
	}

	public void setId(ApelacionMomentoPK id) {
		this.id = id;
	}

	public Date getFechaMomento() {
		return this.fechaMomento;
	}

	public void setFechaMomento(Time fechaMomento) {
		this.fechaMomento = fechaMomento;
	}

	public Momento getMomento() {
		return this.momento;
	}

	public void setMomento(Momento momento) {
		this.momento = momento;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return this.solicitudApelacion;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

}