package sigarep.modelos.data.maestros;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "contacto_sigarep")
public class ContactoSigarep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_contacto")
	private Integer idContacto;
	
	@Column(name = "quienes_somos", length = 20000)
	private String quienesSomos;
	
	@Column(name = "correo_contacto", length = 150, nullable = false)
	private String correoContacto;
	
	@Column(name = "twitter", length = 140)
	private String twitter;
	
	@Column(name = "facebook", length = 140)
	private String facebook;
	
	@Column(name = "telefono_contacto", length = 11)
	private String telefonoContacto;
	
	@Column(name = "direccion_contacto", length = 500)
	private String direccionContacto;

	public String getQuienesSomos() {
		return quienesSomos;
	}

	public void setQuienesSomos(String quienesSomos) {
		this.quienesSomos = quienesSomos;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getDireccionContacto() {
		return direccionContacto;
	}

	public void setDireccionContacto(String direccionContacto) {
		this.direccionContacto = direccionContacto;
	}
}
