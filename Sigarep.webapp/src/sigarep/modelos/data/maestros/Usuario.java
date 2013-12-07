package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.MiembroGrupo;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nombre_usuario", unique=true, nullable=false, length=30)
	private String nombreUsuario;

	@Column(nullable=false, length=60)
	private String contrasenia;

	@Column(nullable=false, length=60)
	private String correo;

	@Column(nullable=false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion", nullable=false)
	private Date fechaCreacion;

	@Column(name="nombre_completo", nullable=false, length=255)
	private String nombreCompleto;

	@Column(name="ultimo_acceso")
	private Time ultimoAcceso;

	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="usuario", cascade={CascadeType.ALL})
	private List<MiembroGrupo> miembroGrupos;

	public Usuario() {
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Time getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	public void setUltimoAcceso(Time ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public List<MiembroGrupo> getMiembroGrupos() {
		return this.miembroGrupos;
	}

	public void setMiembroGrupos(List<MiembroGrupo> miembroGrupos) {
		this.miembroGrupos = miembroGrupos;
	}

	public MiembroGrupo addMiembroGrupo(MiembroGrupo miembroGrupo) {
		getMiembroGrupos().add(miembroGrupo);
		miembroGrupo.setUsuario(this);

		return miembroGrupo;
	}

	public MiembroGrupo removeMiembroGrupo(MiembroGrupo miembroGrupo) {
		getMiembroGrupos().remove(miembroGrupo);
		miembroGrupo.setUsuario(null);

		return miembroGrupo;
	}

}