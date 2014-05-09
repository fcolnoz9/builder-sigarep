package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.maestros.Recaudo;

/**
 * Recaudo Entregado registra los recaudos estregado por un estudiante en un
 * momento dado
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 07/01/14
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "recaudo_entregado")
public class RecaudoEntregado implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@EmbeddedId
	// Clave principal de la clase
	private RecaudoEntregadoPK id;

	@Column(nullable = false)
	Boolean estatus;

	@Column(name = "observacion_experto", length = 255)
	private String observacionExperto;

	// bi-directional many-to-one association to Motivo
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "cedula_estudiante", referencedColumnName = "cedula_estudiante", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "codigo_lapso", referencedColumnName = "codigo_lapso", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "id_instancia_apelada", referencedColumnName = "id_instancia_apelada", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "id_tipo_motivo", referencedColumnName = "id_tipo_motivo", nullable = false, insertable = false, updatable = false) })
	private Motivo motivo;

	// bi-directional many-to-one association to Recaudo
	@ManyToOne
	@JoinColumn(name = "id_recaudo", nullable = false, insertable = false, updatable = false)
	private Recaudo recaudo;

	// bi-directional one-to-one association to Soporte
	@OneToOne(mappedBy = "recaudoEntregado")
	private Soporte soporte;

	// constructor por defecto
	public RecaudoEntregado() {
	}
	
	/**
	 * Constructor RecaudoEntregado
	 * 
	 * @param id, estatus, observacionExperto
	 */
	public RecaudoEntregado(RecaudoEntregadoPK id, Boolean estatus,
			String observacionExperto) {
		super();
		this.id = id;
		this.estatus = estatus;
		this.observacionExperto = observacionExperto;
	}
	
	// Métodos Set y Get
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

	public Soporte getSoporte() {
		return this.soporte;
	}

	public void setSoporte(Soporte soporte) {
		this.soporte = soporte;
	}
	//Fin Métodos Set y Get
}