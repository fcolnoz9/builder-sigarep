
package sigarep.modelos.data.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.Persona;
import sigarep.modelos.data.transacciones.UsuarioGrupo;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre_usuario", unique = true ,length=35, nullable=false)
	private String nombreUsuario;
	
	@Column(name = "clave")
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

	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(fetch = FetchType.EAGER, mappedBy="usuario", cascade={CascadeType.ALL})
	private Set<UsuarioGrupo> usuariosGrupos = new HashSet<UsuarioGrupo>();
	
	//bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy="nombreUsuario")
	private List<Estudiante> estudiantes;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="nombreUsuario")
	private List<Persona> personas;
	
	public Usuario(Integer idUsuario,String nombreUsuario, String correo, String clave,
			String nombreCompleto, Boolean estatus) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.clave = clave;
		this.nombreCompleto = nombreCompleto;
		this.estatus = estatus;
	}


	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuariosGrupos(Set<UsuarioGrupo> usuariosGrupos) {
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


	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}


	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}


	public List<Persona> getPersonas() {
		return personas;
	}


	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
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
}