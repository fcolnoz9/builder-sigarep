package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the noticia database table.
 * 
 */
@Entity
@Table(name="noticia")
public class Noticia implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idNoticia;

	private String contenido;

	private String enlaceNoticia;

	private Boolean estatus;

	
	private Date fechaRegistro;

	private byte[] imagen;

	private String titulo;

	
	private Date vencimiento;

	public Noticia() {
	}
	
	
	
	public Noticia(Integer idNoticia, String contenido, String enlaceNoticia,
			Boolean estatus, Date fechaRegistro, byte[] imagen, String titulo,
			Date vencimiento) {
		super();
		this.idNoticia = idNoticia;
		this.contenido = contenido;
		this.enlaceNoticia = enlaceNoticia;
		this.estatus = estatus;
		this.fechaRegistro = fechaRegistro;
		this.imagen = imagen;
		this.titulo = titulo;
		this.vencimiento = vencimiento;
	}


	@Id
	@Column(name="id_noticia", unique=true, nullable=false)
	public Integer getIdNoticia() {
		return this.idNoticia;
	}
	
	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}
	@Column(nullable=false, length=255)
	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	@Column(name="enlace_noticia", length=255)
	public String getEnlaceNoticia() {
		return this.enlaceNoticia;
	}

	public void setEnlaceNoticia(String enlaceNoticia) {
		this.enlaceNoticia = enlaceNoticia;
	}
	@Column(nullable=false)
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	@Column(nullable=true)
	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	@Column(nullable=false, length=60)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Column(name="fecha_vencimiento")
	@Temporal(TemporalType.DATE)
	public Date getVencimiento() {
		return this.vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

}