package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;

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
	
	@Embedded
	private Archivo imagen;

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;

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

	public Boolean getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(Boolean tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Archivo getImagen() {
		return imagen;
	}

	public void setImagen(Archivo imagen) {
		this.imagen = imagen;
	}

}