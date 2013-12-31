package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;



import sigarep.modelos.data.transacciones.UsuarioGrupo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nombreUsuario, String contrasenia, boolean estatus,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
	}
	
	
	public Usuario(String nombreUsuario, String contrasenia,String correo, boolean estatus, Date fechaCreacion){
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.correo = correo;
		this.estatus = estatus;
		this.fechaCreacion = fechaCreacion;
	}
	private static final long serialVersionUID = 1L;

	@Id
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

	/*@Column(name="nombre_completo", nullable=false, length=255)
	private String nombreCompleto;*/
	
	@Temporal(TemporalType.DATE)
	@Column(name="ultimo_acceso")
	private Date ultimoAcceso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_eliminacion")
	private Date fechaEliminacion;
	
	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="usuario", cascade={CascadeType.ALL})
	private List<UsuarioGrupo> usuariosGrupos = new LinkedList<UsuarioGrupo>();


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

	/*public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}*/

	public Date getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	public void setUltimoAcceso(Time ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.usuario")
	public List<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuariosGrupos(List<UsuarioGrupo> usuariosGrupos) {
		this.usuariosGrupos = usuariosGrupos;
	}

	public UsuarioGrupo addUsuarioGrupo(UsuarioGrupo usuariosGrupos) {
		getUsuariosGrupos().add(usuariosGrupos);
		usuariosGrupos.setUsuario(this);
		return usuariosGrupos;
	}

	public UsuarioGrupo removeUsuarioGrupo(UsuarioGrupo usuariosGrupos) {
		getUsuariosGrupos().remove(usuariosGrupos);
		usuariosGrupos.setUsuario(null);

		return usuariosGrupos;
	}

}