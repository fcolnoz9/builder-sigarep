
package sigarep.modelos.data.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@Column(name="nombreusuario", unique = true ,length=20, nullable=false)
	private String nombreUsuario;
	
	@Column(name = "clave")
    private String clave;
	
	@Column(name = "correo")
    private String correo;
	
	@Column(name = "nombreCompleto")
    private String nombreCompleto;
	
	@Column(name = "estado")
    private Boolean estatus;
	
	@ManyToMany(fetch=FetchType.EAGER) 
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "usuarioGrupo",
	joinColumns = { @JoinColumn(name = "nombreUsuario",referencedColumnName = "nombreUsuario") },
	inverseJoinColumns = { @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupo") })
    private List<Grupo> grupos = new LinkedList<Grupo>();

	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="usuario", cascade={CascadeType.ALL})
	private List<UsuarioGrupo> usuariosGrupos = new LinkedList<UsuarioGrupo>();
	
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
		this.grupos = new ArrayList<Grupo>();
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

	 public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
   

	public void addGrupo(Grupo grupo){
	  this.grupos.add(grupo);
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
}