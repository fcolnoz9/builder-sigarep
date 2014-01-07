package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Recaudo;

import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the recaudo_entregado database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="recaudo_entregado")
public class RecaudoEntregado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecaudoEntregadoPK id;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="observacion_experto", length=255)
	private String observacionExperto;

	//bi-directional many-to-one association to Motivo
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_instancia_apelada", referencedColumnName="id_instancia_apelada", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_tipo_motivo", referencedColumnName="id_tipo_motivo", nullable=false, insertable=false, updatable=false)
		})
	private Motivo motivo;

	//bi-directional many-to-one association to Recaudo
	@ManyToOne
	@JoinColumn(name="id_recaudo", nullable=false, insertable=false, updatable=false)
	private Recaudo recaudo;

	//bi-directional many-to-one association to Soporte
	@OneToMany(mappedBy="recaudoEntregado")
	private List<Soporte> soportes = new LinkedList<Soporte>();

	public RecaudoEntregado() {
	}

	public RecaudoEntregadoPK getId() {
		return this.id;
	}

	public void setId(RecaudoEntregadoPK id) {
		this.id = id;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getObservacionExperto() {
		return this.observacionExperto;
	}

	public void setObservacionExperto(String observacionExperto) {
		this.observacionExperto = observacionExperto;
	}

	public Motivo getMotivo() {
		return this.motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Recaudo getRecaudo() {
		return this.recaudo;
	}

	public void setRecaudo(Recaudo recaudo) {
		this.recaudo = recaudo;
	}

	public List<Soporte> getSoportes() {
		return this.soportes;
	}

	public void setSoportes(List<Soporte> soportes) {
		this.soportes = soportes;
	}

	public Soporte addSoporte(Soporte soporte) {
		getSoportes().add(soporte);
		soporte.setRecaudoEntregado(this);

		return soporte;
	}

	public Soporte removeSoporte(Soporte soporte) {
		getSoportes().remove(soporte);
		soporte.setRecaudoEntregado(null);

		return soporte;
	}

}