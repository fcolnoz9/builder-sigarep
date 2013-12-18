package sigarep.modelos.data.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "enlace_interes")

public class EnlacesInteres {
	private Integer id_enlace;
	private String nombre_enlace;
	private String direccion_enlace;
	//private imagen;
	private String descripcion_enlace;
	private Boolean estatus;
	
	
	public EnlacesInteres(Integer id_enlace, String nombre_enlace,
			String direccion_enlace, String descripcion_enlace, Boolean estatus) {
		super();
		this.id_enlace = id_enlace;
		this.nombre_enlace = nombre_enlace;
		this.direccion_enlace = direccion_enlace;
		this.descripcion_enlace = descripcion_enlace;
	    this.estatus = estatus;
	}


	public EnlacesInteres() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name= "id_enlace")
	public Integer getId_enlace() {
		return id_enlace;
	}
	public void setId_enlace(Integer id_enlace) {
		this.id_enlace = id_enlace;
	}

	@Column(name="nombre_enlace")
	public String getNombre_enlace() {
		return nombre_enlace;
	}
	public void setNombre_enlace(String nombre_enlace) {
		this.nombre_enlace = nombre_enlace;
	}

	@Column(name="direccion_enlace")
	public String getDireccion_enlace() {
		return direccion_enlace;
	}
	public void setDireccion_enlace(String direccion_enlace) {
		this.direccion_enlace = direccion_enlace;
	}

	@Column(name="descripcion_enlace")
	public String getDescripcion_enlace() {
		return descripcion_enlace;
	}
	public void setDescripcion_enlace(String descripcion_enlace) {
		this.descripcion_enlace = descripcion_enlace;
	}

	@Column(name="estatus")
	public Boolean getEstatus() {
		return estatus;
	}


	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	
}
