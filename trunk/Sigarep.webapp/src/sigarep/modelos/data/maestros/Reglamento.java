package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reglamento database table.
 * 
 */
@Entity
@Table(name="reglamento")
public class Reglamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento", unique=true, nullable=false)
	private Integer idDocumento;

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private byte[] documento;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(nullable=false, length=4)
	private String extension;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;

	@Column(name="nombre_documento", nullable=false, length=60)
	private String nombreDocumento;

	@Column(name="tipo_documento", nullable=false)
	private Boolean tipoDocumento;

	public Reglamento() {
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

	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Date getFechaSubida() {
		return this.fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public String getNombreDocumento() {
		return this.nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public Boolean getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(Boolean tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}