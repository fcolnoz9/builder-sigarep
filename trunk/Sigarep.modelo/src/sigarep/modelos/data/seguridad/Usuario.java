package sigarep.modelos.data.seguridad;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.maestros.Persona;
import sigarep.modelos.data.transacciones.UsuarioGrupo;

/** Clase Usuario
 * Registra y Modifica el Usuario autorizado para ingresar al sistema.
 * @author Equipo Builder 
 * @version 1
 * @since 04/12/2013 
 * @last 08/05/2014
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	//@Index(name = "nombreUsuarioIndeX")
	@Column(name="nombre_usuario", unique = true ,length=35, nullable=false)
	private String nombreUsuario;

	@Column(name = "clave", length = 30, nullable = false)
	private String clave;

	@Column(name = "correo")
	private String correo;

	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion", nullable=true)
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="ultimo_acceso", nullable=true)
	private Date ultimoAcceso;

	@Column(name = "estado")
	private Boolean estatus;

	@Embedded()
	private Archivo foto;

	// Relación bidireccional de uno a muchos, asociada a la clase UsuarioGrupo
	@OneToMany(fetch = FetchType.EAGER, mappedBy="usuario", cascade={CascadeType.ALL})
	private Set<UsuarioGrupo> usuariosGrupos = new HashSet<UsuarioGrupo>();

	// Relación bidireccional de uno a uno, asociada a la clase Persona
	@OneToOne(mappedBy = "usuario")
	private Persona persona;

	// Constructor por defecto
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Usuario
	 * @param idUsuario, nombreUsuario, correo, clave, nombreCompleto, estatus
	 */
	public Usuario(Integer idUsuario,String nombreUsuario, String correo, String clave,
			String nombreCompleto, Boolean estatus) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.clave = clave;
		this.nombreCompleto = nombreCompleto;
		this.estatus = estatus;
	}

	// Métodos Set y Get
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Set<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuariosGrupos(Set<UsuarioGrupo> usuariosGrupos) {
		this.usuariosGrupos = usuariosGrupos;
	}

	public Archivo getFoto() {
		return foto;
	}

	public void setFoto(Archivo foto) {
		this.foto = foto;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	// Fin Métodos Set y Get
	/**
	 * Relación de la clase Usuario con la clase UsuarioGrupo, Agregar UsuarioGrupo
	 * 
	 * @see UsuariosGrupo
	 * @param usuariosGrupo
	 * @return usuariosGrupo
	 */
	public UsuarioGrupo addUsuarioGrupo(UsuarioGrupo usuariosGrupos) {
		getUsuariosGrupos().add(usuariosGrupos);
		usuariosGrupos.setUsuario(this);
		return usuariosGrupos;
	}
	/**
	 * Relación de la clase Grupo con la clase UsuarioGrupo, Quitar UsuarioGrupo
	 * 
	 * @see UsuariosGrupo
	 * @param usuariosGrupo
	 * @return usuariosGrupo
	 */
	public UsuarioGrupo removeUsuarioGrupo(UsuarioGrupo usuariosGrupos) {
		getUsuariosGrupos().remove(usuariosGrupos);
		usuariosGrupos.setUsuario(null);
		return usuariosGrupos;
	}
}
