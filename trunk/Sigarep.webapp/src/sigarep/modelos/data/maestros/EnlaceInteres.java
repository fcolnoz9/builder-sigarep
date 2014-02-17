package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;



/**
 * The persistent class for the enlace_interes database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="enlace_interes")
public class EnlaceInteres implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_enlace", unique=true, nullable=false)
	private Integer idEnlace;

	@Column(name="nombre_enlace", nullable=false, length=60)
	private String nombreEnlace;
	
	@Column(name="direccion_enlace", nullable=false, length=255)
	private String direccionEnlace;
	
	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Embedded
	private Archivo imagen = new Archivo();

	public EnlaceInteres() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnlaceInteres(Integer idEnlace, String nombreEnlace,
			String direccionEnlace, String descripcion, Boolean estatus,
			Archivo imagen) {
		super();
		this.idEnlace = idEnlace;
		this.nombreEnlace = nombreEnlace;
		this.direccionEnlace = direccionEnlace;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.imagen = imagen;
	}

	public Integer getIdEnlace() {
		return idEnlace;
	}

	public void setIdEnlace(Integer idEnlace) {
		this.idEnlace = idEnlace;
	}

	public String getNombreEnlace() {
		return nombreEnlace;
	}

	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}

	public String getDireccionEnlace() {
		return direccionEnlace;
	}

	public void setDireccionEnlace(String direccionEnlace) {
		this.direccionEnlace = direccionEnlace;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Archivo getImagen() {
		return imagen;
	}

	public void setImagen(Archivo imagen) {
		this.imagen = imagen;
	}

	
}