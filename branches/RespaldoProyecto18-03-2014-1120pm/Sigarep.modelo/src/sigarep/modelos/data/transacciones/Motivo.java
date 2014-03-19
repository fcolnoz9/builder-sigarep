package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;
import sigarep.modelos.data.maestros.TipoMotivo;
import java.util.LinkedList;
import java.util.List;

/**
 * Instancia Motivo
 * 
 * @author Equipo: Builder-SIGAREP
 * @version 1.0
 * @since 20/12/13
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "motivo")
public class Motivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MotivoPK id;

	@Column(length = 255)
	private String descripcion;

	@Column(nullable = false)
	private Boolean estatus;

	// bi-directional many-to-one association to SolicitudApelacion
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "cedula_estudiante", referencedColumnName = "cedula_estudiante", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "codigo_lapso", referencedColumnName = "codigo_lapso", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "id_instancia_apelada", referencedColumnName = "id_instancia_apelada", nullable = false, insertable = false, updatable = false) })
	private SolicitudApelacion solicitudApelacion;

	// bi-directional many-to-one association to TipoMotivo
	@ManyToOne
	@JoinColumn(name = "id_tipo_motivo", nullable = false, insertable = false, updatable = false)
	private TipoMotivo tipoMotivo;

	// bi-directional many-to-one association to RecaudoEntregado
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "motivo")
	private List<RecaudoEntregado> recaudoEntregados = new LinkedList<RecaudoEntregado>();

	public Motivo() {
	}

	// metodos set y get
	public MotivoPK getId() {
		return this.id;
	}

	public void setId(MotivoPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return this.solicitudApelacion;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

	public TipoMotivo getTipoMotivo() {
		return this.tipoMotivo;
	}

	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public List<RecaudoEntregado> getRecaudoEntregados() {
		return this.recaudoEntregados;
	}

	public void setRecaudoEntregados(List<RecaudoEntregado> recaudoEntregados) {
		this.recaudoEntregados = recaudoEntregados;
	}

	public RecaudoEntregado addRecaudoEntregado(
			RecaudoEntregado recaudoEntregado) {
		getRecaudoEntregados().add(recaudoEntregado);
		recaudoEntregado.setMotivo(this);

		return recaudoEntregado;
	}

	public RecaudoEntregado removeRecaudoEntregado(
			RecaudoEntregado recaudoEntregado) {
		getRecaudoEntregados().remove(recaudoEntregado);
		recaudoEntregado.setMotivo(null);

		return recaudoEntregado;
	}

}