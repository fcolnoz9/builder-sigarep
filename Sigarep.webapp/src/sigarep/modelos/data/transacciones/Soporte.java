package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Documento;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the soporte database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="soporte")
public class Soporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SoportePK id;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;
	
	@Embedded
	private Documento documento;

	//bi-directional many-to-one association to RecaudoEntregado
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_instancia_apelada", referencedColumnName="id_instancia_apelada", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_recaudo", referencedColumnName="id_recaudo", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_tipo_motivo", referencedColumnName="id_tipo_motivo", nullable=false, insertable=false, updatable=false)
		})
	private RecaudoEntregado recaudoEntregado;

	public Soporte() {
	}

	public SoportePK getId() {
		return this.id;
	}

	public void setId(SoportePK id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaSubida() {
		return this.fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public RecaudoEntregado getRecaudoEntregado() {
		return this.recaudoEntregado;
	}

	public void setRecaudoEntregado(RecaudoEntregado recaudoEntregado) {
		this.recaudoEntregado = recaudoEntregado;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}