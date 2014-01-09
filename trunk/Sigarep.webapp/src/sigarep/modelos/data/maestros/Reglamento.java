package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.Documento;

import java.util.Date;


/**
 * The persistent class for the reglamento database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="reglamento")
public class Reglamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento", unique=true, nullable=false)
	private Integer idDocumento;
	
	@Embedded
	private Documento documento;

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;

	@Column(name="categoria", nullable=false, length=30)
	private String categoria;

	public Reglamento() {
	}
	
	public Reglamento(Integer idDocumento, Documento documento,
			String descripcion, Boolean estatus, Date fechaSubida,
			String categoria) {
		super();
		this.idDocumento = idDocumento;
		this.documento = documento;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaSubida = fechaSubida;
		this.categoria = categoria;
	}

	public Integer getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
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

	public Date getFechaSubida() {
		return this.fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}