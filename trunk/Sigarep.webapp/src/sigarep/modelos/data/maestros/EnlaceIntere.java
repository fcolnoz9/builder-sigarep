package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;


/**
 * The persistent class for the enlace_interes database table.
 * 
 */
@Entity
@Table(name="enlace_interes")
public class EnlaceIntere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_enlace", unique=true, nullable=false)
	private Integer idEnlace;

	@Column(length=255)
	private String descripcion;

	@Column(name="direccion_enlace", nullable=false, length=255)
	private String direccionEnlace;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_enlace", nullable=false, length=60)
	private String nombreEnlace;
	
	@Embedded
	private Archivo imagen;

	public EnlaceIntere() {
	}

	public Integer getIdEnlace() {
		return this.idEnlace;
	}

	public void setIdEnlace(Integer idEnlace) {
		this.idEnlace = idEnlace;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccionEnlace() {
		return this.direccionEnlace;
	}

	public void setDireccionEnlace(String direccionEnlace) {
		this.direccionEnlace = direccionEnlace;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreEnlace() {
		return this.nombreEnlace;
	}

	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}

	public Archivo getImagen() {
		return imagen;
	}

	public void setImagen(Archivo imagen) {
		this.imagen = imagen;
	}

}