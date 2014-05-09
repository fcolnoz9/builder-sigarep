package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Documento;

import java.util.Date;


/**
 * Soporte
 * @author Equipo Builder
 * @version 1.0
 * @since 07/01/14
 * @last 08/05/2014
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="soporte")
public class Soporte implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name="id_soporte", unique=true, nullable=false)
	private Integer idSoporte;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;
	
	@Embedded
	private Documento documento;
	
	// constructor por defecto
	public Soporte() {
	}
	
	/**
	 * Constructor Soporte
	 * 
	 * @param estatus, fechaSubida, documento, recaudoEntregado
	 */
	public Soporte(Boolean estatus, Date fechaSubida,
			Documento documento, RecaudoEntregado recaudoEntregado) {
		super();
		this.estatus = estatus;
		this.fechaSubida = fechaSubida;
		this.documento = documento;
		this.recaudoEntregado = recaudoEntregado;
	}
	
	//bi-directional one-to-one association to RecaudoEntregado
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false),
		@JoinColumn(name="id_instancia_apelada", referencedColumnName="id_instancia_apelada", nullable=false),
		@JoinColumn(name="id_recaudo", referencedColumnName="id_recaudo", nullable=false),
		@JoinColumn(name="id_tipo_motivo", referencedColumnName="id_tipo_motivo", nullable=false)
		})
	private RecaudoEntregado recaudoEntregado;

	// Métodos Set y Get
	public Integer getIdSoporte() {
		return this.idSoporte;
	}

	public void setIdSoporte(Integer idSoporte) {
		this.idSoporte = idSoporte;
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
	//Fin Métodos Set y Get
}